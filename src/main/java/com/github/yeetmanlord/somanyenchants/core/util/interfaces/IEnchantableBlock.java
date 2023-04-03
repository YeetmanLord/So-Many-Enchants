package com.github.yeetmanlord.somanyenchants.core.util.interfaces;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.ListNBT;

public interface IEnchantableBlock
{
	ListNBT getEnchantments();
	void addEnchantment(Enchantment ench, short lvl);
}
