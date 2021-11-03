package com.yeetmanlord.somanyenchants.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedShulkerBoxTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.ShulkerModel;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedShulkerBoxTileEntityRenderer extends TileEntityRenderer<EnchantedShulkerBoxTileEntity> {
	private final ShulkerModel<?> model;

	@SuppressWarnings("rawtypes")
	public EnchantedShulkerBoxTileEntityRenderer(TileEntityRendererDispatcher p_i226013_2_) {
		super(p_i226013_2_);
		this.model = new ShulkerModel();
	}

	public void render(EnchantedShulkerBoxTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Direction direction = Direction.UP;
		if (tileEntityIn.hasWorld()) {
			BlockState blockstate = tileEntityIn.getWorld().getBlockState(tileEntityIn.getPos());
			if (blockstate.getBlock() instanceof EnchantedShulkerBoxBlock) {
				direction = blockstate.get(EnchantedShulkerBoxBlock.FACING);
			}
		}

		DyeColor dyecolor = tileEntityIn.getColor();
		RenderMaterial rendermaterial;
		if (dyecolor == null) {
			rendermaterial = Atlases.DEFAULT_SHULKER_TEXTURE;
		} else {
			rendermaterial = Atlases.SHULKER_TEXTURES.get(dyecolor.getId());
		}

		matrixStackIn.push();
		matrixStackIn.translate(0.5D, 0.5D, 0.5D);
		float f = 0.9995F;
		matrixStackIn.scale(0.9995F, 0.9995F, 0.9995F);
		matrixStackIn.rotate(direction.getRotation());
		matrixStackIn.scale(1.0F, -1.0F, -1.0F);
		matrixStackIn.translate(0.0D, -1.0D, 0.0D);
		IVertexBuilder ivertexbuilder = rendermaterial.getBuffer(bufferIn, RenderType::getEntityCutoutNoCull);
		this.model.getBase().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		matrixStackIn.translate(0.0D, (double) (-tileEntityIn.getProgress(partialTicks) * 0.5F), 0.0D);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(270.0F * tileEntityIn.getProgress(partialTicks)));
		this.model.getLid().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		matrixStackIn.pop();
	}
}
