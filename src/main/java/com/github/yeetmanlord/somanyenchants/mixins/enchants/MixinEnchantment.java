package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.SweepingEdgeEnchantment;

@Mixin(Enchantment.class)
public class MixinEnchantment {

	@Inject(at = @At("HEAD"), method = "checkCompatibility(Lnet/minecraft/world/item/enchantment/Enchantment;)Z", cancellable = true)
	private void checkCompatibility(Enchantment ench, CallbackInfoReturnable<Boolean> callback) {

		if (((Enchantment) (Object) this) instanceof SweepingEdgeEnchantment) {
		callback.setReturnValue(ench != EnchantmentInit.CRITICAL.get());
		}
	}

}
