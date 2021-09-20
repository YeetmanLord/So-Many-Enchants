package com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class ProtectionEnchantments extends EnchantmentConfig
{
	
	public ProtectionEnchantments(int absMax) {
		super(absMax);
		// TODO Auto-generated constructor stub
	}


	protected static String name = "Protection Enchantments";
	protected static int max = 10;
	protected static int vanilla = 4;
	
	
	public void init(Builder builder) 
	{
		builder.comment(" Contains Protection, Fire Protection, Blast Protection, Feather Falling, and Bane Of Arthropods").push(name);
		
		isEnabled = builder
				.comment(" Whether the mod changes these enchantments")
				.define("isEnabled", true);
		
		maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 10);
		
		builder.pop();
	}
}
