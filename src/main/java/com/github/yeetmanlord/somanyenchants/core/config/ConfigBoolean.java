package com.github.yeetmanlord.somanyenchants.core.config;

import java.io.IOException;

public class ConfigBoolean extends ConfigValue<Boolean> {

	public ConfigBoolean(String key, String valueName, boolean def) {

		super(key, valueName, def);

	}

	@Override
	public void set(Boolean value) throws IOException {

		this.internal = value;
		Config.sendChanges();

	}

}
