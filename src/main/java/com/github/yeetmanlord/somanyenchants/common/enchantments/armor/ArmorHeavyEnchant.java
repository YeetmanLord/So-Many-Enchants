package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ArmorHeavyEnchant extends ModEnchantment {

	public ArmorHeavyEnchant(Rarity rarityIn, EquipmentSlot[] slots) {

		super(rarityIn, EnchantmentCategory.ARMOR, Config.heavyArmor, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 25;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return super.getMinCost(enchantmentLevel) + 100;

	}

	@Override
	protected boolean checkCompatibility(@NotNull Enchantment ench) {

		return super.checkCompatibility(ench) && ench != EnchantmentInit.REINFORCED_ARMOR.get() && ench != EnchantmentInit.TEMPERED_ARMOR.get();

	}

}
