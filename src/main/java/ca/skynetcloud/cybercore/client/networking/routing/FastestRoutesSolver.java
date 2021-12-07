package ca.skynetcloud.cybercore.client.networking.routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import ca.skynetcloud.cybercore.common.block.blocks.cables.ItemCable;
import ca.skynetcloud.cybercore.client.networking.helper.PosHelper;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FastestRoutesSolver {

	public static List<Route> generateRoutes(RoutingNetwork network, Level world, BlockPos startPos) {

		Object2IntOpenHashMap<BlockPos> tubeDists = new Object2IntOpenHashMap<BlockPos>();
		tubeDists.put(startPos, 0);
		Object2IntOpenHashMap<Endpoint> endpointDists = new Object2IntOpenHashMap<Endpoint>();

		HashSet<BlockPos> visitedTubes = new HashSet<BlockPos>();
		HashSet<Endpoint> visitedEndpoints = new HashSet<Endpoint>();

		HashMap<BlockPos, BlockPos> tubePrevs = new HashMap<BlockPos, BlockPos>();
		HashMap<Endpoint, BlockPos> endpointPrevs = new HashMap<Endpoint, BlockPos>();

		PriorityQueue<PosAndDist> distQueue = new PriorityQueue<PosAndDist>(network.getSize());
		distQueue.add(new PosAndDist(startPos, 0));

		while (!distQueue.isEmpty()) {

			PosAndDist node = distQueue.poll();
			visitedTubes.add(node.pos);

			BlockState state = world.getBlockState(node.pos);

			List<Direction> dirs = ItemCable.getConnectedDirections(state);
			for (Direction face : dirs) {
				BlockPos checkPos = node.pos.relative(face);
				Endpoint maybeEndpoint = new Endpoint(checkPos, face.getOpposite());

				if (!visitedTubes.contains(checkPos) && network.tubes.contains(checkPos)) {
					int newDist = node.dist + 1;

					if (!tubeDists.containsKey(checkPos) || newDist < tubeDists.getInt(checkPos)) {
						tubeDists.put(checkPos, newDist);
						tubePrevs.put(checkPos, node.pos);
					}

					distQueue.add(new PosAndDist(checkPos, tubeDists.getInt(checkPos)));
				} else if (!visitedEndpoints.contains(maybeEndpoint) && network.endpoints.contains(maybeEndpoint)) {
					visitedEndpoints.add(maybeEndpoint);
					int newDist = node.dist + 1;
					if (!endpointDists.containsKey(maybeEndpoint) || newDist < endpointDists.getInt(maybeEndpoint)) {
						endpointDists.put(maybeEndpoint, newDist);
						endpointPrevs.put(maybeEndpoint, node.pos);
					}
					// don't add endpoint to distqueue since endpoints are singlesided
				}
			}

		}

		List<Route> routes = new ArrayList<Route>(network.endpoints.size());

		for (Endpoint endpoint : network.endpoints) {
			LinkedList<Direction> sequenceOfMoves = getSequenceOfMoves(endpoint, startPos, new LinkedList<Direction>(),
					tubePrevs, endpointPrevs);
			if (sequenceOfMoves != null) {
				routes.add(new Route(endpoint, sequenceOfMoves.size(), sequenceOfMoves));
			}
		}

		routes.sort(null);

		return routes;
	}

	private static LinkedList<Direction> getSequenceOfMoves(Endpoint endpoint, BlockPos startPos,
			LinkedList<Direction> returnList, HashMap<BlockPos, BlockPos> tubePrevs,
			HashMap<Endpoint, BlockPos> endpointPrevs) {
		if (!endpointPrevs.containsKey(endpoint)) {
			return null;
		}
		BlockPos prevPos = endpointPrevs.get(endpoint);
		returnList.addFirst(endpoint.face.getOpposite());

		if (prevPos.equals(startPos)) {
			return returnList;
		} else {
			return getSequenceOfMoves(prevPos, startPos, returnList, tubePrevs);
		}
	}

	// recursively assemble the sequence of moves required to get to a given
	// position from the startPos
	private static LinkedList<Direction> getSequenceOfMoves(BlockPos pos, BlockPos startPos,
			LinkedList<Direction> returnList, HashMap<BlockPos, BlockPos> prevs) {
		if (!prevs.containsKey(pos)) {
			return null; // if the endpoint can't be reached from the start point, then we don't create a
							// route to it
		}

		BlockPos prevPos = prevs.get(pos);
		returnList.addFirst(PosHelper.getTravelDirectionFromTo(prevPos, pos));

		// TODO at the moment we're going to blindly trust that the route solver didn't
		// create any loops, may need to add safeguard later
		// to be honest if we do run into a loop, that's a problem to be fixed in the
		// route solver, not here
		if (prevPos.equals(startPos)) {
			return returnList;
		} else {
			return getSequenceOfMoves(prevPos, startPos, returnList, prevs);
		}
	}
}
