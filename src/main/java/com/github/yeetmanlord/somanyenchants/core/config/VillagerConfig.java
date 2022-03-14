package com.github.yeetmanlord.somanyenchants.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class VillagerConfig {

	public ForgeConfigSpec.BooleanValue isEnabled;

	public VillagerConfig() {
		this.init(Config.builder);
	}

	private void init(ForgeConfigSpec.Builder builder) 
	{
		builder.push("Enchanter Villager");
		
		this.isEnabled = builder
				.comment(" Whether the enchanter villager is enabled")
				.define("isEnabled", true);
		
		builder.pop();
		
	}

}
