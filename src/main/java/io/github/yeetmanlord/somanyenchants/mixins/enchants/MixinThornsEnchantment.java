package io.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.yeetmanlord.somanyenchants.core.config.Config;
import net.minecraft.world.item.enchantment.ThornsEnchantment;

@Mixin(ThornsEnchantment.class)
public class MixinThornsEnchantment {
	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {
		if (Config.thorns.isEnabled.get() == false) {
			callback.setReturnValue(3);
		} else
			callback.setReturnValue(Config.thorns.maxLevel.get());
	}
}
