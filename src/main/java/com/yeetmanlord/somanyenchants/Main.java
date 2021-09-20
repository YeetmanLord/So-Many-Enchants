package com.yeetmanlord.somanyenchants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yeetmanlord.somanyenchants.client.ConfigMenu;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.init.ParticleTypesInit;
import com.yeetmanlord.somanyenchants.core.init.TileEntityTypeInit;
import com.yeetmanlord.somanyenchants.core.init.VillagerProfessionInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
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
    
    public Main() {
    	Config.hasInit = false;
    	instance=this;
    	
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    		
    	
    		modEventBus.addListener(this::setup);
    		
    		modEventBus.addListener(this::doClientStuff);
    		EnchantmentInit.ENCHANTMENTS.register(modEventBus);
    		ContainerTypeInit.CONTIAINER_TYPES.register(modEventBus);
    		TileEntityTypeInit.TILE_ENTITY_TYPES.register(modEventBus);
    		BlockInit.BLOCKS.register(modEventBus);
    		BlockInit.BLOCK_ITEMS.register(modEventBus);
    		VillagerProfessionInit.POI_TYPE.register(modEventBus);
    		VillagerProfessionInit.JOBS.register(modEventBus);
    		ParticleTypesInit.PARTICLES.register(modEventBus);;
    		AttributeInit.ATTRIBUTES.register(modEventBus);
    		
    		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.config);
    		
    		Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString());
    		
    		ModLoadingContext.get().registerExtensionPoint(
                    ExtensionPoint.CONFIGGUIFACTORY,
                    () -> (mc, screen) -> new ConfigMenu()
            );
    	
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    
    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("PREINIT IS FUNCTIONING");
		VillagerProfessionInit.fillTradeData();
        
//        event.enqueueWork(() ->
//        {
//        	
//        });
    }
    
    

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    	
    }
}
