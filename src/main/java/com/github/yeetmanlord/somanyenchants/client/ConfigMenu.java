package com.github.yeetmanlord.somanyenchants.client;

import com.electronwill.nightconfig.core.io.WritingException;
import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.CycleOption;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

	private OptionsList optionsRowList;

	public ConfigMenu() {

		super(new TranslatableComponent("so_many_enchants.screen.config"));

	}

	private static void setMaxLevel(EnchantmentConfig config, double val) {

		try {
			config.maxLevel.set((int) val);
		}
		catch (WritingException exc) {
			SoManyEnchants.LOGGER.info("Could not save " + config.name + " Because the file is in use.");
		}

	}

	private static void setIsEnabled(EnchantmentConfig config, boolean value) {

		try {
			config.isEnabled.set(value);
		}
		catch (WritingException exc) {
			SoManyEnchants.LOGGER.info("Could not save " + config.name + " config because the file is in use.");
		}

	}

	@Override
	protected void init() {

		this.optionsRowList = new OptionsList(this.minecraft, this.width, this.height, OPTIONS_LIST_TOP_HEIGHT, this.height - OPTIONS_LIST_BOTTOM_OFFSET, OPTIONS_LIST_ITEM_HEIGHT);

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.de", 1, Config.damageEnchantments.absoluteMax, 1, get -> (double) Config.damageEnchantments.maxLevel.get(), (set, val) -> setMaxLevel(Config.damageEnchantments, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.de") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.damageEnchantments.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.damageEnchantments, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.e", 1, Config.efficiency.absoluteMax, 1, get -> (double) Config.efficiency.maxLevel.get(), (set, val) -> setMaxLevel(Config.efficiency, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.e") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.efficiency.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.efficiency, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.fa", 1, Config.fireAspect.absoluteMax, 1, get -> (double) Config.fireAspect.maxLevel.get(), (set, val) -> setMaxLevel(Config.fireAspect, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.fa") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.fireAspect.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.fireAspect, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.i", 1, Config.impaling.absoluteMax, 1, get -> (double) Config.impaling.maxLevel.get(), (set, val) -> setMaxLevel(Config.impaling, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.i") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.impaling.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.impaling, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.k", 1, Config.knockback.absoluteMax, 1, get -> (double) Config.knockback.maxLevel.get(), (set, val) -> setMaxLevel(Config.knockback, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.k") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.knockback.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.knockback, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.lbe", 1, Config.lootBonusEnchantments.absoluteMax, 1, get -> (double) Config.lootBonusEnchantments.maxLevel.get(), (set, val) -> setMaxLevel(Config.lootBonusEnchantments, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.lbe") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.lootBonusEnchantments.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.lootBonusEnchantments, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.lo", 1, Config.loyalty.absoluteMax, 1, get -> (double) Config.loyalty.maxLevel.get(), (set, val) -> setMaxLevel(Config.loyalty, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.lo") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.loyalty.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.loyalty, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.l", 1, Config.lure.absoluteMax, 1, get -> (double) Config.lure.maxLevel.get(), (set, val) -> setMaxLevel(Config.lure, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.l") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.lure.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.lure, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.p", 1, Config.piercing.absoluteMax, 1, get -> (double) Config.piercing.maxLevel.get(), (set, val) -> setMaxLevel(Config.piercing, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.p") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.piercing.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.piercing, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.po", 1, Config.power.absoluteMax, 1, get -> (double) Config.power.maxLevel.get(), (set, val) -> setMaxLevel(Config.power, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.po") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.power.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.power, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.pr", 1, Config.protectionEnchantments.absoluteMax, 1, get -> (double) Config.protectionEnchantments.maxLevel.get(), (set, val) -> setMaxLevel(Config.protectionEnchantments, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.pr") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.protectionEnchantments.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.protectionEnchantments, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.pu", 1, Config.punch.absoluteMax, 1, get -> (double) Config.punch.maxLevel.get(), (set, val) -> setMaxLevel(Config.punch, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.pu") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.punch.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.punch, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.q", 1, Config.quickCharge.absoluteMax, 1, get -> (double) Config.quickCharge.maxLevel.get(), (set, val) -> setMaxLevel(Config.quickCharge, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.q") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.quickCharge.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.quickCharge, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.r", 1, Config.respiration.absoluteMax, 1, get -> (double) Config.respiration.maxLevel.get(), (set, val) -> setMaxLevel(Config.respiration, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.r") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.respiration.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.respiration, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.ri", 1, Config.riptide.absoluteMax, 1, get -> (double) Config.riptide.maxLevel.get(), (set, val) -> setMaxLevel(Config.riptide, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.ri") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.riptide.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.riptide, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.ss", 1, Config.soulSpeed.absoluteMax, 1, get -> (double) Config.soulSpeed.maxLevel.get(), (set, val) -> setMaxLevel(Config.soulSpeed, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.ss") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.soulSpeed.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.soulSpeed, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.s", 1, Config.sweeping.absoluteMax, 1, get -> (double) Config.sweeping.maxLevel.get(), (set, val) -> setMaxLevel(Config.sweeping, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.s") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.sweeping.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.sweeping, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.t", 1, Config.thorns.absoluteMax, 1, get -> (double) Config.thorns.maxLevel.get(), (set, val) -> setMaxLevel(Config.thorns, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.t") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.thorns.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.thorns, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.u", 1, Config.unbreaking.absoluteMax, 1, get -> (double) Config.unbreaking.maxLevel.get(), (set, val) -> setMaxLevel(Config.unbreaking, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.u") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.unbreaking.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.unbreaking, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.he", 1, Config.heavyArmor.absoluteMax, 1, get -> (double) Config.heavyArmor.maxLevel.get(), (set, val) -> setMaxLevel(Config.heavyArmor, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.he") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.heavyArmor.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.heavyArmor, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.rei", 1, Config.reinforcement.absoluteMax, 1, get -> (double) Config.reinforcement.maxLevel.get(), (set, val) -> setMaxLevel(Config.reinforcement, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.rei") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.reinforcement.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.reinforcement, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.te", 1, Config.temper.absoluteMax, 1, get -> (double) Config.temper.maxLevel.get(), (set, val) -> setMaxLevel(Config.temper, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.te") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.temper.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.temper, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.c", get -> (boolean) Config.catVision.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.catVision, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.fl", 1, Config.flight.absoluteMax, 1, get -> (double) Config.flight.maxLevel.get(), (set, val) -> setMaxLevel(Config.flight, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.fl") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.flight.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.flight, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.h", 1, Config.healthBoost.absoluteMax, 1, get -> (double) Config.healthBoost.maxLevel.get(), (set, val) -> setMaxLevel(Config.healthBoost, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.h") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.healthBoost.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.healthBoost, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.sa", 1, Config.stepAssist.absoluteMax, 1, get -> (double) Config.stepAssist.maxLevel.get(), (set, val) -> setMaxLevel(Config.stepAssist, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.sa") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.stepAssist.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.stepAssist, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.f", get -> (boolean) Config.fastHopper.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.fastHopper, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.b", 1, Config.blockReach.absoluteMax, 1, get -> (double) Config.blockReach.maxLevel.get(), (set, val) -> setMaxLevel(Config.blockReach, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.b") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.blockReach.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.blockReach, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.d", 1, Config.doubleBreak.absoluteMax, 1, get -> (double) Config.doubleBreak.maxLevel.get(), (set, val) -> setMaxLevel(Config.doubleBreak, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.d") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.doubleBreak.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.doubleBreak, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.re", get -> (boolean) Config.replanting.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.replanting, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.a", 1, Config.attackReach.absoluteMax, 1, get -> (double) Config.attackReach.maxLevel.get(), (set, val) -> setMaxLevel(Config.attackReach, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.a") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.attackReach.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.attackReach, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.cr", 1, Config.critical.absoluteMax, 1, get -> (double) Config.critical.maxLevel.get(), (set, val) -> setMaxLevel(Config.critical, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.cr") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.critical.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.critical, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.fr", 1, Config.freezing.absoluteMax, 1, get -> (double) Config.freezing.maxLevel.get(), (set, val) -> setMaxLevel(Config.freezing, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.fr") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.freezing.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.freezing, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.v", get -> (boolean) Config.villager.isEnabled.get(), (options, set, val) -> {

			try {
				Config.villager.isEnabled.set(val);
			}
			catch (WritingException exc) {
				SoManyEnchants.LOGGER.info("Could not save villager config because the file is in use.");
			}

		}));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.cs", get -> (boolean) Config.cavernousStorage.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.cavernousStorage, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.cf", get -> (boolean) Config.camouflage.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.camouflage, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.fs", 1, Config.fastSmelt.absoluteMax, 1, get -> (double) Config.fastSmelt.maxLevel.get(), (set, val) -> setMaxLevel(Config.fastSmelt, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.fs") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.fastSmelt.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.fastSmelt, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.fuelE", 1, Config.fuelEfficient.absoluteMax, 1, get -> (double) Config.fuelEfficient.maxLevel.get(), (set, val) -> setMaxLevel(Config.fuelEfficient, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.fuelE") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.fuelEfficient.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.fuelEfficient, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.eExp", 1, Config.extraExperience.absoluteMax, 1, get -> (double) Config.extraExperience.maxLevel.get(), (set, val) -> setMaxLevel(Config.extraExperience, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.eExp") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.extraExperience.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.extraExperience, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.hb", 1, Config.heavyBlade.absoluteMax, 1, get -> (double) Config.heavyBlade.maxLevel.get(), (set, val) -> setMaxLevel(Config.heavyBlade, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.hb") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.heavyBlade.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.heavyBlade, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.lightBlade", 1, Config.lightBlade.absoluteMax, 1, get -> (double) Config.lightBlade.maxLevel.get(), (set, val) -> setMaxLevel(Config.lightBlade, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.lightBlade") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.lightBlade.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.lightBlade, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.blindness", get -> (boolean) Config.blindness.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.blindness, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.fireResistance", get -> (boolean) Config.fireResistance.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.fireResistance, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.haste", 1, Config.haste.absoluteMax, 1, get -> (double) Config.haste.maxLevel.get(), (set, val) -> setMaxLevel(Config.haste, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.haste") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.haste.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.haste, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.hunger", 1, Config.hunger.absoluteMax, 1, get -> (double) Config.hunger.maxLevel.get(), (set, val) -> setMaxLevel(Config.hunger, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.hunger") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.hunger.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.hunger, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.invisibility", get -> (boolean) Config.invisibility.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.invisibility, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.jumpBoost", 1, Config.jumpBoost.absoluteMax, 1, get -> (double) Config.jumpBoost.maxLevel.get(), (set, val) -> setMaxLevel(Config.jumpBoost, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.jumpBoost") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.jumpBoost.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.jumpBoost, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.miningFatigue", 1, Config.miningFatigue.absoluteMax, 1, get -> (double) Config.miningFatigue.maxLevel.get(), (set, val) -> setMaxLevel(Config.miningFatigue, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.miningFatigue") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.miningFatigue.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.miningFatigue, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.nausea", get -> (boolean) Config.nausea.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.nausea, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.poison", 1, Config.poison.absoluteMax, 1, get -> (double) Config.poison.maxLevel.get(), (set, val) -> setMaxLevel(Config.poison, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.poison") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.poison.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.poison, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.regeneration", 1, Config.regeneration.absoluteMax, 1, get -> (double) Config.regeneration.maxLevel.get(), (set, val) -> setMaxLevel(Config.regeneration, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.regeneration") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.regeneration.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.regeneration, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.resistance", 1, Config.resistance.absoluteMax, 1, get -> (double) Config.resistance.maxLevel.get(), (set, val) -> setMaxLevel(Config.resistance, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.resistance") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.resistance.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.resistance, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.saturation", 1, Config.saturation.absoluteMax, 1, get -> (double) Config.saturation.maxLevel.get(), (set, val) -> setMaxLevel(Config.saturation, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.saturation") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.saturation.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.saturation, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.slowFalling", get -> (boolean) Config.slowFalling.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.slowFalling, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.slowness", 1, Config.slowness.absoluteMax, 1, get -> (double) Config.slowness.maxLevel.get(), (set, val) -> setMaxLevel(Config.slowness, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.slowness") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.slowness.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.slowness, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.speed", 1, Config.speed.absoluteMax, 1, get -> (double) Config.speed.maxLevel.get(), (set, val) -> setMaxLevel(Config.speed, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.speed") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.speed.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.speed, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.strength", 1, Config.strength.absoluteMax, 1, get -> (double) Config.strength.maxLevel.get(), (set, val) -> setMaxLevel(Config.strength, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.strength") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.strength.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.strength, val)));

		this.optionsRowList.addBig(CycleOption.createOnOff("so_many_enchants.screen.config.enabled.waterBreathing", get -> (boolean) Config.waterBreathing.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.waterBreathing, val)));

		this.optionsRowList.addSmall(new ProgressOption("so_many_enchants.screen.config.max_level.weakness", 1, Config.weakness.absoluteMax, 1, get -> (double) Config.weakness.maxLevel.get(), (set, val) -> setMaxLevel(Config.weakness, val), (gs, option) -> new TextComponent(I18n.get("so_many_enchants.screen.config.max_level.weakness") + ": " + (int) option.get(gs))), CycleOption.createOnOff("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.weakness.isEnabled.get(), (options, set, val) -> setIsEnabled(Config.weakness, val)));

		this.addWidget(this.optionsRowList);

		this.addRenderableWidget(new Button((this.width - BUTTON_WIDTH * 2) / 2, this.height - DONE_BUTTON_TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslatableComponent("gui.done"), button -> this.onClose()));

		this.addRenderableWidget(new Button((this.width - BUTTON_WIDTH / 3 + 100) / 2, this.height - DONE_BUTTON_TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslatableComponent("so_many_enchants.screen.config.default"), button -> this.setDefault()));

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

		Config.blindness.maxLevel.set(1);
		Config.blindness.isEnabled.set(true);

		Config.fireResistance.maxLevel.set(1);
		Config.fireResistance.isEnabled.set(true);

		Config.haste.maxLevel.set(3);
		Config.haste.isEnabled.set(true);

		Config.hunger.maxLevel.set(3);
		Config.hunger.isEnabled.set(true);

		Config.invisibility.maxLevel.set(1);
		Config.invisibility.isEnabled.set(true);

		Config.jumpBoost.maxLevel.set(3);
		Config.jumpBoost.isEnabled.set(true);

		Config.miningFatigue.maxLevel.set(3);
		Config.miningFatigue.isEnabled.set(true);

		Config.nausea.maxLevel.set(1);
		Config.nausea.isEnabled.set(true);

		Config.poison.maxLevel.set(3);
		Config.poison.isEnabled.set(true);

		Config.regeneration.maxLevel.set(3);
		Config.regeneration.isEnabled.set(true);

		Config.resistance.maxLevel.set(3);
		Config.resistance.isEnabled.set(true);

		Config.saturation.maxLevel.set(3);
		Config.saturation.isEnabled.set(true);

		Config.slowFalling.maxLevel.set(1);
		Config.slowFalling.isEnabled.set(true);

		Config.slowness.maxLevel.set(3);
		Config.slowness.isEnabled.set(true);

		Config.speed.maxLevel.set(3);
		Config.speed.isEnabled.set(true);

		Config.strength.maxLevel.set(3);
		Config.strength.isEnabled.set(true);

		Config.waterBreathing.maxLevel.set(1);
		Config.waterBreathing.isEnabled.set(true);

		Config.weakness.maxLevel.set(3);
		Config.weakness.isEnabled.set(true);

		this.refresh();

	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {

		this.renderBackground(matrixStack);
		this.optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
		drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
		super.render(matrixStack, mouseX, mouseY, partialTicks);

	}

	@Override
	public void onClose() {

		this.minecraft.setScreen((Screen) null);

	}

	private void refresh() {

		this.minecraft.setScreen(new ConfigMenu());

	}

}
