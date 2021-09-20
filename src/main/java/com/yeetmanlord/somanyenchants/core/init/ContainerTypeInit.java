package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.container.EnchantedHopperContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

	public static final DeferredRegister<ContainerType<?>>
 		CONTIAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);
 
 
	public static final RegistryObject<ContainerType<EnchantedHopperContainer>> ENCHANTED_HOPPER = 
		 CONTIAINER_TYPES.register("energizer",
		 () -> new ContainerType<EnchantedHopperContainer>(EnchantedHopperContainer::new));

}
