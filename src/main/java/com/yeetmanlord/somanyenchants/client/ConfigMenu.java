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

	/*
	 * Thank You So Much Leo3148 For Giving A Tutorial For The Config Screen! :D
	 */

	private static final int TITLE_HEIGHT = 8;

	private static final int OPTIONS_LIST_TOP_HEIGHT = 24;

	private static final int OPTIONS_LIST_BOTTOM_OFFSET = 32;

	private static final int OPTIONS_LIST_ITEM_HEIGHT = 25;

	private static final int BUTTON_WIDTH = 200;

	private static final int BUTTON_HEIGHT = 20;

	private static final int DONE_BUTTON_TOP_OFFSET = 26;

	private OptionsRowList optionsRowList;

	public ConfigMenu() {
		super(new TranslationTextComponent("so_many_enchants.screen.config"));
	}

	@Override
	protected void init() {
		final CommentedFileConfig file = CommentedFileConfig
				.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync()
				.autosave().writingMode(WritingMode.REPLACE).build();
		file.load();
		Config.load(file);
		this.optionsRowList = new OptionsRowList(this.minecraft, this.width, this.height, OPTIONS_LIST_TOP_HEIGHT,
				this.height - OPTIONS_LIST_BOTTOM_OFFSET, OPTIONS_LIST_ITEM_HEIGHT);

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.de", 1,
				Config.damageEnchantments.absoluteMax, 1, get -> (double) Config.damageEnchantments.maxLevel.get(),
				(set, val) -> Config.damageEnchantments.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.de") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.damageEnchantments.isEnabled.get(),
						(set, val) -> Config.damageEnchantments.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.e", 1,
				Config.efficiency.absoluteMax, 1, get -> (double) Config.efficiency.maxLevel.get(),
				(set, val) -> Config.efficiency.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.e") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.efficiency.isEnabled.get(),
						(set, val) -> Config.efficiency.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fa", 1,
				Config.fireAspect.absoluteMax, 1, get -> (double) Config.fireAspect.maxLevel.get(),
				(set, val) -> Config.fireAspect.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.fa") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.fireAspect.isEnabled.get(),
						(set, val) -> Config.fireAspect.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.i", 1,
				Config.impaling.absoluteMax, 1, get -> (double) Config.impaling.maxLevel.get(),
				(set, val) -> Config.impaling.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.i") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.impaling.isEnabled.get(),
						(set, val) -> Config.impaling.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.k", 1,
				Config.knockback.absoluteMax, 1, get -> (double) Config.knockback.maxLevel.get(),
				(set, val) -> Config.knockback.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.k") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.knockback.isEnabled.get(),
						(set, val) -> Config.knockback.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lbe", 1,
				Config.lootBonusEnchantments.absoluteMax, 1, get -> (double) Config.lootBonusEnchantments.maxLevel.get(),
				(set, val) -> Config.lootBonusEnchantments.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.lbe") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.lootBonusEnchantments.isEnabled.get(),
						(set, val) -> Config.lootBonusEnchantments.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lo", 1,
				Config.loyalty.absoluteMax, 1, get -> (double) Config.loyalty.maxLevel.get(),
				(set, val) -> Config.loyalty.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.lo") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.loyalty.isEnabled.get(),
						(set, val) -> Config.loyalty.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.l", 1,
				Config.lure.absoluteMax, 1, get -> (double) Config.lure.maxLevel.get(),
				(set, val) -> Config.lure.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.l") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.lure.isEnabled.get(),
						(set, val) -> Config.lure.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.p", 1,
				Config.piercing.absoluteMax, 1, get -> (double) Config.piercing.maxLevel.get(),
				(set, val) -> Config.piercing.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.p") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.piercing.isEnabled.get(),
						(set, val) -> Config.piercing.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.po", 1,
				Config.power.absoluteMax, 1, get -> (double) Config.power.maxLevel.get(),
				(set, val) -> Config.power.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.po") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.power.isEnabled.get(),
						(set, val) -> Config.power.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.pr", 1,
				Config.protectionEnchantments.absoluteMax, 1, get -> (double) Config.protectionEnchantments.maxLevel.get(),
				(set, val) -> Config.protectionEnchantments.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.pr") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.protectionEnchantments.isEnabled.get(),
						(set, val) -> Config.protectionEnchantments.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.pu", 1,
				Config.punch.absoluteMax, 1, get -> (double) Config.punch.maxLevel.get(),
				(set, val) -> Config.punch.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.pu") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.punch.isEnabled.get(),
						(set, val) -> Config.punch.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.q", 1,
				Config.quickCharge.absoluteMax, 1, get -> (double) Config.quickCharge.maxLevel.get(),
				(set, val) -> Config.quickCharge.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.q") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.quickCharge.isEnabled.get(),
						(set, val) -> Config.quickCharge.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.r", 1,
				Config.respiration.absoluteMax, 1, get -> (double) Config.respiration.maxLevel.get(),
				(set, val) -> Config.respiration.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.r") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.respiration.isEnabled.get(),
						(set, val) -> Config.respiration.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.ri", 1,
				Config.riptide.absoluteMax, 1, get -> (double) Config.riptide.maxLevel.get(),
				(set, val) -> Config.riptide.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.ri") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.riptide.isEnabled.get(),
						(set, val) -> Config.riptide.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.ss", 1,
				Config.soulSpeed.absoluteMax, 1, get -> (double) Config.soulSpeed.maxLevel.get(),
				(set, val) -> Config.soulSpeed.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.ss") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.soulSpeed.isEnabled.get(),
						(set, val) -> Config.soulSpeed.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.s", 1,
				Config.sweeping.absoluteMax, 1, get -> (double) Config.sweeping.maxLevel.get(),
				(set, val) -> Config.sweeping.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.s") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.sweeping.isEnabled.get(),
						(set, val) -> Config.sweeping.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.t", 1,
				Config.thorns.absoluteMax, 1, get -> (double) Config.thorns.maxLevel.get(),
				(set, val) -> Config.thorns.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.t") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.thorns.isEnabled.get(),
						(set, val) -> Config.thorns.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.u", 1,
				Config.unbreaking.absoluteMax, 1, get -> (double) Config.unbreaking.maxLevel.get(),
				(set, val) -> Config.unbreaking.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.u") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.vanilla",
						get -> (boolean) Config.unbreaking.isEnabled.get(),
						(set, val) -> Config.unbreaking.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.he", 1,
				Config.heavyArmor.absoluteMax, 1, get -> (double) Config.heavyArmor.maxLevel.get(),
				(set, val) -> Config.heavyArmor.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.he") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.heavyArmor.isEnabled.get(),
						(set, val) -> Config.heavyArmor.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.rei", 1,
				Config.reinforcement.absoluteMax, 1, get -> (double) Config.reinforcement.maxLevel.get(),
				(set, val) -> Config.reinforcement.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.rei") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.reinforcement.isEnabled.get(),
						(set, val) -> Config.reinforcement.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.te", 1,
				Config.temper.absoluteMax, 1, get -> (double) Config.temper.maxLevel.get(),
				(set, val) -> Config.temper.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.te") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.temper.isEnabled.get(),
						(set, val) -> Config.temper.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.c",
				get -> (boolean) Config.catVision.isEnabled.get(), (set, val) -> Config.catVision.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fl", 1,
				Config.flight.absoluteMax, 1, get -> (double) Config.flight.maxLevel.get(),
				(set, val) -> Config.flight.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.fl") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.flight.isEnabled.get(),
						(set, val) -> Config.flight.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.h", 1,
				Config.healthBoost.absoluteMax, 1, get -> (double) Config.healthBoost.maxLevel.get(),
				(set, val) -> Config.healthBoost.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.h") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.healthBoost.isEnabled.get(),
						(set, val) -> Config.healthBoost.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.sa", 1,
				Config.stepAssist.absoluteMax, 1, get -> (double) Config.stepAssist.maxLevel.get(),
				(set, val) -> Config.stepAssist.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.sa") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.stepAssist.isEnabled.get(),
						(set, val) -> Config.stepAssist.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.f",
				get -> (boolean) Config.fastHopper.isEnabled.get(), (set, val) -> Config.fastHopper.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.b", 1,
				Config.blockReach.absoluteMax, 1, get -> (double) Config.blockReach.maxLevel.get(),
				(set, val) -> Config.blockReach.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.b") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.blockReach.isEnabled.get(),
						(set, val) -> Config.blockReach.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.d", 1,
				Config.doubleBreak.absoluteMax, 1, get -> (double) Config.doubleBreak.maxLevel.get(),
				(set, val) -> Config.doubleBreak.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.d") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.doubleBreak.isEnabled.get(),
						(set, val) -> Config.doubleBreak.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.re",
				get -> (boolean) Config.replanting.isEnabled.get(), (set, val) -> Config.replanting.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.a", 1,
				Config.attackReach.absoluteMax, 1, get -> (double) Config.attackReach.maxLevel.get(),
				(set, val) -> Config.attackReach.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.a") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.attackReach.isEnabled.get(),
						(set, val) -> Config.attackReach.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.cr", 1,
				Config.critical.absoluteMax, 1, get -> (double) Config.critical.maxLevel.get(),
				(set, val) -> Config.critical.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.cr") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.critical.isEnabled.get(),
						(set, val) -> Config.critical.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fr", 1,
				Config.freezing.absoluteMax, 1, get -> (double) Config.freezing.maxLevel.get(),
				(set, val) -> Config.freezing.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(
						I18n.format("so_many_enchants.screen.config.max_level.fr") + ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.freezing.isEnabled.get(),
						(set, val) -> Config.freezing.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.v",
				get -> (boolean) Config.villager.isEnabled.get(), (set, val) -> Config.villager.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.cs",
				get -> (boolean) Config.cavernousStorage.isEnabled.get(), (set, val) -> Config.cavernousStorage.isEnabled.set(val.booleanValue())));

		this.optionsRowList.addOption(new BooleanOption("so_many_enchants.screen.config.enabled.cf",
				get -> (boolean) Config.camouflage.isEnabled.get(), (set, val) -> Config.camouflage.isEnabled.set(val.booleanValue())));


		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fs", 1,
				Config.fastSmelt.absoluteMax, 1, get -> (double) Config.fastSmelt.maxLevel.get(),
				(set, val) -> Config.fastSmelt.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(I18n.format("so_many_enchants.screen.config.max_level.fs")
						+ ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.fastSmelt.isEnabled.get(),
						(set, val) -> Config.fastSmelt.isEnabled.set(val.booleanValue())));
		

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fuelE", 1,
				Config.fuelEfficient.absoluteMax, 1, get -> (double) Config.fuelEfficient.maxLevel.get(),
				(set, val) -> Config.fuelEfficient.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(I18n.format("so_many_enchants.screen.config.max_level.fuelE")
						+ ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.fuelEfficient.isEnabled.get(),
						(set, val) -> Config.fuelEfficient.isEnabled.set(val.booleanValue())));
		

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.eExp", 1,
				Config.extraExperience.absoluteMax, 1, get -> (double) Config.extraExperience.maxLevel.get(),
				(set, val) -> Config.extraExperience.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(I18n.format("so_many_enchants.screen.config.max_level.eExp")
						+ ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.extraExperience.isEnabled.get(),
						(set, val) -> Config.extraExperience.isEnabled.set(val.booleanValue())));
		

		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.hb", 1,
				Config.heavyBlade.absoluteMax, 1, get -> (double) Config.heavyBlade.maxLevel.get(),
				(set, val) -> Config.heavyBlade.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(I18n.format("so_many_enchants.screen.config.max_level.hb")
						+ ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.heavyBlade.isEnabled.get(),
						(set, val) -> Config.heavyBlade.isEnabled.set(val.booleanValue())));
		
		this.optionsRowList.addOption(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lightBlade", 1,
				Config.lightBlade.absoluteMax, 1, get -> (double) Config.lightBlade.maxLevel.get(),
				(set, val) -> Config.lightBlade.maxLevel.set(val.intValue()),
				(gs, option) -> new StringTextComponent(I18n.format("so_many_enchants.screen.config.max_level.lightBlade")
						+ ": " + (int) option.get(gs))),
				new BooleanOption("so_many_enchants.screen.config.enabled.modded",
						get -> (boolean) Config.lightBlade.isEnabled.get(),
						(set, val) -> Config.lightBlade.isEnabled.set(val.booleanValue())));

		this.children.add(this.optionsRowList);

		this.addButton(new Button((this.width - BUTTON_WIDTH * 2) / 2, this.height - DONE_BUTTON_TOP_OFFSET,
				BUTTON_WIDTH, BUTTON_HEIGHT, new TranslationTextComponent("gui.done"), button -> this.closeScreen()));

		this.addButton(new Button((this.width - BUTTON_WIDTH / 3 + 100) / 2, this.height - DONE_BUTTON_TOP_OFFSET,
				BUTTON_WIDTH, BUTTON_HEIGHT, new TranslationTextComponent("so_many_enchants.screen.config.default"),
				button -> this.setDefault()));

		file.close();
	}

	/**
	 * 
	 */
	private void setDefault() {
		Config.damageEnchantments.maxLevel.set(10);
		Config.damageEnchantments.isEnabled.set(true);

		Config.efficiency.maxLevel.set(10);
		Config.efficiency.isEnabled.set(true);

		Config.fireAspect.maxLevel.set(10);
		Config.fireAspect.isEnabled.set(true);

		Config.impaling.maxLevel.set(10);
		Config.impaling.isEnabled.set(true);

		Config.knockback.maxLevel.set(10);
		Config.knockback.isEnabled.set(true);

		Config.lootBonusEnchantments.maxLevel.set(10);
		Config.lootBonusEnchantments.isEnabled.set(true);

		Config.loyalty.maxLevel.set(5);
		Config.loyalty.isEnabled.set(true);

		Config.lure.maxLevel.set(5);
		Config.lure.isEnabled.set(true);

		Config.piercing.maxLevel.set(10);
		Config.piercing.isEnabled.set(true);

		Config.power.maxLevel.set(10);
		Config.power.isEnabled.set(true);

		Config.protectionEnchantments.maxLevel.set(10);
		Config.protectionEnchantments.isEnabled.set(true);

		Config.punch.maxLevel.set(10);
		Config.punch.isEnabled.set(true);

		Config.quickCharge.maxLevel.set(5);
		Config.quickCharge.isEnabled.set(true);

		Config.respiration.maxLevel.set(5);
		Config.respiration.isEnabled.set(true);

		Config.riptide.maxLevel.set(5);
		Config.riptide.isEnabled.set(true);

		Config.soulSpeed.maxLevel.set(5);
		Config.soulSpeed.isEnabled.set(true);

		Config.sweeping.maxLevel.set(10);
		Config.sweeping.isEnabled.set(true);

		Config.thorns.maxLevel.set(10);
		Config.thorns.isEnabled.set(true);

		Config.unbreaking.maxLevel.set(10);
		Config.unbreaking.isEnabled.set(true);

		Config.heavyArmor.maxLevel.set(1);
		Config.heavyArmor.isEnabled.set(true);

		Config.reinforcement.maxLevel.set(1);
		Config.reinforcement.isEnabled.set(true);

		Config.temper.maxLevel.set(1);
		Config.temper.isEnabled.set(true);

		Config.catVision.maxLevel.set(1);
		Config.catVision.isEnabled.set(true);

		Config.flight.maxLevel.set(3);
		Config.flight.isEnabled.set(false);

		Config.healthBoost.maxLevel.set(5);
		Config.healthBoost.isEnabled.set(true);

		Config.stepAssist.maxLevel.set(1);
		Config.stepAssist.isEnabled.set(false);

		Config.fastHopper.isEnabled.set(true);

		Config.blockReach.maxLevel.set(3);
		Config.blockReach.isEnabled.set(true);

		Config.doubleBreak.maxLevel.set(5);
		Config.doubleBreak.isEnabled.set(false);

		Config.replanting.maxLevel.set(1);
		Config.replanting.isEnabled.set(true);

		Config.attackReach.maxLevel.set(1);
		Config.attackReach.isEnabled.set(false);

		Config.critical.maxLevel.set(5);
		Config.critical.isEnabled.set(false);

		Config.freezing.maxLevel.set(3);
		Config.freezing.isEnabled.set(true);

		Config.villager.isEnabled.set(true);

		Config.cavernousStorage.isEnabled.set(true);

		Config.camouflage.isEnabled.set(true);
		
		Config.heavyBlade.maxLevel.set(5);
		Config.heavyBlade.isEnabled.set(true);
		
		Config.lightBlade.maxLevel.set(3);
		Config.lightBlade.isEnabled.set(true);

		Config.fastSmelt.maxLevel.set(1);
		Config.fastSmelt.isEnabled.set(true);
		
		Config.fuelEfficient.maxLevel.set(1);
		Config.fuelEfficient.isEnabled.set(true);
		
		Config.extraExperience.maxLevel.set(1);
		Config.extraExperience.isEnabled.set(true);
		this.refresh();
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		this.optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
		drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void closeScreen() {
		final CommentedFileConfig file = CommentedFileConfig
				.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync()
				.autosave().writingMode(WritingMode.REPLACE).build();
		file.load();
		Config.save(file);

		Config.SyncedServerConfig.setConfig(file);
		Config.SyncedServerConfig.save();

		file.save();

		this.minecraft.displayGuiScreen((Screen) null);

	}

	private void refresh() {
		final CommentedFileConfig file = CommentedFileConfig
				.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync()
				.autosave().writingMode(WritingMode.REPLACE).build();
		file.load();
		Config.save(file);

		Config.SyncedServerConfig.setConfig(file);
		Config.SyncedServerConfig.save();

		file.save();

		this.minecraft.displayGuiScreen(new ConfigMenu());
	}
}
