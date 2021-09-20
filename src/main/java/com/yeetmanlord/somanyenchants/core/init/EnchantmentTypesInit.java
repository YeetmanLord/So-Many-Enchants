package com.yeetmanlord.somanyenchants.core.init;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;

public class EnchantmentTypesInit {
	
	private static java.util.function.Predicate<Item> delegate;
	
	private EnchantmentTypesInit(java.util.function.Predicate<Item> delegate)
	{
		EnchantmentTypesInit.delegate = delegate;
	}
	
	public static final EnchantmentType HOPPER = EnchantmentType.create("hopper", delegate);
			
	public boolean canEnchantItem(Item itemIn) {
	      return EnchantmentTypesInit.delegate == null ? false : EnchantmentTypesInit.delegate.test(itemIn);
	}

}
