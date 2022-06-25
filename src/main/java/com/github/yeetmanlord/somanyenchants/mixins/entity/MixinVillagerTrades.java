package com.github.yeetmanlord.somanyenchants.mixins.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.npc.VillagerTrades;

@Mixin(VillagerTrades.EnchantBookForEmeralds.class)
public class MixinVillagerTrades {
	@Shadow
	private int villagerXp;

	@ModifyVariable(method = "getOffer", at = @At("STORE"), ordinal = 0)
	public int modifyMaxEnchantLevel(int i) {
		if (i > 5 && Config.villager.isEnabled.get()) {
			return 5;
		}
		return i;
	}

}
