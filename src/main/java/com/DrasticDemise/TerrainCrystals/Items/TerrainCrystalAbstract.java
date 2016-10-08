package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.core.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TerrainCrystalAbstract extends Item{
	public static HashSet replaceableBlockStates;
	public static HashSet invalidSpaces;
	
	/**
	 * Initializes the hashSet with block states that can be replaced by the platform.
	 */
	public static void initReplaceableBlocks(){
		replaceableBlockStates = new HashSet();
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(2));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(3));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(4));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(5));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(6));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(7));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(8));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getStateFromMeta(9));
		replaceableBlockStates.add(Blocks.FLOWING_WATER.getDefaultState());
		
		replaceableBlockStates.add(Blocks.TALLGRASS.getDefaultState());
		replaceableBlockStates.add(Blocks.TALLGRASS.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.RED_MUSHROOM.getDefaultState());
		replaceableBlockStates.add(Blocks.BROWN_MUSHROOM.getDefaultState());

		replaceableBlockStates.add(Blocks.MELON_BLOCK.getDefaultState());
		replaceableBlockStates.add(Blocks.CACTUS.getDefaultState());
		
		replaceableBlockStates.add(Blocks.AIR.getDefaultState());
		
		replaceableBlockStates.add(Blocks.SAPLING.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.DEADBUSH.getDefaultState());
		replaceableBlockStates.add(Blocks.LEAVES.getDefaultState());
		replaceableBlockStates.add(Blocks.LEAVES.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.LEAVES.getStateFromMeta(3));
		replaceableBlockStates.add(Blocks.YELLOW_FLOWER.getDefaultState());
		
		for(int i = 0; i < 9; i ++){
			replaceableBlockStates.add(Blocks.RED_FLOWER.getStateFromMeta(i));
		}
	}
	public static void initInvalidSpaces(){
		invalidSpaces = new HashSet();
		invalidSpaces.add(Blocks.LOG.getDefaultState());
		invalidSpaces.add(Blocks.LOG.getStateFromMeta(1));
		invalidSpaces.add(Blocks.LOG.getStateFromMeta(2));
		invalidSpaces.add(Blocks.LOG.getStateFromMeta(3));
		invalidSpaces.add(Blocks.LOG2.getDefaultState());
		
		invalidSpaces.add(Blocks.LEAVES.getDefaultState());
		invalidSpaces.add(Blocks.LEAVES.getStateFromMeta(1));
		invalidSpaces.add(Blocks.LEAVES.getStateFromMeta(2));
		invalidSpaces.add(Blocks.LEAVES.getStateFromMeta(3));
		
		invalidSpaces.add(Blocks.SAPLING.getDefaultState());
		invalidSpaces.add(Blocks.SAPLING.getStateFromMeta(1));
		invalidSpaces.add(Blocks.SAPLING.getStateFromMeta(2));
		invalidSpaces.add(Blocks.SAPLING.getStateFromMeta(3));
		//Used for end crystal
		invalidSpaces.add(Blocks.OBSIDIAN.getDefaultState());
		//Used to ice spikes plains
		invalidSpaces.add(Blocks.PACKED_ICE.getDefaultState());
		//Mushroom Islands
		invalidSpaces.add(Blocks.RED_MUSHROOM_BLOCK.getDefaultState());
		invalidSpaces.add(Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState());
	}
	/**
	 * If the block given has enough room around it to generate a tree.
	 * @param blockState Takes a block state
	 * @param pos Position
	 * @return Returns a boolean if the blockstate given is an eligble location for a tree.
	 */
	protected static boolean eligibleSpaceForTree(IBlockState blockState, BlockPos pos){
		if(pos.getY() > 1){
			if(invalidSpaces.contains(blockState)){
				return false;
			}
			return true;
		}
		return false;
	}
	/**
	 * Returns if the state is eligible for replacing. Checks Y Level and block state
	 * @param pos Takes the intended block state from the position in the world
	 * @return returns a boolean if eligible or not.
	 */
	public static boolean eligibleStateLocation(World worldIn, BlockPos pos){
		if(pos.getY() > 1){
			if(replaceableBlockStates.contains(worldIn.getBlockState(pos))){
				return true;
			}
		}
		return false;
	}
	/**
	 * Needs to return the itemstack from the method call gatherBlockGenList with the itemStack,
	 * world, player, diameter, desired biome type and the biome change boolean.
	 */
	public abstract ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand);
	//Each class needs to provide its own platform makeup.
	/**
	 * This method is called after the list of positions has been created. Each position is then passed into the method
	 * and needs to be set the world as a blockstate. The positions need to have a Y value greater than 1.
	 * Checks should be made for AIR blocks before placement and a call to
	 * eligibleStateLocation: EX: eligibleStateLocation(worldIn.getBlockState(pos))
	 * This method also needs to call super.setBiome at blocks that are placed. 
	 * @param pos Position that the block is being placed at.
	 * @param worldIn The world
	 * @param playerIn The player
	 * @param blocksGenerated The number of the blocks placed in the world. This keeps track of durability
	 * @param desiredBiome The desired biome type of the world. 
	 * @param changeBiome If the biome will be changed at the block column.
	 * @return Needs to return the amount of blocks placed. This keeps track of the durability.
	 */
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated, Biome desiredBiome, boolean changeBiome){
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
				decoratePlatform(worldIn, pos);
			}else if(ConfigurationFile.generateStone && posY - pos.getY() >= ConfigurationFile.stoneSpawnDepth){
				if(ConfigurationFile.generateOres && Math.random() < 0.05){
					worldIn.setBlockState(pos, oreListHelper());
				}else{
					worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
				}
			}
			else{
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			}
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}
	public static IBlockState oreListHelper(){
		double oreType = Math.random();
		if(oreType < .40){
			return Blocks.COAL_ORE.getDefaultState();
		}else if(oreType < .65){
			return Blocks.IRON_ORE.getDefaultState();
		}else if(oreType < .80){
			return Blocks.GOLD_ORE.getDefaultState();
		}else if(oreType < .90){
			return Blocks.REDSTONE_ORE.getDefaultState();
		}else if(oreType < .98){
			return Blocks.LAPIS_ORE.getDefaultState();
		}else if(oreType < 1){
			return Blocks.DIAMOND_ORE.getDefaultState();
		}
		return Blocks.COAL_ORE.getDefaultState();
	}
		
	//Each class needs to provide its own decoration rules
	/**
	 * Optional implementation that needs to be called by generateBlocksInWorld on the SURFACE of the platform.
	 * @param worldIn The world
	 * @param pos The SURFACE block to be decorated.
	 */
	protected abstract void decoratePlatform(World worldIn, BlockPos pos);
	//Code taken from Lumien's Random Things Nature Core tile entity
	/**
	 * Bonemeals the grass at a given position
	 * @param worldIn World
	 * @param pos Pos
	 */
	protected void bonemeal(World worldIn, BlockPos pos){
		IBlockState state = worldIn.getBlockState(pos);
		Random rand = new Random();
		//Try-catching our worries away!
		try{
			if (state.getBlock() instanceof IGrowable)
			{
				IGrowable growable = (IGrowable) state.getBlock();
				if (growable.canGrow(worldIn, pos, state, worldIn.isRemote))
				{
					worldIn.playBroadcastSound(2005, pos, 0);
					growable.grow(worldIn, rand, pos, state);
				}
			}
		}catch(Exception e){
		}
	}
	protected void bonemealTree(World worldIn, BlockPos pos){
		try{
			IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
			Random rand = new Random();	
			int attemptCap = 0;
			while(attemptCap < 10){
				growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
				attemptCap++;
			}
			if(attemptCap > 8){
				worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
			}
		}catch(Exception e){
			
		}
	}
	protected void bonemealBlockNoRemoval(World worldIn, BlockPos pos){
		try{
			IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
			Random rand = new Random();	
			int attemptCap = 0;
			while(attemptCap < 10){
				growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
				attemptCap++;
			}
		}catch(Exception e){
			
		}
	}
	
	/**
	 * Gathers the list of blocks to be generated 
	 * @param itemStackIn
	 * @param worldIn
	 * @param playerIn
	 * @param diameter
	 * @param desiredBiome
	 * @param changeBiome
	 * @return
	 */
	protected ItemStack gatherBlockGenList(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, int diameter, Biome desiredBiome, Boolean changeBiome){
		if(!worldIn.isRemote){
			int blocksGenerated = 0;
			int posX = MathHelper.floor_double(playerIn.posX); 
			int posY = MathHelper.floor_double(playerIn.posY);
			int posZ = MathHelper.floor_double(playerIn.posZ);
			int center;
			double radius = diameter/2.0;
			if(diameter%2 != 0){
				center = (int) (radius + 0.5);
			}else{
				center = (int) (radius);
			}
			int offsetXFirstHalf = (int) (posX + radius);
			//Not sure why this has to be offset by 1 extra, but it does.
			int offsetXSecondHalf = (int) (posX - radius + 1);
			ArrayList<BlockPos> posList = new ArrayList<BlockPos>(68);
			for(int i = 0; i < (center); i ++){
				//Creates a circle and fills it
				for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
					//Fills across the circle
					posList.add(new BlockPos(offsetXFirstHalf - i, posY - 1, posZ - i + placeInwards));
					posList.add(new BlockPos(offsetXFirstHalf - i, posY - 1, posZ + i - placeInwards));
				}
			}
			//Generates the second half
			for(int i = 0; i < (center); i ++){
				posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ  + i)); 
				posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i));
				for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
						posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ + i - placeInwards));
						posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i + placeInwards));
				}
			}
			//Hacky-fix to the island not generating properly on a single call
			for(int i = 0; i < 15; i++){
				blocksGenerated = generateSpike(posList, worldIn, playerIn, blocksGenerated, itemStackIn, desiredBiome, changeBiome);
			}
			itemStackIn.damageItem(blocksGenerated, playerIn);
		}
		return itemStackIn;
	}
	/**
	 * Converts the list of positions into blocks in the shape of a spike.
	 * @param posList List of positions
	 * @param worldIn World
	 * @param playerIn Player location
	 * @param blocksGenerated Number of blocks that have been generated so far
	 * @param itemStackIn The item used
	 * @param desiredBiome Biome type desired
	 * @param changeBiome If the config allows the biome to be changed
	 * @return Returns an int, usually the number of blocks generated in the world.
	 */
	protected int generateSpike(ArrayList<BlockPos> posList, World worldIn, EntityPlayer playerIn, int blocksGenerated, ItemStack itemStackIn, Biome desiredBiome, boolean changeBiome){
		ArrayList<BlockPos> recursiveList = new ArrayList<BlockPos>();
		for(BlockPos pos : posList){
			int surroundingBlocks = 0;
			blocksGenerated = generateBlocksInWorld(pos, worldIn, playerIn, blocksGenerated, desiredBiome, changeBiome);
			if(worldIn.getBlockState(pos.north()) != Blocks.AIR.getDefaultState()){
				surroundingBlocks++;
			}
			
			if(worldIn.getBlockState(pos.east()) != Blocks.AIR.getDefaultState()){
				surroundingBlocks++;
			}
			
			if(worldIn.getBlockState(pos.south()) != Blocks.AIR.getDefaultState()){
				surroundingBlocks++;
			}
			
			if(worldIn.getBlockState(pos.west()) != Blocks.AIR.getDefaultState()){
				surroundingBlocks++;
			}
			if((surroundingBlocks >= 3 || Math.random() < 0.05) && pos.getY() > 1){
				blocksGenerated = generateBlocksInWorld(pos.down(), worldIn, playerIn, blocksGenerated, desiredBiome, changeBiome);
				recursiveList.add(pos.down());
			}
		}
		if(!recursiveList.isEmpty()){
			blocksGenerated = generateSpike(recursiveList, worldIn, playerIn, blocksGenerated, itemStackIn, desiredBiome, changeBiome);
		}
		return blocksGenerated;
	}
	
	//Code taken from World Edit by Skq89
	//https://goo.gl/iEi0oU
	/**
	 * Changes the biome at a given position to the one given from the original method calls.
	 * @param worldIn World that it takes place in
	 * @param position Location of the biome change
	 * @param desiredBiome Biome ID that the biome will be moved to
	 * @param changeBiome If the config allows the biome change
	 * @return Returns whether or not the biome was changed.
	 */
	protected boolean setBiome(World worldIn, BlockPos position, Biome desiredBiome, Boolean changeBiome) {
        if(changeBiome){
			Chunk chunk = worldIn.getChunkFromBlockCoords(position);
	        if ((chunk != null) && (chunk.isLoaded())) {
	        	if(Biome.getIdForBiome(worldIn.getChunkFromBlockCoords(position).getBiome(position, worldIn.getBiomeProvider())) != Biome.getIdForBiome(desiredBiome)){
	        		chunk.getBiomeArray()[((position.getZ() & 0xF) << 4 | position.getX() & 0xF)] = (byte) desiredBiome.getIdForBiome(desiredBiome);
		            return true;
	        	}
	        }
        }
        return false;
    }
	/**
	 * Pass this method the position that the sapling will OCCUPY, not REST ON. Meaning Pos.UP of the platform position.
	 * @param worldIn World
	 * @param pos Position
	 * @return Returns if viable location
	 */
	protected boolean spacedFarEnough(World worldIn, BlockPos pos){
		int diameter = 5;
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		int center;
		ArrayList<BlockPos> posList = new ArrayList<BlockPos>(13);
		double radius = diameter/2.0;
		if(diameter%2 != 0){
			center = (int) (radius + 0.5);
		}else{
			center = (int) (radius);
		}
		int offsetXFirstHalf = (int) (posX + radius);
		//Not sure why this has to be offset by 1 extra, but it does.
		int offsetXSecondHalf = (int) (posX - radius + 1);
		for(int i = 0; i < (center); i ++){
			//Creates a circle and fills it
			for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
				//Fills across the circle
				posList.add(new BlockPos(offsetXFirstHalf - i, posY, posZ - i + placeInwards));
				posList.add(new BlockPos(offsetXFirstHalf - i, posY, posZ + i - placeInwards));
			}
		}
		//Generates the second half
		for(int i = 0; i < (center); i ++){
			posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ + i)); 
			posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ - i));
			
			for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
				posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ + i - placeInwards));
				posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ - i + placeInwards));
			}
		}
		for(BlockPos b : posList){
			if(!eligibleSpaceForTree(worldIn.getBlockState(b), b)){
				return false;
			}
		}
		return true;
	}
	protected boolean spacedFarEnough(World worldIn, BlockPos pos, int diameter){
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		int center;
		ArrayList<BlockPos> posList = new ArrayList<BlockPos>(13);
		double radius = diameter/2.0;
		if(diameter%2 != 0){
			center = (int) (radius + 0.5);
		}else{
			center = (int) (radius);
		}
		int offsetXFirstHalf = (int) (posX + radius);
		//Not sure why this has to be offset by 1 extra, but it does.
		int offsetXSecondHalf = (int) (posX - radius + 1);
		for(int i = 0; i < (center); i ++){
			//Creates a circle and fills it
			for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
				//Fills across the circle
				posList.add(new BlockPos(offsetXFirstHalf - i, posY, posZ - i + placeInwards));
				posList.add(new BlockPos(offsetXFirstHalf - i, posY, posZ + i - placeInwards));
			}
		}
		//Generates the second half
		for(int i = 0; i < (center); i ++){
			posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ  + i)); 
			posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ - i));
			for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
				posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ + i - placeInwards));
				posList.add(new BlockPos(offsetXSecondHalf + i, posY, posZ - i + placeInwards));
			}
		}
		for(BlockPos b : posList){
			if(!eligibleSpaceForTree(worldIn.getBlockState(b), b)){
				return false;
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add("Relog for client sync.");
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
