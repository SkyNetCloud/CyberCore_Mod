package ca.skynetcloud.cybercore.util.networking.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.util.TE.techblock.ItemPipeTileEntity;
import ca.skynetcloud.cybercore.util.networking.util.ClientProxy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class WorldHelper {
	public static List<ItemPipeTileEntity> getTubesAdjacentTo(Level world, BlockPos pos) {
		List<ItemPipeTileEntity> tes = new ArrayList<ItemPipeTileEntity>(6);
		for (Direction face : Direction.values()) {
			BlockPos checkPos = pos.relative(face);
			BlockEntity te = world.getBlockEntity(checkPos);
			if (te instanceof ItemPipeTileEntity) {
				tes.add((ItemPipeTileEntity) te);
			}
		}

		return tes;
	}

	public static Stream<ItemPipeTileEntity> getBlockPositionsAsTubeTileEntities(Level world,
			Collection<BlockPos> posCollection) {
		Stream<BlockEntity> teStream = posCollection.stream().map(tubePos -> world.getBlockEntity(tubePos));
		Stream<BlockEntity> filteredStream = teStream.filter(te -> te instanceof ItemPipeTileEntity);
		return filteredStream.map(te -> (ItemPipeTileEntity) te);
	}

	public static Optional<BlockEntity> getTileEntityAt(Level world, BlockPos pos) {
		BlockEntity te = world.getBlockEntity(pos);
		return Optional.ofNullable(te);
	}

	@SuppressWarnings("unchecked")
	public static <T extends BlockEntity> Optional<T> getTileEntityAt(Class<? extends T> clazz, LevelReader world,
			BlockPos pos) {
		BlockEntity te = world.getBlockEntity(pos);
		return Optional.ofNullable(te != null && clazz.isInstance(te) ? (T) te : null);
	}

	public static LazyOptional<IItemHandler> getTEItemHandlerAt(Level world, BlockPos pos, Direction faceOfBlockPos) {
		BlockEntity te = world.getBlockEntity(pos);

		return te != null ? te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, faceOfBlockPos)
				: LazyOptional.empty();
	}

	public static LazyOptional<IItemHandler> getTEItemHandlerAtIf(Level world, BlockPos pos, Direction faceOfBlockPos,
			Predicate<BlockEntity> pred) {
		BlockEntity te = world.getBlockEntity(pos);

		if (te == null)
			return LazyOptional.empty();

		if (!pred.test(te))
			return LazyOptional.empty();

		return te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, faceOfBlockPos);
	}

	public static void ejectItemstack(Level world, BlockPos from_pos, @Nullable Direction output_dir, ItemStack stack) {
		// if there is room in front of the shunt, eject items there
		double x, y, z, xVel, yVel, zVel, xOff, yOff, zOff;
		BlockPos output_pos;
		if (output_dir != null) {
			output_pos = from_pos.relative(output_dir);
			xOff = output_dir.getStepX();
			yOff = output_dir.getStepY();
			zOff = output_dir.getStepZ();
		} else {
			output_pos = from_pos;
			xOff = 0D;
			yOff = 0D;
			zOff = 0D;
		}
		if (!world.getBlockState(output_pos).canOcclude()) {
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
		itementity.setDefaultPickUpDelay();
		itementity.setDeltaMovement(xVel, yVel, zVel);
		world.addFreshEntity(itementity);
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

	@SuppressWarnings("resource")
	public static Direction getBlockFacingForPlacement(BlockPlaceContext context) {
		// if sprint is being held (i.e. ctrl by default), facing is based on the face
		// of the block that was clicked on
		// otherwise, facing is based on the look vector of the player
		// holding sneak reverses the facing of the placement to the opposite face
		boolean isSprintKeyHeld;
		if (context.getLevel().isClientSide) // client thread
		{
			isSprintKeyHeld = ClientProxy.INSTANCE.map(client -> client.getWasSprinting()).orElse(false);
		} else // server thread
		{
			isSprintKeyHeld = PlayerData.getSprinting(context.getPlayer().getUUID());
		}

		Direction placeDir = isSprintKeyHeld ? context.getClickedFace().getOpposite()
				: context.getNearestLookingDirection();
		placeDir = context.isSecondaryUseActive() ? placeDir : placeDir.getOpposite(); // is player sneaking
		return placeDir;
	}

}
