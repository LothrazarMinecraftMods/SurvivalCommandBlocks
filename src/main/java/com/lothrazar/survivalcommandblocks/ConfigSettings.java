package com.lothrazar.survivalcommandblocks;

import net.minecraftforge.common.config.Configuration;

public class ConfigSettings 
{
	public boolean daycycle_recipe;
	public boolean firetick_recipe;
	public boolean mobgrief_recipe;
	public boolean regen_recipe;
	public boolean sky_recipe;
	public boolean weather_recipe;
	
	Configuration config;
	public ConfigSettings(Configuration configuration) 
	{
		config = configuration;
		String category = "";
		daycycle_recipe = config.getBoolean("daycycle_recipe", category, true, "Toggles gamerule doDaylightCycle on redstone power.  Setting false will disable the recipe.");
		firetick_recipe = config.getBoolean("firetick_recipe", category, true, "Toggles gamerule doFireTick on redstone power.  Setting false will disable the recipe.");
		mobgrief_recipe = config.getBoolean("mobgrief_recipe", category, true, "Toggles gamerule doMobgrief on redstone power.  Setting false will disable the recipe.");
		regen_recipe = config.getBoolean("regen_recipe", category, true, "Toggles gamerule naturalRegeneration on redstone power.  Setting false will disable the recipe.");
		sky_recipe = config.getBoolean("sky_recipe", category, true, "Sends the player 64 blocks up on redstone power.  Setting false will disable the recipe.");
		weather_recipe = config.getBoolean("weather_recipe", category, true, "Executes toggledownfall on redstone power.  Setting false will disable the recipe.");
		
		
	}

}
