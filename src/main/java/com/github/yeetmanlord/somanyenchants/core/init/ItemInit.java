package com.github.yeetmanlord.somanyenchants.core.init;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	private static Item.Properties p = new Item.Properties().stacksTo(1);

	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SoManyEnchants.MOD_ID);

	public static final RegistryObject<BlockItem> ENCHANTED_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_WHITE_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_white_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_WHITE_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_ORANGE_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_orange_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_ORANGE_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_MAGENTA_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_magenta_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_MAGENTA_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_LIGHT_BLUE_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_light_blue_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_LIGHT_BLUE_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_YELLOW_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_yellow_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_YELLOW_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_LIME_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_lime_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_LIME_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_PINK_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_pink_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_PINK_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_GRAY_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_gray_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_GRAY_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_LIGHT_GRAY_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_light_gray_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_LIGHT_GRAY_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_CYAN_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_cyan_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_CYAN_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_PURPLE_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_purple_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_PURPLE_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_BLUE_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_blue_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_BLUE_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_BROWN_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_brown_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_BROWN_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_GREEN_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_green_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_GREEN_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_RED_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_red_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_RED_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_BLACK_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_black_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_BLACK_SHULKER_BOX.get(), p));

	public static final RegistryObject<BlockItem> ENCHANTED_FURNACE = BLOCK_ITEMS.register("enchanted_furnace", () -> new BlockItem(BlockInit.ENCHANTED_FURNACE.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> ENCHANTED_BLAST_FURNACE = BLOCK_ITEMS.register("enchanted_blast_furnace", () -> new BlockItem(BlockInit.ENCHANTED_BLAST_FURNACE.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> ENCHANTED_SMOKER = BLOCK_ITEMS.register("enchanted_smoker", () -> new BlockItem(BlockInit.ENCHANTED_SMOKER.get(), new Item.Properties()));

}
