package com.yeetmanlord.somanyenchants.common.blocks;

import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedHopperTileEntity;

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

	   private static final VoxelShape INPUT_SHAPE = Block.makeCuboidShape(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	   private static final VoxelShape MIDDLE_SHAPE = Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 10.0D, 12.0D);
	   private static final VoxelShape INPUT_MIDDLE_SHAPE = VoxelShapes.or(MIDDLE_SHAPE, INPUT_SHAPE);
	   private static final VoxelShape BASE_SHAPE = VoxelShapes.combineAndSimplify(INPUT_MIDDLE_SHAPE, IHopper.INSIDE_BOWL_SHAPE, IBooleanFunction.ONLY_FIRST);
	   private static final VoxelShape DOWN_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D));
	   private static final VoxelShape EAST_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.makeCuboidShape(12.0D, 4.0D, 6.0D, 16.0D, 8.0D, 10.0D));
	   private static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.makeCuboidShape(6.0D, 4.0D, 0.0D, 10.0D, 8.0D, 4.0D));
	   private static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.makeCuboidShape(6.0D, 4.0D, 12.0D, 10.0D, 8.0D, 16.0D));
	   private static final VoxelShape WEST_SHAPE = VoxelShapes.or(BASE_SHAPE, Block.makeCuboidShape(0.0D, 4.0D, 6.0D, 4.0D, 8.0D, 10.0D));
	   private static final VoxelShape DOWN_RAYTRACE_SHAPE = IHopper.INSIDE_BOWL_SHAPE;
	   private static final VoxelShape EAST_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE_BOWL_SHAPE, Block.makeCuboidShape(12.0D, 8.0D, 6.0D, 16.0D, 10.0D, 10.0D));
	   private static final VoxelShape NORTH_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE_BOWL_SHAPE, Block.makeCuboidShape(6.0D, 8.0D, 0.0D, 10.0D, 10.0D, 4.0D));
	   private static final VoxelShape SOUTH_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE_BOWL_SHAPE, Block.makeCuboidShape(6.0D, 8.0D, 12.0D, 10.0D, 10.0D, 16.0D));
	   private static final VoxelShape WEST_RAYTRACE_SHAPE = VoxelShapes.or(IHopper.INSIDE_BOWL_SHAPE, Block.makeCuboidShape(0.0D, 8.0D, 6.0D, 4.0D, 10.0D, 10.0D));
	   
	
	   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		      switch((Direction)state.get(FACING)) {
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

		   public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		      switch((Direction)state.get(FACING)) {
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
		         return IHopper.INSIDE_BOWL_SHAPE;
		      }
		   }

	public EnchantedHopper(Properties properties) {
		super(properties);
	    this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.DOWN).with(ENABLED, Boolean.valueOf(true)));
	}
	
	 public TileEntity createNewTileEntity(IBlockReader worldIn) {
	      return new EnchantedHopperTileEntity();
	   }

	   /**
	    * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	    */
	   public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
	      if (stack.hasDisplayName()) {
	         TileEntity tileentity = worldIn.getTileEntity(pos);
	         if (tileentity instanceof EnchantedHopperTileEntity) {
	            ((EnchantedHopperTileEntity)tileentity).setCustomName(stack.getDisplayName());
	         }
	      }

	   }

	   public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
	      if (!oldState.matchesBlock(state.getBlock())) {
	         this.updateState(worldIn, pos, state);
	      }
	   }
	   
	   private void updateState(World worldIn, BlockPos pos, BlockState state) {
		      boolean flag = !worldIn.isBlockPowered(pos);
		      if (flag != state.get(ENABLED)) {
		         worldIn.setBlockState(pos, state.with(ENABLED, Boolean.valueOf(flag)), 4);
		      }
	   }

	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      if (worldIn.isRemote) {
	         return ActionResultType.SUCCESS;
	      } else {
	         TileEntity tileentity = worldIn.getTileEntity(pos);
	         if (tileentity instanceof EnchantedHopperTileEntity) {
	        	NetworkHooks.openGui((ServerPlayerEntity) player, (EnchantedHopperTileEntity) tileentity, pos);
	            player.addStat(Stats.INSPECT_HOPPER);
	         }

	         return ActionResultType.CONSUME;
	      }
	   }
	   
	   public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		      if (!state.matchesBlock(newState.getBlock())) {
		         TileEntity tileentity = worldIn.getTileEntity(pos);
		         if (tileentity instanceof EnchantedHopperTileEntity) {
		            InventoryHelper.dropInventoryItems(worldIn, pos, (EnchantedHopperTileEntity)tileentity);
		            worldIn.updateComparatorOutputLevel(pos, this);
		         }

		         super.onReplaced(state, worldIn, pos, newState, isMoving);
		      }
		   }
}
