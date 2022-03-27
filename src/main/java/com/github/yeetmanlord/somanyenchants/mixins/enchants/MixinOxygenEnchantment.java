package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.OxygenEnchantment;

@Mixin(OxygenEnchantment.class)
public class MixinOxygenEnchantment {

	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {

		if (Config.respiration.isEnabled.get()) {
			callback.setReturnValue(Config.respiration.maxLevel.get());
		}

	}

}
