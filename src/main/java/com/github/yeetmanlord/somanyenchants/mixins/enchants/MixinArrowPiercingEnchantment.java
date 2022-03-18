package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.ArrowPiercingEnchantment;

@Mixin(ArrowPiercingEnchantment.class)
public class MixinArrowPiercingEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.piercing.isEnabled.get() == false) {
			return 3;
		}
		else return Config.piercing.maxLevel.get();

	}

}
