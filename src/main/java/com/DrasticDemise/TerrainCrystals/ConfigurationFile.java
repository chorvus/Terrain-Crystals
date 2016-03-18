package com.DrasticDemise.TerrainCrystals;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationFile {
	public static void configFile(Configuration config){
		config.load();
		
		//Plains Crystal
		int propPlainsCrystalDurability = config.getInt("Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		plainsCrystalDurability = propPlainsCrystalDurability;
		
		int propPlainsCrystalDiameter = config.getInt("Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		plainsCrystalDiameter = propPlainsCrystalDiameter;
		
		boolean propPlainsCrystalGenerateTallGrass = config.getBoolean("Plains Crystal generate tall grass", Configuration.CATEGORY_GENERAL, true, "Should platforms have a bonemeal effect on the surface?");
		plainsCrystalGenerateTallGrass = propPlainsCrystalGenerateTallGrass;
		//This needs tested
		boolean propPlainsCrystalGenerateTrees = config.getBoolean("Plains Crystal Spawns Trees", Configuration.CATEGORY_GENERAL, true, "Should the plains biome have a chance to spawn trees? Tall Grass must be enabled.");
		plainsCrystalGenerateTrees = propPlainsCrystalGenerateTrees;
		
		boolean propPlainsCrystalChangesBiome = config.getBoolean("Plains Crystal Turns Other Biomes Plains", Configuration.CATEGORY_GENERAL, true, "Should the plains biome change biomes? Changes biomes in each location of a spawned surface block.");
		plainsCrystalChangesBiome = propPlainsCrystalChangesBiome;
		//Desert Crystal
		int propDesertCrystalDurability = config.getInt("Desert Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		desertCrystalDurability = propDesertCrystalDurability;
		
		int propDesertCrystalDiameter = config.getInt("Desert Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		desertCrystalDiameter = propDesertCrystalDiameter;
		
		boolean propDesertCrystalGenerateCactus = config.getBoolean("Desert Crystal Generates Cactus", Configuration.CATEGORY_GENERAL, true, "Should platform have cactus on it?");
		desertCrystalGenerateCactus = propDesertCrystalGenerateCactus;
		
		boolean propDesertCrystalChangesBiome = config.getBoolean("Desert Crystal Turns Other Biomes Desert", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		desertCrystalChangesBiome = propDesertCrystalChangesBiome;
		
		//Mesa Crystal
		int propMesaCrystalDurability = config.getInt("Mesa Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		mesaCrystalDurability = propMesaCrystalDurability;
		
		int propMesaCrystalDiameter = config.getInt("Mesa Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		mesaCrystalDiameter = propMesaCrystalDiameter;
		boolean propMesaCrystalChangesBiome = config.getBoolean("Mesa Crystal Turns Other Biomes Mesa", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		mesaCrystalChangesBiome = propMesaCrystalChangesBiome;
		//Mushroom Crystal
		int propMushroomCrystalDurability = config.getInt("Mushroom Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		mushroomCrystalDurability = propMushroomCrystalDurability;
		
		int propMushroomCrystalDiameter = config.getInt("Mushroom Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		mushroomCrystalDiameter = propMushroomCrystalDiameter;
		
		boolean propMushroomCrystalChangesBiome = config.getBoolean("Mushroom Crystal Turns Other Biomes Mushroom", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		mushroomCrystalChangesBiome = propMushroomCrystalChangesBiome;
		//Nether Crystal
		int propNetherCrystalDurability = config.getInt("Nether Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		netherCrystalDurability = propNetherCrystalDurability;
		
		int propNetherCrystalDiameter = config.getInt("Nether Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		netherCrystalDiameter = propNetherCrystalDiameter;
		
		boolean propNetherCrystalChangesBiome = config.getBoolean("Nether Crystal Turns Other Biomes Nether", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		netherCrystalChangesBiome = propNetherCrystalChangesBiome;
		//End Crystal
		int propEndCrystalDurability = config.getInt("End Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		endCrystalDurability = propEndCrystalDurability;
		
		int propEndCrystalDiameter = config.getInt("End Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		endCrystalDiameter = propEndCrystalDiameter;
		
		boolean propEndCrystalGenerateObsidianSpikes = config.getBoolean("End Crystal Generates Obsidian Spikes", Configuration.CATEGORY_GENERAL, true, "Should platforms have ruin-like structures on top?");
		endCrystalGenerateObsidianSpikes = propEndCrystalGenerateObsidianSpikes;
		
		boolean propEndCrystalChangesBiome = config.getBoolean("End Crystal Turns Other Biomes End", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		endCrystalChangesBiome = propEndCrystalChangesBiome;
		
		
		int propTaigaCrystalDurability = config.getInt("Taiga Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		taigaCrystalDurability = propTaigaCrystalDurability;
		
		int propTaigaCrystalDiameter = config.getInt("Taiga Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		taigaCrystalDiameter = propTaigaCrystalDiameter;
		
		boolean propTaigaCrystalGeneratesTrees = config.getBoolean("Taiga Crystal Generates Trees", Configuration.CATEGORY_GENERAL, true, "Should platform have spruce trees on it?");
		taigaCrystalGeneratesTrees = propTaigaCrystalGeneratesTrees;
		
		boolean propTaigaCrystalChangesBiome = config.getBoolean("Taiga Crystal Turns Other Biomes Cold Taiga", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		taigaCrystalChangesBiome = propTaigaCrystalChangesBiome;
		
		//Ice Plains Spikes
		int propPlainsIceCrystalDurability = config.getInt("Ice Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		plainsIceCrystalDurability = propPlainsIceCrystalDurability;
		
		int propPlainsIceCrystalDiameter = config.getInt("Ice Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		plainsIceCrystalDiameter = propPlainsIceCrystalDiameter;
		
		boolean propPlainsIceCrystalChangesBiome = config.getBoolean("Ice Plains Crystal Turns Other Biomes Ice Plains", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		plainsIceCrystalChangesBiome = propPlainsIceCrystalChangesBiome;
		
		if(config.hasChanged()){
			config.save();
		}
	}
	//Plains
	public static int plainsCrystalDurability;
	public static int plainsCrystalDiameter;
	public static boolean plainsCrystalGenerateTallGrass;
    public static boolean plainsCrystalGenerateTrees;
    public static boolean plainsCrystalChangesBiome;
    //Desert
    public static int desertCrystalDurability;
	public static int desertCrystalDiameter;
    public static boolean desertCrystalGenerateCactus;
    public static boolean desertCrystalChangesBiome;
    //Mesa
    public static int mesaCrystalDurability;
	public static int mesaCrystalDiameter;
    public static boolean mesaCrystalChangesBiome;
    //Mushroom
    public static int mushroomCrystalDurability;
	public static int mushroomCrystalDiameter;
    public static boolean mushroomCrystalChangesBiome;
    //End
    public static int endCrystalDurability;
	public static int endCrystalDiameter;
	public static boolean endCrystalGenerateObsidianSpikes;
    public static boolean endCrystalChangesBiome;
    //Nether
    public static int netherCrystalDurability;
	public static int netherCrystalDiameter;
    public static boolean netherCrystalChangesBiome;
    //Taiga
    public static int taigaCrystalDurability;
    public static int taigaCrystalDiameter;
    public static boolean taigaCrystalGeneratesTrees;
    public static boolean taigaCrystalChangesBiome;
    
    public static int plainsIceCrystalDurability;
    public static int plainsIceCrystalDiameter;
    public static boolean plainsIceCrystalChangesBiome;
}
