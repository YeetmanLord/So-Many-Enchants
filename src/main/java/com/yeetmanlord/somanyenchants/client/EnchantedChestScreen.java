package com.yeetmanlord.somanyenchants.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedChestScreen extends ContainerScreen<EnchantedChestContainer> implements IHasContainer<EnchantedChestContainer> {
   /** The ResourceLocation containing the chest GUI texture. */
   private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Main.MOD_ID + ":textures/gui/container/generic_72.png");
   /** Window height is calculated with these values" the more rows, the higher */
   private final int inventoryRows;

   public EnchantedChestScreen(EnchantedChestContainer container, PlayerInventory playerInventory, ITextComponent title) {
      super(container, playerInventory, title);
      this.passEvents = false;
      this.inventoryRows = container.getNumRows();
      this.ySize = 114 + this.inventoryRows * 18;
      this.playerInventoryTitleY = this.ySize - 94;
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
      int j = (this.height - this.ySize) / 2 ;
      this.blit(matrixStack, i, j + 2, 0, 0, this.xSize, this.inventoryRows * 18 + 15);
      this.blit(matrixStack, i, j + this.inventoryRows * 18 + 17, 0, 160, this.xSize, 96);
   }
}