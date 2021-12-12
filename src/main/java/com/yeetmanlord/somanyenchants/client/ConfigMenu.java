package com.yeetmanlord.somanyenchants.client;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLPaths;


@OnlyIn(Dist.CLIENT)
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
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
    	file.load();
    	Config.load(file);
	        this.optionsRowList = new OptionsRowList
	        (
	                this.minecraft, this.width, this.height,
	                OPTIONS_LIST_TOP_HEIGHT,
	                this.height - OPTIONS_LIST_BOTTOM_OFFSET,
	                OPTIONS_LIST_ITEM_HEIGHT
	        );
	        
	        
	       
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.de", 1, Config.de.absoluteMax, 1, get -> (double) Config.de.maxLevel.get(), (set, val) -> Config.de.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.de")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.de.isEnabled.get(), (set, val) -> Config.de.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.e", 1, Config.e.absoluteMax, 1, get -> (double) Config.e.maxLevel.get(), (set, val) -> Config.e.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.e")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.e.isEnabled.get(), (set, val) -> Config.e.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fa", 1, Config.fa.absoluteMax, 1, get -> (double) Config.fa.maxLevel.get(), (set, val) -> Config.fa.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.fa")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.fa.isEnabled.get(), (set, val) -> Config.fa.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.i", 1, Config.i.absoluteMax, 1, get -> (double) Config.i.maxLevel.get(), (set, val) -> Config.i.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.i")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.i.isEnabled.get(), (set, val) -> Config.i.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.k", 1, Config.k.absoluteMax, 1, get -> (double) Config.k.maxLevel.get(), (set, val) -> Config.k.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.k")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.k.isEnabled.get(), (set, val) -> Config.k.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lbe", 1, Config.lbe.absoluteMax, 1, get -> (double) Config.lbe.maxLevel.get(), (set, val) -> Config.lbe.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.lbe")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.lbe.isEnabled.get(), (set, val) -> Config.lbe.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lo", 1, Config.lo.absoluteMax, 1, get -> (double) Config.lo.maxLevel.get(), (set, val) -> Config.lo.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.lo")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.lo.isEnabled.get(), (set, val) -> Config.lo.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.l", 1, Config.l.absoluteMax, 1, get -> (double) Config.l.maxLevel.get(), (set, val) -> Config.l.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.l")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.l.isEnabled.get(), (set, val) -> Config.l.isEnabled.set(val.booleanValue())));
	        
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.p", 1, Config.p.absoluteMax, 1, get -> (double) Config.p.maxLevel.get(), (set, val) -> Config.p.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.p")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.p.isEnabled.get(), (set, val) -> Config.p.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.po", 1, Config.po.absoluteMax, 1, get -> (double) Config.po.maxLevel.get(), (set, val) -> Config.po.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.po")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.po.isEnabled.get(), (set, val) -> Config.po.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.pr", 1, Config.pr.absoluteMax, 1, get -> (double) Config.pr.maxLevel.get(), (set, val) -> Config.pr.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.pr")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.pr.isEnabled.get(), (set, val) -> Config.pr.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.pu", 1, Config.pu.absoluteMax, 1, get -> (double) Config.pu.maxLevel.get(), (set, val) -> Config.pu.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.pu")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.pu.isEnabled.get(), (set, val) -> Config.pu.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.q", 1, Config.q.absoluteMax, 1, get -> (double) Config.q.maxLevel.get(), (set, val) -> Config.q.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.q")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.q.isEnabled.get(), (set, val) -> Config.q.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.r", 1, Config.r.absoluteMax, 1, get -> (double) Config.r.maxLevel.get(), (set, val) -> Config.r.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.r")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.r.isEnabled.get(), (set, val) -> Config.r.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.ri", 1, Config.ri.absoluteMax, 1, get -> (double) Config.ri.maxLevel.get(), (set, val) -> Config.ri.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.ri")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.ri.isEnabled.get(), (set, val) -> Config.ri.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.ss", 1, Config.pr.absoluteMax, 1, get -> (double) Config.ss.maxLevel.get(), (set, val) -> Config.ss.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.ss")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.ss.isEnabled.get(), (set, val) -> Config.ss.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.s", 1, Config.s.absoluteMax, 1, get -> (double) Config.s.maxLevel.get(), (set, val) -> Config.s.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.s")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.s.isEnabled.get(), (set, val) -> Config.s.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.t", 1, Config.t.absoluteMax, 1, get -> (double) Config.t.maxLevel.get(), (set, val) -> Config.t.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.t")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.t.isEnabled.get(), (set, val) -> Config.t.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.u", 1, Config.u.absoluteMax, 1, get -> (double) Config.u.maxLevel.get(), (set, val) -> Config.u.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.u")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) 
	      				Config.u.isEnabled.get(), (set, val) -> Config.u.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.he", 1, Config.he.absoluteMax, 1, get -> (double) Config.he.maxLevel.get(), (set, val) -> Config.he.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.he")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.he.isEnabled.get(), (set, val) -> Config.he.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.rei", 1, Config.rei.absoluteMax, 1, get -> (double) Config.rei.maxLevel.get(), (set, val) -> Config.rei.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.rei")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.rei.isEnabled.get(), (set, val) -> Config.rei.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.te", 1, Config.te.absoluteMax, 1, get -> (double) Config.te.maxLevel.get(), (set, val) -> Config.te.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.te")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.te.isEnabled.get(), (set, val) -> Config.te.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.c", get -> (boolean) 
	      				Config.c.isEnabled.get(), (set, val) -> Config.c.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fl", 1, Config.fl.absoluteMax, 1, get -> (double) Config.fl.maxLevel.get(), (set, val) -> Config.fl.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.fl")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.fl.isEnabled.get(), (set, val) -> Config.fl.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.h", 1, Config.h.absoluteMax, 1, get -> (double) Config.h.maxLevel.get(), (set, val) -> Config.h.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.h")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.h.isEnabled.get(), (set, val) -> Config.h.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.sa", 1, Config.sa.absoluteMax, 1, get -> (double) Config.sa.maxLevel.get(), (set, val) -> Config.sa.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.sa")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.sa.isEnabled.get(), (set, val) -> Config.sa.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.f", get -> (boolean) 
	      				Config.f.isEnabled.get(), (set, val) -> Config.f.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.b", 1, Config.b.absoluteMax, 1, get -> (double) Config.b.maxLevel.get(), (set, val) -> Config.b.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.b")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.b.isEnabled.get(), (set, val) -> Config.b.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.d", 1, Config.d.absoluteMax, 1, get -> (double) Config.d.maxLevel.get(), (set, val) -> Config.d.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.d")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.d.isEnabled.get(), (set, val) -> Config.d.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.re", get -> (boolean) 
	      				Config.re.isEnabled.get(), (set, val) -> Config.re.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.a", 1, Config.a.absoluteMax, 1, get -> (double) Config.a.maxLevel.get(), (set, val) -> Config.a.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.a")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.a.isEnabled.get(), (set, val) -> Config.a.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.cr", 1, Config.cr.absoluteMax, 1, get -> (double) Config.cr.maxLevel.get(), (set, val) -> Config.cr.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.cr")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.cr.isEnabled.get(), (set, val) -> Config.cr.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fr", 1, Config.fr.absoluteMax, 1, get -> (double) Config.fr.maxLevel.get(), (set, val) -> Config.fr.maxLevel.set(val.intValue()), (gs, option) ->
	      new StringTextComponent(
	              I18n.format("so_many_enchants.screen.config.max_level.fr")
	               + ": "
	               + (int) option.get(gs))), 
	      		new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) 
	      				Config.fr.isEnabled.get(), (set, val) -> Config.fr.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.v", get -> (boolean) 
  				Config.v.isEnabled.get(), (set, val) -> Config.v.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.cs", get -> (boolean) 
  				Config.cs.isEnabled.get(), (set, val) -> Config.cs.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.cf", get -> (boolean) 
	  				Config.cf.isEnabled.get(), (set, val) -> Config.cf.isEnabled.set(val.booleanValue())));
	      
	      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.fs", get -> (boolean) 
	  				Config.fs.isEnabled.get(), (set, val) -> Config.fs.isEnabled.set(val.booleanValue())));
	      
	        this.children.add(this.optionsRowList);
	        
	        this.addButton(new Button(
	                (this.width - BUTTON_WIDTH * 2) / 2,
	                this.height - DONE_BUTTON_TOP_OFFSET,
	                BUTTON_WIDTH, BUTTON_HEIGHT,
	                new TranslationTextComponent("gui.done"),
	                button -> this.closeScreen()
	        ));
	        
	        this.addButton(new Button(
	                (this.width - BUTTON_WIDTH / 3 + 100) / 2,
	                this.height - DONE_BUTTON_TOP_OFFSET,
	                BUTTON_WIDTH, BUTTON_HEIGHT,
	                new TranslationTextComponent("so_many_enchants.screen.config.default"),
	                button -> this.setDefault()
	        ));
	        
	        file.close();
    }
	
	
	

    private void setDefault()
    {
    	Config.de.maxLevel.set(10);
    	Config.de.isEnabled.set(true);
    	
    	Config.e.maxLevel.set(10);
    	Config.e.isEnabled.set(true);
    	
    	Config.fa.maxLevel.set(10);
    	Config.fa.isEnabled.set(true);
    	
    	Config.i.maxLevel.set(10);
    	Config.i.isEnabled.set(true);
    	
    	Config.k.maxLevel.set(10);
    	Config.k.isEnabled.set(true);
    	
    	Config.lbe.maxLevel.set(10);
    	Config.lbe.isEnabled.set(true);
    	
    	Config.lo.maxLevel.set(5);
    	Config.lo.isEnabled.set(true);
    	
    	Config.l.maxLevel.set(5);
    	Config.l.isEnabled.set(true);
    	
    	Config.p.maxLevel.set(10);
    	Config.p.isEnabled.set(true);
    	
    	Config.po.maxLevel.set(10);
    	Config.po.isEnabled.set(true);
    	
    	Config.pr.maxLevel.set(10);
    	Config.pr.isEnabled.set(true);
    	
    	Config.pu.maxLevel.set(10);
    	Config.pu.isEnabled.set(true);
    	
    	Config.q.maxLevel.set(5);
    	Config.q.isEnabled.set(true);
    	
    	Config.r.maxLevel.set(5);
    	Config.r.isEnabled.set(true);
    	
    	Config.ri.maxLevel.set(5);
    	Config.ri.isEnabled.set(true);
    	
    	Config.ss.maxLevel.set(5);
    	Config.ss.isEnabled.set(true);
    	
    	Config.s.maxLevel.set(10);
    	Config.s.isEnabled.set(true);
    	
    	Config.t.maxLevel.set(10);
    	Config.t.isEnabled.set(true);
    	
    	Config.u.maxLevel.set(10);
    	Config.u.isEnabled.set(true);
    	
    	Config.he.maxLevel.set(1);
    	Config.he.isEnabled.set(true);
    	
    	Config.rei.maxLevel.set(1);
    	Config.rei.isEnabled.set(true);
    	
    	Config.te.maxLevel.set(1);
    	Config.te.isEnabled.set(true);
    	
    	Config.c.maxLevel.set(1);
    	Config.c.isEnabled.set(true);
    	
    	Config.fl.maxLevel.set(3);
    	Config.fl.isEnabled.set(false);
    	
    	Config.h.maxLevel.set(5);
    	Config.h.isEnabled.set(true);
    	
    	Config.sa.maxLevel.set(1);
    	Config.sa.isEnabled.set(false);

    	
    	Config.f.isEnabled.set(true);

    	
    	Config.b.maxLevel.set(3);
    	Config.b.isEnabled.set(true);
    	
    	Config.d.maxLevel.set(5);
    	Config.d.isEnabled.set(false);
    	
    	Config.re.maxLevel.set(1);
    	Config.re.isEnabled.set(true);
    	
    	
    	Config.a.maxLevel.set(1);
    	Config.a.isEnabled.set(false);
    	
    	Config.cr.maxLevel.set(5);
    	Config.cr.isEnabled.set(false);
    	
    	Config.fr.maxLevel.set(3);
    	Config.fr.isEnabled.set(true);

    	Config.v.isEnabled.set(true);
    	
    	Config.cs.isEnabled.set(true);
    	
    	Config.cf.isEnabled.set(true);
    	this.refresh();
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
    
    @Override
    public void closeScreen()
    {
    	final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
    	file.load();
    	Config.save(file);
    	
    	Config.SyncedServerConfig.setConfig(file);
    	Config.SyncedServerConfig.save();
    	
    	file.save();
    	
    	
    	
    	
    	this.minecraft.displayGuiScreen((Screen)null);
    	
    	
    }
    
    private void refresh()
    {
    	final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
    	file.load();
    	Config.save(file);
    	
    	Config.SyncedServerConfig.setConfig(file);
    	Config.SyncedServerConfig.save();
    	
    	file.save();
    	
    	this.minecraft.displayGuiScreen(new ConfigMenu());
    }
}
