package com.yeetmanlord.somanyenchants.client.renderer.tileentity;

import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedHiddenTrappedChestTileEntity;

import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedHiddenTrappedChestTileEntityRenderer extends EnchantedChestTileEntityRenderer<EnchantedHiddenTrappedChestTileEntity> 
{
	public EnchantedHiddenTrappedChestTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}
	
	@Override
	protected RenderMaterial getMaterial(EnchantedHiddenTrappedChestTileEntity tileEntity, ChestType chestType) 
	{
		return super.getMaterial(tileEntity, chestType);
	}
}
