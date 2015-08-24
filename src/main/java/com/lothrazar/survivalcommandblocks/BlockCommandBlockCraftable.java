package com.lothrazar.survivalcommandblocks;

import java.util.Random; 

import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCommandBlockCraftable extends BlockCommandBlock
{ 
	public static enum CommandType
	{
		Teleport, Gamerule, Weather
	}
	
	private CommandType type;
	private String rule = null;
	private int dist = 0;
	
	private void setConstructorDefaults()
	{ 
		this.setHardness(3F);
		this.setResistance(5F);
		this.setCreativeTab(ModSurvCommand.tab);
	}
	
	public BlockCommandBlockCraftable(CommandType t)
	{   
		type = t;
		this.rule = null;
		setConstructorDefaults(); 
	}
	
	public BlockCommandBlockCraftable(CommandType t, String rl)
	{     
		type = t;
		this.rule = rl;
		setConstructorDefaults();
	}
	
	public BlockCommandBlockCraftable(CommandType t, int r)
	{     
		type = t;
		this.dist = r;
		setConstructorDefaults();
	}
	 
	@Override
	public void onBlockClicked(World w, BlockPos ps, EntityPlayer p) 
	{  
		super.onBlockClicked( w, ps,  p) ;
	}

	@Override
	public void updateTick(World w,  BlockPos ps,IBlockState state, Random r)
    {   
		//this fires on redstone power 
        TileEntity tileentity = w.getTileEntity(ps); 
        if (tileentity == null ) {return;}
        if(!(tileentity instanceof TileEntityCommandBlock)) {return;}
        int d = 2;
     
        String command = null;   //set the command of the block as a string, just as a player would type it

        switch(type)
        {
	        case Teleport:
	    		
	    		EntityPlayer p = w.getClosestPlayer(ps.getX(), ps.getY(), ps.getZ(), 6);
	    		if(p==null){return;}
	    		
	    		//boolean inWall = true;
	    		//Block current;

	        	int _x = (int) p.posX;//w.getWorldInfo().getSpawnX();
	    		int _y = (int) p.posY + dist;//w.getWorldInfo().getSpawnY();
	    		int _z = (int) p.posZ;//w.getWorldInfo().getSpawnZ();
	    		/*
	    		while(inWall)
	    		{
	    			current = w.getBlock(_x, _y, _z); 
	    			
	    			if(current == Blocks.air) 
	    			{
	    				inWall = false;
	    			}
	    			else 
	    			{
	    				_y++; 
	    			}
	    			//either we are out in open air, or we have moved up one block so loop again
	    		}
	    		*/
	    		command = "tp @p " + _x +  " "+_y+" "+_z;
	        break; 
	        case Gamerule:
	        	
	        	String lastVal = w.getGameRules().getGameRuleStringValue(rule); 
	
	        	//toggle it based on previous value
				lastVal = (lastVal.equals("false")) ? "true" : "false";  
	
				//Chat.addMessage(w, rule+" = "+lastVal);  
		        
	            command = "gamerule "+ rule +" "+lastVal;
	            
	        	break;
	        case Weather:
	        	
	        	command = "toggledownfall";
	        	
	        break;
        }
         
        //in 1.8 snapshot, we will use execute possibly?
       // commandblocklogic.func_145752_a("/execute @p "+x+" "+y+" "+z+" toggledownfall");
         
        if(command != null)
        {
        	command = "/"+command;

	        CommandBlockLogic commandblocklogic = ((TileEntityCommandBlock)tileentity).getCommandBlockLogic();//func_145993_a();
	         
	        commandblocklogic.setCommand(command);//.func_145752_a(command); //set current command into this CommandClock
	        
	        //execute my current command in the World
	        commandblocklogic.trigger(w);//.func_145755_a(w);
	        w.notifyBlockOfStateChange(ps, this);//.func_147453_f(x, y, z, this);
	        w.markBlockForUpdate(ps);
        }
    }
	  
	@Override
	//public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{ 
		//disables the player from opening the edit screen to alter the command
		return false;
    }
	
	@Override 
	public int quantityDropped(Random p_149745_1_)
    {
		//change from 0 to 1 so it is harvestable
        return 4;
    }
	
	@Override
	public Item getItemDropped(IBlockState state_, Random p_149650_2_, int fortune)
    { 
       return Item.getItemFromBlock(Blocks.redstone_block);//force them to use silk touch to get it back
    }
	
	@Override
	public boolean canSilkHarvest(World world,BlockPos  pos,IBlockState state,EntityPlayer player)
    {
		return true;
    }
}
