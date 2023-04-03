package com.github.yeetmanlord.somanyenchants.common.blocks;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedHiddenTrappedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EnchantedTrappedChestBlock extends EnchantedChestBlock {

	private boolean hidden;

	public EnchantedTrappedChestBlock(Properties builder,
			Supplier<TileEntityType<? extends EnchantedChestTileEntity>> tileEntityTypeIn, boolean hidden) {
		super(builder, tileEntityTypeIn);
		this.hidden = hidden;
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader reader) {
		if (hidden) {
			return new EnchantedHiddenTrappedChestTileEntity();
		}
		return new EnchantedTrappedChestTileEntity();
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			INamedContainerProvider inamedcontainerprovider = this.getMenuProvider(state, worldIn, pos);
			if (inamedcontainerprovider != null) {
				player.openMenu(inamedcontainerprovider);
				player.awardStat(this.getOpenChestStat());
				PiglinTasks.angerNearbyPiglins(player, true);
			}

			return ActionResultType.CONSUME;
		}
	}

	@Override
	protected Stat<ResourceLocation> getOpenChestStat() {
		return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
	}

	@Override
	public boolean isSignalSource(BlockState p_57587_) {
		return true;
	}

	@Override
	public int getSignal(BlockState p_57577_, IBlockReader p_57578_, BlockPos p_57579_, Direction p_57580_) {
		return MathHelper.clamp(ChestTileEntity.getOpenCount(p_57578_, p_57579_), 0, 15);
	}

	@Override
	public int getDirectSignal(BlockState p_57582_, IBlockReader p_57583_, BlockPos p_57584_, Direction p_57585_) {
		return p_57585_ == Direction.UP ? p_57582_.getSignal(p_57583_, p_57584_, p_57585_) : 0;
	}

	private static final TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<INamedContainerProvider>> CONTAINER_MERGER = new TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<INamedContainerProvider>>() {
		@Override
		public Optional<INamedContainerProvider> acceptDouble(final EnchantedChestTileEntity p_225539_1_,
				final EnchantedChestTileEntity p_225539_2_) {
			final IInventory iinventory = new DoubleSidedInventory(p_225539_1_, p_225539_2_);
			return Optional.of(new INamedContainerProvider() {

				@Override
				@Nullable
				public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
						PlayerEntity p_createMenu_3_) {
					if (p_225539_1_.canOpen(p_createMenu_3_) && p_225539_2_.canOpen(p_createMenu_3_)) {
						p_225539_1_.unpackLootTable(p_createMenu_2_.player);
						p_225539_2_.unpackLootTable(p_createMenu_2_.player);
						if (p_225539_2_ instanceof EnchantedTrappedChestTileEntity) {
							if (ModEnchantmentHelper.isCavernousStorage(
									((EnchantedTrappedChestTileEntity) p_225539_2_).getEnchants())) {
								return EnchantedChestContainer.createGeneric9X8(p_createMenu_1_, p_createMenu_2_,
										iinventory);
							}
							return EnchantedChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_,
									iinventory);
						} else if (p_225539_2_ instanceof EnchantedHiddenTrappedChestTileEntity) {
							if (ModEnchantmentHelper.isCavernousStorage(
									((EnchantedHiddenTrappedChestTileEntity) p_225539_2_).getEnchants())) {
								return EnchantedChestContainer.createGeneric9X8(p_createMenu_1_, p_createMenu_2_,
										iinventory);
							}
							return EnchantedChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_,
									iinventory);
						} else
							return EnchantedChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_,
									iinventory);
					} else {
						return null;
					}
				}

				@Override
				public ITextComponent getDisplayName() {
					if (p_225539_1_.hasCustomName()) {
						return p_225539_1_.getDisplayName();
					} else {
						return (ITextComponent) (p_225539_2_.hasCustomName() ? p_225539_2_.getDisplayName()
								: new TranslationTextComponent("container.enchantedChestDouble"));
					}
				}
			});
		}

		@Override
		public Optional<INamedContainerProvider> acceptSingle(EnchantedChestTileEntity p_225538_1_) {
			return Optional.of(p_225538_1_);
		}

		@Override
		public Optional<INamedContainerProvider> acceptNone() {
			return Optional.empty();
		}
	};

	@Nullable
	@Override
	public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
		return this.combine(state, worldIn, pos, false).<Optional<INamedContainerProvider>>apply(CONTAINER_MERGER)
				.orElse((INamedContainerProvider) null);
	}
}
