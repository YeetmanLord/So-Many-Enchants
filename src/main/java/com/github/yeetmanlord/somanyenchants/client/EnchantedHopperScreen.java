package com.github.yeetmanlord.somanyenchants.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedHopperContainer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedHopperScreen extends AbstractContainerScreen<EnchantedHopperContainer> {
   private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(SoManyEnchants.MOD_ID + ":textures/gui/container/enchanted_hopper.png");

   public EnchantedHopperScreen(EnchantedHopperContainer container, Inventory playerInventory, Component title) {
      super(container, playerInventory, title);
      this.passEvents = false;
      this.imageHeight = 133;
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
      this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
   }
}