package com.github.yeetmanlord.somanyenchants.common.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedShulkerBoxTileEntity;
import com.github.yeetmanlord.somanyenchants.core.enums.EnchantedShulkerAnimationStatuses;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.util.Mirror;
import net.minecraft.block.BlockRenderType;
import net.minecraft.util.Rotation;
import net.minecraft.util.ShulkerAABBHelper;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.state.EnumProperty;
import net.minecraft.block.material.PushReaction;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedShulkerBoxBlock extends ContainerBlock {

	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

	public static final ResourceLocation CONTENTS = new ResourceLocation("contents");

	@Nullable
	private final DyeColor color;

	public EnchantedShulkerBoxBlock(@Nullable DyeColor color, AbstractBlock.Properties properties) {

		super(properties);
		this.color = color;
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));

	}

	@Override
	public TileEntity newBlockEntity(IBlockReader p_196283_1_) {

		return new ShulkerBoxTileEntity(this.color);

	}

	@Override
	public BlockRenderType getRenderShape(BlockState state) {

		return BlockRenderType.ENTITYBLOCK_ANIMATED;

	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		}
		else if (player.isSpectator()) {
			return ActionResultType.CONSUME;
		}
		else {
			TileEntity tileentity = worldIn.getBlockEntity(pos);

			if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
				EnchantedShulkerBoxTileEntity EnchantedShulkerBoxTileEntity = (EnchantedShulkerBoxTileEntity) tileentity;

				if (canOpen(state, worldIn, pos, EnchantedShulkerBoxTileEntity)) {
					player.openMenu(EnchantedShulkerBoxTileEntity);
					player.awardStat(Stats.OPEN_SHULKER_BOX);
					PiglinTasks.angerNearbyPiglins(player, true);
				}

				return ActionResultType.CONSUME;
			}
			else {
				return ActionResultType.PASS;
			}

		}

	}

	private static boolean canOpen(BlockState state, World world, BlockPos pos, EnchantedShulkerBoxTileEntity p_154550_) {

		if (p_154550_.getAnimationStatus() != EnchantedShulkerAnimationStatuses.CLOSED) {
			return true;
		}
		else {
			Direction direction = state.getValue(FACING);
			return world.noCollision(ShulkerAABBHelper.openBoundingBox(pos, direction));
		}

	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {

		return this.defaultBlockState().setValue(FACING, context.getClickedFace());

	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {

		builder.add(FACING);

	}

	/**
	 * Called before the Block is set to air in the world. Called regardless of if
	 * the player's tool can actually collect this block
	 */
	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {

		TileEntity tileentity = worldIn.getBlockEntity(pos);

		if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
			EnchantedShulkerBoxTileEntity enchantedShulkerBoxTileEntity = (EnchantedShulkerBoxTileEntity) tileentity;

			if (!worldIn.isClientSide && player.isCreative() && !enchantedShulkerBoxTileEntity.isEmpty()) {
				ItemStack itemstack = getColoredItemStack(this.getColor());
				CompoundNBT compoundnbt = enchantedShulkerBoxTileEntity.saveToNbt(new CompoundNBT());

				if (!compoundnbt.isEmpty()) {
					itemstack.addTagElement("BlockEntityTag", compoundnbt);
				}

				if (enchantedShulkerBoxTileEntity.hasCustomName()) {
					itemstack.setHoverName(enchantedShulkerBoxTileEntity.getCustomName());
				}

				ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, itemstack);
				itementity.setDefaultPickUpDelay();
				worldIn.addFreshEntity(itementity);
			}
			else {
				enchantedShulkerBoxTileEntity.unpackLootTable(player);
			}

		}

		super.playerWillDestroy(worldIn, pos, state, player);

	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

		TileEntity tileentity = builder.getOptionalParameter(LootParameters.BLOCK_ENTITY);

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
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

		if (stack.hasCustomHoverName()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);

			if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
				((EnchantedShulkerBoxTileEntity) tileentity).setCustomName(stack.getHoverName());
			}

		}

	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {

		if (!state.is(newState.getBlock())) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);

			if (tileentity instanceof EnchantedShulkerBoxTileEntity) {
				worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		CompoundNBT compoundnbt = stack.getTagElement("BlockEntityTag");

		if (compoundnbt != null) {

			if (compoundnbt.contains("LootTable", 8)) {
				tooltip.add(new StringTextComponent("???????"));
			}

			if (compoundnbt.contains("Items", 9)) {
				NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
				ItemStackHelper.loadAllItems(compoundnbt, nonnulllist);
				int i = 0;
				int j = 0;

				for (ItemStack itemstack : nonnulllist) {

					if (!itemstack.isEmpty()) {
						++j;

						if (i <= 4) {
							++i;
							IFormattableTextComponent iformattabletextcomponent = itemstack.getHoverName().copy();
							iformattabletextcomponent.append(" x").append(String.valueOf(itemstack.getCount()));
							tooltip.add(iformattabletextcomponent);
						}

					}

				}

				if (j - i > 0) {
					tooltip.add((new TranslationTextComponent("container.shulkerBox.more", j - i)).withStyle(
							TextFormatting.ITALIC));
				}

			}

		}

	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {

		return PushReaction.DESTROY;

	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

		TileEntity tileentity = worldIn.getBlockEntity(pos);
		return tileentity instanceof EnchantedShulkerBoxTileEntity ? VoxelShapes.create(
				((EnchantedShulkerBoxTileEntity) tileentity).getBoundingBox(state)) : VoxelShapes.block();

	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {

		return true;

	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {

		return Container.getRedstoneSignalFromContainer((IInventory) worldIn.getBlockEntity(pos));

	}

	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {

		ItemStack itemstack = super.getCloneItemStack(worldIn, pos, state);
		EnchantedShulkerBoxTileEntity EnchantedShulkerBoxTileEntity = (EnchantedShulkerBoxTileEntity) worldIn.getBlockEntity(
				pos);
		CompoundNBT compoundnbt = EnchantedShulkerBoxTileEntity.saveToNbt(new CompoundNBT());

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
		}
		else {

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

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {

		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));

	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {

		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));

	}

}
