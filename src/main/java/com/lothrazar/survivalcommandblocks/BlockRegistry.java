package com.lothrazar.survivalcommandblocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lothrazar.survivalcommandblocks.BlockCommandBlockCraftable.CommandType;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry 
{

	static BlockCommandBlockCraftable command_block_regen;
	static BlockCommandBlockCraftable command_block_firetick;
	static BlockCommandBlockCraftable command_block_daycycle;
	static BlockCommandBlockCraftable command_block_mobgrief;
	static BlockCommandBlockCraftable command_block_weather;
	static BlockCommandBlockCraftable command_block_sky; 
	
	public static void init()
	{ 
		command_block_regen = new BlockCommandBlockCraftable( CommandType.Gamerule, "naturalRegeneration");
		registerBlockHelper(command_block_regen,"command_block_regen");
		if(ModSurvCommand.settings.regen_recipe)
			addRecipe(command_block_regen,new ItemStack(Items.golden_apple)); 
  
		command_block_mobgrief = new BlockCommandBlockCraftable(CommandType.Gamerule, "mobGriefing");
		registerBlockHelper(command_block_mobgrief,"command_block_mobgrief");
		if(ModSurvCommand.settings.mobgrief_recipe)
			addRecipe(command_block_mobgrief,new ItemStack(Blocks.tnt)); 

		command_block_firetick = new BlockCommandBlockCraftable(CommandType.Gamerule, "doFireTick"); 
		registerBlockHelper(command_block_firetick,"command_block_firetick");
		if(ModSurvCommand.settings.firetick_recipe)
			addRecipe(command_block_firetick,new ItemStack( Items.lava_bucket)); 
 
		command_block_daycycle = new BlockCommandBlockCraftable(CommandType.Gamerule,"doDaylightCycle");
		registerBlockHelper(command_block_daycycle,"command_block_daycycle");
		if(ModSurvCommand.settings.daycycle_recipe)
			addRecipe(command_block_daycycle,new ItemStack(Blocks.glowstone)); 
  
		command_block_weather = new BlockCommandBlockCraftable(CommandType.Weather); 
		registerBlockHelper(command_block_weather,"command_block_weather");  
		if(ModSurvCommand.settings.weather_recipe)
				addRecipe(command_block_weather,new ItemStack(Items.water_bucket)); 
		
		command_block_sky = new BlockCommandBlockCraftable(CommandType.Teleport,64); 
		registerBlockHelper(command_block_sky,"command_block_sky"); 
		if(ModSurvCommand.settings.sky_recipe)
			addRecipe(command_block_sky,new ItemStack(Items.ender_eye)); 
	}
	
	private static void addRecipe(BlockCommandBlockCraftable output, ItemStack center)
	{ 
		GameRegistry.addRecipe(new ItemStack(output), 
				"rcr", 
				"tet",
				"rcr", 
				'c', Items.comparator, 
				'e', center, 
				'r', Blocks.redstone_block, 
				't', Items.ghast_tear);
	}
    public static ArrayList<Item> delay = new ArrayList<Item>();
	public static ArrayList<String> delayNames = new ArrayList<String>(); 
	public static void registerBlockHelper(Block s, String name)
	{
		s.setUnlocalizedName(name);//.setBlockTextureName(TEXTURE_LOCATION + name)
		GameRegistry.registerBlock(s, name);

		 setTextureNameForItem(Item.getItemFromBlock(s), name); 
	}
	public static void registerItemHelper(Item s, String name)
	{
		s.setUnlocalizedName(name);//.setTextureName(TEXTURE_LOCATION + name)
		GameRegistry.registerItem(s, name);
		 setTextureNameForItem(s, name); 
	}
	private static void setTextureNameForItem(Item s, String name)                                   
    {                                                                                              	
    	 delay.add(s);                                                                              	
    	 delayNames.add(name);                                                                      	
    }  
}
