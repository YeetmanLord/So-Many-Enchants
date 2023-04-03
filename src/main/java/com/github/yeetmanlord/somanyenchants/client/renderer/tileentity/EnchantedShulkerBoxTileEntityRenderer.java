package com.github.yeetmanlord.somanyenchants.client.renderer.tileentity;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedShulkerBoxTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.ShulkerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedShulkerBoxTileEntityRenderer extends TileEntityRenderer<EnchantedShulkerBoxTileEntity> {
	private final ShulkerModel<?> model;

	public EnchantedShulkerBoxTileEntityRenderer(TileEntityRendererDispatcher dis) {
		super(dis);
		this.model = new ShulkerModel<>();
	}

	@Override
	public void render(EnchantedShulkerBoxTileEntity p_112478_, float p_112479_, MatrixStack p_112480_,
			IRenderTypeBuffer p_112481_, int p_112482_, int p_112483_) {
		Direction direction = Direction.UP;
		if (p_112478_.hasLevel()) {
			BlockState blockstate = p_112478_.getLevel().getBlockState(p_112478_.getBlockPos());
			if (blockstate.getBlock() instanceof EnchantedShulkerBoxBlock) {
				direction = blockstate.getValue(EnchantedShulkerBoxBlock.FACING);
			}
		}

		DyeColor dyecolor = p_112478_.getColor();
		RenderMaterial material;
		if (dyecolor == null) {
			material = Atlases.DEFAULT_SHULKER_TEXTURE_LOCATION;
		} else {
			material = Atlases.SHULKER_TEXTURE_LOCATION.get(dyecolor.getId());
		}

		p_112480_.pushPose();
		p_112480_.translate(0.5D, 0.5D, 0.5D);
		float f = 0.9995F;
		p_112480_.scale(0.9995F, 0.9995F, 0.9995F);
		p_112480_.mulPose(direction.getRotation());
		p_112480_.scale(1.0F, -1.0F, -1.0F);
		p_112480_.translate(0.0D, -1.0D, 0.0D);
		ModelRenderer modelpart = this.model.getLid();
		modelpart.setPos(0.0F, 24.0F - p_112478_.getProgress(p_112479_) * 0.5F * 16.0F, 0.0F);
		modelpart.yRot = 270.0F * p_112478_.getProgress(p_112479_) * ((float) Math.PI / 180F);
		IVertexBuilder vertexconsumer = material.buffer(p_112481_, RenderType::entityCutoutNoCull);
		this.model.renderToBuffer(p_112480_, vertexconsumer, p_112482_, p_112483_, 1.0F, 1.0F, 1.0F, 1.0F);
		p_112480_.popPose();
	}
}
