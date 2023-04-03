package com.github.yeetmanlord.somanyenchants.common.blocks;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedBarrelTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class EnchantedBarrelBlock extends BaseEntityBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

	public EnchantedBarrelBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false)));
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
			InteractionHand hand, BlockHitResult raycast) {
		if (world.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			BlockEntity blockentity = world.getBlockEntity(pos);
			if (blockentity instanceof EnchantedBarrelTileEntity) {
				player.openMenu((EnchantedBarrelTileEntity) blockentity);
				player.awardStat(Stats.OPEN_BARREL);
				PiglinAi.angerNearbyPiglins(player, true);
			}

			return InteractionResult.CONSUME;
		}
	}

	@Override
	public void onRemove(BlockState p_49076_, Level p_49077_, BlockPos p_49078_, BlockState p_49079_,
			boolean p_49080_) {
		if (!p_49076_.is(p_49079_.getBlock())) {
			BlockEntity blockentity = p_49077_.getBlockEntity(p_49078_);
			if (blockentity instanceof Container) {
				Containers.dropContents(p_49077_, p_49078_, (Container) blockentity);
				p_49077_.updateNeighbourForOutputSignal(p_49078_, this);
			}

			super.onRemove(p_49076_, p_49077_, p_49078_, p_49079_, p_49080_);
		}
	}

	@Override
	public void tick(BlockState p_49060_, ServerLevel p_49061_, BlockPos p_49062_, RandomSource p_49063_) {
		BlockEntity blockentity = p_49061_.getBlockEntity(p_49062_);
		if (blockentity instanceof EnchantedBarrelTileEntity) {
			((EnchantedBarrelTileEntity) blockentity).recheckOpen();
		}

	}

	@Override
	@Nullable
	public BlockEntity newBlockEntity(BlockPos p_152102_, BlockState p_152103_) {
		return new EnchantedBarrelTileEntity(p_152102_, p_152103_);
	}

	@Override
	public RenderShape getRenderShape(BlockState p_49090_) {
		return RenderShape.MODEL;
	}

	@Override
	public void setPlacedBy(Level p_49052_, BlockPos p_49053_, BlockState p_49054_, @Nullable LivingEntity p_49055_,
			ItemStack p_49056_) {
		if (p_49056_.hasCustomHoverName()) {
			BlockEntity blockentity = p_49052_.getBlockEntity(p_49053_);
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
	public int getAnalogOutputSignal(BlockState p_49065_, Level p_49066_, BlockPos p_49067_) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_49066_.getBlockEntity(p_49067_));
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49088_) {
		p_49088_.add(FACING, OPEN);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_49048_) {
		return this.defaultBlockState().setValue(FACING, p_49048_.getNearestLookingDirection().getOpposite());
	}
}