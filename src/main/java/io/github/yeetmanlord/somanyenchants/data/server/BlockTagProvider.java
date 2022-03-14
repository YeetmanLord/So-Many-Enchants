package io.github.yeetmanlord.somanyenchants.data.server;

import io.github.yeetmanlord.somanyenchants.SoManyEnchants;
import io.github.yeetmanlord.somanyenchants.core.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
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
		public static final TagKey<Block> PICKAXE_MINABLES = minecraftTag("mineable/pickaxe");
		public static final TagKey<Block> AXE_MINABLES = minecraftTag("mineable/axe");
		public static final TagKey<Block> SHULKER_BOXES = minecraftTag("shulker_boxes");

		public static TagKey<Block> minecraftTag(String path) {
			return net.minecraft.tags.BlockTags.create(new ResourceLocation("minecraft", path));
		}
	}

}
