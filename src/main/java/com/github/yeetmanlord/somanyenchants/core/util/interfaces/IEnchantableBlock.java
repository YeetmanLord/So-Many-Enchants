package com.github.yeetmanlord.somanyenchants.core.util.interfaces;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.nbt.ListTag;

public interface IEnchantableBlock
{
	ListTag getEnchantments();
	void addEnchantment(Enchantment ench, short lvl);
}
