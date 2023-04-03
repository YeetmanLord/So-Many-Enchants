package com.github.yeetmanlord.somanyenchants.client;

import java.io.IOException;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;
import com.mojang.blaze3d.matrix.MatrixStack;

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

	private static void setMaxLevel(EnchantmentConfig config, double val) {

		try {
			config.maxLevel.set((int) val);
		}
		catch (IOException exc) {
			SoManyEnchants.LOGGER.error("Could not save " + config.name + " config because the file is in use. Retrying...");
			setMaxLevel(config, val);
		}

	}

	private static void setIsEnabled(EnchantmentConfig config, boolean value) {

		try {
			config.isEnabled.set(value);
		}
		catch (IOException exc) {
			SoManyEnchants.LOGGER.error("Could not save " + config.name + " config because the file is in use. Retrying...");
			setIsEnabled(config, value);
		}

	}

	@Override
	protected void init() {

		this.optionsRowList = new OptionsRowList(this.minecraft, this.width, this.height, OPTIONS_LIST_TOP_HEIGHT, this.height - OPTIONS_LIST_BOTTOM_OFFSET, OPTIONS_LIST_ITEM_HEIGHT);

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.de", 1, Config.damageEnchantments.absoluteMax, 1, get -> (double) Config.damageEnchantments.maxLevel.get(), (set, val) -> setMaxLevel(Config.damageEnchantments, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.de") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.damageEnchantments.isEnabled.get(), (set, val) -> setIsEnabled(Config.damageEnchantments, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.e", 1, Config.efficiency.absoluteMax, 1, get -> (double) Config.efficiency.maxLevel.get(), (set, val) -> setMaxLevel(Config.efficiency, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.e") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.efficiency.isEnabled.get(), (set, val) -> setIsEnabled(Config.efficiency, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fa", 1, Config.fireAspect.absoluteMax, 1, get -> (double) Config.fireAspect.maxLevel.get(), (set, val) -> setMaxLevel(Config.fireAspect, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.fa") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.fireAspect.isEnabled.get(), (set, val) -> setIsEnabled(Config.fireAspect, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.i", 1, Config.impaling.absoluteMax, 1, get -> (double) Config.impaling.maxLevel.get(), (set, val) -> setMaxLevel(Config.impaling, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.i") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.impaling.isEnabled.get(), (set, val) -> setIsEnabled(Config.impaling, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.k", 1, Config.knockback.absoluteMax, 1, get -> (double) Config.knockback.maxLevel.get(), (set, val) -> setMaxLevel(Config.knockback, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.k") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.knockback.isEnabled.get(), (set, val) -> setIsEnabled(Config.knockback, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lbe", 1, Config.lootBonusEnchantments.absoluteMax, 1, get -> (double) Config.lootBonusEnchantments.maxLevel.get(), (set, val) -> setMaxLevel(Config.lootBonusEnchantments, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.lbe") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.lootBonusEnchantments.isEnabled.get(), (set, val) -> setIsEnabled(Config.lootBonusEnchantments, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lo", 1, Config.loyalty.absoluteMax, 1, get -> (double) Config.loyalty.maxLevel.get(), (set, val) -> setMaxLevel(Config.loyalty, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.lo") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.loyalty.isEnabled.get(), (set, val) -> setIsEnabled(Config.loyalty, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.l", 1, Config.lure.absoluteMax, 1, get -> (double) Config.lure.maxLevel.get(), (set, val) -> setMaxLevel(Config.lure, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.l") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.lure.isEnabled.get(), (set, val) -> setIsEnabled(Config.lure, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.p", 1, Config.piercing.absoluteMax, 1, get -> (double) Config.piercing.maxLevel.get(), (set, val) -> setMaxLevel(Config.piercing, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.p") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.piercing.isEnabled.get(), (set, val) -> setIsEnabled(Config.piercing, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.po", 1, Config.power.absoluteMax, 1, get -> (double) Config.power.maxLevel.get(), (set, val) -> setMaxLevel(Config.power, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.po") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.power.isEnabled.get(), (set, val) -> setIsEnabled(Config.power, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.pr", 1, Config.protectionEnchantments.absoluteMax, 1, get -> (double) Config.protectionEnchantments.maxLevel.get(), (set, val) -> setMaxLevel(Config.protectionEnchantments, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.pr") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.protectionEnchantments.isEnabled.get(), (set, val) -> setIsEnabled(Config.protectionEnchantments, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.pu", 1, Config.punch.absoluteMax, 1, get -> (double) Config.punch.maxLevel.get(), (set, val) -> setMaxLevel(Config.punch, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.pu") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.punch.isEnabled.get(), (set, val) -> setIsEnabled(Config.punch, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.q", 1, Config.quickCharge.absoluteMax, 1, get -> (double) Config.quickCharge.maxLevel.get(), (set, val) -> setMaxLevel(Config.quickCharge, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.q") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.quickCharge.isEnabled.get(), (set, val) -> setIsEnabled(Config.quickCharge, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.r", 1, Config.respiration.absoluteMax, 1, get -> (double) Config.respiration.maxLevel.get(), (set, val) -> setMaxLevel(Config.respiration, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.r") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.respiration.isEnabled.get(), (set, val) -> setIsEnabled(Config.respiration, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.ri", 1, Config.riptide.absoluteMax, 1, get -> (double) Config.riptide.maxLevel.get(), (set, val) -> setMaxLevel(Config.riptide, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.ri") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.riptide.isEnabled.get(), (set, val) -> setIsEnabled(Config.riptide, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.ss", 1, Config.soulSpeed.absoluteMax, 1, get -> (double) Config.soulSpeed.maxLevel.get(), (set, val) -> setMaxLevel(Config.soulSpeed, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.ss") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.soulSpeed.isEnabled.get(), (set, val) -> setIsEnabled(Config.soulSpeed, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.s", 1, Config.sweeping.absoluteMax, 1, get -> (double) Config.sweeping.maxLevel.get(), (set, val) -> setMaxLevel(Config.sweeping, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.s") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.sweeping.isEnabled.get(), (set, val) -> setIsEnabled(Config.sweeping, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.t", 1, Config.thorns.absoluteMax, 1, get -> (double) Config.thorns.maxLevel.get(), (set, val) -> setMaxLevel(Config.thorns, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.t") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.thorns.isEnabled.get(), (set, val) -> setIsEnabled(Config.thorns, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.u", 1, Config.unbreaking.absoluteMax, 1, get -> (double) Config.unbreaking.maxLevel.get(), (set, val) -> setMaxLevel(Config.unbreaking, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.u") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.vanilla", get -> (boolean) Config.unbreaking.isEnabled.get(), (set, val) -> setIsEnabled(Config.unbreaking, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.he", 1, Config.heavyArmor.absoluteMax, 1, get -> (double) Config.heavyArmor.maxLevel.get(), (set, val) -> setMaxLevel(Config.heavyArmor, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.he") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.heavyArmor.isEnabled.get(), (set, val) -> setIsEnabled(Config.heavyArmor, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.rei", 1, Config.reinforcement.absoluteMax, 1, get -> (double) Config.reinforcement.maxLevel.get(), (set, val) -> setMaxLevel(Config.reinforcement, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.rei") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.reinforcement.isEnabled.get(), (set, val) -> setIsEnabled(Config.reinforcement, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.te", 1, Config.temper.absoluteMax, 1, get -> (double) Config.temper.maxLevel.get(), (set, val) -> setMaxLevel(Config.temper, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.te") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.temper.isEnabled.get(), (set, val) -> setIsEnabled(Config.temper, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.c", get -> (boolean) Config.catVision.isEnabled.get(), (set, val) -> setIsEnabled(Config.catVision, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fl", 1, Config.flight.absoluteMax, 1, get -> (double) Config.flight.maxLevel.get(), (set, val) -> setMaxLevel(Config.flight, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.fl") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.flight.isEnabled.get(), (set, val) -> setIsEnabled(Config.flight, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.h", 1, Config.healthBoost.absoluteMax, 1, get -> (double) Config.healthBoost.maxLevel.get(), (set, val) -> setMaxLevel(Config.healthBoost, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.h") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.healthBoost.isEnabled.get(), (set, val) -> setIsEnabled(Config.healthBoost, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.sa", 1, Config.stepAssist.absoluteMax, 1, get -> (double) Config.stepAssist.maxLevel.get(), (set, val) -> setMaxLevel(Config.stepAssist, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.sa") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.stepAssist.isEnabled.get(), (set, val) -> setIsEnabled(Config.stepAssist, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.f", get -> (boolean) Config.fastHopper.isEnabled.get(), (set, val) -> setIsEnabled(Config.fastHopper, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.b", 1, Config.blockReach.absoluteMax, 1, get -> (double) Config.blockReach.maxLevel.get(), (set, val) -> setMaxLevel(Config.blockReach, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.b") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.blockReach.isEnabled.get(), (set, val) -> setIsEnabled(Config.blockReach, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.d", 1, Config.doubleBreak.absoluteMax, 1, get -> (double) Config.doubleBreak.maxLevel.get(), (set, val) -> setMaxLevel(Config.doubleBreak, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.d") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.doubleBreak.isEnabled.get(), (set, val) -> setIsEnabled(Config.doubleBreak, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.re", get -> (boolean) Config.replanting.isEnabled.get(), (set, val) -> setIsEnabled(Config.replanting, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.a", 1, Config.attackReach.absoluteMax, 1, get -> (double) Config.attackReach.maxLevel.get(), (set, val) -> setMaxLevel(Config.attackReach, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.a") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.attackReach.isEnabled.get(), (set, val) -> setIsEnabled(Config.attackReach, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.cr", 1, Config.critical.absoluteMax, 1, get -> (double) Config.critical.maxLevel.get(), (set, val) -> setMaxLevel(Config.critical, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.cr") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.critical.isEnabled.get(), (set, val) -> setIsEnabled(Config.critical, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fr", 1, Config.freezing.absoluteMax, 1, get -> (double) Config.freezing.maxLevel.get(), (set, val) -> setMaxLevel(Config.freezing, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.fr") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.freezing.isEnabled.get(), (set, val) -> setIsEnabled(Config.freezing, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.v", get -> (boolean) Config.villager.isEnabled.get(), (set, val) -> {

			try {
				Config.villager.isEnabled.set(val);
			}
			catch (IOException exc) {
				SoManyEnchants.LOGGER.info("Could not save villager config because the file is in use.");
			}

		}));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.cs", get -> (boolean) Config.cavernousStorage.isEnabled.get(), (set, val) -> setIsEnabled(Config.cavernousStorage, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.cf", get -> (boolean) Config.camouflage.isEnabled.get(), (set, val) -> setIsEnabled(Config.camouflage, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fs", 1, Config.fastSmelt.absoluteMax, 1, get -> (double) Config.fastSmelt.maxLevel.get(), (set, val) -> setMaxLevel(Config.fastSmelt, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.fs") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.fastSmelt.isEnabled.get(), (set, val) -> setIsEnabled(Config.fastSmelt, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.fuelE", 1, Config.fuelEfficient.absoluteMax, 1, get -> (double) Config.fuelEfficient.maxLevel.get(), (set, val) -> setMaxLevel(Config.fuelEfficient, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.fuelE") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.fuelEfficient.isEnabled.get(), (set, val) -> setIsEnabled(Config.fuelEfficient, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.eExp", 1, Config.extraExperience.absoluteMax, 1, get -> (double) Config.extraExperience.maxLevel.get(), (set, val) -> setMaxLevel(Config.extraExperience, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.eExp") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.extraExperience.isEnabled.get(), (set, val) -> setIsEnabled(Config.extraExperience, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.hb", 1, Config.heavyBlade.absoluteMax, 1, get -> (double) Config.heavyBlade.maxLevel.get(), (set, val) -> setMaxLevel(Config.heavyBlade, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.hb") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.heavyBlade.isEnabled.get(), (set, val) -> setIsEnabled(Config.heavyBlade, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.lightBlade", 1, Config.lightBlade.absoluteMax, 1, get -> (double) Config.lightBlade.maxLevel.get(), (set, val) -> setMaxLevel(Config.lightBlade, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.lightBlade") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.lightBlade.isEnabled.get(), (set, val) -> setIsEnabled(Config.lightBlade, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.blindness", get -> (boolean) Config.blindness.isEnabled.get(), (set, val) -> setIsEnabled(Config.blindness, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.fireResistance", get -> (boolean) Config.fireResistance.isEnabled.get(), (set, val) -> setIsEnabled(Config.fireResistance, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.haste", 1, Config.haste.absoluteMax, 1, get -> (double) Config.haste.maxLevel.get(), (set, val) -> setMaxLevel(Config.haste, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.haste") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.haste.isEnabled.get(), (set, val) -> setIsEnabled(Config.haste, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.hunger", 1, Config.hunger.absoluteMax, 1, get -> (double) Config.hunger.maxLevel.get(), (set, val) -> setMaxLevel(Config.hunger, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.hunger") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.hunger.isEnabled.get(), (set, val) -> setIsEnabled(Config.hunger, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.invisibility", get -> (boolean) Config.invisibility.isEnabled.get(), (set, val) -> setIsEnabled(Config.invisibility, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.jumpBoost", 1, Config.jumpBoost.absoluteMax, 1, get -> (double) Config.jumpBoost.maxLevel.get(), (set, val) -> setMaxLevel(Config.jumpBoost, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.jumpBoost") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.jumpBoost.isEnabled.get(), (set, val) -> setIsEnabled(Config.jumpBoost, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.miningFatigue", 1, Config.miningFatigue.absoluteMax, 1, get -> (double) Config.miningFatigue.maxLevel.get(), (set, val) -> setMaxLevel(Config.miningFatigue, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.miningFatigue") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.miningFatigue.isEnabled.get(), (set, val) -> setIsEnabled(Config.miningFatigue, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.nausea", get -> (boolean) Config.nausea.isEnabled.get(), (set, val) -> setIsEnabled(Config.nausea, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.poison", 1, Config.poison.absoluteMax, 1, get -> (double) Config.poison.maxLevel.get(), (set, val) -> setMaxLevel(Config.poison, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.poison") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.poison.isEnabled.get(), (set, val) -> setIsEnabled(Config.poison, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.regeneration", 1, Config.regeneration.absoluteMax, 1, get -> (double) Config.regeneration.maxLevel.get(), (set, val) -> setMaxLevel(Config.regeneration, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.regeneration") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.regeneration.isEnabled.get(), (set, val) -> setIsEnabled(Config.regeneration, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.resistance", 1, Config.resistance.absoluteMax, 1, get -> (double) Config.resistance.maxLevel.get(), (set, val) -> setMaxLevel(Config.resistance, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.resistance") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.resistance.isEnabled.get(), (set, val) -> setIsEnabled(Config.resistance, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.saturation", 1, Config.saturation.absoluteMax, 1, get -> (double) Config.saturation.maxLevel.get(), (set, val) -> setMaxLevel(Config.saturation, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.saturation") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.saturation.isEnabled.get(), (set, val) -> setIsEnabled(Config.saturation, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.slowFalling", get -> (boolean) Config.slowFalling.isEnabled.get(), (set, val) -> setIsEnabled(Config.slowFalling, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.slowness", 1, Config.slowness.absoluteMax, 1, get -> (double) Config.slowness.maxLevel.get(), (set, val) -> setMaxLevel(Config.slowness, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.slowness") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.slowness.isEnabled.get(), (set, val) -> setIsEnabled(Config.slowness, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.speed", 1, Config.speed.absoluteMax, 1, get -> (double) Config.speed.maxLevel.get(), (set, val) -> setMaxLevel(Config.speed, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.speed") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.speed.isEnabled.get(), (set, val) -> setIsEnabled(Config.speed, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.strength", 1, Config.strength.absoluteMax, 1, get -> (double) Config.strength.maxLevel.get(), (set, val) -> setMaxLevel(Config.strength, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.strength") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.strength.isEnabled.get(), (set, val) -> setIsEnabled(Config.strength, val)));

		this.optionsRowList.addBig(new BooleanOption("so_many_enchants.screen.config.enabled.waterBreathing", get -> (boolean) Config.waterBreathing.isEnabled.get(), (set, val) -> setIsEnabled(Config.waterBreathing, val)));

		this.optionsRowList.addSmall(new SliderPercentageOption("so_many_enchants.screen.config.max_level.weakness", 1, Config.weakness.absoluteMax, 1, get -> (double) Config.weakness.maxLevel.get(), (set, val) -> setMaxLevel(Config.weakness, val), (gs, option) -> new StringTextComponent(I18n.get("so_many_enchants.screen.config.max_level.weakness") + ": " + (int) option.get(gs))), new BooleanOption("so_many_enchants.screen.config.enabled.modded", get -> (boolean) Config.weakness.isEnabled.get(), (set, val) -> setIsEnabled(Config.weakness, val)));

		this.addWidget(this.optionsRowList);

		this.addButton(new Button((this.width - BUTTON_WIDTH * 2) / 2, this.height - DONE_BUTTON_TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslationTextComponent("gui.done"), button -> this.onClose()));

		this.addButton(new Button((this.width - BUTTON_WIDTH / 3 + 100) / 2, this.height - DONE_BUTTON_TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslationTextComponent("so_many_enchants.screen.config.default"), button -> this.setDefault()));

	}

	private void setDefault() {

		Config.configSections.values().forEach((value) -> {
			value.isEnabled.setNoUpdate(value.isEnabled.getDefault());
			value.maxLevel.setNoUpdate(value.maxLevel.getDefault());
		});

		Config.villager.isEnabled.setNoUpdate(true);
		this.refresh();

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
	public void onClose() {

		try {
			Config.sendChanges();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		this.minecraft.setScreen((Screen) null);

	}

	private void refresh() {

		try {
			Config.sendChanges();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		this.minecraft.setScreen(new ConfigMenu());

	}

}
