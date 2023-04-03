package com.github.yeetmanlord.somanyenchants.common.blocks;

import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedHopperTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnchantedHopper extends HopperBlock {

	private static final VoxelShape INPUT_SHAPE = Block.box(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape MIDDLE_SHAPE = Block.box(4.0D, 4.0D, 4.0D, 12.0D, 10.0D, 12.0D);
	private static final VoxelShape INPUT_MIDDLE_SHAPE = VoxelShapes.or(MIDDLE_SHAPE, INPUT_SHAPE);
	private static final VoxelShape BASE_SHAPE = VoxelShapes.join(INPUT_MIDDLE_SHAPE, IHopper.INSIDE, IBooleanFunction.ONLY_FIRST);
	private static final VoxelShape DOWN_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D));
	private static final VoxelShape EAST_SHAPE = VoxelShapes.or(BASE_SHAPE,
			Block.box(12.0D, 4.0D, 6.0D, 16.0D, 8.0D, 10.0D));
	private static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.box(6.0D, 4.0D, 0.0D, 10.0D, 8.0D, 4.0D));
	private static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(BASE_SHAPE,
			Block.box(6.0D, 4.0D, 12.0D, 10.0D, 8.0D, 16.0D));
	private static final VoxelShape WEST_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.box(0.0D, 4.0D, 6.0D, 4.0D, 8.0D, 10.0D));
	private static final VoxelShape DOWN_RAYTRACE_SHAPE = IHopper.INSIDE;
	private static final VoxelShape EAST_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE,
			Block.box(12.0D, 8.0D, 6.0D, 16.0D, 10.0D, 10.0D));
	private static final VoxelShape NORTH_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE,
			Block.box(6.0D, 8.0D, 0.0D, 10.0D, 10.0D, 4.0D));
	private static final VoxelShape SOUTH_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE,
			Block.box(6.0D, 8.0D, 12.0D, 10.0D, 10.0D, 16.0D));
	private static final VoxelShape WEST_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE,
			Block.box(0.0D, 8.0D, 6.0D, 4.0D, 10.0D, 10.0D));

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch ((Direction) state.getValue(FACING)) {
		case DOWN:
			return DOWN_SHAPE;
		case NORTH:
			return NORTH_SHAPE;
		case SOUTH:
			return SOUTH_SHAPE;
		case WEST:
			return WEST_SHAPE;
		case EAST:
			return EAST_SHAPE;
		default:
			return BASE_SHAPE;
		}
	}

	@Override
	public VoxelShape getInteractionShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		switch ((Direction) state.getValue(FACING)) {
		case DOWN:
			return DOWN_RAYTRACE_SHAPE;
		case NORTH:
			return NORTH_RAYTRACE_SHAPE;
		case SOUTH:
			return SOUTH_RAYTRACE_SHAPE;
		case WEST:
			return WEST_RAYTRACE_SHAPE;
		case EAST:
			return EAST_RAYTRACE_SHAPE;
		default:
			return IHopper.INSIDE;
		}
	}

	public EnchantedHopper(Properties properties) {
		super(properties);
		this.registerDefaultState(
				this.stateDefinition.any().setValue(FACING, Direction.DOWN).setValue(ENABLED, Boolean.valueOf(true)));
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader reader) {
		return new EnchantedHopperTileEntity();
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow post-place
	 * logic
	 */
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof EnchantedHopperTileEntity) {
				((EnchantedHopperTileEntity) tileentity).setCustomName(stack.getHoverName());
			}
		}

	}

	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!oldState.is(state.getBlock())) {
			this.updateState(worldIn, pos, state);
		}
	}

	private void updateState(World worldIn, BlockPos pos, BlockState state) {
		boolean flag = !worldIn.hasNeighborSignal(pos);
		if (flag != state.getValue(ENABLED)) {
			worldIn.setBlock(pos, state.setValue(ENABLED, Boolean.valueOf(flag)), 4);
		}
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof EnchantedHopperTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (EnchantedHopperTileEntity) tileentity, pos);
				player.awardStat(Stats.INSPECT_HOPPER);
			}

			return ActionResultType.CONSUME;
		}
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof EnchantedHopperTileEntity) {
				InventoryHelper.dropContents(worldIn, pos, (EnchantedHopperTileEntity) tileentity);
				worldIn.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}
}
