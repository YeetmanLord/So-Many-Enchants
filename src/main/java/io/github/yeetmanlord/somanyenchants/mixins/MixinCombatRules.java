package io.github.yeetmanlord.somanyenchants.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.CombatRules;

@Mixin(CombatRules.class)
public class MixinCombatRules {
	@Overwrite
	/**
	 * This overwrite increases the maximum armor amount from 20 to 1024
	 * @param damage The input damage
	 * @param totalArmor Armor level
	 * @param toughnessAttribute Toughness level
	 * @return The armor reduction with enchantment modifiers
	 */
	public static float getDamageAfterAbsorb(float damage, float totalArmor, float toughnessAttribute) {
		float f = 2.0F + toughnessAttribute / 4.0F;
		float f1 = Mth.clamp(totalArmor - damage / f, totalArmor * 0.2F, 1024F);
		return damage * (1.0F - f1 / 25F);
	}
	
	@Overwrite
	/**
	 * This overwrite increases the protection cap from 80% to 98%
	 * @param damage The input damage
	 * @param totalArmor Armor level
	 * @param toughnessAttribute Toughness level
	 * @return The armor reduction with enchantment modifiers
	 */
	public static float getDamageAfterMagicAbsorb(float damage, float enchantModifiers) {
		float f = Mth.clamp(enchantModifiers, 0.0F, 24.5F);
		return damage * (1.0F - f / 25F);
	}
}
