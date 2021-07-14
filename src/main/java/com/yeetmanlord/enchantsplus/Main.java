package com.yeetmanlord.enchantsplus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yeetmanlord.enchantsplus.core.init.AttributeInit;
import com.yeetmanlord.enchantsplus.core.init.EnchantmentInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("enchants_plus")
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class Main 
{
	public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "enchants_plus";
    public static Main instance;
    
    public Main() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    		
    	
    		modEventBus.addListener(this::setup);
    		
    		modEventBus.addListener(this::doClientStuff);
    		EnchantmentInit.ENCHANTMENTS.register(modEventBus);
    		AttributeInit.ATTRIBUTES.register(modEventBus);
    		
    	instance=this;
    	
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    
    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("PREINIT IS FUNCTIONING");
        
//        event.enqueueWork(() ->
//        {
//        	
//        });
    }
    
    

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
       
    }
}
