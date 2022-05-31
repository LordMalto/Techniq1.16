package com.entisy.techniq.common.block.cable.fluidCable;

import com.entisy.techniq.api.ConnectionType;
import com.entisy.techniq.core.capabilities.fluid.CapabilityFluid;
import com.entisy.techniq.core.capabilities.fluid.IFluidStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.LazyOptional;

import java.util.*;

public class FluidCableNetwork implements IFluidStorage {

    public static final int TRANSFER_PER_CONNECTION = 1000;

    private final IWorldReader world;
    private final Map<BlockPos, Set<Connection>> connections = new HashMap<>();
    private boolean connectionsBuilt;
    private int fluidStored;

    private FluidCableNetwork(IWorldReader world, Set<BlockPos> wires, int fluidStored) {
        this.world = world;
        wires.forEach(pos -> connections.put(pos, Collections.emptySet()));
        this.fluidStored = fluidStored;
    }

    static FluidCableNetwork buildNetwork(IWorldReader world, BlockPos pos) {
        Set<BlockPos> wires = buildWireSet(world, pos);
        int fluidStored = wires.stream().mapToInt(p -> {
            TileEntity tileEntity = world.getBlockEntity(p);
            return tileEntity instanceof FluidCableTileEntity ? ((FluidCableTileEntity) tileEntity).fluidStored : 0;
        }).sum();
        return new FluidCableNetwork(world, wires, fluidStored);
    }

    private static Set<BlockPos> buildWireSet(IWorldReader world, BlockPos pos) {
        return buildWireSet(world, pos, new HashSet<>());
    }

    private static Set<BlockPos> buildWireSet(IWorldReader world, BlockPos pos, Set<BlockPos> set) {
        // Get all positions that have a wire connected to the wire at pos
        set.add(pos);
        for (Direction side : Direction.values()) {
            BlockPos pos1 = pos.relative(side);
            if (!set.contains(pos1) && world.getBlockEntity(pos1) instanceof FluidCableTileEntity) {
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

    private void updateWireFluid() {
        int fluidPerWire = fluidStored / getWireCount();
        connections.keySet().forEach(p -> {
            TileEntity tileEntity = world.getBlockEntity(p);
            if (tileEntity instanceof FluidCableTileEntity) {
                ((FluidCableTileEntity) tileEntity).fluidStored = fluidPerWire;
            }
        });
    }

    void invalidate() {
        connections.values().forEach(set -> set.forEach(con -> con.getLazyOptional().invalidate()));
    }

    @Override
    public int receiveFluid(int maxReceive, boolean simulate) {
        buildConnections();
        int received = Math.min(getMaxFluidStored() - fluidStored, Math.min(maxReceive, TRANSFER_PER_CONNECTION));
        if (received > 0) {
//            SilentMechanisms.LOGGER.debug("receive ({}): {}, {} -> {}", simulate, received, fluidStored, fluidStored + received);
            if (!simulate) {
                fluidStored += received;
                updateWireFluid();
            }
        }
        return received;
    }

    @Override
    public int extractFluid(int maxExtract, boolean simulate) {
        buildConnections();
        int extracted = Math.min(fluidStored, Math.min(maxExtract, TRANSFER_PER_CONNECTION));
        if (extracted > 0) {
//            SilentMechanisms.LOGGER.debug("extract ({}): {}, {} -> {}", simulate, extracted, fluidStored, fluidStored - extracted);
            if (!simulate) {
                fluidStored -= extracted;
                updateWireFluid();
            }
        }
        return extracted;
    }

    void sendFluid() {
        buildConnections();

        // Send stored fluid to connected blocks
//        for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
//            BlockPos pos = entry.getKey();
//            Set<Connection> connections = entry.getValue();
//            for (Connection con : connections) {
//                if (con.type.canExtract()) {
//                    IFluidStorage fluid = FluidUtils.getFluid(world, pos.relative(con.side));
//                    if (fluid != null && fluid.canReceive()) {
//                        int toSend = extractFluid(TRANSFER_PER_CONNECTION, true);
//                        int accepted = fluid.receiveFluid(toSend, false);
//                        extractFluid(accepted, false);
//                  }
//                }
//            }
//        }
    }

    @Override
    public int getFluidStored() {
        return fluidStored;
    }

    @Override
    public int getMaxFluidStored() {
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
            if (te != null && !(te instanceof FluidCableTileEntity) && te.getCapability(CapabilityFluid.FLUID).isPresent()) {
                ConnectionType type = FluidCableBlock.getConnection(world.getBlockState(pos), direction);
                connections.add(new Connection(this, direction, type));
            }
        }
        return connections;
    }

    @Override
    public String toString() {
        return String.format("WireNetwork %s, %d wires, %,d FE", Integer.toHexString(hashCode()), connections.size(), fluidStored);
    }

    public static class Connection implements IFluidStorage {
        private final FluidCableNetwork network;
        private final Direction side;
        private final ConnectionType type;
        private final LazyOptional<Connection> lazyOptional;

        Connection(FluidCableNetwork network, Direction side, ConnectionType type) {
            this.network = network;
            this.side = side;
            this.type = type;
            this.lazyOptional = LazyOptional.of(() -> this);
        }

        public LazyOptional<Connection> getLazyOptional() {
            return lazyOptional;
        }

        @Override
        public int receiveFluid(int maxReceive, boolean simulate) {
            if (!canReceive()) {
                return 0;
            }
            return network.receiveFluid(maxReceive, simulate);
        }

        @Override
        public int extractFluid(int maxExtract, boolean simulate) {
            if (!canExtract()) {
                return 0;
            }
            return network.extractFluid(maxExtract, simulate);
        }

        @Override
        public int getFluidStored() {
            return network.fluidStored;
        }

        @Override
        public int getMaxFluidStored() {
            return network.getMaxFluidStored();
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
