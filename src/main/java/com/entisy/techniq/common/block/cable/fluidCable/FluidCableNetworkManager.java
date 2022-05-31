package com.entisy.techniq.common.block.cable.fluidCable;

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
public class FluidCableNetworkManager {

    private static final Collection<LazyOptional<FluidCableNetwork>> NETWORK_LIST = Collections.synchronizedList(new ArrayList<>());

    private FluidCableNetworkManager() {
        throw new IllegalAccessError("Utility class");
    }

    @Nullable
    public static FluidCableNetwork get(IWorldReader world, BlockPos pos) {
        return getLazy(world, pos).orElse(null);
    }

    public static LazyOptional<FluidCableNetwork> getLazy(IWorldReader world, BlockPos pos) {
        synchronized (NETWORK_LIST) {
            for (LazyOptional<FluidCableNetwork> network : NETWORK_LIST) {
                if (network.isPresent()) {
                    FluidCableNetwork net = network.orElseThrow(IllegalStateException::new);
                    if (net.contains(world, pos)) {
//                    SilentMechanisms.LOGGER.debug("get network {}", network);
                        return network;
                    }
                }
            }

            // Create new
            FluidCableNetwork network = FluidCableNetwork.buildNetwork(world, pos);
            LazyOptional<FluidCableNetwork> lazy = LazyOptional.of(() -> network);
            NETWORK_LIST.add(lazy);
            return lazy;
        }
    }

    public static void invalidateNetwork(IWorldReader world, BlockPos pos) {
        Collection<LazyOptional<FluidCableNetwork>> toRemove = NETWORK_LIST.stream()
                .filter(n -> n != null && n.isPresent() && n.orElseThrow(IllegalStateException::new).contains(world, pos))
                .collect(Collectors.toList());
        toRemove.forEach(FluidCableNetworkManager::invalidateNetwork);
    }

    private static void invalidateNetwork(LazyOptional<FluidCableNetwork> network) {
        synchronized (NETWORK_LIST) {
            NETWORK_LIST.removeIf(n -> n.isPresent() && n.equals(network));
            network.ifPresent(FluidCableNetwork::invalidate);
            network.invalidate();
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        // Send fluid from wire networks to connected blocks
        synchronized (NETWORK_LIST) {
            NETWORK_LIST.stream()
                    .filter(n -> n != null && n.isPresent())
                    .forEach(n -> n.ifPresent(FluidCableNetwork::sendFluid));
        }
    }
}
