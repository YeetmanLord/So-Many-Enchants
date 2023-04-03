package com.github.yeetmanlord.somanyenchants;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.yeetmanlord.somanyenchants.client.ConfigMenu;
import com.github.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedChestTileEntityRenderer;
import com.github.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedShulkerBoxTileEntityRenderer;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;
import com.github.yeetmanlord.somanyenchants.core.init.ItemInit;
import com.github.yeetmanlord.somanyenchants.core.init.ParticleTypesInit;
import com.github.yeetmanlord.somanyenchants.core.init.VillagerProfessionInit;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.util.PlayerUtilities;
import com.github.yeetmanlord.somanyenchants.core.util.Scheduler;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("so_many_enchants") @Mod.EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.MOD)
public class SoManyEnchants {

	public static final Scheduler MOD_SCHEDULER = new Scheduler(null);

	public static final Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "so_many_enchants";

	public static SoManyEnchants instance;

	public static HashMap<PlayerEntity, PlayerUtilities> playerUtils;

	public static HashMap<PlayerEntity, Scheduler> playerTaskSchedulers = new HashMap<>();

	public SoManyEnchants() {

		playerUtils = new HashMap<>();
		instance = this;
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> new DistExecutor.SafeRunnable() {

			private static final long serialVersionUID = 5789682203789505777L;

			@Override
			public void run() {

				ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (mc, screen) -> new ConfigMenu());
				Config.load();

			}

		});
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(this::setup);

		modEventBus.addListener(this::doClientStuff);

		EnchantmentInit.ENCHANTMENTS.register(modEventBus);
		ContainerTypeInit.CONTAINER_TYPES.register(modEventBus);
		BlockEntityTypeInit.BLOCK_ENTITY_TYPES.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		ItemInit.BLOCK_ITEMS.register(modEventBus);
		VillagerProfessionInit.POI_TYPE.register(modEventBus);
		VillagerProfessionInit.JOBS.register(modEventBus);
		ParticleTypesInit.PARTICLES.register(modEventBus);
		AttributeInit.ATTRIBUTES.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);

	}

	private void setup(final FMLCommonSetupEvent event) {

		ItemGroup.TAB_DECORATIONS.setEnchantmentCategories(new EnchantmentType[] { EnchantmentTypesInit.HOPPER, EnchantmentTypesInit.STORAGE, EnchantmentTypesInit.TRAPPED_CHEST, EnchantmentTypesInit.SMELTER });
		LOGGER.info("PREINIT IS FUNCTIONING");
		VillagerProfessionInit.fillTradeData();
		NetworkHandler.init();

//        event.enqueueWork(() ->
//        {
//        	
//        });

	}

	private void doClientStuff(final FMLClientSetupEvent event) {

		ClientRegistry.bindTileEntityRenderer(BlockEntityTypeInit.ENCHANTED_SHULKER_BOX.get(), EnchantedShulkerBoxTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(BlockEntityTypeInit.ENCHANTED_CHEST.get(), EnchantedChestTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(BlockEntityTypeInit.TRAPPED_ENCHANTED_CHEST.get(), EnchantedChestTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(BlockEntityTypeInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get(), EnchantedChestTileEntityRenderer::new);

	}

	public static PlayerUtilities getPlayerUtil(PlayerEntity player) {

		PlayerUtilities util;

		if (playerUtils.containsKey(player)) {
			return playerUtils.get(player);
		}
		else {
			util = new PlayerUtilities(player);
			playerUtils.put(player, util);
			return util;
		}

	}

	public static Scheduler getScheduler(PlayerEntity player) {

		if (playerTaskSchedulers.get(player) != null) {
			return playerTaskSchedulers.get(player);
		}
		else {
			Scheduler scheduler = new Scheduler(player);
			playerTaskSchedulers.put(player, scheduler);
			return scheduler;
		}

	}

}
