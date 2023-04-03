package com.github.yeetmanlord.somanyenchants.data.client;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

	public ItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, SoManyEnchants.MOD_ID, existingFileHelper);
		
	}

	@Override
	protected void registerModels() {
		withExistingParent("enchanted_chest", modLoc("block/enchanted_chest"));
		withExistingParent("enchanted_hopper", mcLoc("item/hopper"));
		withExistingParent("enchanted_trapped_chest", modLoc("block/enchanted_trapped_chest"));
		withExistingParent("hidden_enchanted_trapped_chest", modLoc("block/hidden_enchanted_trapped_chest"));
		withExistingParent("enchanted_furnace", mcLoc("block/furnace"));
		withExistingParent("enchanted_blast_furnace", mcLoc("block/blast_furnace"));
		withExistingParent("enchanted_smoker", mcLoc("block/smoker"));
		
		ModelFile shulkerBox = getExistingFile(modLoc("item/template_shulker_block"));
		
		getBuilder("enchanted_shulker_box").parent(shulkerBox).texture("0", "blocks/shulkers/shulker").texture("particle", "minecraft:block/shulker_box");
		
		for (DyeColor color : DyeColor.values()) {
			String fileName = "enchanted_" + color.getName() + "_shulker_box";
			String texture = "blocks/shulkers/shulker_" + color.getName();
			String particle = "minecraft:block/" + color.getName() + "_shulker_box";
			
			getBuilder(fileName).parent(shulkerBox).texture("0", texture).texture("particle", particle);
		}
	}

}
