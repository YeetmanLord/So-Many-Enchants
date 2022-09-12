package com.github.yeetmanlord.somanyenchants.core.config;

public class EnchantmentConfig {

	public IntRange maxLevel;

	public ConfigBoolean isEnabled;

	public int absoluteMax;

	public int normal;

	public String name;

	public boolean enabled;

	public EnchantmentConfig(int absMax, String name, int normal, boolean enabled) {

		this.enabled = enabled;
		this.name = name;
		absoluteMax = absMax;
		this.normal = normal;
		maxLevel = new IntRange(name, "maxLevel", normal, absMax, 1);
		this.isEnabled = new ConfigBoolean(name, "isEnabled", enabled);
		Config.configSections.put(name, this);

	}

}
