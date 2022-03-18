package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.QuickChargeEnchantment;

@Mixin(QuickChargeEnchantment.class)
public class MixinQuickChargeEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.quickCharge.isEnabled.get() == false) {
			return 3;
		}
		else return Config.quickCharge.maxLevel.get();

	}
}
