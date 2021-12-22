package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace.EnchantedBlastFurnaceTileEntity;
import com.yeetmanlord.somanyenchants.common.blocks.smelters.furnace.EnchantedFurnaceTileEntity;
import com.yeetmanlord.somanyenchants.common.blocks.smelters.smoker.EnchantedSmokerTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedBarrelTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedHiddenTrappedChestTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedHopperTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedShulkerBoxTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

	public static final RegistryObject<TileEntityType<EnchantedHopperTileEntity>> ENCHANTED_HOPPER = TILE_ENTITY_TYPES
			.register("enchanted_hopper", () -> TileEntityType.Builder
					.create(EnchantedHopperTileEntity::new, BlockInit.ENCHANTED_HOPPER.get()).build(null));

	public static final RegistryObject<TileEntityType<EnchantedChestTileEntity>> ENCHANTED_CHEST = TILE_ENTITY_TYPES
			.register("enchanted_chest", () -> TileEntityType.Builder
					.create(EnchantedChestTileEntity::new, BlockInit.ENCHANTED_CHEST.get()).build(null));

	public static final RegistryObject<TileEntityType<EnchantedBarrelTileEntity>> ENCHANTED_BARREL = TILE_ENTITY_TYPES
			.register("enchanted_barrel", () -> TileEntityType.Builder
					.create(EnchantedBarrelTileEntity::new, BlockInit.ENCHANTED_BARREL.get()).build(null));

	public static final RegistryObject<TileEntityType<EnchantedTrappedChestTileEntity>> TRAPPED_ENCHANTED_CHEST = TILE_ENTITY_TYPES
			.register("enchanted_trapped_chest", () -> TileEntityType.Builder
					.create(EnchantedTrappedChestTileEntity::new, BlockInit.TRAPPED_ENCHANTED_CHEST.get()).build(null));

	public static final RegistryObject<TileEntityType<EnchantedHiddenTrappedChestTileEntity>> HIDDEN_TRAPPED_ENCHANTED_CHEST = TILE_ENTITY_TYPES
			.register("enchanted_hidden_trapped_chest", () -> TileEntityType.Builder
					.create(EnchantedHiddenTrappedChestTileEntity::new, BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get())
					.build(null));

	public static final RegistryObject<TileEntityType<EnchantedShulkerBoxTileEntity>> ENCHANTED_SHULKER_BOX = TILE_ENTITY_TYPES
			.register("enchanted_shulker_box", () -> TileEntityType.Builder
					.create(EnchantedShulkerBoxTileEntity::new, BlockInit.ENCHANTED_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_BLACK_SHULKER_BOX.get(), BlockInit.ENCHANTED_BLUE_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_BROWN_SHULKER_BOX.get(), BlockInit.ENCHANTED_CYAN_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_GRAY_SHULKER_BOX.get(), BlockInit.ENCHANTED_GREEN_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_LIGHT_BLUE_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_LIGHT_GRAY_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_LIME_SHULKER_BOX.get(), BlockInit.ENCHANTED_MAGENTA_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_ORANGE_SHULKER_BOX.get(), BlockInit.ENCHANTED_PINK_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_PURPLE_SHULKER_BOX.get(), BlockInit.ENCHANTED_RED_SHULKER_BOX.get(),
							BlockInit.ENCHANTED_WHITE_SHULKER_BOX.get(), BlockInit.ENCHANTED_YELLOW_SHULKER_BOX.get())
					.build(null));

	public static final RegistryObject<TileEntityType<EnchantedFurnaceTileEntity>> ENCHANTED_FURNACE = TILE_ENTITY_TYPES
			.register("enchanted_furnace", () -> TileEntityType.Builder
					.create(EnchantedFurnaceTileEntity::new, BlockInit.ENCHANTED_FURNACE.get()).build(null));

	public static final RegistryObject<TileEntityType<EnchantedBlastFurnaceTileEntity>> ENCHANTED_BLAST_FURNACE = TILE_ENTITY_TYPES
			.register("enchanted_blast_furnace", () -> TileEntityType.Builder
					.create(EnchantedBlastFurnaceTileEntity::new, BlockInit.ENCHANTED_BLAST_FURNACE.get()).build(null));
	
	public static final RegistryObject<TileEntityType<EnchantedSmokerTileEntity>> ENCHANTED_SMOKER = TILE_ENTITY_TYPES
			.register("enchanted_smoker", () -> TileEntityType.Builder
					.create(EnchantedSmokerTileEntity::new, BlockInit.ENCHANTED_SMOKER.get()).build(null));
	
	
}
