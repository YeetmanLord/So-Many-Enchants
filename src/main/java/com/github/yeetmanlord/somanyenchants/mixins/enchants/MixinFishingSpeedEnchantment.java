package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.FishingSpeedEnchantment;

@Mixin(FishingSpeedEnchantment.class)
public class MixinFishingSpeedEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.lure.isEnabled.get() == false) {
			return 3;
		}
		else return Config.lure.maxLevel.get();

	}
}
