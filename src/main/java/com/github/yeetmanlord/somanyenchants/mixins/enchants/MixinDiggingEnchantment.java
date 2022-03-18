package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.DiggingEnchantment;

@Mixin(DiggingEnchantment.class)
public class MixinDiggingEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.efficiency.isEnabled.get() == false) {
			return 5;
		}
		else return Config.efficiency.maxLevel.get();

	}

}
