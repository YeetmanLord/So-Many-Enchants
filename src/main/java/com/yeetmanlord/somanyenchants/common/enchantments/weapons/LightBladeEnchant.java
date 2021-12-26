package com.yeetmanlord.somanyenchants.common.enchantments.weapons;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;

public class LightBladeEnchant extends Enchantment {

	public LightBladeEnchant(Rarity rarityIn, EquipmentSlotType... slots) {
		super(rarityIn, EnchantmentType.WEAPON, slots);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return this.type.canEnchantItem(stack.getItem());
	}

	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true
				: super.canApply(stack);
	}

	@Override
	public boolean canVillagerTrade() {
		return true;
	}

	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}

	public int getMinEnchantability(int enchantmentLevel) {
		return 15 + (enchantmentLevel - 1) * 9;
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

	@Override
	public int getMaxLevel() {
		if (Config.lightBlade.isEnabled.get() == false) {
			return 0;
		} else
			return Config.lightBlade.maxLevel.get();
	}

	@Override
	public boolean canGenerateInLoot() {
		return true;
	}

	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return ench == EnchantmentInit.HEAVY_BLADE.get() || ench instanceof DamageEnchantment
				|| ench == EnchantmentInit.ATTACK_REACH.get() || ench == EnchantmentInit.BLOCK_REACH.get()
						? false : super.canApplyTogether(ench);
	}
	
	@Override
	public float calcDamageByCreature(int level, CreatureAttribute creatureType) 
	{
		return (float) (((Math.max(0, level - 1)) * -.5) - 1);
	}

}
