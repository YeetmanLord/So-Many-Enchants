package com.github.yeetmanlord.somanyenchants.client;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class EnchantedChestScreen extends AbstractContainerScreen<EnchantedChestContainer>
        implements MenuAccess<EnchantedChestContainer> {
    /**
     * The ResourceLocation containing the chest GUI texture.
     */
    private static final ResourceLocation CONTAINER_BACKGROUND = new ResourceLocation(
            SoManyEnchants.MOD_ID + ":textures/gui/container/generic_72.png");
    /**
     * Window height is calculated with these values" the more rows, the higher
     */
    private final int containerRows;

    public EnchantedChestScreen(EnchantedChestContainer container, Inventory inventory, Component title) {
        super(container, inventory, title);
        int i = 222;
        int j = 114;
        this.containerRows = container.getNumRows();
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics graphics, float partialTicks, int x, int y) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        graphics.blit(CONTAINER_BACKGROUND, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        graphics.blit(CONTAINER_BACKGROUND, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }
}