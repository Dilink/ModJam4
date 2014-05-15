package net.lomeli.modjam4.client;

import net.lomeli.modjam4.block.tile.TileAltar;
import net.lomeli.modjam4.block.tile.TileItemAltar;
import net.lomeli.modjam4.client.render.RenderAltar;
import net.lomeli.modjam4.core.Proxy;
import net.lomeli.modjam4.lib.ModLibs;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends Proxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
        ModLibs.altarRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderAltar altarRenderer = new RenderAltar();
        ClientRegistry.bindTileEntitySpecialRenderer(TileAltar.class, altarRenderer);
        ClientRegistry.bindTileEntitySpecialRenderer(TileItemAltar.class, altarRenderer);
    }

    @Override
    public void postInit() {
        super.postInit();
    }

}
