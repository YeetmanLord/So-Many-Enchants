package com.yeetmanlord.somanyenchants.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EnchantmentConfig 
{
	public ForgeConfigSpec.IntValue maxLevel;
	public ForgeConfigSpec.BooleanValue isEnabled;
	
	public int absoluteMax;

	public EnchantmentConfig(int absMax)
	{
		absoluteMax = absMax;
		init(Config.builder);
	}
	
	public void init(ForgeConfigSpec.Builder builder)
	{
		return;
	}
}
