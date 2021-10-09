package com.yeetmanlord.somanyenchants.core.config.so_many_enchants;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class Flight extends EnchantmentConfig
{
	
	public Flight(int absMax) {
		super(absMax);
		// TODO Auto-generated constructor stub
	}


	protected static String name = "Flight";
	protected static int max = 3;
	
	
	public void init(Builder builder) 
	{
		builder.push(name);
		
		this.isEnabled = builder
				.comment(" Whether this enchantment is used")
				.define("isEnabled", false);
		
		this.maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 10);
		
		builder.pop();
	}

}
