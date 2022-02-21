package com.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.SweepingEdgeEnchantment;

@Mixin(SweepingEdgeEnchantment.class)
public class MixinSweepingEdgeEnchantment {
	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {
		if (Config.sweeping.isEnabled.get() == false) {
			callback.setReturnValue(3);
		} else
			callback.setReturnValue(Config.sweeping.maxLevel.get());
	}
	
	@Overwrite
	/**
	 * Overwriting the compatibility function of {@link DamageEnchantment} to add enchantments from this mod
	 * @param ench The enchantment to check
	 * @return If the enchantment is not any of the banned enchants returns true
	 */
	public boolean checkCompatibility(Enchantment ench) {
		return ench != EnchantmentInit.CRITICAL.get();
	}
}
