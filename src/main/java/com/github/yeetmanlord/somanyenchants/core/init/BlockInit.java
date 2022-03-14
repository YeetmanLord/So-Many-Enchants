package com.github.yeetmanlord.somanyenchants.core.init;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedBarrelBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedHopper;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedTrappedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace.EnchantedBlastFurnaceBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace.EnchantedFurnaceBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker.EnchantedSmokerBlock;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedShulkerBoxTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SoManyEnchants.MOD_ID);

	public static final RegistryObject<Block> ENCHANTED_HOPPER = BLOCKS.register("enchanted_hopper",
			() -> new EnchantedHopper(Block.Properties.copy(Blocks.HOPPER).isRedstoneConductor(BlockInit::isntSolid)
					.isViewBlocking(BlockInit::isntSolid).isSuffocating(BlockInit::isntSolid)));

	public static final RegistryObject<Block> ENCHANTED_CHEST = BLOCKS.register("enchanted_chest",
			() -> new EnchantedChestBlock(Block.Properties.copy(Blocks.CHEST), () -> {
				return BlockEntityTypeInit.ENCHANTED_CHEST.get();
			}));

	public static final RegistryObject<Block> ENCHANTED_BARREL = BLOCKS.register("enchanted_barrel",
			() -> new EnchantedBarrelBlock(Block.Properties.copy(Blocks.BARREL)));

	public static final RegistryObject<Block> TRAPPED_ENCHANTED_CHEST = BLOCKS.register("enchanted_trapped_chest",
			() -> new EnchantedTrappedChestBlock(Block.Properties.copy(Blocks.TRAPPED_CHEST), () -> {
				return BlockEntityTypeInit.TRAPPED_ENCHANTED_CHEST.get();
			}, false));

	public static final RegistryObject<Block> HIDDEN_TRAPPED_ENCHANTED_CHEST = BLOCKS.register(
			"hidden_enchanted_trapped_chest",
			() -> new EnchantedTrappedChestBlock(Block.Properties.copy(Blocks.TRAPPED_CHEST), () -> {
				return BlockEntityTypeInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get();
			}, true));

	public static final RegistryObject<Block> ENCHANTED_SHULKER_BOX = BLOCKS.register("enchanted_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties((DyeColor) null,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL)));
	public static final RegistryObject<Block> ENCHANTED_WHITE_SHULKER_BOX = BLOCKS.register(
			"enchanted_white_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.WHITE,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.SNOW)));
	public static final RegistryObject<Block> ENCHANTED_ORANGE_SHULKER_BOX = BLOCKS.register(
			"enchanted_orange_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.ORANGE,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_ORANGE)));
	public static final RegistryObject<Block> ENCHANTED_MAGENTA_SHULKER_BOX = BLOCKS.register(
			"enchanted_magenta_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.MAGENTA,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_MAGENTA)));
	public static final RegistryObject<Block> ENCHANTED_LIGHT_BLUE_SHULKER_BOX = BLOCKS.register(
			"enchanted_light_blue_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.LIGHT_BLUE,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_LIGHT_BLUE)));
	public static final RegistryObject<Block> ENCHANTED_YELLOW_SHULKER_BOX = BLOCKS.register(
			"enchanted_yellow_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.YELLOW,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_YELLOW)));
	public static final RegistryObject<Block> ENCHANTED_LIME_SHULKER_BOX = BLOCKS.register("enchanted_lime_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.LIME,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_LIGHT_GREEN)));
	public static final RegistryObject<Block> ENCHANTED_PINK_SHULKER_BOX = BLOCKS.register("enchanted_pink_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.PINK,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_PINK)));
	public static final RegistryObject<Block> ENCHANTED_GRAY_SHULKER_BOX = BLOCKS.register("enchanted_gray_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.GRAY,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_GRAY)));
	public static final RegistryObject<Block> ENCHANTED_LIGHT_GRAY_SHULKER_BOX = BLOCKS.register(
			"enchanted_light_gray_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.LIGHT_GRAY,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_LIGHT_GRAY)));
	public static final RegistryObject<Block> ENCHANTED_CYAN_SHULKER_BOX = BLOCKS.register("enchanted_cyan_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.CYAN,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_CYAN)));
	public static final RegistryObject<Block> ENCHANTED_PURPLE_SHULKER_BOX = BLOCKS.register(
			"enchanted_purple_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.PURPLE,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.TERRACOTTA_PURPLE)));
	public static final RegistryObject<Block> ENCHANTED_BLUE_SHULKER_BOX = BLOCKS.register("enchanted_blue_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.BLUE,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_BLUE)));
	public static final RegistryObject<Block> ENCHANTED_BROWN_SHULKER_BOX = BLOCKS.register(
			"enchanted_brown_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.BROWN,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_BROWN)));
	public static final RegistryObject<Block> ENCHANTED_GREEN_SHULKER_BOX = BLOCKS.register(
			"enchanted_green_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.GREEN,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_GREEN)));
	public static final RegistryObject<Block> ENCHANTED_RED_SHULKER_BOX = BLOCKS.register("enchanted_red_shulker_box",
			() -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.RED,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_RED)));
	public static final RegistryObject<Block> ENCHANTED_BLACK_SHULKER_BOX = BLOCKS.register(
			"enchanted_black_shulker_box", () -> createEnchantedShulkerBoxFromColorAndProperties(DyeColor.BLACK,
					BlockBehaviour.Properties.of(Material.SHULKER_SHELL, MaterialColor.COLOR_BLACK)));

	public static final RegistryObject<Block> ENCHANTED_FURNACE = BLOCKS.register("enchanted_furnace", () -> new EnchantedFurnaceBlock(Block.Properties.copy(Blocks.FURNACE)));
	public static final RegistryObject<Block> ENCHANTED_BLAST_FURNACE = BLOCKS.register("enchanted_blast_furnace", () -> new EnchantedBlastFurnaceBlock(Block.Properties.copy(Blocks.BLAST_FURNACE)));
	public static final RegistryObject<Block> ENCHANTED_SMOKER = BLOCKS.register("enchanted_smoker", () -> new EnchantedSmokerBlock(Block.Properties.copy(Blocks.SMOKER)));
	
	private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
		return false;
	}

	private static EnchantedShulkerBoxBlock createEnchantedShulkerBoxFromColorAndProperties(DyeColor color,
			BlockBehaviour.Properties properties) {
		BlockBehaviour.StatePredicate abstractblock$ipositionpredicate = (state, reader, pos) -> {
			BlockEntity tileentity = reader.getBlockEntity(pos);
			if (!(tileentity instanceof EnchantedShulkerBoxTileEntity)) {
				return true;
			} else {
				EnchantedShulkerBoxTileEntity shulkerboxtileentity = (EnchantedShulkerBoxTileEntity) tileentity;
				return shulkerboxtileentity.isVisuallyClosed();
			}
		};
		return new EnchantedShulkerBoxBlock(color, properties.strength(2.0F).dynamicShape().noOcclusion()
				.isSuffocating(abstractblock$ipositionpredicate).isViewBlocking(abstractblock$ipositionpredicate));
	}
}
