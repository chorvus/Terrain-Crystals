package com.DrasticDemise.TerrainCrystals;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TerrainCrystals.MODID, name = TerrainCrystals.MODNAME, useMetadata = true, version = TerrainCrystals.VERSION)


public class TerrainCrystals {
	public static final String MODID = "terrainCrystals";
	public static final String MODNAME = "Terrain Crystals";
	public static final String VERSION = "1.0.3";
	public static final String URL = "https://raw.githubusercontent.com/DrasticDemise/Terrain-Crystals/master/UpdateHandler";

	@SidedProxy
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static TerrainCrystals instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		//logger = event.getModLog();
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e){
		proxy.init(e);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e){
		proxy.postInit(e);
	}
	
	public static class CommonProxy {
		public void preInit(FMLPreInitializationEvent e){
			Configuration config = new Configuration(e.getSuggestedConfigurationFile());
			ConfigurationFile.configFile(config);
			//Initialization of Blocks and Items
			InitBlocks.init();
			InitItems.init();
			//InitItems.recipes();
			//InitItems.initModels();
			//ModCrafting.init();
		}
		public void init(FMLInitializationEvent e){
			InitItems.recipes();
			InitItems.oreRegistration();
			new VersionChecker().init();
		}
		public void postInit(FMLPostInitializationEvent e){
			
		}
	}
	
	public static class ClientProxy extends CommonProxy{
		@Override
		public void preInit(FMLPreInitializationEvent e){
			super.preInit(e);
			//Initialization of models
			//ModRenderers.preInit();
			InitItems.initModels();
		}

	}
	
	public static class ServerProxy extends CommonProxy{

	}
	
}

