package com.yeetmanlord.somanyenchants.client;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.DamageEnchantments;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.loading.FMLPaths;

public class SharpnessMenu extends Screen {
	
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
    
    private static DamageEnchantments configType = Config.de;
    
    
    private static String name = "Damage Enchantments";
    
    private OptionsRowList optionsRowList;
    
	public SharpnessMenu()
	{
		super(new TranslationTextComponent("so_many_enchants.screen.config.damage_enchantments"));
	}
		
	@SuppressWarnings("static-access")
	@Override
    protected void init() 
	{
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
    	file.load();
    	configType.maxLevel.set(file.get(name + ".maxLevel"));
    	configType.isEnabled.set(file.get(name + ".isEnabled"));
		
        this.optionsRowList = new OptionsRowList
        (
                this.minecraft, this.width, this.height,
                OPTIONS_LIST_TOP_HEIGHT,
                this.height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );
        

        
        this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level", 1, configType.absoluteMax, 1, get -> (double) configType.maxLevel.get(), (set, val) -> configType.maxLevel.set(val.intValue()), (gs, option) ->
        new StringTextComponent(
               I18n.format("so_many_enchants.screen.config.max_level")
                + ": "
                + (int) option.get(gs))));
        
        this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled", get -> (boolean) configType.isEnabled.get(), (set, val) -> configType.isEnabled.set(val.booleanValue())));
        
        this.children.add(this.optionsRowList);
        
        this.addButton(new Button(
                (this.width - BUTTON_WIDTH) / 2,
                this.height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                new TranslationTextComponent("gui.back"),
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
    
    @SuppressWarnings("static-access")
	@Override
    public void closeScreen()
    {
    	final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
    	file.load();
    	file.set(name + ".maxLevel", Config.de.maxLevel.get());
    	file.set(name + ".isEnabled", Config.de.isEnabled.get());
    	Config.builder
    		.comment(" Whether the mod changes these enchantments")
    		.defineInRange(name + ".maxLevel", Config.de.maxLevel.get(), 1, 10);
    	Config.builder
    		.comment(" The maximum enchantment level")
    		.define(name + ".isEnabled", Config.de.isEnabled.get());
    	Config.config.setConfig(file);
    	this.minecraft.displayGuiScreen(new ConfigMenu());
    }
    
    
    
}
