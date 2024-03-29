package ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.routing;

import ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.wrapper.WorldHelperWrapper;
import ca.skynetcloud.cybercore.client.world.level.block.techentity.ItemCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.*;

public class RoutingNetwork {

    public final Set<BlockPos> itemcables = new HashSet<BlockPos>(); // set of the tubes that make up the network interior
    public final Set<Endpoint> endpoints = new HashSet<Endpoint>();
    private final HashMap<BlockPos, List<Route>> bestRoutes = new HashMap<BlockPos, List<Route>>();
    public boolean invalid = false;

    public static final RoutingNetwork INVALID_NETWORK = new RoutingNetwork();
    static {
        INVALID_NETWORK.invalid = true;
    }

    private int ticksPerTube = 10; // true value is set when the network is built

    private RoutingNetwork() {

    }

    public int getTicksPerTube() {
        return this.ticksPerTube;
    }

    private void setTicksPerTube() {
        int baseDuration = 5;
        int size = 1;
        int softCap = 2;

        if (size < softCap) {
            this.ticksPerTube = baseDuration;
            return;
        }

        float slope = 1F / (softCap);
        float offset = (-softCap) * slope;
        float dilation = size * slope + offset;
        float time = 1F / (dilation * dilation);
        this.ticksPerTube = (int) (time * baseDuration);
    }

    /**
     * For tubes, only pos of tube is relevant for endpoints, face is the side of
     * the endpoint block that faces the network
     *
     * @param pos
     * @param face
     * @return
     */
    public boolean contains(BlockPos pos, Direction face) {
        if (this.itemcables.contains(pos)) {
            return true;
        } else {
            for (Endpoint endpoint : this.endpoints) {
                if (endpoint.pos.equals(pos) && endpoint.face.equals(face)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Returns true if a blockpos can potentially be part of this network Must have
     * a TileEntity associated with that position that is either a tube or has the
     * inventory capability on the given side
     *
     * @param pos   to check
     * @param world to use
     * @param face  of the block being checked that items would be inserted into
     * @return
     */
    public boolean isValidToBeInNetwork(BlockPos pos, Level world, Direction face) {
        if (this.invalid)
            return false;

        BlockEntity te = world.getBlockEntity(pos);
        return (te != null && (te instanceof ItemCableBlockEntity
                || te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face).isPresent()));

    }

    public int getSize() {
        return this.itemcables.size() + this.endpoints.size();
    }

    // gets the route to the nearest endpoint based on the position of the initial
    // tube
    // and the side of that tube that the item was inserted into
    // returns NULL if there are no valid routes
    @Nullable
    public Route getBestRoute(Level world, BlockPos startPos, Direction insertionSide, ItemStack stack) {
        if (stack.getCount() <= 0)
            return null; // can't fit round pegs in square holes

        // lazily generate the routes if they don't exist yet
        List<Route> routes;
        if (this.bestRoutes.containsKey(startPos)) {
            routes = this.bestRoutes.get(startPos);
        } else {
            routes = this.generateRoutes(world, startPos);
            this.bestRoutes.put(startPos, routes);
        }

        // this list is sorted by travel time to an endpoint from this position
        for (Route route : routes) {
            // ignore the block the item was inserted from, all else are valid
            if (route.isRouteDestinationValid(world, startPos, insertionSide, stack)) {
                return route;
            }
        }

        return null; // no valid routes
    }

    private List<Route> generateRoutes(Level world, BlockPos startPos) {
        return FastestRoutesSolver.generateRoutes(this, world, startPos);
    }

    public static RoutingNetwork buildNetworkFrom(BlockPos pos, Level level) {
        HashSet<BlockPos> visited = new HashSet<BlockPos>();
        HashSet<BlockPos> potentialEndpoints = new HashSet<BlockPos>();
        RoutingNetwork network = new RoutingNetwork();
        // recursivelyBuildNetworkFrom(pos, world, network, visited,
        // potentialEndpoints);
        iterativelyBuildNetworkFrom(pos, level, network, visited, potentialEndpoints);
        // we now have a set of tubes and a set of potential endpoints
        // narrow down the endpoint TEs to useable ones
        for (BlockPos endPos : potentialEndpoints) {
            // Endpoint point = Endpoint.createEndpoint(endPos, world, network.tubes);
            BlockEntity te = level.getBlockEntity(endPos);
            if (te == null)
                continue; // just in case

            for (Direction face : Direction.values()) {
                // if the te has an item handler on this face, add an endpoint (representing
                // that face) to the network
                if (network.itemcables.contains(endPos.relative(face))) {
                    LazyOptional<IItemHandler> possibleHandler = te
                            .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face);
                    possibleHandler.ifPresent(handler -> network.endpoints.add(new Endpoint(endPos, face)));
                }
            }
        }

        network.confirmAllTubes(level);// make sure all tubes in this network are using this network
        network.setTicksPerTube();
        return network;
    }

    // very large networks throw stackoverflow if recursion is used
    private static void iterativelyBuildNetworkFrom(BlockPos startPos, Level world, RoutingNetwork network,
                                                    HashSet<BlockPos> visited, HashSet<BlockPos> potentialEndpoints) {
        LinkedList<BlockPos> blocksToVisit = new LinkedList<BlockPos>();
        blocksToVisit.add(startPos);
        while (!blocksToVisit.isEmpty()) {

            BlockPos visitedPos = blocksToVisit.poll();
            visited.add(visitedPos);
            BlockEntity te = world.getBlockEntity(visitedPos);
            if (te instanceof ItemCableBlockEntity) {
                network.itemcables.add(visitedPos);
                List<Direction> dirs = ((ItemCableBlockEntity) te).getConnectedDirections();
                for (Direction face : dirs) {
                    BlockPos checkPos = visitedPos.relative(face);
                    if (!visited.contains(checkPos)) {
                        blocksToVisit.add(checkPos);
                    }
                }
            } else if (te != null) // te exists but isn't tube
            {
                // keep track of it for now and reconsider it later
                potentialEndpoints.add(visitedPos);
                // but don't look at it again for now
                // endpoints don't need to be visited more than once, we evaluate the sides
                // later
            }
        }
    }

    // sets the network of every tube in this network to this network
    public void confirmAllTubes(Level world) {
        WorldHelperWrapper.getBlockPositionsAsTubeTileEntities(world, this.itemcables).forEach(tube -> tube.setNetwork(this));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof RoutingNetwork) {
            RoutingNetwork otherNetwork = (RoutingNetwork) other;
            return this.endpoints.equals(otherNetwork.endpoints) && this.itemcables.equals(otherNetwork.itemcables);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.endpoints.hashCode() ^ this.itemcables.hashCode();
    }

    @Override
    public String toString() {
        String endpointText = this.endpoints.stream().map(endpoint -> endpoint.toString()).reduce("Endpoints:\n",
                (head, tail) -> head + tail + "\n");
        String tubeText = this.itemcables.stream().map(tube -> tube.toString()).reduce("Tubes:\n",
                (head, tail) -> head + tail + "\n");
        return endpointText + "\n" + tubeText;
    }
}
