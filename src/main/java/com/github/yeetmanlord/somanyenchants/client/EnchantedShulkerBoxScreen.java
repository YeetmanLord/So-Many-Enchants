package com.github.yeetmanlord.somanyenchants.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedShulkerBoxContainer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class EnchantedShulkerBoxScreen extends AbstractContainerScreen<EnchantedShulkerBoxContainer> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(SoManyEnchants.MOD_ID + ":textures/gui/container/enchanted_shulker_box.png");

    public EnchantedShulkerBoxScreen(EnchantedShulkerBoxContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.imageHeight = 185;
        this.imageWidth = 175;
        this.inventoryLabelY = super.inventoryLabelY + 18;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        graphics.blit(GUI_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
