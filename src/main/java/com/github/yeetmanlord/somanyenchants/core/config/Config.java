package com.github.yeetmanlord.somanyenchants.core.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.network.message.ConfigSyncPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class Config {

	public static final String CLIENT_CONFIG = "config/so_many_enchants-client.json";

	@OnlyIn(Dist.CLIENT)
	public static void load() {

		Minecraft mc = Minecraft.getInstance();
		Dist side = Dist.CLIENT;

		if (mc.player != null && mc.player.getServer().isDedicatedServer()) {
			side = Dist.DEDICATED_SERVER;
		}

		loadConfig(side);

	}

	@OnlyIn(Dist.CLIENT)
	public static void unload() {

		loadConfig(Dist.CLIENT);

	}

	@OnlyIn(Dist.CLIENT)
	private static void loadConfig(Dist side) {

		if (side == Dist.DEDICATED_SERVER) {
			NetworkHandler.CHANNEL.sendToServer(new ConfigSyncPacket(0));
		}
		else {

			try {
				File config = new File(CLIENT_CONFIG);

				if (!config.exists()) {
					config.createNewFile();
				}

				loadConfig(new String(Files.readAllBytes(Paths.get(CLIENT_CONFIG))));

			}
			catch (IOException exc) {
				exc.printStackTrace();
			}

		}

	}

	public static void loadConfig(String string) {

		JsonObject json = (JsonObject) JsonParser.parseString(string);

		for (String key : json.keySet()) {

			JsonObject sub = json.get(key).getAsJsonObject();
			boolean enabled = sub.get("isEnabled").getAsBoolean();

			if (key.equals("Villager")) {
				villager.isEnabled.setNoUpdate(enabled);
			}
			else if (configSections.containsKey(key)) {
				int maxLevel = sub.get("maxLevel").getAsInt();
				configSections.get(key).maxLevel.setNoUpdate(maxLevel);
				configSections.get(key).isEnabled.setNoUpdate(enabled);
			}

		}

	}

	public static boolean hasInit = false;

	public static final HashMap<String, EnchantmentConfig> configSections = new HashMap<>();

	public static VanillaEnchantmentConfig damageEnchantments;

	public static VanillaEnchantmentConfig efficiency;

	public static VanillaEnchantmentConfig fireAspect;

	public static VanillaEnchantmentConfig impaling;

	public static VanillaEnchantmentConfig knockback;

	public static VanillaEnchantmentConfig lootBonusEnchantments;

	public static VanillaEnchantmentConfig loyalty;

	public static VanillaEnchantmentConfig lure;

	public static VanillaEnchantmentConfig piercing;

	public static VanillaEnchantmentConfig power;

	public static VanillaEnchantmentConfig protectionEnchantments;

	public static VanillaEnchantmentConfig punch;

	public static VanillaEnchantmentConfig quickCharge;

	public static VanillaEnchantmentConfig respiration;

	public static VanillaEnchantmentConfig riptide;

	public static VanillaEnchantmentConfig soulSpeed;

	public static VanillaEnchantmentConfig sweeping;

	public static VanillaEnchantmentConfig thorns;

	public static VanillaEnchantmentConfig unbreaking;

	public static VillagerConfig villager;

	public static EnchantmentConfig attackReach;

	public static EnchantmentConfig blockReach;

	public static EnchantmentConfig catVision;

	public static EnchantmentConfig camouflage;

	public static EnchantmentConfig cavernousStorage;

	public static EnchantmentConfig critical;

	public static EnchantmentConfig doubleBreak;

	public static EnchantmentConfig fastHopper;

	public static EnchantmentConfig flight;

	public static EnchantmentConfig freezing;

	public static EnchantmentConfig healthBoost;

	public static EnchantmentConfig reinforcement;

	public static EnchantmentConfig heavyArmor;

	public static EnchantmentConfig stepAssist;

	public static EnchantmentConfig replanting;

	public static EnchantmentConfig temper;

	public static EnchantmentConfig fastSmelt;

	public static EnchantmentConfig heavyBlade;

	public static EnchantmentConfig lightBlade;

	public static EnchantmentConfig fuelEfficient;

	public static EnchantmentConfig extraExperience;

	// Effect Enchantments
	public static EnchantmentConfig blindness;

	public static EnchantmentConfig fireResistance;

	public static EnchantmentConfig haste;

	public static EnchantmentConfig hunger;

	public static EnchantmentConfig invisibility;

	public static EnchantmentConfig jumpBoost;

	public static EnchantmentConfig miningFatigue;

	public static EnchantmentConfig nausea;

	public static EnchantmentConfig poison;

	public static EnchantmentConfig regeneration;

	public static EnchantmentConfig resistance;

	public static EnchantmentConfig saturation;

	public static EnchantmentConfig slowFalling;

	public static EnchantmentConfig slowness;

	public static EnchantmentConfig speed;

	public static EnchantmentConfig strength;

	public static EnchantmentConfig waterBreathing;

	public static EnchantmentConfig weakness;

	static {

		if (!hasInit) {
			damageEnchantments = new VanillaEnchantmentConfig(10, "Damage Enchantments", 10, 5);
			efficiency = new VanillaEnchantmentConfig(10, "Efficiency", 10, 5);
			fireAspect = new VanillaEnchantmentConfig(10, "Fire Aspect", 10, 2);
			impaling = new VanillaEnchantmentConfig(10, "Impaling", 10, 5);
			knockback = new VanillaEnchantmentConfig(10, "Knockback", 10, 2);
			lootBonusEnchantments = new VanillaEnchantmentConfig(10, "Loot Bonus Enchantments", 10, 3);
			loyalty = new VanillaEnchantmentConfig(5, "Loyalty", 5, 3);
			lure = new VanillaEnchantmentConfig(5, "Lure", 5, 3);
			piercing = new VanillaEnchantmentConfig(10, "Piercing", 10, 4);
			power = new VanillaEnchantmentConfig(10, "Power", 10, 5);
			protectionEnchantments = new VanillaEnchantmentConfig(10, "Protection Enchantments", 10, 4);
			punch = new VanillaEnchantmentConfig(10, "Punch", 10, 2);
			quickCharge = new VanillaEnchantmentConfig(5, "Quick Charge", 5, 3);
			respiration = new VanillaEnchantmentConfig(5, "Respiration", 5, 3);
			riptide = new VanillaEnchantmentConfig(5, "Riptide", 5, 3);
			soulSpeed = new VanillaEnchantmentConfig(5, "Soul Speed", 5, 3);
			sweeping = new VanillaEnchantmentConfig(10, "Sweeping Edge", 10, 3);
			thorns = new VanillaEnchantmentConfig(10, "Thorns", 10, 3);
			unbreaking = new VanillaEnchantmentConfig(10, "Unbreaking", 10, 3);
			villager = new VillagerConfig();

			attackReach = new EnchantmentConfig(10, "Attack Reach", 1, false);
			blockReach = new EnchantmentConfig(10, "Block Reach", 3, false);
			catVision = new EnchantmentConfig(1, "Cat Vision", 1, true);
			camouflage = new EnchantmentConfig(1, "Camouflage", 1, true);
			critical = new EnchantmentConfig(5, "Critical", 5, false);
			cavernousStorage = new EnchantmentConfig(1, "Cavernous Storage", 1, false);
			doubleBreak = new EnchantmentConfig(5, "Double Break", 5, false);
			fastHopper = new EnchantmentConfig(1, "Fast Hopper", 1, true);
			flight = new EnchantmentConfig(3, "Flight", 3, false);
			freezing = new EnchantmentConfig(10, "Freezing", 3, true);
			healthBoost = new EnchantmentConfig(10, "Health Boost", 5, true);
			heavyArmor = new EnchantmentConfig(10, "Heavy", 5, true);
			reinforcement = new EnchantmentConfig(10, "Reinforcement", 5, true);
			replanting = new EnchantmentConfig(1, "Replanting", 1, true);
			stepAssist = new EnchantmentConfig(3, "Step Assist", 3, false);
			temper = new EnchantmentConfig(10, "Temper", 5, true);
			fastSmelt = new EnchantmentConfig(5, "Fast Smelt", 1, true);
			fuelEfficient = new EnchantmentConfig(5, "Fuel Efficient", 1, true);
			extraExperience = new EnchantmentConfig(5, "Extra Experience", 1, true);
			heavyBlade = new EnchantmentConfig(5, "Heavy Blade", 5, true);
			lightBlade = new EnchantmentConfig(5, "Light Blade", 3, true);

			blindness = new EnchantmentConfig(1, "Blindness", 1, true);
			fireResistance = new EnchantmentConfig(1, "Fire Resistance", 1, true);
			haste = new EnchantmentConfig(5, "Haste", 3, true);
			healthBoost = new EnchantmentConfig(5, "Health Boost", 3, true);
			hunger = new EnchantmentConfig(5, "Hunger", 3, true);
			invisibility = new EnchantmentConfig(1, "Invisibility", 1, true);
			jumpBoost = new EnchantmentConfig(5, "Jump Boost", 3, true);
			miningFatigue = new EnchantmentConfig(5, "Mining Fatigue", 3, true);
			nausea = new EnchantmentConfig(1, "Nausea", 1, true);
			poison = new EnchantmentConfig(5, "Poison", 3, true);
			regeneration = new EnchantmentConfig(5, "Regeneration", 3, true);
			resistance = new EnchantmentConfig(4, "Resistance", 3, true);
			saturation = new EnchantmentConfig(5, "Saturation", 3, true);
			slowFalling = new EnchantmentConfig(1, "Slow Falling", 1, true);
			slowness = new EnchantmentConfig(3, "Slowness", 3, true);
			speed = new EnchantmentConfig(5, "Speed", 3, true);
			strength = new EnchantmentConfig(5, "Strength", 3, true);
			waterBreathing = new EnchantmentConfig(1, "Water Breathing", 1, true);
			weakness = new EnchantmentConfig(3, "Weakness", 3, true);

			DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> new DistExecutor.SafeRunnable() {

				private static final long serialVersionUID = -822837623096255215L;

				@Override
				public void run() {

					setDefaults(new File(CLIENT_CONFIG));
					validate(new File(CLIENT_CONFIG));

				}

			});

			Config.hasInit = true;
		}

	}

	@OnlyIn(Dist.CLIENT)
	public static void sendChanges() throws IOException {

		JsonObject jsonToWrite = new JsonObject();

		for (String confKey : configSections.keySet()) {
			EnchantmentConfig conf = configSections.get(confKey);
			JsonObject val = new JsonObject();
			val.addProperty("isEnabled", conf.isEnabled.get());
			val.addProperty("maxLevel", conf.maxLevel.get());
			jsonToWrite.add(confKey, val);
		}

		JsonObject val = new JsonObject();
		val.addProperty("isEnabled", villager.isEnabled.get());
		jsonToWrite.add("Villager", val);

		sendChanges(new File(CLIENT_CONFIG), jsonToWrite);

	}

	public static void sendChanges(File file, JsonObject data) throws IOException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(data);
		FileWriter writer = new FileWriter(file);
		writer.write(json);
		writer.close();

	}

	public static void setDefaults(File file) {

		if (file.exists()) {
			return;
		}

		try {
			file.createNewFile();

			JsonObject jsonToWrite = new JsonObject();

			for (String confKey : configSections.keySet()) {
				EnchantmentConfig conf = configSections.get(confKey);
				JsonObject val = new JsonObject();
				val.addProperty("isEnabled", conf.isEnabled.get());
				val.addProperty("maxLevel", conf.maxLevel.get());
				jsonToWrite.add(confKey, val);
			}

			JsonObject val = new JsonObject();
			val.addProperty("isEnabled", villager.isEnabled.get());
			jsonToWrite.add("Villager", val);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(jsonToWrite);

			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void validate(File file) {

		if (file.exists()) {
			String content;

			try {
				content = new String(Files.readAllBytes(Paths.get(file.getPath())));
				JsonObject json = (JsonObject) JsonParser.parseString(content);
				boolean write = false;

				for (String key : configSections.keySet()) {
					EnchantmentConfig conf = configSections.get(key);

					if (!json.keySet().contains(key)) {
						write = true;
						JsonObject val = new JsonObject();
						val.addProperty("isEnabled", conf.isEnabled.get());
						val.addProperty("maxLevel", conf.maxLevel.get());
						json.add(key, val);
					}

					if (conf.maxLevel.get() > conf.absoluteMax) {
						json.remove(key);
						write = true;
						JsonObject val = new JsonObject();
						val.addProperty("isEnabled", conf.isEnabled.get());
						val.addProperty("maxLevel", conf.absoluteMax);
						json.add(key, val);
					}

				}

				if (!json.keySet().contains("Villager")) {
					write = true;
					JsonObject val = new JsonObject();
					VillagerConfig conf = villager;
					val.addProperty("isEnabled", conf.isEnabled.get());
					json.add("Villager", val);
				}

				if (write) {
					SoManyEnchants.LOGGER.info("Config not valid, correcting");
					SoManyEnchants.MOD_SCHEDULER.schedule(() -> () -> {

						try {
							sendChanges(file, json);
						}
						catch (IOException e) {
							e.printStackTrace();
						}

					}, 1);

				}

			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
