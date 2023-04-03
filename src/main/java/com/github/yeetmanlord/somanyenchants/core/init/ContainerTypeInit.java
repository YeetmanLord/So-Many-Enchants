package com.github.yeetmanlord.somanyenchants.core.init;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace.EnchantedBlastFurnaceContainer;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace.EnchantedFurnaceContainer;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker.EnchantedSmokerContainer;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedHopperContainer;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedShulkerBoxContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerTypeInit {

	public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.MENU_TYPES, SoManyEnchants.MOD_ID);

	public static final RegistryObject<MenuType<EnchantedHopperContainer>> ENCHANTED_HOPPER = CONTAINER_TYPES
			.register("enchanted_hopper",
					() -> new MenuType<EnchantedHopperContainer>(EnchantedHopperContainer::new));

	public static final RegistryObject<MenuType<EnchantedChestContainer>> GENERIC_9X8 = CONTAINER_TYPES.register(
			"generic_9x8", () -> new MenuType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X8));

	public static final RegistryObject<MenuType<EnchantedChestContainer>> GENERIC_9X4 = CONTAINER_TYPES.register(
			"generic_9x4", () -> new MenuType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X4));

	public static final RegistryObject<MenuType<EnchantedChestContainer>> GENERIC_9X3 = CONTAINER_TYPES.register(
			"generic_9x3", () -> new MenuType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X3));

	public static final RegistryObject<MenuType<EnchantedChestContainer>> GENERIC_9X6 = CONTAINER_TYPES.register(
			"generic_9x6", () -> new MenuType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X6));

	public static final RegistryObject<MenuType<EnchantedShulkerBoxContainer>> ENCHANTED_SHULKER_BOX = CONTAINER_TYPES
			.register("enchanted_shulker_box_container",
					() -> new MenuType<EnchantedShulkerBoxContainer>(EnchantedShulkerBoxContainer::new));

	public static final RegistryObject<MenuType<EnchantedFurnaceContainer>> ENCHANTED_FURNACE = CONTAINER_TYPES
			.register("enchanted_furnace",
					() -> new MenuType<EnchantedFurnaceContainer>(EnchantedFurnaceContainer::new));
	
	public static final RegistryObject<MenuType<EnchantedBlastFurnaceContainer>> ENCHANTED_BLAST_FURNACE = CONTAINER_TYPES
			.register("enchanted_blast_furnace",
					() -> new MenuType<EnchantedBlastFurnaceContainer>(EnchantedBlastFurnaceContainer::new));
	
	public static final RegistryObject<MenuType<EnchantedSmokerContainer>> ENCHANTED_SMOKER = CONTAINER_TYPES
			.register("enchanted_smoker",
					() -> new MenuType<EnchantedSmokerContainer>(EnchantedSmokerContainer::new));

}
