package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.yeetmanlord.somanyenchants.common.container.EnchantedHopperContainer;
import com.yeetmanlord.somanyenchants.common.container.EnchantedShulkerBoxContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

	public static final DeferredRegister<ContainerType<?>> CONTIAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);

	public static final RegistryObject<ContainerType<EnchantedHopperContainer>> ENCHANTED_HOPPER = CONTIAINER_TYPES
			.register("enchanted_hopper",
					() -> new ContainerType<EnchantedHopperContainer>(EnchantedHopperContainer::new));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X8 = CONTIAINER_TYPES.register(
			"generic_9x8", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X8));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X4 = CONTIAINER_TYPES.register(
			"generic_9x4", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X4));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X3 = CONTIAINER_TYPES.register(
			"generic_9x3", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X3));

	public static final RegistryObject<ContainerType<EnchantedChestContainer>> GENERIC_9X6 = CONTIAINER_TYPES.register(
			"generic_9x6", () -> new ContainerType<EnchantedChestContainer>(EnchantedChestContainer::createGeneric9X6));

	public static final RegistryObject<ContainerType<EnchantedShulkerBoxContainer>> ENCHANTED_SHULKER_BOX = CONTIAINER_TYPES
			.register("enchanted_shulker_box_container",
					() -> new ContainerType<EnchantedShulkerBoxContainer>(EnchantedShulkerBoxContainer::new));

	public static final DeferredRegister<ContainerType<?>> MINECRAFT_CONTIAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, "minecraft");

}
