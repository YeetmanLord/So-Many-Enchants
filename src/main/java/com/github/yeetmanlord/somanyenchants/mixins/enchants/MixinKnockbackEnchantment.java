package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.KnockbackEnchantment;

@Mixin(KnockbackEnchantment.class)
public class MixinKnockbackEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.knockback.isEnabled.get() == false) {
			return 2;
		}
		else return Config.knockback.maxLevel.get();

	}
}
