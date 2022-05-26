package com.entisy.techniq.common.block.cable.itemCable;

import com.entisy.techniq.Techniq;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Techniq.MOD_ID)
public class ItemCableNetworkManager {
	
	private static final Collection<LazyOptional<ItemCableNetwork>> NETWORK_LIST = Collections.synchronizedList(new ArrayList<>());

    private ItemCableNetworkManager() {throw new IllegalAccessError("Utility class");}

    @Nullable
    public static ItemCableNetwork get(IWorldReader world, BlockPos pos) {
        return getLazy(world, pos).orElse(null);
    }

    public static LazyOptional<ItemCableNetwork> getLazy(IWorldReader world, BlockPos pos) {
        synchronized (NETWORK_LIST) {
            for (LazyOptional<ItemCableNetwork> network : NETWORK_LIST) {
                if (network.isPresent()) {
                    ItemCableNetwork net = network.orElseThrow(IllegalStateException::new);
                    if (net.contains(world, pos)) {
//                    SilentMechanisms.LOGGER.debug("get network {}", network);
                        return network;
                    }
                }
            }

            // Create new
            ItemCableNetwork network = ItemCableNetwork.buildNetwork(world, pos);
            LazyOptional<ItemCableNetwork> lazy = LazyOptional.of(() -> network);
            NETWORK_LIST.add(lazy);
            return lazy;
        }
    }

    public static void invalidateNetwork(IWorldReader world, BlockPos pos) {
        Collection<LazyOptional<ItemCableNetwork>> toRemove = NETWORK_LIST.stream()
                .filter(n -> n != null && n.isPresent() && n.orElseThrow(IllegalStateException::new).contains(world, pos))
                .collect(Collectors.toList());
        toRemove.forEach(ItemCableNetworkManager::invalidateNetwork);
    }

    private static void invalidateNetwork(LazyOptional<ItemCableNetwork> network) {
        synchronized (NETWORK_LIST) {
            NETWORK_LIST.removeIf(n -> n.isPresent() && n.equals(network));
            network.ifPresent(ItemCableNetwork::invalidate);
            network.invalidate();
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        // Send item from wire networks to connected blocks
        synchronized (NETWORK_LIST) {
            NETWORK_LIST.stream()
                    .filter(n -> n != null && n.isPresent())
                    .forEach(n -> n.ifPresent(ItemCableNetwork::sendItem));
        }
    }
}
