package com.github.yeetmanlord.somanyenchants.mixins;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(HopperBlockEntity.class)
public class MixinHopperTileEntity {
	
	@Inject(at = @At("RETURN"), method = "getContainerAt(Lnet/minecraft/world/level/Level;DDD)Lnet/minecraft/world/Container;", cancellable = true)
	@Nullable
	/**
	 * This inject lets hoppers move items into 
	 * @param world The BlockEntity's world
	 * @param x X position to check
	 * @param y Y position to check
	 * @param z Z position to check
	 * @return If a container exists at the checked location will return the container's inventory otherwise will try to check other areas
	 */
	private static void getContainerAt(Level world, double x, double y, double z, CallbackInfoReturnable<Container> callback) {
		Container container = null;
		BlockPos blockpos = new BlockPos(x, y, z);
		BlockState blockstate = world.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		if (block instanceof WorldlyContainerHolder) {
			container = ((WorldlyContainerHolder) block).getContainer(blockstate, world, blockpos);
		} else if (blockstate.hasBlockEntity()) {
			BlockEntity blockentity = world.getBlockEntity(blockpos);
			if (blockentity instanceof Container) {
				container = (Container) blockentity;
				if(container instanceof EnchantedChestTileEntity && block instanceof EnchantedChestBlock) {
					container = EnchantedChestBlock.getContainer((EnchantedChestBlock)block, blockstate, world, blockpos, true);
					callback.setReturnValue(container);
				}
			}
		}
	}
}
