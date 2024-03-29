package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class AttackReachEnchant extends ModEnchantment {

	public AttackReachEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentCategory.WEAPON, Config.attackReach, slots);

	}

	@Override
	public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {

		return false;

	}

	@Override
	public boolean canEnchant(@NotNull ItemStack stack) {

		boolean flag = stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem || EnchantmentCategory.WEAPON.canEnchant(stack.getItem());
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
	protected boolean checkCompatibility(@NotNull Enchantment ench) {

		return super.checkCompatibility(ench) && ench != EnchantmentInit.BLOCK_REACH.get();

	}

	@Override
	public boolean isTradeable() {

		return false;

	}

}
