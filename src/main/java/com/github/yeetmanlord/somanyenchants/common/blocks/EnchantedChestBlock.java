package com.github.yeetmanlord.somanyenchants.common.blocks;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EnchantedChestBlock extends ContainerBlock implements IWaterLoggable {
	public final Supplier<TileEntityType<? extends EnchantedChestTileEntity>> blockEntityType;
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final int EVENT_SET_OPEN_COUNT = 1;
	protected static final int AABB_OFFSET = 1;
	protected static final int AABB_HEIGHT = 14;
	protected static final VoxelShape NORTH_AABB = Block.box(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape SOUTH_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
	protected static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape EAST_AABB = Block.box(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
	protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	private static final TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<IInventory>> CHEST_COMBINER = new TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<IInventory>>() {
		@Override
		public Optional<IInventory> acceptDouble(EnchantedChestTileEntity p_51591_, EnchantedChestTileEntity p_51592_) {
			return Optional.of(new DoubleSidedInventory(p_51591_, p_51592_));
		}

		@Override
		public Optional<IInventory> acceptSingle(EnchantedChestTileEntity p_51589_) {
			return Optional.of(p_51589_);
		}

		@Override
		public Optional<IInventory> acceptNone() {
			return Optional.empty();
		}
	};
	private static final TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<INamedContainerProvider>> MENU_PROVIDER_COMBINER = new TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<INamedContainerProvider>>() {
		@Override
		public Optional<INamedContainerProvider> acceptDouble(final EnchantedChestTileEntity p_51604_,
				final EnchantedChestTileEntity p_51605_) {
			final IInventory container = new DoubleSidedInventory(p_51604_, p_51605_);
			return Optional.of(new INamedContainerProvider() {
				@Override
				@Nullable
				public Container createMenu(int p_51622_, PlayerInventory p_51623_, PlayerEntity p_51624_) {
					if (p_51604_.canOpen(p_51624_) && p_51605_.canOpen(p_51624_)) {
						p_51604_.unpackLootTable(p_51623_.player);
						p_51605_.unpackLootTable(p_51623_.player);
						return EnchantedChestContainer.createGeneric9X8(p_51622_, p_51623_, container);
					} else {
						return null;
					}
				}

				@Override
				public ITextComponent getDisplayName() {
					if (p_51604_.hasCustomName()) {
						return p_51604_.getDisplayName();
					} else {
						return (ITextComponent) (p_51605_.hasCustomName() ? p_51605_.getDisplayName()
								: new TranslationTextComponent("container.enchantedChestDouble"));
					}
				}
			});
		}

		@Override
		public Optional<INamedContainerProvider> acceptSingle(EnchantedChestTileEntity p_51602_) {
			return Optional.of(p_51602_);
		}

		@Override
		public Optional<INamedContainerProvider> acceptNone() {
			return Optional.empty();
		}
	};

	public EnchantedChestBlock(AbstractBlock.Properties properties,
			Supplier<TileEntityType<? extends EnchantedChestTileEntity>> type) {
		super(properties);
		this.blockEntityType = type;
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
				.setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public static TileEntityMerger.Type getBlockType(BlockState p_51583_) {
		ChestType chesttype = p_51583_.getValue(TYPE);
		if (chesttype == ChestType.SINGLE) {
			return TileEntityMerger.Type.SINGLE;
		} else {
			return chesttype == ChestType.RIGHT ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
		}
	}

	@Override
	public BlockRenderType getRenderShape(BlockState p_51567_) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public BlockState updateShape(BlockState p_51555_, Direction p_51556_, BlockState p_51557_, IWorld p_51558_,
			BlockPos p_51559_, BlockPos p_51560_) {
		if (p_51555_.getValue(WATERLOGGED)) {
			p_51558_.getLiquidTicks().scheduleTick(p_51559_, Fluids.WATER, Fluids.WATER.getTickDelay(p_51558_));
		}

		if (p_51557_.is(this) && p_51556_.getAxis().isHorizontal()) {
			ChestType chesttype = p_51557_.getValue(TYPE);
			if (p_51555_.getValue(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE
					&& p_51555_.getValue(FACING) == p_51557_.getValue(FACING)
					&& getConnectedDirection(p_51557_) == p_51556_.getOpposite()) {
				return p_51555_.setValue(TYPE, chesttype.getOpposite());
			}
		} else if (getConnectedDirection(p_51555_) == p_51556_) {
			return p_51555_.setValue(TYPE, ChestType.SINGLE);
		}

		return super.updateShape(p_51555_, p_51556_, p_51557_, p_51558_, p_51559_, p_51560_);
	}

	@Override
	public VoxelShape getShape(BlockState p_51569_, IBlockReader p_51570_, BlockPos p_51571_,
			ISelectionContext p_51572_) {
		if (p_51569_.getValue(TYPE) == ChestType.SINGLE) {
			return AABB;
		} else {
			switch (getConnectedDirection(p_51569_)) {
			case NORTH:
			default:
				return NORTH_AABB;
			case SOUTH:
				return SOUTH_AABB;
			case WEST:
				return WEST_AABB;
			case EAST:
				return EAST_AABB;
			}
		}
	}

	public static Direction getConnectedDirection(BlockState p_51585_) {
		Direction direction = p_51585_.getValue(FACING);
		return p_51585_.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext p_51493_) {
		ChestType chesttype = ChestType.SINGLE;
		Direction direction = p_51493_.getHorizontalDirection().getOpposite();
		FluidState fluidstate = p_51493_.getLevel().getFluidState(p_51493_.getClickedPos());
		boolean flag = p_51493_.isSecondaryUseActive();
		Direction direction1 = p_51493_.getClickedFace();
		if (direction1.getAxis().isHorizontal() && flag) {
			Direction direction2 = this.candidatePartnerFacing(p_51493_, direction1.getOpposite());
			if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
				direction = direction2;
				chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT
						: ChestType.LEFT;
			}
		}

		if (chesttype == ChestType.SINGLE && !flag) {
			if (direction == this.candidatePartnerFacing(p_51493_, direction.getClockWise())) {
				chesttype = ChestType.LEFT;
			} else if (direction == this.candidatePartnerFacing(p_51493_, direction.getCounterClockWise())) {
				chesttype = ChestType.RIGHT;
			}
		}

		return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, chesttype).setValue(WATERLOGGED,
				Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public FluidState getFluidState(BlockState p_51581_) {
		return p_51581_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_51581_);
	}

	@Nullable
	private Direction candidatePartnerFacing(BlockItemUseContext p_51495_, Direction p_51496_) {
		BlockState blockstate = p_51495_.getLevel().getBlockState(p_51495_.getClickedPos().relative(p_51496_));
		return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING)
				: null;
	}

	@Override
	public void setPlacedBy(World p_51503_, BlockPos p_51504_, BlockState p_51505_, LivingEntity p_51506_,
			ItemStack p_51507_) {
		if (p_51507_.hasCustomHoverName()) {
			TileEntity blockentity = p_51503_.getBlockEntity(p_51504_);
			if (blockentity instanceof EnchantedChestTileEntity) {
				((EnchantedChestTileEntity) blockentity).setCustomName(p_51507_.getHoverName());
			}
		}

	}

	@Override
	public void onRemove(BlockState p_51538_, World p_51539_, BlockPos p_51540_, BlockState p_51541_,
			boolean p_51542_) {
		if (!p_51538_.is(p_51541_.getBlock())) {
			TileEntity blockentity = p_51539_.getBlockEntity(p_51540_);
			if (blockentity instanceof IInventory) {
				InventoryHelper.dropContents(p_51539_, p_51540_, (IInventory) blockentity);
				p_51539_.updateNeighbourForOutputSignal(p_51540_, this);
			}

			super.onRemove(p_51538_, p_51539_, p_51540_, p_51541_, p_51542_);
		}
	}

	@Override
	public ActionResultType use(BlockState p_51531_, World p_51532_, BlockPos p_51533_, PlayerEntity p_51534_,
			Hand p_51535_, BlockRayTraceResult p_51536_) {
		if (p_51532_.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			INamedContainerProvider menuprovider = this.getMenuProvider(p_51531_, p_51532_, p_51533_);
			if (menuprovider != null) {
				p_51534_.openMenu(menuprovider);
				p_51534_.awardStat(this.getOpenChestStat());
				PiglinTasks.angerNearbyPiglins(p_51534_, true);
			}

			return ActionResultType.CONSUME;
		}
	}

	protected Stat<ResourceLocation> getOpenChestStat() {
		return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	}

	public TileEntityType<? extends EnchantedChestTileEntity> blockEntityType() {
		return this.blockEntityType.get();
	}

	@Nullable
	public static IInventory getContainer(EnchantedChestBlock p_51512_, BlockState p_51513_, World p_51514_,
			BlockPos p_51515_, boolean p_51516_) {
		return p_51512_.combine(p_51513_, p_51514_, p_51515_, p_51516_).<Optional<IInventory>>apply(CHEST_COMBINER)
				.orElse((IInventory) null);
	}

	public TileEntityMerger.ICallbackWrapper<? extends EnchantedChestTileEntity> combine(BlockState p_51544_,
			World p_51545_, BlockPos p_51546_, boolean p_51547_) {
		BiPredicate<IWorld, BlockPos> bipredicate;
		if (p_51547_) {
			bipredicate = (p_51578_, p_51579_) -> {
				return false;
			};
		} else {
			bipredicate = EnchantedChestBlock::isEnchantedChestBlockedAt;
		}

		return TileEntityMerger.combineWithNeigbour(this.blockEntityType.get(), EnchantedChestBlock::getBlockType,
				EnchantedChestBlock::getConnectedDirection, FACING, p_51544_, p_51545_, p_51546_, bipredicate);
	}

	@Override
	@Nullable
	public INamedContainerProvider getMenuProvider(BlockState p_51574_, World p_51575_, BlockPos p_51576_) {
		return this.combine(p_51574_, p_51575_, p_51576_, false)
				.<Optional<INamedContainerProvider>>apply(MENU_PROVIDER_COMBINER)
				.orElse((INamedContainerProvider) null);
	}

	public static TileEntityMerger.ICallback<EnchantedChestTileEntity, Float2FloatFunction> opennessCombiner(
			final IChestLid p_51518_) {
		return new TileEntityMerger.ICallback<EnchantedChestTileEntity, Float2FloatFunction>() {
			@Override
			public Float2FloatFunction acceptDouble(EnchantedChestTileEntity p_51633_,
					EnchantedChestTileEntity p_51634_) {
				return (p_51638_) -> {
					return Math.max(p_51633_.getOpenNess(p_51638_), p_51634_.getOpenNess(p_51638_));
				};
			}

			@Override
			public Float2FloatFunction acceptSingle(EnchantedChestTileEntity p_51631_) {
				return p_51631_::getOpenNess;
			}

			@Override
			public Float2FloatFunction acceptNone() {
				return p_51518_::getOpenNess;
			}
		};
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader reader) {
		return new EnchantedChestTileEntity();
	}

	public static boolean isEnchantedChestBlockedAt(IWorld p_51509_, BlockPos p_51510_) {
		return isBlockedChestByBlock(p_51509_, p_51510_) || isCatSittingOnChest(p_51509_, p_51510_);
	}

	private static boolean isBlockedChestByBlock(IBlockReader p_51500_, BlockPos p_51501_) {
		BlockPos blockpos = p_51501_.above();
		return p_51500_.getBlockState(blockpos).isRedstoneConductor(p_51500_, blockpos);
	}

	private static boolean isCatSittingOnChest(IWorld p_51564_, BlockPos p_51565_) {
		List<CatEntity> list = p_51564_.getEntitiesOfClass(CatEntity.class,
				new AxisAlignedBB((double) p_51565_.getX(), (double) (p_51565_.getY() + 1), (double) p_51565_.getZ(),
						(double) (p_51565_.getX() + 1), (double) (p_51565_.getY() + 2),
						(double) (p_51565_.getZ() + 1)));
		if (!list.isEmpty()) {
			for (CatEntity cat : list) {
				if (cat.isInSittingPose()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_51520_) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState p_51527_, World p_51528_, BlockPos p_51529_) {
		return Container.getRedstoneSignalFromContainer(getContainer(this, p_51527_, p_51528_, p_51529_, false));
	}

	@Override
	public BlockState rotate(BlockState p_51552_, Rotation p_51553_) {
		return p_51552_.setValue(FACING, p_51553_.rotate(p_51552_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_51549_, Mirror p_51550_) {
		BlockState rotated = p_51549_.rotate(p_51550_.getRotation(p_51549_.getValue(FACING)));
		return p_51550_ == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_51562_) {
		p_51562_.add(FACING, TYPE, WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState p_51522_, IBlockReader p_51523_, BlockPos p_51524_, PathType p_51525_) {
		return false;
	}
}
