package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.SoulSpeedEnchantment;

@Mixin(SoulSpeedEnchantment.class)
public class MixinSoulSpeedEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.soulSpeed.isEnabled.get() == false) {
			return 3;
		}
		else return Config.soulSpeed.maxLevel.get();

	}
}
