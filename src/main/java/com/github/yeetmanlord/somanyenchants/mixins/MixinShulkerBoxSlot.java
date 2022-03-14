package com.github.yeetmanlord.somanyenchants.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;

import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

@Mixin(ShulkerBoxSlot.class)
public class MixinShulkerBoxSlot {
	@Inject(at = @At("HEAD"), method = "mayPlace(Lnet/minecraft/world/item/ItemStack;)Z", cancellable = true)
	private void isItemValid(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {
		callback.setReturnValue(!(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock)
				&& !(Block.byItem(stack.getItem()) instanceof EnchantedShulkerBoxBlock));
	}
}
