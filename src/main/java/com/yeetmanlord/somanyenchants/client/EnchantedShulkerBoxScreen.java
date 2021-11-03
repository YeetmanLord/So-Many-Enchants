package com.yeetmanlord.somanyenchants.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.container.EnchantedShulkerBoxContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedShulkerBoxScreen extends ContainerScreen<EnchantedShulkerBoxContainer> {
   private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Main.MOD_ID + ":textures/gui/container/enchanted_shulker_box.png");

   public EnchantedShulkerBoxScreen(EnchantedShulkerBoxContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
      super(screenContainer, inv, titleIn);
      this.ySize = 185;
      this.xSize = 175;
      this.playerInventoryTitleY = super.playerInventoryTitleY + 18; 
   }

   public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.renderBackground(matrixStack);
      super.render(matrixStack, mouseX, mouseY, partialTicks);
      this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
   }

   protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
      int i = (this.width - this.xSize) / 2;
      int j = (this.height - this.ySize) / 2;
      this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
   }
}
