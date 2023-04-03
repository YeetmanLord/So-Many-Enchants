package com.github.yeetmanlord.somanyenchants.client;

import java.io.IOException;
import java.util.List;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
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

		super(Component.translatable("so_many_enchants.screen.config"));

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

	private OptionInstance<Integer> createMaxLevelOption(String textComponent, EnchantmentConfig configEntry, String tooltip) {

		if (tooltip == null) {
			return new OptionInstance<Integer>("so_many_enchants.screen.config.max_level." + textComponent, OptionInstance.noTooltip(), (component, value) -> {
				return Options.genericValueLabel(component, value);
			}, new OptionInstance.IntRange(1, configEntry.absoluteMax), configEntry.maxLevel.get(), (value) -> {
				setMaxLevel(configEntry, value);
			});
		}
		else {
			return new OptionInstance<Integer>("so_many_enchants.screen.config.max_level." + textComponent, (p_231858_) -> {
				List<FormattedCharSequence> list = p_231858_.font.split(Component.translatable(tooltip), 200);
				return (p_231883_) -> {
					return list;
				};
			}, (component, value) -> {
				return Options.genericValueLabel(component, value);
			}, new OptionInstance.IntRange(1, configEntry.absoluteMax), configEntry.maxLevel.get(), (value) -> {
				setMaxLevel(configEntry, value);
			});
		}

	}

	private OptionInstance<Boolean> createEnabledOption(String textComponent, EnchantmentConfig configEntry, String tooltipOn, String tooltipOff) {

		if (tooltipOn == null || tooltipOff == null) {
			return OptionInstance.createBoolean("so_many_enchants.screen.config.enabled." + textComponent, configEntry.isEnabled.get(), (value) -> {
				setIsEnabled(configEntry, value);
			});
		}
		else {
			return OptionInstance.createBoolean("so_many_enchants.screen.config.enabled." + textComponent, (p_231858_) -> {
				List<FormattedCharSequence> list = p_231858_.font.split(Component.translatable(tooltipOn), 200);
				List<FormattedCharSequence> list1 = p_231858_.font.split(Component.translatable(tooltipOff), 200);
				return (p_231883_) -> {
					return p_231883_ ? list : list1;
				};
			}, configEntry.isEnabled.get(), (value) -> {
				setIsEnabled(configEntry, value);
			});
		}

	}

	private OptionInstance<Boolean> createEnabledOption(String textComponent, EnchantmentConfig configEntry) {

		return createEnabledOption(textComponent, configEntry, null, null);

	}

	private OptionInstance<Integer> createMaxLevelOption(String textComponent, EnchantmentConfig configEntry) {

		return createMaxLevelOption(textComponent, configEntry, null);

	}

	@Override
	protected void init() {

		this.optionsRowList = new OptionsList(this.minecraft, this.width, this.height, OPTIONS_LIST_TOP_HEIGHT, this.height - OPTIONS_LIST_BOTTOM_OFFSET, OPTIONS_LIST_ITEM_HEIGHT);

		this.optionsRowList.addSmall(createMaxLevelOption("de", Config.damageEnchantments), createEnabledOption("vanilla", Config.damageEnchantments));

		this.optionsRowList.addSmall(createMaxLevelOption("e", Config.efficiency), createEnabledOption("vanilla", Config.efficiency));

		this.optionsRowList.addSmall(createMaxLevelOption("fa", Config.fireAspect), createEnabledOption("vanilla", Config.fireAspect));

		this.optionsRowList.addSmall(createMaxLevelOption("i", Config.impaling), createEnabledOption("vanilla", Config.impaling));

		this.optionsRowList.addSmall(createMaxLevelOption("k", Config.knockback), createEnabledOption("vanilla", Config.knockback));

		this.optionsRowList.addSmall(createMaxLevelOption("lbe", Config.lootBonusEnchantments), createEnabledOption("vanilla", Config.lootBonusEnchantments));

		this.optionsRowList.addSmall(createMaxLevelOption("lo", Config.loyalty), createEnabledOption("vanilla", Config.loyalty));

		this.optionsRowList.addSmall(createMaxLevelOption("l", Config.lure), createEnabledOption("vanilla", Config.lure));

		this.optionsRowList.addSmall(createMaxLevelOption("p", Config.piercing), createEnabledOption("vanilla", Config.piercing));

		this.optionsRowList.addSmall(createMaxLevelOption("po", Config.power), createEnabledOption("vanilla", Config.power));

		this.optionsRowList.addSmall(createMaxLevelOption("pr", Config.protectionEnchantments), createEnabledOption("vanilla", Config.protectionEnchantments));

		this.optionsRowList.addSmall(createMaxLevelOption("pu", Config.punch), createEnabledOption("vanilla", Config.punch));

		this.optionsRowList.addSmall(createMaxLevelOption("q", Config.quickCharge), createEnabledOption("vanilla", Config.quickCharge));

		this.optionsRowList.addSmall(createMaxLevelOption("r", Config.respiration), createEnabledOption("vanilla", Config.respiration));

		this.optionsRowList.addSmall(createMaxLevelOption("ri", Config.riptide), createEnabledOption("vanilla", Config.riptide));

		this.optionsRowList.addSmall(createMaxLevelOption("ss", Config.soulSpeed), createEnabledOption("vanilla", Config.soulSpeed));

		this.optionsRowList.addSmall(createMaxLevelOption("s", Config.sweeping), createEnabledOption("vanilla", Config.sweeping));

		this.optionsRowList.addSmall(createMaxLevelOption("t", Config.thorns), createEnabledOption("vanilla", Config.thorns));

		this.optionsRowList.addSmall(createMaxLevelOption("u", Config.unbreaking), createEnabledOption("vanilla", Config.unbreaking));

		this.optionsRowList.addSmall(createMaxLevelOption("he", Config.heavyArmor), createEnabledOption("modded", Config.heavyArmor));

		this.optionsRowList.addSmall(createMaxLevelOption("rei", Config.reinforcement), createEnabledOption("modded", Config.reinforcement));

		this.optionsRowList.addSmall(createMaxLevelOption("te", Config.temper), createEnabledOption("modded", Config.temper));

		this.optionsRowList.addBig(createEnabledOption("c", Config.catVision));

		this.optionsRowList.addSmall(createMaxLevelOption("fl", Config.flight), createEnabledOption("modded", Config.flight));

		this.optionsRowList.addSmall(createMaxLevelOption("h", Config.healthBoost), createEnabledOption("modded", Config.healthBoost));

		this.optionsRowList.addSmall(createMaxLevelOption("sa", Config.stepAssist), createEnabledOption("modded", Config.stepAssist));

		this.optionsRowList.addBig(createEnabledOption("f", Config.fastHopper));

		this.optionsRowList.addSmall(createMaxLevelOption("b", Config.blockReach), createEnabledOption("modded", Config.blockReach));

		this.optionsRowList.addSmall(createMaxLevelOption("d", Config.doubleBreak), createEnabledOption("modded", Config.doubleBreak));

		this.optionsRowList.addBig(createEnabledOption("re", Config.replanting));

		this.optionsRowList.addSmall(createMaxLevelOption("a", Config.attackReach), createEnabledOption("modded", Config.attackReach));

		this.optionsRowList.addSmall(createMaxLevelOption("cr", Config.critical), createEnabledOption("modded", Config.critical));

		this.optionsRowList.addSmall(createMaxLevelOption("fr", Config.freezing), createEnabledOption("modded", Config.freezing));

		this.optionsRowList.addBig(OptionInstance.createBoolean("so_many_enchants.screen.config.enabled.v", Config.villager.isEnabled.getDefault(), (value) -> {

			try {
				Config.villager.isEnabled.set(value);
			}
			catch (IOException exc) {
				SoManyEnchants.LOGGER.error("Could not save villager config because the file is in use. Retrying");
			}

		}));

		this.optionsRowList.addBig(createEnabledOption("cs", Config.cavernousStorage));

		this.optionsRowList.addBig(createEnabledOption("cf", Config.camouflage));

		this.optionsRowList.addSmall(createMaxLevelOption("fs", Config.fastSmelt), createEnabledOption("modded", Config.fastSmelt));

		this.optionsRowList.addSmall(createMaxLevelOption("fuelE", Config.fuelEfficient), createEnabledOption("modded", Config.fuelEfficient));

		this.optionsRowList.addSmall(createMaxLevelOption("eExp", Config.extraExperience), createEnabledOption("modded", Config.extraExperience));

		this.optionsRowList.addSmall(createMaxLevelOption("hb", Config.heavyBlade), createEnabledOption("modded", Config.heavyBlade));

		this.optionsRowList.addSmall(createMaxLevelOption("lightBlade", Config.lightBlade), createEnabledOption("modded", Config.lightBlade));

		this.optionsRowList.addBig(createEnabledOption("blindness", Config.blindness));

		this.optionsRowList.addBig(createEnabledOption("fireResistance", Config.fireResistance));

		this.optionsRowList.addSmall(createMaxLevelOption("haste", Config.haste), createEnabledOption("modded", Config.haste));

		this.optionsRowList.addSmall(createMaxLevelOption("hunger", Config.hunger), createEnabledOption("modded", Config.hunger));

		this.optionsRowList.addBig(createEnabledOption("invisibility", Config.invisibility));

		this.optionsRowList.addSmall(createMaxLevelOption("hunger", Config.hunger), createEnabledOption("modded", Config.hunger));

		this.optionsRowList.addSmall(createMaxLevelOption("miningFatigue", Config.miningFatigue), createEnabledOption("modded", Config.miningFatigue));

		this.optionsRowList.addBig(createEnabledOption("nausea", Config.nausea));

		this.optionsRowList.addSmall(createMaxLevelOption("poison", Config.poison), createEnabledOption("modded", Config.poison));

		this.optionsRowList.addSmall(createMaxLevelOption("regeneration", Config.regeneration), createEnabledOption("modded", Config.regeneration));

		this.optionsRowList.addSmall(createMaxLevelOption("resistance", Config.resistance), createEnabledOption("modded", Config.resistance));

		this.optionsRowList.addSmall(createMaxLevelOption("saturation", Config.saturation), createEnabledOption("modded", Config.saturation));

		this.optionsRowList.addBig(createEnabledOption("slowFalling", Config.slowFalling));

		this.optionsRowList.addSmall(createMaxLevelOption("slowness", Config.slowness), createEnabledOption("modded", Config.slowness));

		this.optionsRowList.addSmall(createMaxLevelOption("speed", Config.speed), createEnabledOption("modded", Config.speed));

		this.optionsRowList.addSmall(createMaxLevelOption("strength", Config.strength), createEnabledOption("modded", Config.strength));

		this.optionsRowList.addBig(createEnabledOption("waterBreathing", Config.waterBreathing));

		this.optionsRowList.addSmall(createMaxLevelOption("weakness", Config.weakness), createEnabledOption("modded", Config.weakness));

		this.addWidget(this.optionsRowList);

		this.addRenderableWidget(new Button((this.width - BUTTON_WIDTH * 2) / 2, this.height - DONE_BUTTON_TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, Component.translatable("gui.done"), button -> this.onClose()));

		this.addRenderableWidget(new Button((this.width - BUTTON_WIDTH / 3 + 100) / 2, this.height - DONE_BUTTON_TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, Component.translatable("so_many_enchants.screen.config.default"), button -> this.setDefault()));

	}

	/**
	 * 
	 */
	private void setDefault() {

		Config.configSections.values().forEach((value) -> {
			value.isEnabled.setNoUpdate(value.isEnabled.getDefault());
			value.maxLevel.setNoUpdate(value.maxLevel.getDefault());
		});

		Config.villager.isEnabled.setNoUpdate(true);
		this.refresh();

	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {

		this.renderBackground(matrixStack);
		this.optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
		drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		List<FormattedCharSequence> list = OptionsSubScreen.tooltipAt(this.optionsRowList, mouseX, mouseY);
		this.renderTooltip(matrixStack, list, mouseX, mouseY);

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
