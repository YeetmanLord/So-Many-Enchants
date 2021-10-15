package com.yeetmanlord.somanyenchants.core.init;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Items;

public class EnchantmentTypesInit {
	
	public static final EnchantmentType HOPPER = EnchantmentType.create("hopper", item -> item.equals(Items.HOPPER));

}
