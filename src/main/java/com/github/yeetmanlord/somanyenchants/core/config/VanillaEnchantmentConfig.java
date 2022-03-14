package com.github.yeetmanlord.somanyenchants.core.config;

public class VanillaEnchantmentConfig extends EnchantmentConfig {
	public int vanilla;

	public VanillaEnchantmentConfig(int absMax, String name, int normal, int vanilla) 
	{
		super(absMax, name, normal, true);
		this.vanilla = vanilla;
	}

}
