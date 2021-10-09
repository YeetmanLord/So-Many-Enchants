package com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class LootBonusEnchantments extends EnchantmentConfig
{
	
	public LootBonusEnchantments(int absMax) {
		super(absMax);
		// TODO Auto-generated constructor stub
	}


	protected static String name = "Loot Bonus Enchantments";
	protected static int max = 10;
	protected static int vanilla = 3;
	
	
	public void init(Builder builder) 
	{
		builder.comment(" Contains Looting, Fortune, and Luck of the Sea").push(name);
		
		this.isEnabled = builder
				.comment(" Whether the mod changes these enchantments")
				.define("isEnabled", true);
		
		this.maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 10);
		
		builder.pop();
	}

}
