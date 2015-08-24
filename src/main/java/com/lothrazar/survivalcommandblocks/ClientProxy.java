package com.lothrazar.survivalcommandblocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class ClientProxy extends CommonProxy  
{
	 
	@Override
	public void registerRenderers() 
    {  
		String item;
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
   		for(int i = 0; i < BlockRegistry.delay.size(); i++)
   		{
   			item = ModSurvCommand.TEXTURE_LOCATION + BlockRegistry.delayNames.get(i); 
   			mesher.register(BlockRegistry.delay.get(i), 0, new ModelResourceLocation( item , "inventory"));					
   		} 
    }
}
