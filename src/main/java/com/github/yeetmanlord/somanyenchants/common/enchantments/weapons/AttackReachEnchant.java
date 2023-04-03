package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;

public class AttackReachEnchant extends ModEnchantment {

	public AttackReachEnchant(Rarity rarityIn, EquipmentSlotType... slots) {

		super(rarityIn, EnchantmentType.WEAPON, Config.attackReach, slots);

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return false;

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		boolean flag = stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true : EnchantmentType.WEAPON.canEnchant(stack.getItem());
		return flag && this.config.isEnabled.get();

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 10 + (enchantmentLevel - 1) * 12;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 25;

	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {

		return super.checkCompatibility(ench) && ench != EnchantmentInit.BLOCK_REACH.get();

	}

	@Override
	public boolean isTradeable() {

		return false;

	}

}
