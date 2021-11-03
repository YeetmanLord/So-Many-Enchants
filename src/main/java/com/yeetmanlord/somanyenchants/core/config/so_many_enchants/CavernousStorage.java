package com.yeetmanlord.somanyenchants.core.config.so_many_enchants;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class CavernousStorage extends EnchantmentConfig {

	public CavernousStorage(int absMax) {
		super(absMax);
	}


	protected static String name = "Cavernous Storage";
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
