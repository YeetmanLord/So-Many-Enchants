package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.FireAspectEnchantment;

@Mixin(FireAspectEnchantment.class)
public class MixinFireAspectEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.fireAspect.isEnabled.get() == false) {
			return 2;
		}
		else return Config.fireAspect.maxLevel.get();

	}
}
