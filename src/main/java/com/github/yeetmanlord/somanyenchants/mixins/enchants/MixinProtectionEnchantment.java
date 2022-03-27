package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;

@Mixin(ProtectionEnchantment.class)
public class MixinProtectionEnchantment {

	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {

		if (Config.protectionEnchantments.isEnabled.get()) {
			callback.setReturnValue(Config.protectionEnchantments.maxLevel.get());
		}

	}

	@Shadow
	private ProtectionEnchantment.Type type;

	@Inject(at = @At("HEAD"), method = "getDamageProtection(ILnet/minecraft/world/damagesource/DamageSource;)I", cancellable = true)
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
	private void getDamageProtection(int level, DamageSource source, CallbackInfoReturnable<Integer> callback) {

		if (Config.protectionEnchantments.isEnabled.get()) {

			if (source.isBypassInvul()) {
				callback.setReturnValue(0);
			}
			else if (this.type == ProtectionEnchantment.Type.ALL) {

				if (level <= 4) {
					callback.setReturnValue(level);
				}
				else if (level <= 6) {
					callback.setReturnValue((int) ((level - 4) * 0.5) + 4);
				}
				else if (level <= 10) {
					callback.setReturnValue((int) ((int) ((level - 6) * 0.1875) + 5));
				}
				else {
					callback.setReturnValue(7);
				}

			}
			else if (this.type == ProtectionEnchantment.Type.FIRE && source.isFire()) {

				if (level <= 4) {
					callback.setReturnValue((int) (level * 1.5));
				}
				else if (level <= 8) {
					callback.setReturnValue((int) ((level - 4) * .25) + 6);
				}
				else if (level <= 10) {
					callback.setReturnValue((int) ((10 - 8) * .5) + 7);
				}
				else {
					callback.setReturnValue(10);
				}

			}
			else if (this.type == ProtectionEnchantment.Type.FALL && source == DamageSource.FALL) {
				callback.setReturnValue(level * 2);
			}
			else if (this.type == ProtectionEnchantment.Type.EXPLOSION && source.isExplosion()) {

				if (level <= 4) {
					callback.setReturnValue((int) (level * 1.5));
				}
				else if (level <= 8) {
					callback.setReturnValue((int) ((level - 4) * .25) + 6);
				}
				else if (level <= 10) {
					callback.setReturnValue((int) ((10 - 8) * .5) + 7);
				}
				else {
					callback.setReturnValue(10);
				}

			}
			else if (this.type == ProtectionEnchantment.Type.PROJECTILE && source.isProjectile()) {

				if (level <= 4) {
					callback.setReturnValue((int) (level * 1.5));
				}
				else if (level <= 8) {
					callback.setReturnValue((int) ((level - 4) * .25) + 6);
				}
				else if (level <= 10) {
					callback.setReturnValue((int) ((10 - 8) * .5) + 7);
				}
				else {
					callback.setReturnValue(10);
				}

			}
			else {
				callback.setReturnValue(0);
			}

		}

	}

}
