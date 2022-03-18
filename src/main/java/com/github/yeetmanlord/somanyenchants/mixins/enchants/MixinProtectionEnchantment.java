package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;

@Mixin(ProtectionEnchantment.class)
public class MixinProtectionEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.protectionEnchantments.isEnabled.get() == false) {
			return 4;
		}
		else return Config.protectionEnchantments.maxLevel.get();

	}

	@Shadow
	private ProtectionEnchantment.Type type;

	@Overwrite
	/**
	 * Modifies the logic of each protection type to make less linear and to prevent
	 * waste (Before this overwrite maxed protection levels would be the same as
	 * prot 5)
	 * 
	 * @param level  The level of the enchant
	 * @param source The specific damage source (Like fire, explosion, etc.) That
	 *               the protection enchant will apply to.
	 * @return Returns the protection value for each damage source based on the type
	 *         of protection.
	 */
	public int getDamageProtection(int level, DamageSource source) {

		if (source.isBypassInvul()) {
			return 0;
		}
		else if (this.type == ProtectionEnchantment.Type.ALL) {

			if (level <= 4) {
				return level;
			}
			else if (level <= 6) {
				return (int) ((level - 4) * 0.5) + 4;
			}
			else if (level <= 10) {
				return (int) ((int) ((level - 6) * 0.1875) + 5);
			}
			else {
				return 6;
			}

		}
		else if (this.type == ProtectionEnchantment.Type.FIRE && source.isFire()) {

			if (level <= 4) {
				return (int) (level * 1.5);
			}
			else if (level <= 8) {
				return (int) ((level - 4) * .25) + 6;
			}
			else if (level <= 10) {
				return (int) ((10 - 8) * .5) + 7;
			}
			else {
				return 10;
			}

		}
		else if (this.type == ProtectionEnchantment.Type.FALL && source == DamageSource.FALL) {
			return level * 2;
		}
		else if (this.type == ProtectionEnchantment.Type.EXPLOSION && source.isExplosion()) {

			if (level <= 4) {
				return (int) (level * 1.5);
			}
			else if (level <= 8) {
				return (int) ((level - 4) * .25) + 6;
			}
			else if (level <= 10) {
				return (int) ((10 - 8) * .5) + 7;
			}
			else {
				return 10;
			}

		}
		else if (this.type == ProtectionEnchantment.Type.PROJECTILE && source.isProjectile()) {

			if (level <= 4) {
				return (int) (level * 1.5);
			}
			else if (level <= 8) {
				return (int) ((level - 4) * .25) + 6;
			}
			else if (level <= 10) {
				return (int) ((10 - 8) * .5) + 7;
			}
			else {
				return 10;
			}

		}
		else {
			return 0;
		}

	}

}
