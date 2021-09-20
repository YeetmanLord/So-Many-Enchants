package com.yeetmanlord.somanyenchants.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EnchantmentConfig 
{
	public static ForgeConfigSpec.IntValue maxLevel;
	public static ForgeConfigSpec.BooleanValue isEnabled;
	
	public static int absoluteMax;
	
	@SuppressWarnings("static-access")
	public EnchantmentConfig(int absMax)
	{
		this.absoluteMax = absMax;
		init(Config.builder);
	}
	
	public void init(ForgeConfigSpec.Builder builder)
	{
		return;
	}
}
