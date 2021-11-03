package com.yeetmanlord.somanyenchants.client.renderer.tileentity;

import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;

import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class EnchantedTrappedChestTileEntityRenderer extends EnchantedChestTileEntityRenderer<EnchantedTrappedChestTileEntity> 
{
	public EnchantedTrappedChestTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}
	
	@Override
	protected RenderMaterial getMaterial(EnchantedTrappedChestTileEntity tileEntity, ChestType chestType) 
	{
		return super.getChestMaterial(chestType, Atlases.CHEST_TRAPPED_MATERIAL, Atlases.CHEST_TRAPPED_LEFT_MATERIAL, Atlases.CHEST_TRAPPED_RIGHT_MATERIAL);
	}
}
