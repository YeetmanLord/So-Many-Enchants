package com.github.yeetmanlord.somanyenchants.common.blocks.smelters;

import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class AbstractEnchantedSmelterBlock extends ContainerBlock {

	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	protected AbstractEnchantedSmelterBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(
				this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {

		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			this.interactWith(worldIn, pos, player);
			return ActionResultType.CONSUME;
		}

	}

	protected abstract void interactWith(World worldIn, BlockPos pos, PlayerEntity player);

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

		if (stack.hasCustomHoverName()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);

			if (tileentity instanceof AbstractEnchantedSmelterTileEntity) {
				((AbstractEnchantedSmelterTileEntity) tileentity).setCustomName(stack.getHoverName());
			}

		}

	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {

		if (!state.is(newState.getBlock())) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);

			if (tileentity instanceof AbstractEnchantedSmelterTileEntity) {
				InventoryHelper.dropContents(worldIn, pos, (AbstractEnchantedSmelterTileEntity) tileentity);
				((AbstractEnchantedSmelterTileEntity) tileentity).grantStoredRecipeExperience(worldIn,
						Vector3d.atCenterOf(pos));
				worldIn.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}

	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
	}

	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIT);
	}

	public abstract Block getUnenchantedBlock();

	public static AbstractEnchantedSmelterBlock getSmelterFromBlock(Block block) {
		if (block == Blocks.FURNACE) {
			return (AbstractEnchantedSmelterBlock) BlockInit.ENCHANTED_FURNACE.get();
		} else if (block == Blocks.BLAST_FURNACE) {
			return (AbstractEnchantedSmelterBlock) BlockInit.ENCHANTED_BLAST_FURNACE.get();
		} else if (block == Blocks.SMOKER) {
			return (AbstractEnchantedSmelterBlock) BlockInit.ENCHANTED_SMOKER.get();
		}
		return null;
	}

}
