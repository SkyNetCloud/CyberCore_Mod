package xyz.skynetcloud.cybercore.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;

@SuppressWarnings("unused")
public class BlockBaseCore extends Block {

	private String name;

	private ItemGroup group;

	public BlockBaseCore(Block.Properties property, String name, ItemGroup group, boolean hasItem) {
		super(property);
		this.group = group;
		this.name = name;
		setRegistryName(name);

	}

	public Item createItemBlock() {
		return (Item) (new BlockItem(this, (new Item.Properties()).group(this.group))).setRegistryName(this.name);
	}

	protected VoxelShape[] makeShapes(float nodeWidth, float extensionWidth, float p_196408_3_, float p_196408_4_,
			float p_196408_5_) {
		float f = 8.0F - nodeWidth;
		float f1 = 8.0F + nodeWidth;
		float f2 = 8.0F - extensionWidth;
		float f3 = 8.0F + extensionWidth;
		VoxelShape voxelshape = Block.makeCuboidShape(f, 0.0D, f, f1, p_196408_3_, f1);
		VoxelShape voxelshape1 = Block.makeCuboidShape(f2, p_196408_4_, 0.0D, f3, p_196408_5_, f3);
		VoxelShape voxelshape2 = Block.makeCuboidShape(f2, p_196408_4_, f2, f3, p_196408_5_, 16.0D);
		VoxelShape voxelshape3 = Block.makeCuboidShape(0.0D, p_196408_4_, f2, f3, p_196408_5_, f3);
		VoxelShape voxelshape4 = Block.makeCuboidShape(f2, p_196408_4_, f2, 16.0D, p_196408_5_, f3);
		VoxelShape voxelshape5 = VoxelShapes.or(voxelshape1, voxelshape4);
		VoxelShape voxelshape6 = VoxelShapes.or(voxelshape2, voxelshape3);
		VoxelShape[] avoxelshape = { VoxelShapes.empty(), voxelshape2, voxelshape3, voxelshape6, voxelshape1,
				VoxelShapes.or(voxelshape2, voxelshape1), VoxelShapes.or(voxelshape3, voxelshape1),
				VoxelShapes.or(voxelshape6, voxelshape1), voxelshape4, VoxelShapes.or(voxelshape2, voxelshape4),
				VoxelShapes.or(voxelshape3, voxelshape4), VoxelShapes.or(voxelshape6, voxelshape4), voxelshape5,
				VoxelShapes.or(voxelshape2, voxelshape5), VoxelShapes.or(voxelshape3, voxelshape5),
				VoxelShapes.or(voxelshape6, voxelshape5) };
		for (int i = 0; i < 16; i++)
			avoxelshape[i] = VoxelShapes.or(voxelshape, avoxelshape[i]);
		return avoxelshape;
	}
}
