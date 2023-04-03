package com.github.yeetmanlord.somanyenchants.common.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedShulkerBoxTileEntity;
import com.github.yeetmanlord.somanyenchants.core.enums.EnchantedShulkerAnimationStatuses;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedShulkerBoxBlock extends BaseEntityBlock {
	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
	public static final ResourceLocation CONTENTS = new ResourceLocation("contents");
	@Nullable
	private final DyeColor color;

	public EnchantedShulkerBoxBlock(@Nullable DyeColor color, BlockBehaviour.Properties properties) {
		super(properties);
		this.color = color;
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EnchantedShulkerBoxTileEntity(this.color, pos, state);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_154543_, BlockState p_154544_,
			BlockEntityType<T> p_154545_) {
		return createTickerHelper(p_154545_, BlockEntityTypeInit.ENCHANTED_SHULKER_BOX.get(), EnchantedShulkerBoxTileEntity::tick);
	}
	
	

	/**
	 * The type of render function called. MODEL for mixed tesr and static model,
	 * MODELBLOCK_ANIMATED for TESR-only, LIQUID for vanilla liquids, INVISIBLE to
	 * skip all rendering
	 * 
	 * @deprecated call via {@link IBlockState#getRenderType()} whenever possible.
	 *             Implementing/overriding is fine.
	 */
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
			BlockHitResult hit) {
		if (worldIn.isClientSide) {
			return InteractionResult.SUCCESS;
		} else if (player.isSpectator()) {
			return InteractionResult.CONSUME;
		} else {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
				EnchantedShulkerBoxTileEntity EnchantedShulkerBoxTileEntity = (EnchantedShulkerBoxTileEntity) tileentity;
				if (canOpen(state, worldIn, pos, EnchantedShulkerBoxTileEntity)) {
					player.openMenu(EnchantedShulkerBoxTileEntity);
					player.awardStat(Stats.OPEN_SHULKER_BOX);
					PiglinAi.angerNearbyPiglins(player, true);
				}

				return InteractionResult.CONSUME;
			} else {
				return InteractionResult.PASS;
			}
		}
	}

	private static boolean canOpen(BlockState p_154547_, Level p_154548_, BlockPos p_154549_,
			EnchantedShulkerBoxTileEntity p_154550_) {
		if (p_154550_.getAnimationStatus() != EnchantedShulkerAnimationStatuses.CLOSED) {
			return true;
		} else {
			AABB aabb = Shulker.getProgressDeltaAabb(p_154547_.getValue(FACING), 0.0F, 0.5F).move(p_154549_)
					.deflate(1.0E-6D);
			return p_154548_.noCollision(aabb);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	/**
	 * Called before the Block is set to air in the world. Called regardless of if
	 * the player's tool can actually collect this block
	 */
	@Override
	public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
		BlockEntity tileentity = worldIn.getBlockEntity(pos);
		if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
			EnchantedShulkerBoxTileEntity enchantedShulkerBoxTileEntity = (EnchantedShulkerBoxTileEntity) tileentity;
			if (!worldIn.isClientSide && player.isCreative() && !enchantedShulkerBoxTileEntity.isEmpty()) {
				ItemStack itemstack = getColoredItemStack(this.getColor());
				CompoundTag compoundnbt = enchantedShulkerBoxTileEntity.saveToNbt(new CompoundTag());
				if (!compoundnbt.isEmpty()) {
					itemstack.addTagElement("BlockEntityTag", compoundnbt);
				}

				if (enchantedShulkerBoxTileEntity.hasCustomName()) {
					itemstack.setHoverName(enchantedShulkerBoxTileEntity.getCustomName());
				}

				ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D,
						(double) pos.getZ() + 0.5D, itemstack);
				itementity.setDefaultPickUpDelay();
				worldIn.addFreshEntity(itementity);
			} else {
				enchantedShulkerBoxTileEntity.unpackLootTable(player);
			}
		}

		super.playerWillDestroy(worldIn, pos, state, player);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		BlockEntity tileentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
		if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
			EnchantedShulkerBoxTileEntity EnchantedShulkerBoxTileEntity = (EnchantedShulkerBoxTileEntity) tileentity;
			builder = builder.withDynamicDrop(CONTENTS, (context, stackConsumer) -> {
				for (int i = 0; i < EnchantedShulkerBoxTileEntity.getContainerSize(); ++i) {
					stackConsumer.accept(EnchantedShulkerBoxTileEntity.getItem(i));
				}

			});
		}

		return super.getDrops(state, builder);
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow post-place
	 * logic
	 */
	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
				((EnchantedShulkerBoxTileEntity) tileentity).setCustomName(stack.getHoverName());
			}
		}

	}

	@Override
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
				worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip,
			TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		CompoundTag compoundnbt = stack.getTagElement("BlockEntityTag");
		if (compoundnbt != null) {
			if (compoundnbt.contains("LootTable", 8)) {
				tooltip.add(Component.literal("???????"));
			}

			if (compoundnbt.contains("Items", 9)) {
				NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
				ContainerHelper.loadAllItems(compoundnbt, nonnulllist);
				int i = 0;
				int j = 0;

				for (ItemStack itemstack : nonnulllist) {
					if (!itemstack.isEmpty()) {
						++j;
						if (i <= 4) {
							++i;
							MutableComponent iformattabletextcomponent = itemstack.getHoverName().copy();
							iformattabletextcomponent.append(" x").append(String.valueOf(itemstack.getCount()));
							tooltip.add(iformattabletextcomponent);
						}
					}
				}

				if (j - i > 0) {
					tooltip.add((Component.translatable("container.shulkerBox.more", j - i))
							.withStyle(ChatFormatting.ITALIC));
				}
			}
		}

	}

	/**
	 * @deprecated call via {@link IBlockState#getMobilityFlag()} whenever possible.
	 *             Implementing/overriding is fine.
	 */
	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		BlockEntity tileentity = worldIn.getBlockEntity(pos);
		return tileentity instanceof EnchantedShulkerBoxTileEntity
				? Shapes.create(((EnchantedShulkerBoxTileEntity) tileentity).getBoundingBox(state))
				: Shapes.block();
	}

	/**
	 * @deprecated call via {@link IBlockState#hasComparatorInputOverride()}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	/**
	 * @deprecated call via
	 *             {@link IBlockState#getComparatorInputOverride(World,BlockPos)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
		return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) worldIn.getBlockEntity(pos));
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		ItemStack itemstack = super.getCloneItemStack(worldIn, pos, state);
		EnchantedShulkerBoxTileEntity EnchantedShulkerBoxTileEntity = (EnchantedShulkerBoxTileEntity) worldIn
				.getBlockEntity(pos);
		CompoundTag compoundnbt = EnchantedShulkerBoxTileEntity.saveToNbt(new CompoundTag());
		if (!compoundnbt.isEmpty()) {
			itemstack.addTagElement("BlockEntityTag", compoundnbt);
		}

		return itemstack;
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public static DyeColor getColorFromItem(Item itemIn) {
		return getColorFromBlock(Block.byItem(itemIn));
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public static DyeColor getColorFromBlock(Block blockIn) {
		return blockIn instanceof ShulkerBoxBlock ? ((ShulkerBoxBlock) blockIn).getColor() : null;
	}

	public static Block getBlockByColor(@Nullable DyeColor colorIn) {
		if (colorIn == null) {
			return BlockInit.ENCHANTED_SHULKER_BOX.get();
		} else {
			switch (colorIn) {
			case WHITE:
				return BlockInit.ENCHANTED_WHITE_SHULKER_BOX.get();
			case ORANGE:
				return BlockInit.ENCHANTED_ORANGE_SHULKER_BOX.get();
			case MAGENTA:
				return BlockInit.ENCHANTED_MAGENTA_SHULKER_BOX.get();
			case LIGHT_BLUE:
				return BlockInit.ENCHANTED_LIGHT_BLUE_SHULKER_BOX.get();
			case YELLOW:
				return BlockInit.ENCHANTED_YELLOW_SHULKER_BOX.get();
			case LIME:
				return BlockInit.ENCHANTED_LIME_SHULKER_BOX.get();
			case PINK:
				return BlockInit.ENCHANTED_PINK_SHULKER_BOX.get();
			case GRAY:
				return BlockInit.ENCHANTED_GRAY_SHULKER_BOX.get();
			case LIGHT_GRAY:
				return BlockInit.ENCHANTED_LIGHT_GRAY_SHULKER_BOX.get();
			case CYAN:
				return BlockInit.ENCHANTED_CYAN_SHULKER_BOX.get();
			case PURPLE:
			default:
				return BlockInit.ENCHANTED_PURPLE_SHULKER_BOX.get();
			case BLUE:
				return BlockInit.ENCHANTED_BLUE_SHULKER_BOX.get();
			case BROWN:
				return BlockInit.ENCHANTED_BROWN_SHULKER_BOX.get();
			case GREEN:
				return BlockInit.ENCHANTED_GREEN_SHULKER_BOX.get();
			case RED:
				return BlockInit.ENCHANTED_RED_SHULKER_BOX.get();
			case BLACK:
				return BlockInit.ENCHANTED_BLACK_SHULKER_BOX.get();
			}
		}
	}

	@Nullable
	public DyeColor getColor() {
		return this.color;
	}

	public static ItemStack getColoredItemStack(@Nullable DyeColor colorIn) {
		return new ItemStack(getBlockByColor(colorIn));
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}