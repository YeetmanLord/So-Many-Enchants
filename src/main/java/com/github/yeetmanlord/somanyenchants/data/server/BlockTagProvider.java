package com.github.yeetmanlord.somanyenchants.data.server;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagProvider extends BlockTagsProvider {

	public BlockTagProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, SoManyEnchants.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		tag(BlockTags.PICKAXE_MINABLES).add(BlockInit.ENCHANTED_BLACK_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_BLAST_FURNACE.get(), BlockInit.ENCHANTED_BLUE_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_FURNACE.get(), BlockInit.ENCHANTED_BROWN_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_CYAN_SHULKER_BOX.get(), BlockInit.ENCHANTED_GRAY_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_GREEN_SHULKER_BOX.get(), BlockInit.ENCHANTED_HOPPER.get(),
				BlockInit.ENCHANTED_LIGHT_BLUE_SHULKER_BOX.get(), BlockInit.ENCHANTED_LIGHT_GRAY_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_LIME_SHULKER_BOX.get(), BlockInit.ENCHANTED_MAGENTA_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_ORANGE_SHULKER_BOX.get(), BlockInit.ENCHANTED_PINK_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_PURPLE_SHULKER_BOX.get(), BlockInit.ENCHANTED_RED_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_SMOKER.get(), BlockInit.ENCHANTED_WHITE_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_YELLOW_SHULKER_BOX.get());

		tag(BlockTags.AXE_MINABLES).add(BlockInit.ENCHANTED_BARREL.get(), BlockInit.ENCHANTED_CHEST.get(),
				BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get(), BlockInit.TRAPPED_ENCHANTED_CHEST.get());

		tag(BlockTags.SHULKER_BOXES).add(BlockInit.ENCHANTED_BLACK_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_BLUE_SHULKER_BOX.get(), BlockInit.ENCHANTED_BROWN_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_CYAN_SHULKER_BOX.get(), BlockInit.ENCHANTED_GRAY_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_GREEN_SHULKER_BOX.get(), BlockInit.ENCHANTED_LIGHT_BLUE_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_LIGHT_GRAY_SHULKER_BOX.get(), BlockInit.ENCHANTED_LIME_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_MAGENTA_SHULKER_BOX.get(), BlockInit.ENCHANTED_ORANGE_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_PINK_SHULKER_BOX.get(), BlockInit.ENCHANTED_PURPLE_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_RED_SHULKER_BOX.get(), BlockInit.ENCHANTED_WHITE_SHULKER_BOX.get(),
				BlockInit.ENCHANTED_YELLOW_SHULKER_BOX.get());
	}

	public static final class BlockTags {
		public static final ITag.INamedTag<Block> PICKAXE_MINABLES = minecraftTag("mineable/pickaxe");
		public static final ITag.INamedTag<Block> AXE_MINABLES = minecraftTag("mineable/axe");
		public static final ITag.INamedTag<Block> SHULKER_BOXES = minecraftTag("shulker_boxes");

		public static ITag.INamedTag<Block> minecraftTag(String path) {
			return (ITag.INamedTag<Block>)net.minecraft.tags.BlockTags.createOptional(new ResourceLocation("minecraft", path));
		}
	}

}
