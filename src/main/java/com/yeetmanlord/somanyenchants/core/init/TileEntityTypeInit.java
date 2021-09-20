package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedHopperTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);
	
	public static final RegistryObject<TileEntityType<EnchantedHopperTileEntity>> ENCHANTED_HOPPER = TILE_ENTITY_TYPES.register("player.attack_distance", () -> TileEntityType.Builder.create(EnchantedHopperTileEntity::new, BlockInit.ENCHANTED_HOPPER.get()).build(null));

}
