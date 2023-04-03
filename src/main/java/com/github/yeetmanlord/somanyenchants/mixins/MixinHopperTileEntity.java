package com.github.yeetmanlord.somanyenchants.mixins;

import java.util.List;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.util.math.BlockPos;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.AxisAlignedBB;

@Mixin(HopperTileEntity.class)
public class MixinHopperTileEntity {

	@Inject(at = @At("TAIL"), cancellable = true, method = "getContainerAt(Lnet/minecraft/world/World;DDD)Lnet/minecraft/inventory/IInventory;") @Nullable
	/**
	 * This inject lets hoppers move items into
	 * 
	 * @param world The BlockEntity's world
	 * @param x     X position to check
	 * @param y     Y position to check
	 * @param z     Z position to check
	 * @return If a container exists at the checked location will return the
	 *         container's inventory otherwise will try to check other areas
	 */
	private static void getContainerAt(World p_59348_, double p_59349_, double p_59350_, double p_59351_, CallbackInfoReturnable<IInventory> callback) {

		if (Config.cavernousStorage.isEnabled.get()) {
			IInventory container = null;
			BlockPos blockpos = new BlockPos(p_59349_, p_59350_, p_59351_);
			BlockState blockstate = p_59348_.getBlockState(blockpos);
			Block block = blockstate.getBlock();

			if (block instanceof ISidedInventoryProvider) {
				container = ((ISidedInventoryProvider) block).getContainer(blockstate, p_59348_, blockpos);
			}
			else if (blockstate.hasTileEntity()) {
				TileEntity blockentity = p_59348_.getBlockEntity(blockpos);

				if (blockentity instanceof IInventory) {
					container = (IInventory) blockentity;

					if (container instanceof EnchantedChestTileEntity && block instanceof EnchantedChestBlock) {
						container = EnchantedChestBlock.getContainer((EnchantedChestBlock) block, blockstate, p_59348_, blockpos, true);
					}

				}

			}

			if (container == null) {
				List<Entity> list = p_59348_.getEntities((Entity) null, new AxisAlignedBB(p_59349_ - 0.5D, p_59350_ - 0.5D, p_59351_ - 0.5D, p_59349_ + 0.5D, p_59350_ + 0.5D, p_59351_ + 0.5D), EntityPredicates.CONTAINER_ENTITY_SELECTOR);

				if (!list.isEmpty()) {
					container = (IInventory) list.get(p_59348_.random.nextInt(list.size()));
				}

			}

			callback.setReturnValue(container);
		}

	}

}
