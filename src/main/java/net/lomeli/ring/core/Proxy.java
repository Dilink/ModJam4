package net.lomeli.ring.core;

import net.lomeli.ring.block.ModBlocks;
import net.lomeli.ring.block.tile.TileAltar;
import net.lomeli.ring.block.tile.TileItemAltar;
import net.lomeli.ring.core.handler.EntityHandler;
import net.lomeli.ring.core.handler.GameEventHandler;
import net.lomeli.ring.item.ModItems;
import net.lomeli.ring.magic.MagicHandler;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class Proxy {
    public MagicHandler magicHandler;
    
    public void preInit() {
        magicHandler = new MagicHandler();
        ModItems.loadItems();
        ModBlocks.loadBlocks();
    }
    
    public void init() {
        GameRegistry.registerTileEntity(TileAltar.class, TileAltar.class.getName().toLowerCase());
        GameRegistry.registerTileEntity(TileItemAltar.class, TileItemAltar.class.getName().toLowerCase());
        
        MinecraftForge.EVENT_BUS.register(new EntityHandler());
        FMLCommonHandler.instance().bus().register(new GameEventHandler());
    }
    
    public void postInit() {
        
    }
}
