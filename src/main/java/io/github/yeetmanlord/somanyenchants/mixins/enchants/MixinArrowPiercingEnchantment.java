package io.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.yeetmanlord.somanyenchants.core.config.Config;
import net.minecraft.world.item.enchantment.ArrowPiercingEnchantment;

@Mixin(ArrowPiercingEnchantment.class)
public class MixinArrowPiercingEnchantment {
	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {
		if (Config.piercing.isEnabled.get() == false) {
			callback.setReturnValue(4);
		} else
			callback.setReturnValue(Config.piercing.maxLevel.get());
	}

}