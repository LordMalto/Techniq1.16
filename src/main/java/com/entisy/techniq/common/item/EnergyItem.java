package com.entisy.techniq.common.item;

import com.entisy.techniq.core.tab.TechniqTab;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnergyItem extends Item implements ITickable {
    private final int capacity, maxReceive;
    private int currentEnergy = 0;

    private EnergyItem(Item.Properties properties, int capacity) {
        super(properties);
        this.capacity = capacity;
        this.maxReceive = 1;
    }

    public EnergyItem(int capacity) {
        this(
                new Item.Properties()
                        .defaultDurability(capacity)
                        .setNoRepair()
                        .tab(TechniqTab.TECHNIQ_TAB),
                capacity);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack item = itemStack.copy();
        if (item.getDamageValue() > capacity) item.hurt(1, null, null);
        return item;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        return super.onEntityItemUpdate(stack, entity);
    }

    private int getDurability() {
        return capacity - getDamage(getDefaultInstance());
    }

    private void setDurability(int amount) {
        setDamage(getDefaultInstance(), capacity - currentEnergy - amount);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxReceive() {
        return maxReceive;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void extractEnergy(int amount) {
        if (currentEnergy + amount >= 0) {
            currentEnergy = currentEnergy - amount;
            setDurability(currentEnergy);
        }
    }

    public void receiveEnergy(int amount) {
        if (currentEnergy + amount <= capacity) {
            currentEnergy = currentEnergy + amount;
            setDurability(currentEnergy);
        }
    }

    @Override
    public void tick() {
        extractEnergy(1);
        System.out.println(currentEnergy);
    }
}
