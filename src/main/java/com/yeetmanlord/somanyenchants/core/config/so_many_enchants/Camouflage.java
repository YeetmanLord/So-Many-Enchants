package com.yeetmanlord.somanyenchants.core.config.so_many_enchants;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class Camouflage extends EnchantmentConfig {

	public Camouflage(int absMax) {
		super(absMax);
	}


	protected static String name = "Camouflage";
	protected static int max = 1;
	
	
	public void init(Builder builder) 
	{
		builder.push(name);
		
		this.isEnabled = builder
				.comment(" Whether this enchantment is used")
				.define("isEnabled", true);
		
		this.maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 1);
		
		builder.pop();
	}

}
