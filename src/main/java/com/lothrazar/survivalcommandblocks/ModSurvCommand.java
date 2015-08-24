package com.lothrazar.survivalcommandblocks;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;  

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
 
@Mod(modid = ModSurvCommand.MODID, useMetadata = true) 
public class ModSurvCommand
{
	public static CreativeTabs tab = new CreativeTabs("tabSurvCommand") 
	{
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(BlockRegistry.command_block_regen);
		}
	};
	//forked from my old repo: 
	//https://github.com/PrinceOfAmber/SamsPowerups/commit/00a32f4a16739c307cf3c6149d2417dfff7ea3f3
	@SidedProxy(clientSide="com.lothrazar.survivalcommandblocks.ClientProxy", serverSide="com.lothrazar.survivalcommandblocks.CommonProxy")
	public static CommonProxy proxy;  @Instance(value = ModSurvCommand.MODID)
	public static ModSurvCommand instance;
	public static Logger logger;
	public final static String MODID = "survivalcommandblocks";
	public static String TEXTURE_LOCATION = MODID+":";

	public static ConfigSettings settings;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{ 
		logger = event.getModLog();
		 
		settings = new ConfigSettings(new Configuration(event.getSuggestedConfigurationFile()));
 
		MinecraftForge.EVENT_BUS.register(instance);
		FMLCommonHandler.instance().bus().register(instance);
	}

	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{     
		BlockRegistry.init();  
		
		proxy.registerRenderers();
	}
 
}