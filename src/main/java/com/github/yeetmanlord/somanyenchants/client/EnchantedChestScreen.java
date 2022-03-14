package com.github.yeetmanlord.somanyenchants.client;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedChestScreen extends AbstractContainerScreen<EnchantedChestContainer>
		implements MenuAccess<EnchantedChestContainer> {
	/** The ResourceLocation containing the chest GUI texture. */
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(
			SoManyEnchants.MOD_ID + ":textures/gui/container/generic_72.png");
	/** Window height is calculated with these values" the more rows, the higher */
	private final int inventoryRows;

	public EnchantedChestScreen(EnchantedChestContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.passEvents = false;
		this.inventoryRows = container.getNumRows();
		this.imageHeight = 114 + this.inventoryRows * 18;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {

		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, GUI_TEXTURE);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, i, j + 2, 0, 0, this.imageWidth, this.inventoryRows * 18 + 15);
		this.blit(matrixStack, i, j + this.inventoryRows * 18 + 17, 0, 160, this.imageWidth, 96);
	}
}