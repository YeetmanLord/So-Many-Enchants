package com.github.yeetmanlord.somanyenchants.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedBarrelTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EnchantedBarrelBlock extends ContainerBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

	public EnchantedBarrelBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false)));
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult raycast) {
		if (world.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity blockentity = world.getBlockEntity(pos);
			if (blockentity instanceof EnchantedBarrelTileEntity) {
				player.openMenu((EnchantedBarrelTileEntity) blockentity);
				player.awardStat(Stats.OPEN_BARREL);
				PiglinTasks.angerNearbyPiglins(player, true);
			}

			return ActionResultType.CONSUME;
		}
	}

	@Override
	public void onRemove(BlockState p_49076_, World p_49077_, BlockPos p_49078_, BlockState p_49079_,
			boolean p_49080_) {
		if (!p_49076_.is(p_49079_.getBlock())) {
			TileEntity blockentity = p_49077_.getBlockEntity(p_49078_);
			if (blockentity instanceof IInventory) {
				InventoryHelper.dropContents(p_49077_, p_49078_, (IInventory) blockentity);
				p_49077_.updateNeighbourForOutputSignal(p_49078_, this);
			}

			super.onRemove(p_49076_, p_49077_, p_49078_, p_49079_, p_49080_);
		}
	}

	@Override
	public void tick(BlockState p_49060_, ServerWorld p_49061_, BlockPos p_49062_, Random p_49063_) {
		TileEntity blockentity = p_49061_.getBlockEntity(p_49062_);
		if (blockentity instanceof EnchantedBarrelTileEntity) {
			((EnchantedBarrelTileEntity) blockentity).recheckOpen();
		}

	}

	@Override
	@Nullable
	public TileEntity newBlockEntity(IBlockReader reader) {
		return new EnchantedBarrelTileEntity();
	}

	@Override
	public BlockRenderType getRenderShape(BlockState p_49090_) {
		return BlockRenderType.MODEL;
	}

	@Override
	public void setPlacedBy(World p_49052_, BlockPos p_49053_, BlockState p_49054_, @Nullable LivingEntity p_49055_,
			ItemStack p_49056_) {
		if (p_49056_.hasCustomHoverName()) {
			TileEntity blockentity = p_49052_.getBlockEntity(p_49053_);
			if (blockentity instanceof EnchantedBarrelTileEntity) {
				((EnchantedBarrelTileEntity) blockentity).setCustomName(p_49056_.getHoverName());
			}
		}

	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_49058_) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState p_49065_, World p_49066_, BlockPos p_49067_) {
		return Container.getRedstoneSignalFromBlockEntity(p_49066_.getBlockEntity(p_49067_));
	}

	@Override
	public BlockState rotate(BlockState p_49085_, Rotation p_49086_) {
		return p_49085_.setValue(FACING, p_49086_.rotate(p_49085_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_49082_, Mirror p_49083_) {
		return p_49082_.rotate(p_49083_.getRotation(p_49082_.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_49088_) {
		p_49088_.add(FACING, OPEN);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext p_49048_) {
		return this.defaultBlockState().setValue(FACING, p_49048_.getNearestLookingDirection().getOpposite());
	}
}