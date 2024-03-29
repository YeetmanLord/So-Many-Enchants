package com.github.yeetmanlord.somanyenchants;

import com.github.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedChestTileEntityRenderer;
import com.github.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedShulkerBoxTileEntityRenderer;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.*;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.util.PlayerUtilities;
import com.github.yeetmanlord.somanyenchants.core.util.Scheduler;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@Mod("so_many_enchants")
@Mod.EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.MOD)
public class SoManyEnchants {

    public static final Scheduler MOD_SCHEDULER = new Scheduler(null);

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "so_many_enchants";

    public static SoManyEnchants instance;

    public static HashMap<Player, PlayerUtilities> playerUtils;

    public static HashMap<Player, Scheduler> playerTaskSchedulers = new HashMap<>();

    public SoManyEnchants() {

        playerUtils = new HashMap<>();
        instance = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::doClientStuff);

        modEventBus.addListener(this::registerRenderers);

        EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        ContainerTypeInit.CONTAINER_TYPES.register(modEventBus);
        BlockEntityTypeInit.BLOCK_ENTITY_TYPES.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.BLOCK_ITEMS.register(modEventBus);
        VillagerProfessionInit.POI_TYPE.register(modEventBus);
        VillagerProfessionInit.JOBS.register(modEventBus);
        ParticleTypesInit.PARTICLES.register(modEventBus);
        AttributeInit.ATTRIBUTES.register(modEventBus);

        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.serverConfig);

    }

    private void setup(final FMLCommonSetupEvent event) {

        CreativeModeTab.TAB_DECORATIONS.setEnchantmentCategories(EnchantmentTypesInit.HOPPER, EnchantmentTypesInit.STORAGE, EnchantmentTypesInit.TRAPPED_CHEST, EnchantmentTypesInit.SMELTER);
        LOGGER.info("PREINIT IS FUNCTIONING");
        VillagerProfessionInit.fillTradeData();
        NetworkHandler.init();

//        event.enqueueWork(() ->
//        {
//        	
//        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {

        event.registerBlockEntityRenderer(BlockEntityTypeInit.ENCHANTED_SHULKER_BOX.get(), EnchantedShulkerBoxTileEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityTypeInit.ENCHANTED_CHEST.get(), EnchantedChestTileEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityTypeInit.TRAPPED_ENCHANTED_CHEST.get(), EnchantedChestTileEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityTypeInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get(), EnchantedChestTileEntityRenderer::new);

    }

    public static PlayerUtilities getPlayerUtil(Player player) {

        PlayerUtilities util;

        if (playerUtils.containsKey(player)) {
            return playerUtils.get(player);
        } else {
            util = new PlayerUtilities(player);
            playerUtils.put(player, util);
            return util;
        }

    }

    public static Scheduler getScheduler(Player player) {

        if (playerTaskSchedulers.get(player) != null) {
            return playerTaskSchedulers.get(player);
        } else {
            Scheduler scheduler = new Scheduler(player);
            playerTaskSchedulers.put(player, scheduler);
            return scheduler;
        }

    }

}
