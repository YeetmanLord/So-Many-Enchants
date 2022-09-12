package com.github.yeetmanlord.somanyenchants.common.enchantments;

import com.github.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EffectEnchantment extends ModEnchantment {

	private final MobEffect effect;

	private final boolean canEnchant;

	public EffectEnchantment(MobEffect effect, EnchantmentConfig config, Rarity rarity, EquipmentSlot... equipment) {

		this(effect, config, false, rarity, equipment);

	}

	public EffectEnchantment(MobEffect effect, EnchantmentConfig config, boolean canEnchant, Rarity rarity, EquipmentSlot... equipment) {

		super(rarity, EnchantmentCategory.ARMOR, config, equipment);
		this.effect = effect;
		this.config = config;
		this.canEnchant = true;

	}

	public void applyEffect(Player player, int level) {

		if (this.config.isEnabled.get()) {
			player.addEffect(new MobEffectInstance(effect, 600, level - 1, false, false, false));
		}

	}

	@Override
	public boolean isCurse() {

		return !effect.isBeneficial();

	}

	@Override
	public int getMaxCost(int level) {

		return getMinCost(level) + 20;

	}

	@Override
	public int getMinCost(int level) {

		return level * 8;

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		if (canEnchant) {
			return super.canApplyAtEnchantingTable(stack);
		}

		return false;

	}

}
