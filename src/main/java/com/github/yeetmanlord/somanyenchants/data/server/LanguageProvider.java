package com.github.yeetmanlord.somanyenchants.data.server;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.enchantments.EffectEnchantment;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {

	public LanguageProvider(DataGenerator gen, String locale) {
		super(gen, SoManyEnchants.MOD_ID, locale);
	}

	@Override
	protected void addTranslations() {
		for (RegistryObject<Enchantment> entry : EnchantmentInit.ENCHANTMENTS.getEntries()) {
			Enchantment ench = entry.get();
			if (ench instanceof EffectEnchantment) {
				String name = entry.getId().getPath();
				name = name.replace("_", " ");
				add(ench, toTitleCase(name));
				add("so_many_enchants.screen.config.max_level." + toCammelCase(name),
						toTitleCase(name) + " | Max Level");
			}
		}
	}

	private String toTitleCase(String name) {
		String title = "";
		String[] parts = name.split(" ");
		for (String s : parts) {
			String first = String.valueOf(s.charAt(0));
			s = s.replaceFirst(first, first.toUpperCase());
			title += s + " ";
		}
		title = title.trim();
		return title;
	}

	private String toCammelCase(String name) {
		String cammel = "";
		String[] parts = name.split(" ");
		for (String s : parts) {
			String first = String.valueOf(s.charAt(0));
			s = s.replaceFirst(first, first.toUpperCase());
			cammel += s;
		}
		cammel = cammel.replaceFirst(String.valueOf(cammel.charAt(0)), String.valueOf(cammel.charAt(0)).toLowerCase());
		cammel = cammel.trim();
		return cammel;
	}

}
