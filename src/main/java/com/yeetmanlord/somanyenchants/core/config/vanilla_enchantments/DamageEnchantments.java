package com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class DamageEnchantments extends EnchantmentConfig
{
	
	public DamageEnchantments(int absMax) {
		super(absMax);
		// TODO Auto-generated constructor stub
	}


	protected static String name = "Damage Enchantments";
	protected static int max = 10;
	protected static int vanilla = 5;
	
	
	public void init(Builder builder) 
	{
		Main.LOGGER.info("baldjlkfjlasdk");
		
		builder.comment(" Contains Sharpness, Smite, and Bane Of Arthropods").push(name);
		
		isEnabled = builder
				.comment(" Whether the mod changes these enchantments")
				.define("isEnabled", true);
		
		maxLevel = builder
				.comment(" The maximum enchantment level")
				.defineInRange("maxLevel", max, 1, 10);
		
		builder.pop();
	}

}
