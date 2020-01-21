package xyz.skynetcloud.cybercore.util.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import xyz.skynetcloud.cybercore.util.TE.cable.ItemPipeTileEntity;

public class WorldHelper {
	public static List<ItemPipeTileEntity> getTubesAdjacentTo(World world, BlockPos pos) {
		List<ItemPipeTileEntity> tes = new ArrayList<ItemPipeTileEntity>(6);
		for (Direction face : Direction.values()) {
			BlockPos checkPos = pos.offset(face);
			TileEntity te = world.getTileEntity(checkPos);
			if (te instanceof ItemPipeTileEntity) {
				tes.add((ItemPipeTileEntity) te);
			}
		}

		return tes;
	}

	public static Stream<ItemPipeTileEntity> getBlockPositionsAsTubeTileEntities(World world,
			Collection<BlockPos> posCollection) {
		Stream<TileEntity> teStream = posCollection.stream().map(tubePos -> world.getTileEntity(tubePos));
		Stream<TileEntity> filteredStream = teStream.filter(te -> te instanceof ItemPipeTileEntity);
		return filteredStream.map(te -> (ItemPipeTileEntity) te);
	}

	public static Optional<TileEntity> getTileEntityAt(World world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		return Optional.ofNullable(te);
	}

	@SuppressWarnings("unchecked")
	public static <T extends TileEntity> Optional<T> getTileEntityAt(Class<? extends T> clazz, IWorldReader world,
			BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		return Optional.ofNullable(te != null && clazz.isInstance(te) ? (T) te : null);
	}

	public static LazyOptional<IItemHandler> getTEItemHandlerAt(World world, BlockPos pos, Direction faceOfBlockPos) {
		TileEntity te = world.getTileEntity(pos);

		return te != null ? te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, faceOfBlockPos)
				: LazyOptional.empty();
	}

	public static LazyOptional<IItemHandler> getTEItemHandlerAtIf(World world, BlockPos pos, Direction faceOfBlockPos,
			Predicate<TileEntity> pred) {
		TileEntity te = world.getTileEntity(pos);

		if (te == null)
			return LazyOptional.empty();

		if (!pred.test(te))
			return LazyOptional.empty();

		return te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, faceOfBlockPos);
	}

	public static void ejectItemstack(World world, BlockPos from_pos, @Nullable Direction output_dir, ItemStack stack) {
		// if there is room in front of the shunt, eject items there
		double x, y, z, xVel, yVel, zVel, xOff, yOff, zOff;
		BlockPos output_pos;
		if (output_dir != null) {
			output_pos = from_pos.offset(output_dir);
			xOff = output_dir.getXOffset();
			yOff = output_dir.getYOffset();
			zOff = output_dir.getZOffset();
		} else {
			output_pos = from_pos;
			xOff = 0D;
			yOff = 0D;
			zOff = 0D;
		}
		if (!world.getBlockState(output_pos).isSolid()) {
			x = from_pos.getX() + 0.5D + xOff * 0.75D;
			y = from_pos.getY() + 0.25D + yOff * 0.75D;
			z = from_pos.getZ() + 0.5D + zOff * 0.75D;
			xVel = xOff * 0.1D;
			yVel = yOff * 0.1D;
			zVel = zOff * 0.1D;
		} else // otherwise just eject items inside the shunt
		{
			x = from_pos.getX() + 0.5D;
			y = from_pos.getY() + 0.5D;
			z = from_pos.getZ() + 0.5D;
			xVel = 0D;
			yVel = 0D;
			zVel = 0D;
		}
		ItemEntity itementity = new ItemEntity(world, x, y, z, stack);
		itementity.setDefaultPickupDelay();
		itementity.setMotion(xVel, yVel, zVel);
		world.addEntity(itementity);
	}

	// inserts as much of the item as we can into a given handler
	// we don't copy the itemstack because we assume we are already given a copy of
	// the original stack
	// return the portion that was not inserted
	public static ItemStack disperseItemToHandler(ItemStack stack, IItemHandler handler) {
		int slotCount = handler.getSlots();
		for (int i = 0; i < slotCount; i++) {
			if (handler.isItemValid(i, stack)) {
				stack = handler.insertItem(i, stack, false);
			}
			if (stack.getCount() == 0) {
				return stack.copy();
			}
		}
		return stack.copy();
	}

	public static boolean doesItemHandlerHaveAnyExtractableItems(IItemHandler handler,
			Predicate<ItemStack> doesCallerWantItem) {
		return IntStream.range(0, handler.getSlots()).mapToObj(i -> handler.extractItem(i, 1, true))
				.anyMatch(stack -> stack.getCount() > 0 && doesCallerWantItem.test(stack));
	}
}
