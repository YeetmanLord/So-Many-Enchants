package com.yeetmanlord.somanyenchants.core.config.so_many_enchants;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class CatVision extends EnchantmentConfig
{
	
	public CatVision(int absMax) {
		super(absMax);
		// TODO Auto-generated constructor stub
	}


	protected static String name = "Cat Vision";
	protected static int max = 1;
	
	
	public void init(Builder builder) 
	{
		builder.push(name);
		
		this.isEnabled = builder
				.comment(" Whether this enchantment is used")
				.define("isEnabled", true);
		
		this.maxLevel = (IntValue) builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 1);
		
		builder.pop();
	}

}
