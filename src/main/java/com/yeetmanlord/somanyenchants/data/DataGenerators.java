package com.yeetmanlord.somanyenchants.data;

import com.yeetmanlord.somanyenchants.data.client.BlockModelProvider;
import com.yeetmanlord.somanyenchants.data.client.ItemModelProvider;
import com.yeetmanlord.somanyenchants.data.server.BlockTagProvider;
import com.yeetmanlord.somanyenchants.data.server.LootTableProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

//@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.MOD)
public class DataGenerators {
	
//	@SubscribeEvent
	public static void gatherData(final GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		
		gen.addProvider(new ItemModelProvider(gen, fileHelper));
		gen.addProvider(new BlockTagProvider(gen, fileHelper));
		gen.addProvider(new BlockModelProvider(gen, fileHelper));
		gen.addProvider(new LootTableProvider(gen));
//		gen.addProvider(new LanguageProvider(gen, "en_us"));
	}

}
