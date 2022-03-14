package com.github.yeetmanlord.somanyenchants.data.client;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModelProvider extends net.minecraftforge.client.model.generators.BlockModelProvider {

	public BlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, SoManyEnchants.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		getBuilder("enchanted_chest").texture("particle", mcLoc("block/oak_planks"));
		getBuilder("enchanted_trapped_chest").texture("particle", mcLoc("block/oak_planks"));
		getBuilder("hidden_enchanted_trapped_chest").texture("particle", mcLoc("block/oak_planks"));
	}

}
