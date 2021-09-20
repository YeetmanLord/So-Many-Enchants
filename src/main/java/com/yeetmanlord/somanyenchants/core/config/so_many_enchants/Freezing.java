package com.yeetmanlord.somanyenchants.core.config.so_many_enchants;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class Freezing extends EnchantmentConfig
{
	
	public Freezing(int absMax) {
		super(absMax);
		// TODO Auto-generated constructor stub
	}


	protected static String name = "Freezing";
	protected static int max = 3;
	
	
	public void init(Builder builder) 
	{
		builder.push(name);
		
		isEnabled = builder
				.comment(" Whether this enchantment is used")
				.define("isEnabled", true);
		
		maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 10);
		
		builder.pop();
	}

}
