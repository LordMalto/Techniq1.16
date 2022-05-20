package com.entisy.techniq.common.block.transferNodes;

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
public class TransferNodeNetworkManager {
	
	private static final Collection<LazyOptional<TransferNodeNetwork>> NETWORK_LIST = Collections.synchronizedList(new ArrayList<>());

    private TransferNodeNetworkManager() {throw new IllegalAccessError("Utility class");}

    @Nullable
    public static TransferNodeNetwork get(IWorldReader world, BlockPos pos) {
        return getLazy(world, pos).orElse(null);
    }

    public static LazyOptional<TransferNodeNetwork> getLazy(IWorldReader world, BlockPos pos) {
        synchronized (NETWORK_LIST) {
            for (LazyOptional<TransferNodeNetwork> network : NETWORK_LIST) {
                if (network.isPresent()) {
                    TransferNodeNetwork net = network.orElseThrow(IllegalStateException::new);
                    if (net.contains(world, pos)) {
//                    SilentMechanisms.LOGGER.debug("get network {}", network);
                        return network;
                    }
                }
            }

            // Create new
            TransferNodeNetwork network = TransferNodeNetwork.buildNetwork(world, pos);
            LazyOptional<TransferNodeNetwork> lazy = LazyOptional.of(() -> network);
            NETWORK_LIST.add(lazy);
            return lazy;
        }
    }

    public static void invalidateNetwork(IWorldReader world, BlockPos pos) {
        Collection<LazyOptional<TransferNodeNetwork>> toRemove = NETWORK_LIST.stream()
                .filter(n -> n != null && n.isPresent() && n.orElseThrow(IllegalStateException::new).contains(world, pos))
                .collect(Collectors.toList());
        toRemove.forEach(TransferNodeNetworkManager::invalidateNetwork);
    }

    private static void invalidateNetwork(LazyOptional<TransferNodeNetwork> network) {
        synchronized (NETWORK_LIST) {
            NETWORK_LIST.removeIf(n -> n.isPresent() && n.equals(network));
            network.ifPresent(TransferNodeNetwork::invalidate);
            network.invalidate();
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        // Send energy from wire networks to connected blocks
        synchronized (NETWORK_LIST) {
            NETWORK_LIST.stream()
                    .filter(n -> n != null && n.isPresent())
                    .forEach(n -> n.ifPresent(TransferNodeNetwork::sendEnergy));
        }
    }
}
