package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	private static Item.Properties p = new Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(1);
	
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<BlockItem> ENCHANTED_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_shulker_box",
			() -> new BlockItem(BlockInit.ENCHANTED_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_WHITE_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_white_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_WHITE_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_ORANGE_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_orange_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_ORANGE_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_MAGENTA_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_magenta_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_MAGENTA_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_LIGHT_BLUE_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_light_blue_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_LIGHT_BLUE_SHULKER_BOX.get(), p));
			
	public static final RegistryObject<BlockItem> ENCHANTED_YELLOW_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_yellow_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_YELLOW_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_LIME_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_lime_shulker_box",
			() -> new BlockItem(BlockInit.ENCHANTED_LIME_SHULKER_BOX.get(), p));
			
	public static final RegistryObject<BlockItem> ENCHANTED_PINK_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_pink_shulker_box",
			() -> new BlockItem(BlockInit.ENCHANTED_PINK_SHULKER_BOX.get(), p));
			
	public static final RegistryObject<BlockItem> ENCHANTED_GRAY_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_gray_shulker_box",
			() -> new BlockItem(BlockInit.ENCHANTED_GRAY_SHULKER_BOX.get(), p));
			
	public static final RegistryObject<BlockItem> ENCHANTED_LIGHT_GRAY_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_light_gray_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_LIGHT_GRAY_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_CYAN_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_cyan_shulker_box",
			() -> new BlockItem(BlockInit.ENCHANTED_CYAN_SHULKER_BOX.get(), p));
			
	public static final RegistryObject<BlockItem> ENCHANTED_PURPLE_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_purple_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_PURPLE_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_BLUE_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_blue_shulker_box",
			() -> new BlockItem(BlockInit.ENCHANTED_BLUE_SHULKER_BOX.get(), p));
			
	public static final RegistryObject<BlockItem> ENCHANTED_BROWN_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_brown_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_BROWN_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_GREEN_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_green_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_GREEN_SHULKER_BOX.get(), p));
	public static final RegistryObject<BlockItem> ENCHANTED_RED_SHULKER_BOX = BLOCK_ITEMS.register("enchanted_red_shulker_box",
			() -> new BlockItem(BlockInit.ENCHANTED_RED_SHULKER_BOX.get(), p));
			
	public static final RegistryObject<BlockItem> ENCHANTED_BLACK_SHULKER_BOX = BLOCK_ITEMS.register(
			"enchanted_black_shulker_box", () -> new BlockItem(BlockInit.ENCHANTED_BLACK_SHULKER_BOX.get(), p));
	
	
	public static final DeferredRegister<Item> MINECRAFT_BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
	
	
	
	
	public static final RegistryObject<BlockItem> SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("shulker_box", () -> new BlockItem(BlockInit.SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> WHITE_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("white_shulker_box", () -> new BlockItem(BlockInit.WHITE_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> ORANGE_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("orange_shulker_box", () -> new BlockItem(BlockInit.ORANGE_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> MAGENTA_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("magenta_shulker_box", () -> new BlockItem(BlockInit.MAGENTA_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> LIGHT_BLUE_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("light_blue_shulker_box", () -> new BlockItem(BlockInit.LIGHT_BLUE_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> YELLOW_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("yellow_shulker_box", () -> new BlockItem(BlockInit.YELLOW_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> LIME_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("lime_shulker_box", () -> new BlockItem(BlockInit.LIME_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> PINK_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("pink_shulker_box", () -> new BlockItem(BlockInit.PINK_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> GRAY_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("gray_shulker_box", () -> new BlockItem(BlockInit.GRAY_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> LIGHT_GRAY_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("light_gray_shulker_box", () -> new BlockItem(BlockInit.LIGHT_GRAY_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> CYAN_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("cyan_shulker_box", () -> new BlockItem(BlockInit.CYAN_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> PURPLE_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("purple_shulker_box", () -> new BlockItem(BlockInit.PURPLE_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> BLUE_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("blue_shulker_box", () -> new BlockItem(BlockInit.BLUE_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> BROWN_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("brown_shulker_box", () -> new BlockItem(BlockInit.BROWN_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> GREEN_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("green_shulker_box", () -> new BlockItem(BlockInit.GREEN_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> RED_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("red_shulker_box", () -> new BlockItem(BlockInit.RED_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<BlockItem> BLACK_SHULKER_BOX = MINECRAFT_BLOCK_ITEMS.register("black_shulker_box", () -> new BlockItem(BlockInit.BLACK_SHULKER_BOX.get(), (new Item.Properties()).maxStackSize(1).group(ItemGroup.DECORATIONS)));
}
