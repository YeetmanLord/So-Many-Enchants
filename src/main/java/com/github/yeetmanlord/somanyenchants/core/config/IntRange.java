package com.github.yeetmanlord.somanyenchants.core.config;

import java.io.IOException;

public class IntRange extends ConfigValue<Integer> {

	private int upperBound;

	private int lowerBound;

	public IntRange(String key, String valueName, int def, int upperBound, int lowerBound) {

		super(key, valueName, def);

		this.upperBound = upperBound;
		this.lowerBound = lowerBound;

	}

	@Override
	public void set(Integer value) throws IOException {

		if (value < lowerBound) {
			value = lowerBound;
		}
		else if (value > upperBound) {
			value = upperBound;
		}

		this.internal = value;
		Config.sendChanges();

	}

}
