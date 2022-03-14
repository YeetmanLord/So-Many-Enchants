package com.github.yeetmanlord.somanyenchants.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EnchantmentConfig 
{
	public ForgeConfigSpec.IntValue maxLevel;
	public ForgeConfigSpec.BooleanValue isEnabled;
	
	public int absoluteMax;
	public int normal;
	public String name;
	public boolean enabled;

	public EnchantmentConfig(int absMax, String name, int normal, boolean enabled)
	{
		this.enabled = enabled;
		this.name = name;
		absoluteMax = absMax;
		this.normal = normal;
		init(Config.builder);
		Config.configSections.put(name, this);
	}
	
	public void init(ForgeConfigSpec.Builder builder)
	{		
		builder.push(name).comment("Enchantment configuration for " + this.name);
		
		this.isEnabled = builder
				.comment(" Whether the mod changes these enchantments")
				.define("isEnabled", enabled);
		
		this.maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", normal, 1, absoluteMax);
		
		builder.pop();
	}
}
