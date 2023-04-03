package com.github.yeetmanlord.somanyenchants.common.blocks;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedHiddenTrappedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

public class EnchantedTrappedChestBlock extends EnchantedChestBlock {

	private boolean hidden;

	public EnchantedTrappedChestBlock(Properties builder,
			Supplier<BlockEntityType<? extends EnchantedChestTileEntity>> tileEntityTypeIn, boolean hidden) {
		super(builder, tileEntityTypeIn);
		this.hidden = hidden;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		if (hidden) {
			return new EnchantedHiddenTrappedChestTileEntity(pos, state);
		}
		return new EnchantedTrappedChestTileEntity(pos, state);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
			BlockHitResult hit) {
		if (worldIn.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			MenuProvider inamedcontainerprovider = this.getMenuProvider(state, worldIn, pos);
			if (inamedcontainerprovider != null) {
				player.openMenu(inamedcontainerprovider);
				player.awardStat(this.getOpenChestStat());
				PiglinAi.angerNearbyPiglins(player, true);
			}

			return InteractionResult.CONSUME;
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
	public int getSignal(BlockState p_57577_, BlockGetter p_57578_, BlockPos p_57579_, Direction p_57580_) {
		return Mth.clamp(ChestBlockEntity.getOpenCount(p_57578_, p_57579_), 0, 15);
	}

	@Override
	public int getDirectSignal(BlockState p_57582_, BlockGetter p_57583_, BlockPos p_57584_, Direction p_57585_) {
		return p_57585_ == Direction.UP ? p_57582_.getSignal(p_57583_, p_57584_, p_57585_) : 0;
	}

	private static final DoubleBlockCombiner.Combiner<EnchantedChestTileEntity, Optional<MenuProvider>> CONTAINER_MERGER = new DoubleBlockCombiner.Combiner<EnchantedChestTileEntity, Optional<MenuProvider>>() {
		@Override
		public Optional<MenuProvider> acceptDouble(final EnchantedChestTileEntity p_225539_1_,
				final EnchantedChestTileEntity p_225539_2_) {
			final Container iinventory = new CompoundContainer(p_225539_1_, p_225539_2_);
			return Optional.of(new MenuProvider() {

				@Override
				@Nullable
				public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_,
						Player p_createMenu_3_) {
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
				public Component getDisplayName() {
					if (p_225539_1_.hasCustomName()) {
						return p_225539_1_.getDisplayName();
					} else {
						return (Component) (p_225539_2_.hasCustomName() ? p_225539_2_.getDisplayName()
								: Component.translatable("container.enchantedChestDouble"));
					}
				}
			});
		}

		@Override
		public Optional<MenuProvider> acceptSingle(EnchantedChestTileEntity p_225538_1_) {
			return Optional.of(p_225538_1_);
		}

		@Override
		public Optional<MenuProvider> acceptNone() {
			return Optional.empty();
		}
	};

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		return this.combine(state, worldIn, pos, false).<Optional<MenuProvider>>apply(CONTAINER_MERGER)
				.orElse((MenuProvider) null);
	}
}
