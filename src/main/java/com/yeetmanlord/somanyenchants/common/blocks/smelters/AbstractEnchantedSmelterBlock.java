package com.yeetmanlord.somanyenchants.common.blocks.smelters;

import com.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.yeetmanlord.somanyenchants.core.init.BlockInit;

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

public abstract class AbstractEnchantedSmelterBlock extends ContainerBlock
{

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	protected AbstractEnchantedSmelterBlock(AbstractBlock.Properties properties)
	{
		super(properties);
		this.setDefaultState(
				this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.valueOf(false)));
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit)
	{

		if (worldIn.isRemote)
		{
			return ActionResultType.SUCCESS;
		}
		else
		{
			this.interactWith(worldIn, pos, player);
			return ActionResultType.CONSUME;
		}

	}

	protected abstract void interactWith(World worldIn, BlockPos pos, PlayerEntity player);

	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{

		if (stack.hasDisplayName())
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof AbstractEnchantedSmelterTileEntity)
			{
				((AbstractEnchantedSmelterTileEntity) tileentity).setCustomName(stack.getDisplayName());
			}

		}

	}

	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
	{

		if (!state.matchesBlock(newState.getBlock()))
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof AbstractEnchantedSmelterTileEntity)
			{
				InventoryHelper.dropInventoryItems(worldIn, pos, (AbstractEnchantedSmelterTileEntity) tileentity);
				((AbstractEnchantedSmelterTileEntity) tileentity).grantStoredRecipeExperience(worldIn,
						Vector3d.copyCentered(pos));
				worldIn.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}

	}

	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}

	public BlockState rotate(BlockState state, Rotation rot)
	{
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn)
	{
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, LIT);
	}

	public abstract Block getUnenchantedBlock();

	public static AbstractEnchantedSmelterBlock getSmelterFromBlock(Block block)
	{
		if(block == Blocks.FURNACE)
		{
			return (AbstractEnchantedSmelterBlock) BlockInit.ENCHANTED_FURNACE.get();
		}
		else if(block == Blocks.BLAST_FURNACE)
		{
			return (AbstractEnchantedSmelterBlock) BlockInit.ENCHANTED_BLAST_FURNACE.get();
		}
		else if(block == Blocks.SMOKER)
		{
			return (AbstractEnchantedSmelterBlock) BlockInit.ENCHANTED_SMOKER.get();
		}
		return null;
	}

}
