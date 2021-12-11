package ca.skynetcloud.cybercore.client.networking.wrapper;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ItemInTubeWrapper {
	public ItemStack stack;
	public LinkedList<Direction> remainingMoves;
	public int maximumDurationInTube;
	public int ticksElapsed;
	public boolean freshlyInserted = false;
	public static final String MOVES_REMAINING_TAG = "moves";
	public static final String TICKS_REMAINING_TAG = "ticksRemaining";
	public static final String TICKS_DURATION_TAG = "maxDurationInTicks";
	public static final String IS_FRESHLY_INSERTED = "isFreshly";

	public ItemInTubeWrapper(ItemStack stack, Queue<Direction> moves, int ticksToTravel) {
		this.stack = stack.copy();
		this.remainingMoves = new LinkedList<Direction>();
		for (Direction dir : moves) {
			this.remainingMoves.add(dir);
		}
		this.ticksElapsed = 0;
		this.maximumDurationInTube = ticksToTravel;
	}

	public ItemInTubeWrapper(ItemStack stack, Queue<Direction> moves, int ticksToTravel, Direction firstMove) {
		this(stack, moves, ticksToTravel);
		if (firstMove != null) {
			this.remainingMoves.addFirst(firstMove);
			this.freshlyInserted = true;
		}
	}

	public static ItemInTubeWrapper readFromNBT(CompoundTag compound) {
		ItemStack stack = ItemStack.of(compound);
		int[] moveBuffer = compound.getIntArray(MOVES_REMAINING_TAG);
		int ticksElapsed = compound.getInt(TICKS_REMAINING_TAG);
		int maxDuration = compound.getInt(TICKS_DURATION_TAG);
		boolean isFreshlyInserted = compound.getBoolean(IS_FRESHLY_INSERTED);

		ItemInTubeWrapper wrapper = new ItemInTubeWrapper(stack, decompressMoveList(moveBuffer), maxDuration);
		wrapper.ticksElapsed = ticksElapsed;
		wrapper.freshlyInserted = isFreshlyInserted;
		return wrapper;
	}

	@SuppressWarnings("static-access")
	public CompoundTag writeToNBT(CompoundTag compound) {
		compound.put(MOVES_REMAINING_TAG, compressMoveList(this.remainingMoves));
		compound.putInt(TICKS_REMAINING_TAG, this.ticksElapsed);
		compound.putInt(TICKS_DURATION_TAG, this.maximumDurationInTube);
		compound.putBoolean(IS_FRESHLY_INSERTED, this.freshlyInserted);
		this.stack.of(compound);

		return compound;
	}

	public static IntArrayTag compressMoveList(Queue<Direction> moves) {
		if (moves == null || moves.isEmpty())
			return new IntArrayTag(new int[0]);

		int moveIndex = 0;
		ArrayList<Integer> buffer = new ArrayList<Integer>();
		Direction currentMove = moves.peek();
		buffer.add(currentMove.get2DDataValue());
		buffer.add(0);

		for (Direction dir : moves) {
			if (!dir.equals(currentMove)) {
				buffer.add(dir.get2DDataValue());
				buffer.add(1);
				currentMove = dir;
				moveIndex += 2;
			} else {
				buffer.set(moveIndex + 1, buffer.get(moveIndex + 1) + 1);
			}
		}

		IntArrayTag nbt = new IntArrayTag(buffer);

		return nbt;
	}

	public static Queue<Direction> decompressMoveList(int[] buffer) {
		Queue<Direction> moves = new LinkedList<Direction>();
		int size = buffer.length;
		if (size % 2 != 0) {
			return moves;
		}

		int pairCount = size / 2;

		for (int i = 0; i < pairCount; i++) {
			Direction dir = Direction.from3DDataValue(buffer[i * 2]);
			int moveCount = buffer[i * 2 + 1];
			for (int count = 0; count < moveCount; count++) {
				moves.add(dir);
			}
		}

		return moves;
	}
}
