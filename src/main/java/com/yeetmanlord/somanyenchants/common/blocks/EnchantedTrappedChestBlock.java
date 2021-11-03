package com.yeetmanlord.somanyenchants.common.blocks;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedHiddenTrappedChestTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

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

	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		if(hidden)
		{
			return new EnchantedHiddenTrappedChestTileEntity();
		}
		return new EnchantedTrappedChestTileEntity();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
			if (inamedcontainerprovider != null) {
				player.openContainer(inamedcontainerprovider);
				player.addStat(this.getOpenStat());
				PiglinTasks.func_234478_a_(player, true);
			}

			return ActionResultType.CONSUME;
		}
	}

	protected Stat<ResourceLocation> getOpenStat() {
		return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
	}

	/**
	 * Can this block provide power. Only wire currently seems to have this change
	 * based on its state.
	 * 
	 * @deprecated call via {@link IBlockState#canProvidePower()} whenever possible.
	 *             Implementing/overriding is fine.
	 */
	public boolean canProvidePower(BlockState state) {
		return true;
	}

	/**
	 * @deprecated call via
	 *             {@link IBlockState#getWeakPower(IBlockAccess,BlockPos,EnumFacing)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return MathHelper.clamp(EnchantedChestTileEntity.getPlayersUsing(blockAccess, pos), 0, 15);
	}

	/**
	 * @deprecated call via
	 *             {@link IBlockState#getStrongPower(IBlockAccess,BlockPos,EnumFacing)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return side == Direction.UP ? blockState.getWeakPower(blockAccess, pos, side) : 0;
	}

	private static final TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<INamedContainerProvider>> CONTAINER_MERGER = new TileEntityMerger.ICallback<EnchantedChestTileEntity, Optional<INamedContainerProvider>>() {
		public Optional<INamedContainerProvider> func_225539_a_(final EnchantedChestTileEntity p_225539_1_,
				final EnchantedChestTileEntity p_225539_2_) {
			final IInventory iinventory = new DoubleSidedInventory(p_225539_1_, p_225539_2_);
			return Optional.of(new INamedContainerProvider() {
					
					@Nullable
					public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
							PlayerEntity p_createMenu_3_) {
						if (p_225539_1_.canOpen(p_createMenu_3_) && p_225539_2_.canOpen(p_createMenu_3_)) {
							p_225539_1_.fillWithLoot(p_createMenu_2_.player);
							p_225539_2_.fillWithLoot(p_createMenu_2_.player);
							if(p_225539_2_ instanceof EnchantedTrappedChestTileEntity)
							{
								if(ModEnchantmentHelper.isCavernousStorage(((EnchantedTrappedChestTileEntity) p_225539_2_).getEnchants()))
								{
									return EnchantedChestContainer.createGeneric9X8(p_createMenu_1_, p_createMenu_2_, iinventory);
								}
								return EnchantedChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_, iinventory);
							}
							else if(p_225539_2_ instanceof EnchantedHiddenTrappedChestTileEntity)
							{
								if(ModEnchantmentHelper.isCavernousStorage(((EnchantedHiddenTrappedChestTileEntity) p_225539_2_).getEnchants()))
								{
									return EnchantedChestContainer.createGeneric9X8(p_createMenu_1_, p_createMenu_2_, iinventory);
								}
								return EnchantedChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_, iinventory);
							}
							else return EnchantedChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_, iinventory);
						} else {
							return null;
						}
					}

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

		public Optional<INamedContainerProvider> func_225538_a_(EnchantedChestTileEntity p_225538_1_) {
			return Optional.of(p_225538_1_);
		}

		public Optional<INamedContainerProvider> func_225537_b_() {
			return Optional.empty();
		}
	};

	@Nullable
	@Override
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		return this.combine(state, worldIn, pos, false).<Optional<INamedContainerProvider>>apply(CONTAINER_MERGER)
				.orElse((INamedContainerProvider) null);
	}
}
