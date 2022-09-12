package com.github.yeetmanlord.somanyenchants.core.config;

public class VillagerConfig {

	public ConfigValue<Boolean> isEnabled;

	public VillagerConfig() {

		isEnabled = new ConfigBoolean("Villager", "isEnabled", true);

	}

}
