package com.github.yeetmanlord.somanyenchants.core.init;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace.EnchantedBlastFurnaceContainer;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace.EnchantedFurnaceContainer;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker.EnchantedSmokerContainer;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedHopperContainer;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedShulkerBoxContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, SoManyEnchants.MOD_ID);

	public static final RegistryObject<ContainerType<EnchantedHopperContainer>> ENCHANTED_HOPPER = CONTAINER_TYPES
			.register("enchanted_hopper",
					() -> new ContainerType<EnchantedHopperContainer>(EnchantedHopperContainer::new));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X8 = CONTAINER_TYPES.register(
			"generic_9x8", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X8));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X4 = CONTAINER_TYPES.register(
			"generic_9x4", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X4));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X3 = CONTAINER_TYPES.register(
			"generic_9x3", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X3));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X6 = CONTAINER_TYPES.register(
			"generic_9x6", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X6));

	public static final RegistryObject<ContainerType<EnchantedShulkerBoxContainer>> ENCHANTED_SHULKER_BOX = CONTAINER_TYPES
			.register("enchanted_shulker_box_container",
					() -> new ContainerType<EnchantedShulkerBoxContainer>(EnchantedShulkerBoxContainer::new));

	public static final RegistryObject<ContainerType<EnchantedFurnaceContainer>> ENCHANTED_FURNACE = CONTAINER_TYPES
			.register("enchanted_furnace",
					() -> new ContainerType<EnchantedFurnaceContainer>(EnchantedFurnaceContainer::new));
	
	public static final RegistryObject<ContainerType<EnchantedBlastFurnaceContainer>> ENCHANTED_BLAST_FURNACE = CONTAINER_TYPES
			.register("enchanted_blast_furnace",
					() -> new ContainerType<EnchantedBlastFurnaceContainer>(EnchantedBlastFurnaceContainer::new));
	
	public static final RegistryObject<ContainerType<EnchantedSmokerContainer>> ENCHANTED_SMOKER = CONTAINER_TYPES
			.register("enchanted_smoker",
					() -> new ContainerType<EnchantedSmokerContainer>(EnchantedSmokerContainer::new));

}
