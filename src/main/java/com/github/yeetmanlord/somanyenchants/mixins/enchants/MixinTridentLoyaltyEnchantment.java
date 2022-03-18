package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.TridentLoyaltyEnchantment;

@Mixin(TridentLoyaltyEnchantment.class)
public class MixinTridentLoyaltyEnchantment {
	@Overwrite
	public int getMaxLevel() {

		if (Config.loyalty.isEnabled.get() == false) {
			return 3;
		}
		else return Config.loyalty.maxLevel.get();

	}
}
