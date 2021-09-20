package com.yeetmanlord.somanyenchants.client;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.util.text.TranslationTextComponent;

public class ConfigMenu extends Screen {
	
	/* Thank You So Much
	 Leo3148 For Giving A Tutorial For
	 The Config Screen! :D
	 */
	
	private static final int TITLE_HEIGHT = 8;
	
    private static final int OPTIONS_LIST_TOP_HEIGHT = 24;
    
    private static final int OPTIONS_LIST_BOTTOM_OFFSET = 32;
    
    private static final int OPTIONS_LIST_ITEM_HEIGHT = 25;
    
    private static final int BUTTON_WIDTH = 200;
    
    private static final int BUTTON_HEIGHT = 20;
    
    private static final int DONE_BUTTON_TOP_OFFSET = 26;
    
    private OptionsRowList optionsRowList;
	
	
	public ConfigMenu() 
	{
		super(new TranslationTextComponent("so_many_enchants.screen.config"));
	}
		
	@Override
    protected void init() 
	{
        this.optionsRowList = new OptionsRowList
        (
                this.minecraft, this.width, this.height,
                OPTIONS_LIST_TOP_HEIGHT,
                this.height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );
        
        this.addButton(new Button(
                (this.width - BUTTON_WIDTH) / 2,
                this.height - (BUTTON_HEIGHT * 25) - 10,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                new TranslationTextComponent("so_many_enchants.screen.config.damage_enchantments"),
                button -> this.minecraft.displayGuiScreen(new SharpnessMenu())
        ));
        
        this.children.add(this.optionsRowList);
        
        this.addButton(new Button(
                (this.width - BUTTON_WIDTH) / 2,
                this.height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                new TranslationTextComponent("gui.done"),
                button -> this.closeScreen()
        ));
        
        
    }

    @Override
    public void render(MatrixStack matrixStack,
                       int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(),
                this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
