package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedBarrelBlock;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedHopper;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedTrappedChestBlock;
import com.yeetmanlord.somanyenchants.common.blocks.override.OverridenShulkerBoxBlock;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedShulkerBoxTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.overrides.OverridenShulkerBoxTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);

	public static final RegistryObject<Block> ENCHANTED_HOPPER = BLOCKS.register("enchanted_hopper",
			() -> new EnchantedHopper(Block.Properties.from(Blocks.HOPPER).setOpaque(BlockInit::isntSolid)
					.setBlocksVision(BlockInit::isntSolid).setSuffocates(BlockInit::isntSolid)));

	public static final RegistryObject<Block> ENCHANTED_CHEST = BLOCKS.register("enchanted_chest",
			() -> new EnchantedChestBlock(Block.Properties.from(Blocks.CHEST), () -> {
				return TileEntityTypeInit.ENCHANTED_CHEST.get();
			}));

	public static final RegistryObject<Block> ENCHANTED_BARREL = BLOCKS.register("enchanted_barrel",
			() -> new EnchantedBarrelBlock(Block.Properties.from(Blocks.BARREL)));

	public static final RegistryObject<Block> TRAPPED_ENCHANTED_CHEST = BLOCKS.register("enchanted_trapped_chest",
			() -> new EnchantedTrappedChestBlock(Block.Properties.from(Blocks.TRAPPED_CHEST), () -> {
				return TileEntityTypeInit.TRAPPED_ENCHANTED_CHEST.get();
			}, false));

	public static final RegistryObject<Block> HIDDEN_TRAPPED_ENCHANTED_CHEST = BLOCKS.register(
			"hidden_enchanted_trapped_chest",
			() -> new EnchantedTrappedChestBlock(Block.Properties.from(Blocks.TRAPPED_CHEST), () -> {
				return TileEntityTypeInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get();
			}, true));

	public static final RegistryObject<Block> ENCHANTED_SHULKER_BOX = BLOCKS.register("enchanted_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties((DyeColor) null,
					AbstractBlock.Properties.create(Material.SHULKER)));
	public static final RegistryObject<Block> ENCHANTED_WHITE_SHULKER_BOX = BLOCKS.register(
			"enchanted_white_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.WHITE,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.SNOW)));
	public static final RegistryObject<Block> ENCHANTED_ORANGE_SHULKER_BOX = BLOCKS.register(
			"enchanted_orange_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.ORANGE,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.ADOBE)));
	public static final RegistryObject<Block> ENCHANTED_MAGENTA_SHULKER_BOX = BLOCKS.register(
			"enchanted_magenta_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.MAGENTA,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.MAGENTA)));
	public static final RegistryObject<Block> ENCHANTED_LIGHT_BLUE_SHULKER_BOX = BLOCKS.register(
			"enchanted_light_blue_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.LIGHT_BLUE,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIGHT_BLUE)));
	public static final RegistryObject<Block> ENCHANTED_YELLOW_SHULKER_BOX = BLOCKS.register(
			"enchanted_yellow_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.YELLOW,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.YELLOW)));
	public static final RegistryObject<Block> ENCHANTED_LIME_SHULKER_BOX = BLOCKS.register("enchanted_lime_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.LIME,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIME)));
	public static final RegistryObject<Block> ENCHANTED_PINK_SHULKER_BOX = BLOCKS.register("enchanted_pink_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.PINK,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.PINK)));
	public static final RegistryObject<Block> ENCHANTED_GRAY_SHULKER_BOX = BLOCKS.register("enchanted_gray_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.GRAY,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.GRAY)));
	public static final RegistryObject<Block> ENCHANTED_LIGHT_GRAY_SHULKER_BOX = BLOCKS.register(
			"enchanted_light_gray_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.LIGHT_GRAY,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIGHT_GRAY)));
	public static final RegistryObject<Block> ENCHANTED_CYAN_SHULKER_BOX = BLOCKS.register("enchanted_cyan_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.CYAN,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.CYAN)));
	public static final RegistryObject<Block> ENCHANTED_PURPLE_SHULKER_BOX = BLOCKS.register(
			"enchanted_purple_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.PURPLE,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.PURPLE_TERRACOTTA)));
	public static final RegistryObject<Block> ENCHANTED_BLUE_SHULKER_BOX = BLOCKS.register("enchanted_blue_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.BLUE,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BLUE)));
	public static final RegistryObject<Block> ENCHANTED_BROWN_SHULKER_BOX = BLOCKS.register(
			"enchanted_brown_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.BROWN,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BROWN)));
	public static final RegistryObject<Block> ENCHANTED_GREEN_SHULKER_BOX = BLOCKS.register(
			"enchanted_green_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.GREEN,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.GREEN)));
	public static final RegistryObject<Block> ENCHANTED_RED_SHULKER_BOX = BLOCKS.register("enchanted_red_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.RED,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.RED)));
	public static final RegistryObject<Block> ENCHANTED_BLACK_SHULKER_BOX = BLOCKS.register(
			"enchanted_black_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.BLACK,
					AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BLACK)));

	private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}

	private static EnchantedShulkerBoxBlock createEnchantedShulkerBoxFromColorAndProperties(DyeColor color,
			AbstractBlock.Properties properties) {
		AbstractBlock.IPositionPredicate abstractblock$ipositionpredicate = (state, reader, pos) -> {
			TileEntity tileentity = reader.getTileEntity(pos);
			if (!(tileentity instanceof EnchantedShulkerBoxTileEntity)) {
				return true;
			} else {
				EnchantedShulkerBoxTileEntity shulkerboxtileentity = (EnchantedShulkerBoxTileEntity) tileentity;
				return shulkerboxtileentity.isVisuallyClosed();
			}
		};
		return new EnchantedShulkerBoxBlock(color, properties.hardnessAndResistance(2.0F).variableOpacity().notSolid()
				.setSuffocates(abstractblock$ipositionpredicate).setBlocksVision(abstractblock$ipositionpredicate));
	}
	
	
	public static final DeferredRegister<Block> MINECRAFT_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");
	
	public static final RegistryObject<Block> SHULKER_BOX = MINECRAFT_BLOCKS.register("shulker_box", () -> createShulkerBoxFromColorAndProperties((DyeColor)null, AbstractBlock.Properties.create(Material.SHULKER)));
	public static final RegistryObject<Block> WHITE_SHULKER_BOX = MINECRAFT_BLOCKS.register("white_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.WHITE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.SNOW)));
	public static final RegistryObject<Block> ORANGE_SHULKER_BOX = MINECRAFT_BLOCKS.register("orange_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.ORANGE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.ADOBE)));
	public static final RegistryObject<Block> MAGENTA_SHULKER_BOX = MINECRAFT_BLOCKS.register("magenta_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.MAGENTA, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.MAGENTA)));
	public static final RegistryObject<Block> LIGHT_BLUE_SHULKER_BOX = MINECRAFT_BLOCKS.register("light_blue_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIGHT_BLUE)));
	public static final RegistryObject<Block> YELLOW_SHULKER_BOX = MINECRAFT_BLOCKS.register("yellow_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.YELLOW, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.YELLOW)));
	public static final RegistryObject<Block> LIME_SHULKER_BOX = MINECRAFT_BLOCKS.register("lime_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.LIME, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIME)));
	public static final RegistryObject<Block> PINK_SHULKER_BOX = MINECRAFT_BLOCKS.register("pink_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.PINK, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.PINK)));
	public static final RegistryObject<Block> GRAY_SHULKER_BOX = MINECRAFT_BLOCKS.register("gray_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.GRAY, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.GRAY)));
	public static final RegistryObject<Block> LIGHT_GRAY_SHULKER_BOX = MINECRAFT_BLOCKS.register("light_gray_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIGHT_GRAY)));
	public static final RegistryObject<Block> CYAN_SHULKER_BOX = MINECRAFT_BLOCKS.register("cyan_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.CYAN, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.CYAN)));
	public static final RegistryObject<Block> PURPLE_SHULKER_BOX = MINECRAFT_BLOCKS.register("purple_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.PURPLE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.PURPLE_TERRACOTTA)));
	public static final RegistryObject<Block> BLUE_SHULKER_BOX = MINECRAFT_BLOCKS.register("blue_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.BLUE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BLUE)));
	public static final RegistryObject<Block> BROWN_SHULKER_BOX = MINECRAFT_BLOCKS.register("brown_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.BROWN, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BROWN)));
	public static final RegistryObject<Block> GREEN_SHULKER_BOX = MINECRAFT_BLOCKS.register("green_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.GREEN, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.GREEN)));
	public static final RegistryObject<Block> RED_SHULKER_BOX = MINECRAFT_BLOCKS.register("red_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.RED, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.RED)));
	public static final RegistryObject<Block> BLACK_SHULKER_BOX = MINECRAFT_BLOCKS.register("black_shulker_box", () -> createShulkerBoxFromColorAndProperties(DyeColor.BLACK, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BLACK)));
	
	
	private static OverridenShulkerBoxBlock createShulkerBoxFromColorAndProperties(DyeColor color,
			AbstractBlock.Properties properties) {
		AbstractBlock.IPositionPredicate abstractblock$ipositionpredicate = (state, reader, pos) -> {
			TileEntity tileentity = reader.getTileEntity(pos);
			if (!(tileentity instanceof OverridenShulkerBoxTileEntity)) {
				return true;
			} else {
				OverridenShulkerBoxTileEntity shulkerboxtileentity = (OverridenShulkerBoxTileEntity) tileentity;
				return shulkerboxtileentity.isVisuallyClosed();
			}
		};
		return new OverridenShulkerBoxBlock(color, properties.hardnessAndResistance(2.0F).variableOpacity().notSolid()
				.setSuffocates(abstractblock$ipositionpredicate).setBlocksVision(abstractblock$ipositionpredicate));
	}
}
