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
	    	Config.de.maxLevel.set(file.get("Damage Enchantments" + ".maxLevel"));
	    	Config.de.isEnabled.set(file.get("Damage Enchantments" + ".isEnabled"));
	    	
	    	Config.e.maxLevel.set(file.get("Efficiency" + ".maxLevel"));
	    	Config.e.isEnabled.set(file.get("Efficiency" + ".isEnabled"));
	    	
	    	Config.fa.maxLevel.set(file.get("Fire Aspect" + ".maxLevel"));
	    	Config.fa.isEnabled.set(file.get("Fire Aspect" + ".isEnabled"));
	    	
	    	Config.i.maxLevel.set(file.get("Impaling" + ".maxLevel"));
	    	Config.i.isEnabled.set(file.get("Impaling" + ".isEnabled"));
	    	
	    	Config.k.maxLevel.set(file.get("Knockback" + ".maxLevel"));
	    	Config.k.isEnabled.set(file.get("Knockback" + ".isEnabled"));
	    	
	    	Config.lbe.maxLevel.set(file.get("Loot Bonus Enchantments" + ".maxLevel"));
	    	Config.lbe.isEnabled.set(file.get("Loot Bonus Enchantments" + ".isEnabled"));
	    	
	    	Config.lo.maxLevel.set(file.get("Loyalty" + ".maxLevel"));
	    	Config.lo.isEnabled.set(file.get("Loyalty" + ".isEnabled"));
	    	
	    	Config.l.maxLevel.set(file.get("Lure" + ".maxLevel"));
	    	Config.l.isEnabled.set(file.get("Lure" + ".isEnabled"));
	    	
	    	Config.p.maxLevel.set(file.get("Piercing" + ".maxLevel"));
	    	Config.p.isEnabled.set(file.get("Piercing" + ".isEnabled"));
	    	
	    	Config.po.maxLevel.set(file.get("Power" + ".maxLevel"));
	    	Config.po.isEnabled.set(file.get("Power" + ".isEnabled"));
	    	
	    	Config.pr.maxLevel.set(file.get("Protection Enchantments" + ".maxLevel"));
	    	Config.pr.isEnabled.set(file.get("Protection Enchantments" + ".isEnabled"));
	    	
	    	Config.pu.maxLevel.set(file.get("Punch" + ".maxLevel"));
	    	Config.pu.isEnabled.set(file.get("Punch" + ".isEnabled"));
	    	
	    	Config.q.maxLevel.set(file.get("Quick Charge" + ".maxLevel"));
	    	Config.q.isEnabled.set(file.get("Quick Charge" + ".isEnabled"));
	    	
	    	Config.r.maxLevel.set(file.get("Respiration" + ".maxLevel"));
	    	Config.r.isEnabled.set(file.get("Respiration" + ".isEnabled"));
	    	
	    	Config.ri.maxLevel.set(file.get("Riptide" + ".maxLevel"));
	    	Config.ri.isEnabled.set(file.get("Riptide" + ".isEnabled"));
	    	
	    	Config.ss.maxLevel.set(file.get("Soul Speed" + ".maxLevel"));
	    	Config.ss.isEnabled.set(file.get("Soul Speed" + ".isEnabled"));
	    	
	    	Config.s.maxLevel.set(file.get("Sweeping Edge" + ".maxLevel"));
	    	Config.s.isEnabled.set(file.get("Sweeping Edge" + ".isEnabled"));
	    	
	    	Config.t.maxLevel.set(file.get("Thorns" + ".maxLevel"));
	    	Config.t.isEnabled.set(file.get("Thorns" + ".isEnabled"));
	    	
	    	Config.u.maxLevel.set(file.get("Unbreaking" + ".maxLevel"));
	    	Config.u.isEnabled.set(file.get("Unbreaking" + ".isEnabled"));
	    	
	    	Config.he.maxLevel.set(file.get("Heavy" + ".maxLevel"));
	    	Config.he.isEnabled.set(file.get("Heavy" + ".isEnabled"));
	    	
	    	Config.rei.maxLevel.set(file.get("Reinforcement" + ".maxLevel"));
	    	Config.rei.isEnabled.set(file.get("Reinforcement" + ".isEnabled"));
	    	
	    	Config.te.maxLevel.set(file.get("Temper" + ".maxLevel"));
	    	Config.te.isEnabled.set(file.get("Temper" + ".isEnabled"));

	    	Config.c.isEnabled.set(file.get("Cat Vision" + ".isEnabled"));
	    	
	    	Config.fl.maxLevel.set(file.get("Flight" + ".maxLevel"));
	    	Config.fl.isEnabled.set(file.get("Flight" + ".isEnabled"));
	    	
	    	Config.h.maxLevel.set(file.get("Health Boost" + ".maxLevel"));
	    	Config.h.isEnabled.set(file.get("Health Boost" + ".isEnabled"));
	    	
	    	Config.sa.maxLevel.set(file.get("Step Assist" + ".maxLevel"));
	    	Config.sa.isEnabled.set(file.get("Step Assist" + ".isEnabled"));

	//    	Config.f.isEnabled.set(file.get("Fast Hopper" + ".isEnabled"));
	
	    	
	    	Config.b.maxLevel.set(file.get("Block Reach" + ".maxLevel"));
	    	Config.b.isEnabled.set(file.get("Block Reach" + ".isEnabled"));
	    	
	    	Config.d.maxLevel.set(file.get("Double Break" + ".maxLevel"));
	    	Config.d.isEnabled.set(file.get("Double Break" + ".isEnabled"));

	    	Config.re.isEnabled.set(file.get("Replanting" + ".isEnabled"));
	    	
	    	
	    	Config.a.maxLevel.set(file.get("Attack Reach" + ".maxLevel"));
	    	Config.a.isEnabled.set(file.get("Attack Reach" + ".isEnabled"));
	    	
	    	Config.cr.maxLevel.set(file.get("Critical" + ".maxLevel"));
	    	Config.cr.isEnabled.set(file.get("Critical" + ".isEnabled"));
	    	
	    	Config.fr.maxLevel.set(file.get("Freezing" + ".maxLevel"));
	    	Config.fr.isEnabled.set(file.get("Freezing" + ".isEnabled"));

	    	Config.v.isEnabled.set(file.get("Enchanter Villager" + ".isEnabled"));
			
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
	      
	//      this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.f", get -> (boolean) 
	//      				Config.f.isEnabled.get(), (set, val) -> Config.f.isEnabled.set(val.booleanValue())));
	      
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
//	        I have a config menu and it accesses the config file toml and my game keeps crashing with 
//	        `java.nio.file.FileSystemException: The process cannot access the file because it is being used by another process.`
//	        and
//	        `com.electronwill.nightconfig.core.io.WritingException: An I/O error occured`
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

    	
//    	Config.f.isEnabled.set(true);

    	
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
    	file.set("Damage Enchantments" + ".maxLevel", Config.de.maxLevel.get());
    	file.set("Damage Enchantments" + ".isEnabled", Config.de.isEnabled.get());
    	
    	file.set("Efficiency" + ".maxLevel", Config.e.maxLevel.get());
    	file.set("Efficiency" + ".isEnabled", Config.e.isEnabled.get());
    	;
    	
    	file.set("Fire Aspect" + ".maxLevel", Config.fa.maxLevel.get());
    	file.set("Fire Aspect" + ".isEnabled", Config.fa.isEnabled.get());
    	;
    	
    	file.set("Impaling" + ".maxLevel", Config.i.maxLevel.get());
    	file.set("Impaling" + ".isEnabled", Config.i.isEnabled.get());
    	;
    	
    	file.set("Knockback" + ".maxLevel", Config.k.maxLevel.get());
    	file.set("Knockback" + ".isEnabled", Config.k.isEnabled.get());
    	;
    	
    	file.set("Loot Bonus Enchantments" + ".maxLevel", Config.lbe.maxLevel.get());
    	file.set("Loot Bonus Enchantments" + ".isEnabled", Config.lbe.isEnabled.get());
    	;
    	
    	file.set("Loyalty" + ".maxLevel", Config.lo.maxLevel.get());
    	file.set("Loyalty" + ".isEnabled", Config.lo.isEnabled.get());
    	;
    	
    	file.set("Lure" + ".maxLevel", Config.l.maxLevel.get());
    	file.set("Lure" + ".isEnabled", Config.l.isEnabled.get());
    	;
    	
    	file.set("Piercing" + ".maxLevel", Config.p.maxLevel.get());
    	file.set("Piercing" + ".isEnabled", Config.p.isEnabled.get());
    	;
    	
    	file.set("Power" + ".maxLevel", Config.po.maxLevel.get());
    	file.set("Power" + ".isEnabled", Config.po.isEnabled.get());
    	;
    	
    	file.set("Protection Enchantments" + ".maxLevel", Config.pr.maxLevel.get());
    	file.set("Protection Enchantments" + ".isEnabled", Config.pr.isEnabled.get());
    	
    	file.set("Punch" + ".maxLevel", Config.pu.maxLevel.get());
    	file.set("Punch" + ".isEnabled", Config.pu.isEnabled.get());
    	;
    	
    	file.set("Quick Charge" + ".maxLevel", Config.q.maxLevel.get());
    	file.set("Quick Charge" + ".isEnabled", Config.q.isEnabled.get());
    	;
    	
    	file.set("Respiration" + ".maxLevel", Config.r.maxLevel.get());
    	file.set("Respiration" + ".isEnabled", Config.r.isEnabled.get());
    	;
    	
    	file.set("Riptide" + ".maxLevel", Config.ri.maxLevel.get());
    	file.set("Riptide" + ".isEnabled", Config.ri.isEnabled.get());
    	;
    	
    	file.set("Soul Speed" + ".maxLevel", Config.ss.maxLevel.get());
    	file.set("Soul Speed" + ".isEnabled", Config.ss.isEnabled.get());
    	;
    	
    	file.set("Sweeping" + ".maxLevel", Config.s.maxLevel.get());
    	file.set("Sweeping" + ".isEnabled", Config.s.isEnabled.get());
    	;
    	
    	file.set("Thorns" + ".maxLevel", Config.t.maxLevel.get());
    	file.set("Thorns" + ".isEnabled", Config.t.isEnabled.get());
    	;
    	
    	file.set("Unbreaking" + ".maxLevel", Config.u.maxLevel.get());
    	file.set("Unbreaking" + ".isEnabled", Config.u.isEnabled.get());
    	

    	
    	file.set("Heavy" + ".maxLevel", Config.he.maxLevel.get());
    	file.set("Heavy" + ".isEnabled", Config.he.isEnabled.get());
    	
    	file.set("Reinforcement" + ".maxLevel", Config.rei.maxLevel.get());
    	file.set("Reinforcement" + ".isEnabled", Config.rei.isEnabled.get());
    	
    	file.set("Temper" + ".maxLevel", Config.te.maxLevel.get());
    	file.set("Temper" + ".isEnabled", Config.te.isEnabled.get());

    	file.set("Cat Vision" + ".isEnabled", Config.c.isEnabled.get());
    	
    	file.set("Flight" + ".maxLevel", Config.fl.maxLevel.get());
    	file.set("Flight" + ".isEnabled", Config.fl.isEnabled.get());
    	
    	file.set("Health Boost" + ".maxLevel", Config.h.maxLevel.get());
    	file.set("Health Boost" + ".isEnabled", Config.h.isEnabled.get());
    	
    	file.set("Step Assist" + ".maxLevel", Config.sa.maxLevel.get());
    	file.set("Step Assist" + ".isEnabled", Config.sa.isEnabled.get());

