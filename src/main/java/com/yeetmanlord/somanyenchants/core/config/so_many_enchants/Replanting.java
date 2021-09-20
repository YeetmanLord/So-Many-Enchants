package com.yeetmanlord.somanyenchants.core.config.so_many_enchants;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class Replanting extends EnchantmentConfig
{
	
	public Replanting(int absMax) {
		super(absMax);
		// TODO Auto-generated constructor stub
	}



	protected static String name = "Replanting";
	protected static int max = 1;
	
	
	
	public void init(Builder builder) 
	{
		builder.push(name);
		
		isEnabled = builder
				.comment(" Whether this enchantment is used")
				.define("isEnabled", true);
		
		maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 1);
		
		builder.pop();
	}

}
