package com.github.yeetmanlord.somanyenchants.core.config;

import java.io.IOException;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class ConfigValue<Type> {

	protected Type internal;

	private Type def;

	protected String key;

	protected String valueName;

	public ConfigValue(String key, String valueName, Type def) {

		this.def = def;
		this.internal = def;
		this.key = key;
		this.valueName = valueName;

	}

	public Type get() {

		return internal;

	}

	@OnlyIn(Dist.CLIENT)
	public abstract void set(Type value) throws IOException;

	@OnlyIn(Dist.CLIENT)
	public void setDefault() throws IOException {

		this.set(def);

	}

	public void setNoUpdate(Type value) {

		this.internal = value;

	}

	public Type getDefault() {

		return def;

	}

}
