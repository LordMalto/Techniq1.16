package com.entisy.techniq.common.tileentity;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.AlloySmelterBlock;
import com.entisy.techniq.common.container.MetalPressContainer;
import com.entisy.techniq.common.itemHandlers.MetalPressItemHandler;
import com.entisy.techniq.common.recipe.metalPress.MetalPressRecipe;
import com.entisy.techniq.core.energy.EnergyStorageImpl;
import com.entisy.techniq.core.init.RecipeSerializerInit;
import com.entisy.techniq.core.init.TileEntityTypesInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MetalPressTileEntity extends MachineTileEntity implements ITickableTileEntity, INamedContainerProvider {

	public static final int slots = 2;
	private ITextComponent name;
	public int currentSmeltTime;
	public final int maxSmeltTime = 120;
	private MetalPressItemHandler inventory;

//	public static AtomicInteger currentEnergy = new AtomicInteger();
	public int currentEnergy;
	public static final int maxEnergy = 25000;
	public static final int maxEnergyReceive = 200;
	public static final int maxEnergyExtract = 0;

	private final EnergyStorageImpl energyStorage;

	private final LazyOptional<IEnergyStorage> energy;

	public MetalPressTileEntity(TileEntityType<?> type) {
		super(type);
		inventory = new MetalPressItemHandler(slots);
		energyStorage = createEnergy(maxEnergy);
		energy = LazyOptional.of(() -> energyStorage);
	}

	public MetalPressTileEntity() {
		this(TileEntityTypesInit.METAL_PRESS_TILE_ENTITY.get());
	}

	@Override
	public Container createMenu(final int windowId, final PlayerInventory inv, final PlayerEntity player) {
		return new MetalPressContainer(windowId, inv, this);
	}

	@Override
	public void tick() {
		boolean dirty = false;
		if (level != null && !level.isClientSide()) {
			MetalPressRecipe recipe = getRecipe(inventory.getItem(0));

			if (recipe != null) {
				if (currentEnergy >= recipe.getRequiredEnergy()) {
					if (currentSmeltTime != maxSmeltTime) {
						if ((inventory.getStackInSlot(2).sameItem(recipe.getResultItem()) || inventory.getStackInSlot(2).getItem() == Items.AIR) && inventory.getStackInSlot(2).getCount() < 64) {
							level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, true));
							currentSmeltTime++;
							dirty = true;
						}
					} else {
						energy.ifPresent(iEnergyStorage -> {
							((EnergyStorageImpl)iEnergyStorage).setEnergyDirectly(iEnergyStorage.getEnergyStored() - recipe.getRequiredEnergy());
							currentEnergy = iEnergyStorage.getEnergyStored();
						});
						level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, false));
						currentSmeltTime = 0;
						ItemStack output = recipe.getResultItem();
						inventory.insertItem(1, output.copy(), false);

						inventory.shrink(0, recipe.getCount(inventory.getItem(0)));
						dirty = true;
					}
				}
			} else {
				level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, false));
				currentSmeltTime = 0;
				dirty = true;
			}
		}
		if (dirty) {
			setChanged();
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
		}
	}

	public void setCustomName(ITextComponent name) {
		this.name = name;
	}

	public ITextComponent getName() {
		return name != null ? name : getDefaultName();
	}

	public ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Techniq.MOD_ID + ".metal_press");
	}

	@Override
	public ITextComponent getDisplayName() {
		return getName();
	}

	@Nullable
	public ITextComponent getCustomName() {
		return name;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		if (nbt.contains("CustomName", Constants.NBT.TAG_STRING)) {
			name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, inv);
		inventory.setNonNullList(inv);
		currentSmeltTime = nbt.getInt("CurrentSmeltTime");
		currentEnergy = nbt.getInt("CurrentEnergy");
		energyStorage.setEnergyDirectly(currentEnergy);
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		if (name != null) {
			nbt.putString("CustomName", ITextComponent.Serializer.toJson(name));
		}
		ItemStackHelper.saveAllItems(nbt, inventory.toNonNullList());
		nbt.putInt("CurrentSmeltTime", currentSmeltTime);
		energy.ifPresent(iEnergyStorage -> {
			nbt.putInt("CurrentEnergy", iEnergyStorage.getEnergyStored());
		});
		return nbt;
	}

	@Nullable
	private MetalPressRecipe getRecipe(ItemStack stack) {
		if (stack == null) {
			return null;
		}

		Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.METAL_PRESS_TYPE, level);
		for (IRecipe<?> iRecipe : recipes) {
			MetalPressRecipe recipe = (MetalPressRecipe) iRecipe;
			if (recipe.matches(new RecipeWrapper(inventory), level)) {
				return recipe;
			}
		}

		return null;
	}

	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
		ClientWorld world = Minecraft.getInstance().level;
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
		Set<ItemStack> inputs = new HashSet<ItemStack>();
		Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
		for (IRecipe<?> recipe : recipes) {
			NonNullList<Ingredient> ingredients = recipe.getIngredients();
			ingredients.forEach(ingredient -> {
				for (ItemStack stack : ingredient.getItems()) {
					inputs.add(stack);
				}
			});
		}
		return inputs;
	}
	
	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbt = new CompoundNBT();
		save(nbt);
		return new SUpdateTileEntityPacket(getBlockPos(), 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, SUpdateTileEntityPacket packet) {
		deserializeNBT(packet.getTag());
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = new CompoundNBT();
		save(nbt);
		return nbt;
	}
	
	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
		load(state, nbt);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (capability == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(capability, side);
	}
	
	public IItemHandler getInventory() {
		return inventory;
	}
	private EnergyStorageImpl createEnergy(int capacity){
		return new EnergyStorageImpl(capacity,maxEnergyReceive,maxEnergyExtract,this);
	}
	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
	}
}
