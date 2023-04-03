package com.github.yeetmanlord.somanyenchants.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.container.ShulkerBoxSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;

@Mixin(ShulkerBoxSlot.class)
public class MixinShulkerBoxSlot {

	@Inject(at = @At("HEAD"), method = "mayPlace(Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
	private void mayPlace(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {

		if (Config.cavernousStorage.isEnabled.get()) {
			callback.setReturnValue(!(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock)
					&& !(Block.byItem(stack.getItem()) instanceof EnchantedShulkerBoxBlock));
		}

	}

}