//    	file.set("Fast Hopper" + ".isEnabled", Config.f.isEnabled.get());
    	
    	file.set("Block Reach" + ".maxLevel", Config.b.maxLevel.get());
    	file.set("Block Reach" + ".isEnabled", Config.b.isEnabled.get());
    	
    	file.set("Double Break" + ".maxLevel", Config.d.maxLevel.get());
    	file.set("Double Break" + ".isEnabled", Config.d.isEnabled.get());
    	
    	file.set("Replanting" + ".isEnabled", Config.re.isEnabled.get());
    	
    	file.set("Attack Reach" + ".maxLevel", Config.a.maxLevel.get());
    	file.set("Attack Reach" + ".isEnabled", Config.a.isEnabled.get());
    	
    	file.set("Critical" + ".maxLevel", Config.cr.maxLevel.get());
    	file.set("Critical" + ".isEnabled", Config.cr.isEnabled.get());
    	
    	file.set("Freezing" + ".maxLevel", Config.fr.maxLevel.get());
    	file.set("Freezing" + ".isEnabled", Config.fr.isEnabled.get());

    	file.set("Enchanter Villager" + ".isEnabled", Config.v.isEnabled.get());
    	
    	Config.SyncedServerConfig.setConfig(file);
    	Config.SyncedServerConfig.save();
    	
    	file.save();
    	
    	
    	
    	
    	this.minecraft.displayGuiScreen((Screen)null);
    	
    	
    }
    
    private void refresh()
    {
    	final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
    	file.load();
    	file.set("Damage Enchantments" + ".maxLevel", Config.de.maxLevel.get());
    	file.set("Damage Enchantments" + ".isEnabled", Config.de.isEnabled.get());
    	
    	file.set("Efficiency" + ".maxLevel", Config.e.maxLevel.get());
    	file.set("Efficiency" + ".isEnabled", Config.e.isEnabled.get());
    	;
    	
    	file.set("Fire Aspect" + ".maxLevel", Config.fa.maxLevel.get());
    	file.set("Fire Aspect" + ".isEnabled", Config.fa.isEnabled.get());
    	;
    	
    	file.set("Impaling" + ".maxLevel", Config.i.maxLevel.get());
    	file.set("Impaling" + ".isEnabled", Config.i.isEnabled.get());
    	;
    	
    	file.set("Knockback" + ".maxLevel", Config.k.maxLevel.get());
    	file.set("Knockback" + ".isEnabled", Config.k.isEnabled.get());
    	;
    	
    	file.set("Loot Bonus Enchantments" + ".maxLevel", Config.lbe.maxLevel.get());
    	file.set("Loot Bonus Enchantments" + ".isEnabled", Config.lbe.isEnabled.get());
    	;
    	
    	file.set("Loyalty" + ".maxLevel", Config.lo.maxLevel.get());
    	file.set("Loyalty" + ".isEnabled", Config.lo.isEnabled.get());
    	;
    	
    	file.set("Lure" + ".maxLevel", Config.l.maxLevel.get());
    	file.set("Lure" + ".isEnabled", Config.l.isEnabled.get());
    	;
    	
    	file.set("Piercing" + ".maxLevel", Config.p.maxLevel.get());
    	file.set("Piercing" + ".isEnabled", Config.p.isEnabled.get());
    	;
    	
    	file.set("Power" + ".maxLevel", Config.po.maxLevel.get());
    	file.set("Power" + ".isEnabled", Config.po.isEnabled.get());
    	;
    	
    	file.set("Protection Enchantments" + ".maxLevel", Config.pr.maxLevel.get());
    	file.set("Protection Enchantments" + ".isEnabled", Config.pr.isEnabled.get());
    	
    	file.set("Punch" + ".maxLevel", Config.pu.maxLevel.get());
    	file.set("Punch" + ".isEnabled", Config.pu.isEnabled.get());
    	;
    	
    	file.set("Quick Charge" + ".maxLevel", Config.q.maxLevel.get());
    	file.set("Quick Charge" + ".isEnabled", Config.q.isEnabled.get());
    	;
    	
    	file.set("Respiration" + ".maxLevel", Config.r.maxLevel.get());
    	file.set("Respiration" + ".isEnabled", Config.r.isEnabled.get());
    	;
    	
    	file.set("Riptide" + ".maxLevel", Config.ri.maxLevel.get());
    	file.set("Riptide" + ".isEnabled", Config.ri.isEnabled.get());
    	;
    	
    	file.set("Soul Speed" + ".maxLevel", Config.ss.maxLevel.get());
    	file.set("Soul Speed" + ".isEnabled", Config.ss.isEnabled.get());
    	;
    	
    	file.set("Sweeping" + ".maxLevel", Config.s.maxLevel.get());
    	file.set("Sweeping" + ".isEnabled", Config.s.isEnabled.get());
    	;
    	
    	file.set("Thorns" + ".maxLevel", Config.t.maxLevel.get());
    	file.set("Thorns" + ".isEnabled", Config.t.isEnabled.get());
    	;
    	
    	file.set("Unbreaking" + ".maxLevel", Config.u.maxLevel.get());
    	file.set("Unbreaking" + ".isEnabled", Config.u.isEnabled.get());
    	

    	
    	file.set("Heavy" + ".maxLevel", Config.he.maxLevel.get());
    	file.set("Heavy" + ".isEnabled", Config.he.isEnabled.get());
    	
    	file.set("Reinforcement" + ".maxLevel", Config.rei.maxLevel.get());
    	file.set("Reinforcement" + ".isEnabled", Config.rei.isEnabled.get());
    	
    	file.set("Temper" + ".maxLevel", Config.te.maxLevel.get());
    	file.set("Temper" + ".isEnabled", Config.te.isEnabled.get());

    	file.set("Cat Vision" + ".isEnabled", Config.c.isEnabled.get());
    	
    	file.set("Flight" + ".maxLevel", Config.fl.maxLevel.get());
    	file.set("Flight" + ".isEnabled", Config.fl.isEnabled.get());
    	
    	file.set("Health Boost" + ".maxLevel", Config.h.maxLevel.get());
    	file.set("Health Boost" + ".isEnabled", Config.h.isEnabled.get());
    	
    	file.set("Step Assist" + ".maxLevel", Config.sa.maxLevel.get());
    	file.set("Step Assist" + ".isEnabled", Config.sa.isEnabled.get());

//    	file.set("Fast Hopper" + ".isEnabled", Config.f.isEnabled.get());
    	
    	file.set("Block Reach" + ".maxLevel", Config.b.maxLevel.get());
    	file.set("Block Reach" + ".isEnabled", Config.b.isEnabled.get());
    	
    	file.set("Double Break" + ".maxLevel", Config.d.maxLevel.get());
    	file.set("Double Break" + ".isEnabled", Config.d.isEnabled.get());

    	file.set("Replanting" + ".isEnabled", Config.re.isEnabled.get());
    	
    	file.set("Attack Reach" + ".maxLevel", Config.a.maxLevel.get());
    	file.set("Attack Reach" + ".isEnabled", Config.a.isEnabled.get());
    	
    	file.set("Critical" + ".maxLevel", Config.cr.maxLevel.get());
    	file.set("Critical" + ".isEnabled", Config.cr.isEnabled.get());
    	
    	file.set("Freezing" + ".maxLevel", Config.fr.maxLevel.get());
    	file.set("Freezing" + ".isEnabled", Config.fr.isEnabled.get());

    	file.set("Enchanter Villager" + ".isEnabled", Config.v.isEnabled.get());
    	
    	Config.SyncedServerConfig.setConfig(file);
    	Config.SyncedServerConfig.save();
    	
    	file.save();
    	
    	this.minecraft.displayGuiScreen(new ConfigMenu());
    }
}
