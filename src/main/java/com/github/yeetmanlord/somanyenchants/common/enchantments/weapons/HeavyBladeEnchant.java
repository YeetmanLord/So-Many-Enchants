package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HeavyBladeEnchant extends ModEnchantment {

	public HeavyBladeEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentCategory.WEAPON, Config.heavyBlade, slots);

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return this.category.canEnchant(stack.getItem());

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		boolean flag = canApplyAtEnchantingTable(stack) || stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem;
		return flag && this.config.isEnabled.get();

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 15 + (enchantmentLevel - 1) * 9;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return super.getMinCost(enchantmentLevel) + 50;

	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {

		return ench == EnchantmentInit.LIGHT_BLADE.get() || ench instanceof DamageEnchantment || ench == EnchantmentInit.ATTACK_REACH.get() || ench == EnchantmentInit.BLOCK_REACH.get() ? false : super.checkCompatibility(ench);

	}

	@Override
	public float getDamageBonus(int level, MobType creatureType) {

		return (float) (Math.max(0, level - 1) * 2 + 4);

	}

}
