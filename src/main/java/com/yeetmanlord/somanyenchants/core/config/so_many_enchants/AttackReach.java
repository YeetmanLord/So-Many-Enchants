package com.yeetmanlord.somanyenchants.core.config.so_many_enchants;

import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class AttackReach extends EnchantmentConfig
{
	
	public AttackReach(int absMax)
	{
		super(absMax);
	}

	protected static String name = "Attack Reach";
	protected static int max = 1;
	
	
	
	public void init(Builder builder)
	{
		
		builder.push(name);
		
		this.isEnabled = builder
				.comment(" Whether this enchantment is used")
				.define("isEnabled", false);
		
		this.maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 3);
		
		builder.pop();
	}

}
