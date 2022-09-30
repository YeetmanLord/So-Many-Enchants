package com.github.yeetmanlord.somanyenchants.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@Mixin(Item.class)
public class MixinItem {

	@Inject(at = @At("HEAD"), method = "getEnchantmentValue()I", cancellable = true)
	private void getEnchantmentValue(CallbackInfoReturnable<Integer> cir) {

		if (((Item) (Object) this) instanceof BlockItem) {
			Item item = ((Item) (Object) this);

			if (EnchantmentTypesInit.isModdedEnchantable(item)) {
				if (EnchantmentTypesInit.isSmelter(item)) {
					cir.setReturnValue(15);
				}
				else cir.setReturnValue(1);
			}

		}

	}

	@Inject(at = @At("HEAD"), method = "isEnchantable(Lnet/minecraft/world/item/ItemStack;)Z", cancellable = true)
	private void isEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {

		if (((Item) (Object) this) instanceof BlockItem) {
			Item item = ((Item) (Object) this);

			if (EnchantmentTypesInit.isModdedEnchantable(item) && stack.getCount() == 1 && !stack.isEnchanted()) {
				callback.setReturnValue(true);
			}

		}

	}

}
