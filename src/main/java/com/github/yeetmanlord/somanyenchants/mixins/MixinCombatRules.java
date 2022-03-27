package com.github.yeetmanlord.somanyenchants.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.CombatRules;

@Mixin(CombatRules.class)
public class MixinCombatRules {

	@Inject(cancellable = true, at = @At("HEAD"), method = "getDamageAfterAbsorb(FFF)F")
	/**
	 * This overwrite increases the maximum armor amount from 20 to 1024
	 * 
	 * @param damage             The input damage
	 * @param totalArmor         Armor level
	 * @param toughnessAttribute Toughness level
	 * @return The armor reduction with enchantment modifiers
	 */
	private static void getDamageAfterAbsorb(float damage, float totalArmor, float toughnessAttribute, CallbackInfoReturnable<Float> callback) {

		if (Config.protectionEnchantments.isEnabled.get()) {
			float f = 2.0F + toughnessAttribute / 4.0F;
			float f1 = Mth.clamp(totalArmor - damage / f, totalArmor * 0.2F, 1024F);
			callback.setReturnValue(damage * (1.0F - f1 / 25F));
		}

	}

	@Inject(cancellable = true, at = @At("HEAD"), method = "getDamageAfterMagicAbsorb(FF)F")
	/**
	 * This overwrite increases the protection cap from 80% to 98%
	 * 
	 * @param damage             The input damage
	 * @param totalArmor         Armor level
	 * @param toughnessAttribute Toughness level
	 * @return The armor reduction with enchantment modifiers
	 */
	private static void getDamageAfterMagicAbsorb(float damage, float enchantModifiers, CallbackInfoReturnable<Float> callback) {

		if (Config.protectionEnchantments.isEnabled.get()) {
			float f = Mth.clamp(enchantModifiers, 0.0F, 24.5F);
			callback.setReturnValue(damage * (1.0F - f / 25F));
		}

	}

}
