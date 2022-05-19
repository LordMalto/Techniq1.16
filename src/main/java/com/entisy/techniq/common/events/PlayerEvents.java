package com.entisy.techniq.common.events;

import com.entisy.techniq.Techniq;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Techniq.MOD_ID, bus = Bus.FORGE)
public class PlayerEvents {
	
//	@SubscribeEvent
//	public static void onPlayerToss(ItemTossEvent event) {
//		PlayerEntity player = event.getPlayer();
//		World world = player.getCommandSenderWorld();
//		BlockState state = world.getBlockState(player.blockPosition().below());
//		ItemStack item = event.getEntityItem().getItem();
//		for (final IRecipe<?> recipe : RecipeInit.getRecipes(RecipeInit.EXAMPLE_RECIPE, world.getRecipeManager()).values()) {
//			final ExampleRecipe exampleRecipe = (ExampleRecipe) recipe;
//			if (exampleRecipe.isValid(item, state.getBlock())) {
//				ItemHandlerHelper.giveItemToPlayer(player, recipe.getResultItem().copy());
//				event.getEntity().remove();
//			}
//		}
//	}
}
