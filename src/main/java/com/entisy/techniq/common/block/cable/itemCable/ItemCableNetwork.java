package com.entisy.techniq.common.block.cable.itemCable;

import com.entisy.techniq.api.ConnectionType;
import com.entisy.techniq.core.capabilities.item.CapabilityItem;
import com.entisy.techniq.core.capabilities.item.IItemStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.LazyOptional;

import java.util.*;

public class ItemCableNetwork implements IItemStorage {

    public static final int TRANSFER_PER_CONNECTION = 1000;

    private final IWorldReader world;
    private final Map<BlockPos, Set<Connection>> connections = new HashMap<>();
    private boolean connectionsBuilt;
    private int itemStored;

    private ItemCableNetwork(IWorldReader world, Set<BlockPos> wires, int itemStored) {
        this.world = world;
        wires.forEach(pos -> connections.put(pos, Collections.emptySet()));
        this.itemStored = itemStored;
    }

    static ItemCableNetwork buildNetwork(IWorldReader world, BlockPos pos) {
        Set<BlockPos> wires = buildWireSet(world, pos);
        int itemStored = wires.stream().mapToInt(p -> {
            TileEntity tileEntity = world.getBlockEntity(p);
            return tileEntity instanceof ItemCableTileEntity ? ((ItemCableTileEntity) tileEntity).itemStored : 0;
        }).sum();
        return new ItemCableNetwork(world, wires, itemStored);
    }

    private static Set<BlockPos> buildWireSet(IWorldReader world, BlockPos pos) {
        return buildWireSet(world, pos, new HashSet<>());
    }

    private static Set<BlockPos> buildWireSet(IWorldReader world, BlockPos pos, Set<BlockPos> set) {
        // Get all positions that have a wire connected to the wire at pos
        set.add(pos);
        for (Direction side : Direction.values()) {
            BlockPos pos1 = pos.relative(side);
            if (!set.contains(pos1) && world.getBlockEntity(pos1) instanceof ItemCableTileEntity) {
                set.add(pos1);
                set.addAll(buildWireSet(world, pos1, set));
            }
        }
        return set;
    }

    public boolean contains(IWorldReader world, BlockPos pos) {
        return this.world == world && connections.containsKey(pos);
    }

    public int getWireCount() {
        return connections.size();
    }

    public Connection getConnection(BlockPos pos, Direction side) {
        if (connections.containsKey(pos)) {
            for (Connection connection : connections.get(pos)) {
                if (connection.side == side) {
                    return connection;
                }
            }
        }
        return new Connection(this, side, ConnectionType.NONE);
    }

    private void updateWireItem() {
        int itemPerWire = itemStored / getWireCount();
        connections.keySet().forEach(p -> {
            TileEntity tileEntity = world.getBlockEntity(p);
            if (tileEntity instanceof ItemCableTileEntity) {
                ((ItemCableTileEntity) tileEntity).itemStored = itemPerWire;
            }
        });
    }

    void invalidate() {
        connections.values().forEach(set -> set.forEach(con -> con.getLazyOptional().invalidate()));
    }

    @Override
    public int receiveItem(int maxReceive, boolean simulate) {
        buildConnections();
        int received = Math.min(getMaxItemStored() - itemStored, Math.min(maxReceive, TRANSFER_PER_CONNECTION));
        if (received > 0) {
//            SilentMechanisms.LOGGER.debug("receive ({}): {}, {} -> {}", simulate, received, itemStored, itemStored + received);
            if (!simulate) {
                itemStored += received;
                updateWireItem();
            }
        }
        return received;
    }

    @Override
    public int extractItem(int maxExtract, boolean simulate) {
        buildConnections();
        int extracted = Math.min(itemStored, Math.min(maxExtract, TRANSFER_PER_CONNECTION));
        if (extracted > 0) {
//            SilentMechanisms.LOGGER.debug("extract ({}): {}, {} -> {}", simulate, extracted, itemStored, itemStored - extracted);
            if (!simulate) {
                itemStored -= extracted;
                updateWireItem();
            }
        }
        return extracted;
    }

    void sendItem() {
        buildConnections();

        // Send stored item to connected blocks
//        for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
//            BlockPos pos = entry.getKey();
//            Set<Connection> connections = entry.getValue();
//            for (Connection con : connections) {
//                if (con.type.canExtract()) {
//                    IItemStorage item = ItemUtils.getItem(world, pos.relative(con.side));
//                    if (item != null && item.canReceive()) {
//                        int toSend = extractItem(TRANSFER_PER_CONNECTION, true);
//                        int accepted = item.receiveItem(toSend, false);
//                        extractItem(accepted, false);
//                  }
//                }
//            }
//        }
    }

    @Override
    public int getItemStored() {
        return itemStored;
    }

    @Override
    public int getMaxItemStored() {
        return 1000 * connections.size();
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    private void buildConnections() {
        // Determine all connections. This will be done once the connections are actually needed.
        if (!connectionsBuilt) {
            connections.keySet().forEach(p -> connections.put(p, getConnections(world, p)));
            connectionsBuilt = true;
        }
    }

    private Set<Connection> getConnections(IBlockReader world, BlockPos pos) {
        // Get all connections for the wire at pos
        Set<Connection> connections = new HashSet<>();
        for (Direction direction : Direction.values()) {
            TileEntity te = world.getBlockEntity(pos.relative(direction));
            if (te != null && !(te instanceof ItemCableTileEntity) && te.getCapability(CapabilityItem.ITEM).isPresent()) {
                ConnectionType type = ItemCableBlock.getConnection(world.getBlockState(pos), direction);
                connections.add(new Connection(this, direction, type));
            }
        }
        return connections;
    }

    @Override
    public String toString() {
        return String.format("WireNetwork %s, %d wires, %,d FE", Integer.toHexString(hashCode()), connections.size(), itemStored);
    }

    public static class Connection implements IItemStorage {
        private final ItemCableNetwork network;
        private final Direction side;
        private final ConnectionType type;
        private final LazyOptional<Connection> lazyOptional;

        Connection(ItemCableNetwork network, Direction side, ConnectionType type) {
            this.network = network;
            this.side = side;
            this.type = type;
            this.lazyOptional = LazyOptional.of(() -> this);
        }

        public LazyOptional<Connection> getLazyOptional() {
            return lazyOptional;
        }

        @Override
        public int receiveItem(int maxReceive, boolean simulate) {
            if (!canReceive()) {
                return 0;
            }
            return network.receiveItem(maxReceive, simulate);
        }

        @Override
        public int extractItem(int maxExtract, boolean simulate) {
            if (!canExtract()) {
                return 0;
            }
            return network.extractItem(maxExtract, simulate);
        }

        @Override
        public int getItemStored() {
            return network.itemStored;
        }

        @Override
        public int getMaxItemStored() {
            return network.getMaxItemStored();
        }

        @Override
        public boolean canExtract() {
            return type.canExtract();
        }

        @Override
        public boolean canReceive() {
            return type.canReceive();
        }
    }
}
