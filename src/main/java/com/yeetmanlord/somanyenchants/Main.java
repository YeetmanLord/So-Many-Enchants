package com.yeetmanlord.somanyenchants;

import java.io.File;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.yeetmanlord.somanyenchants.client.ConfigMenu;
import com.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedChestTileEntityRenderer;
import com.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedHiddenTrappedChestTileEntityRenderer;
import com.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedShulkerBoxTileEntityRenderer;
import com.yeetmanlord.somanyenchants.client.renderer.tileentity.EnchantedTrappedChestTileEntityRenderer;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;
import com.yeetmanlord.somanyenchants.core.init.ItemInit;
import com.yeetmanlord.somanyenchants.core.init.ParticleTypesInit;
import com.yeetmanlord.somanyenchants.core.init.TileEntityTypeInit;
import com.yeetmanlord.somanyenchants.core.init.VillagerProfessionInit;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.util.PlayerUtilities;
import com.yeetmanlord.somanyenchants.core.util.Scheduler;

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
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("so_many_enchants")
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class Main 
{
	public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "so_many_enchants";
    public static Main instance;
    
    public static HashMap<PlayerEntity, PlayerUtilities> playerUtils;
	public static HashMap<PlayerEntity, Scheduler> playerTaskSchedulers = new HashMap<>();
    
    public Main() {
    	playerUtils = new HashMap<>();
    	
    	Config.hasInit = false;
    	instance=this;
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    		modEventBus.addListener(this::setup);
    		
    		modEventBus.addListener(this::doClientStuff);
    		EnchantmentInit.ENCHANTMENTS.register(modEventBus);
    		ContainerTypeInit.CONTIAINER_TYPES.register(modEventBus);
    		ContainerTypeInit.MINECRAFT_CONTIAINER_TYPES.register(modEventBus);
    		TileEntityTypeInit.TILE_ENTITY_TYPES.register(modEventBus);
    		BlockInit.BLOCKS.register(modEventBus);
    		ItemInit.BLOCK_ITEMS.register(modEventBus);
    		VillagerProfessionInit.POI_TYPE.register(modEventBus);
    		VillagerProfessionInit.JOBS.register(modEventBus);
    		ParticleTypesInit.PARTICLES.register(modEventBus);;
    		AttributeInit.ATTRIBUTES.register(modEventBus);
    		
    		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.config);
    		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SyncedServerConfig);
    		
    		
    		
    		
    		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> new DistExecutor.SafeRunnable() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 5789682203789505777L;

				@Override
				public void run() {
					final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
			    	file.load();
			    	Config.SyncedServerConfig.setConfig(file);
					
					ModLoadingContext.get().registerExtensionPoint(
			                ExtensionPoint.CONFIGGUIFACTORY,
			                () -> (mc, screen) -> new ConfigMenu()
			        );
					
				}
			});
    	
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	ItemGroup.DECORATIONS.setRelevantEnchantmentTypes(new EnchantmentType[] {EnchantmentTypesInit.HOPPER, EnchantmentTypesInit.STORAGE, EnchantmentTypesInit.TRAPPED_CHEST, EnchantmentTypesInit.SMELTER});
        LOGGER.info("PREINIT IS FUNCTIONING");
		VillagerProfessionInit.fillTradeData();
		NetworkHandler.init();
        
//        event.enqueueWork(() ->
//        {
//        	
//        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    	ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.ENCHANTED_SHULKER_BOX.get(), EnchantedShulkerBoxTileEntityRenderer::new);
    	ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.ENCHANTED_CHEST.get(), EnchantedChestTileEntityRenderer::new);
    	ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.TRAPPED_ENCHANTED_CHEST.get(), EnchantedTrappedChestTileEntityRenderer::new);
    	ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get(), EnchantedHiddenTrappedChestTileEntityRenderer::new);
    }
    
    public static PlayerUtilities getPlayerUtil(PlayerEntity player)
    {
    	PlayerUtilities util;
    	
    	if(playerUtils.containsKey(player))
    	{
    		return playerUtils.get(player);
    	}
    	else
    	{
    		util = new PlayerUtilities(player);
    		playerUtils.put(player, util);
    		return util;
    	}
    }
    
    public static Scheduler getScheduler(PlayerEntity player)
    {
    	if(playerTaskSchedulers.get(player) != null)
    	{
    		return playerTaskSchedulers.get(player);
    	}
    	else
    	{
    		Scheduler scheduler = new Scheduler(player);
    		playerTaskSchedulers.put(player, scheduler);
    		return scheduler;
    	}
    }
}
