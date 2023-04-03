package com.github.yeetmanlord.somanyenchants.client.renderer.tileentity;

import java.util.Calendar;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedChestTileEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
	private final ModelRenderer lid;
	private final ModelRenderer bottom;
	private final ModelRenderer lock;
	private final ModelRenderer doubleLeftLid;
	private final ModelRenderer doubleLeftBottom;
	private final ModelRenderer doubleLeftLock;
	private final ModelRenderer doubleRightLid;
	private final ModelRenderer doubleRightBottom;
	private final ModelRenderer doubleRightLock;
	private boolean xmasTextures;

	public EnchantedChestTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
			this.xmasTextures = true;
		}

		this.bottom = new ModelRenderer(64, 64, 0, 19);
		this.bottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
		this.lid = new ModelRenderer(64, 64, 0, 0);
		this.lid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.lid.y = 9.0F;
		this.lid.z = 1.0F;
		this.lock = new ModelRenderer(64, 64, 0, 0);
		this.lock.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
		this.lock.y = 8.0F;
		this.doubleLeftBottom = new ModelRenderer(64, 64, 0, 19);
		this.doubleLeftBottom.addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.doubleLeftLid = new ModelRenderer(64, 64, 0, 0);
		this.doubleLeftLid.addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.doubleLeftLid.y = 9.0F;
		this.doubleLeftLid.z = 1.0F;
		this.doubleLeftLock = new ModelRenderer(64, 64, 0, 0);
		this.doubleLeftLock.addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.doubleLeftLock.y = 8.0F;
		this.doubleRightBottom = new ModelRenderer(64, 64, 0, 19);
		this.doubleRightBottom.addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.doubleRightLid = new ModelRenderer(64, 64, 0, 0);
		this.doubleRightLid.addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.doubleRightLid.y = 9.0F;
		this.doubleRightLid.z = 1.0F;
		this.doubleRightLock = new ModelRenderer(64, 64, 0, 0);
		this.doubleRightLock.addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.doubleRightLock.y = 8.0F;
	}

	@Override
	public void render(T tileEntity, float p_112364_, MatrixStack mStack, IRenderTypeBuffer renderBuff, int p_112367_,
			int p_112368_) {
		World level = tileEntity.getLevel();
		boolean flag = level != null;
		BlockState blockstate = flag ? tileEntity.getBlockState()
				: BlockInit.ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedChestBlock.FACING,
						Direction.SOUTH);
		ChestType chesttype = blockstate.hasProperty(EnchantedChestBlock.TYPE)
				? blockstate.getValue(EnchantedChestBlock.TYPE)
				: ChestType.SINGLE;
		Block block = blockstate.getBlock();
		if (block instanceof EnchantedChestBlock) {
			EnchantedChestBlock abstractchestblock = (EnchantedChestBlock) block;
			boolean flag1 = chesttype != ChestType.SINGLE;
			mStack.pushPose();
			float f = blockstate.getValue(EnchantedChestBlock.FACING).toYRot();
			mStack.translate(0.5D, 0.5D, 0.5D);
			mStack.mulPose(Vector3f.YP.rotationDegrees(-f));
			mStack.translate(-0.5D, -0.5D, -0.5D);
			TileEntityMerger.ICallbackWrapper<? extends EnchantedChestTileEntity> neighborcombineresult;
			if (flag) {
				neighborcombineresult = abstractchestblock.combine(blockstate, level, tileEntity.getBlockPos(), true);
			} else {
				neighborcombineresult = TileEntityMerger.ICallback::acceptNone;
			}

			float f1 = neighborcombineresult
					.<Float2FloatFunction>apply(EnchantedChestBlock.opennessCombiner(tileEntity)).get(p_112364_);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			int i = neighborcombineresult.<Int2IntFunction>apply(new DualBrightnessCallback<>()).applyAsInt(p_112367_);
			RenderMaterial material = this.getMaterial(tileEntity, chesttype);
			IVertexBuilder vertexconsumer = material.buffer(renderBuff, RenderType::entityCutout);
			if (flag1) {
				if (chesttype == ChestType.RIGHT) {
					this.render(mStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom,
							f1, i, p_112368_);
				} else {
					this.render(mStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock,
							this.doubleRightBottom, f1, i, p_112368_);
				}
			} else {
				this.render(mStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, p_112368_);
			}

			mStack.popPose();
		}
	}

	private void render(MatrixStack p_112370_, IVertexBuilder p_112371_, ModelRenderer p_112372_,
			ModelRenderer p_112373_, ModelRenderer p_112374_, float p_112375_, int p_112376_, int p_112377_) {
		p_112372_.xRot = -(p_112375_ * ((float) Math.PI / 2F));
		p_112373_.xRot = p_112372_.xRot;
		p_112372_.render(p_112370_, p_112371_, p_112376_, p_112377_);
		p_112373_.render(p_112370_, p_112371_, p_112376_, p_112377_);
		p_112374_.render(p_112370_, p_112371_, p_112376_, p_112377_);
	}

	protected RenderMaterial getMaterial(T tileEntity, ChestType chestType) {
		return chooseMaterial(tileEntity, chestType, this.xmasTextures);
	}

	public static RenderMaterial chooseMaterial(TileEntity blockEntity, ChestType type, boolean christmas) {
		if (christmas) {
			return chooseMaterial(type, Atlases.CHEST_XMAS_LOCATION, Atlases.CHEST_XMAS_LOCATION_LEFT,
					Atlases.CHEST_XMAS_LOCATION_RIGHT);
		} else if (blockEntity instanceof EnchantedTrappedChestTileEntity) {
			return chooseMaterial(type, Atlases.CHEST_TRAP_LOCATION, Atlases.CHEST_TRAP_LOCATION_LEFT,
					Atlases.CHEST_TRAP_LOCATION_RIGHT);
		} else {
			return chooseMaterial(type, Atlases.CHEST_LOCATION, Atlases.CHEST_LOCATION_LEFT,
					Atlases.CHEST_LOCATION_RIGHT);
		}
	}

	protected static RenderMaterial chooseMaterial(ChestType chestType, RenderMaterial doubleMaterial,
			RenderMaterial leftMaterial, RenderMaterial rightMaterial) {
		switch (chestType) {
		case LEFT:
			return leftMaterial;
		case RIGHT:
			return rightMaterial;
		case SINGLE:
		default:
			return doubleMaterial;
		}
	}
}
