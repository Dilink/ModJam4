package net.lomeli.ring.magic.spells;

import net.lomeli.ring.lib.ModLibs;
import net.lomeli.ring.magic.ISpell;
import net.lomeli.ring.magic.MagicHandler;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class Harvest implements ISpell {

    @Override
    public boolean activateSpell(World world, EntityPlayer player, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int boost, int cost) {
        for (int j = -1; j < 1; j++)
            for (int k = -1; k < 1; k++) {
                if (MagicHandler.canUse(player, cost())) {
                    Block blk = world.getBlock(x + j, y, z + k);
                    if (blk != null && !world.isAirBlock(x + j, y, z + k)) {
                        BonemealEvent event = new BonemealEvent(player, world, blk, x + j, y, z + k);
                        if (MinecraftForge.EVENT_BUS.post(event))
                            return false;
                        if (blk instanceof IGrowable) {
                            IGrowable grow = (IGrowable) blk;
                            if (grow.func_149851_a(world, x + j, y, z + k, world.isRemote)) {
                                world.spawnParticle("happyVillager", x + j + 0.5, y + 0.5, z + k + 0.5, world.rand.nextDouble() + 2, world.rand.nextDouble() + 2, world.rand.nextDouble() + 2);
                                if (!world.isRemote) {
                                    if (grow.func_149852_a(world, world.rand, x + j, y, z + k)) {
                                        MagicHandler.modifyPlayerMP(player, -cost());
                                        grow.func_149853_b(world, world.rand, x + j, y, z + k);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return false;
    }

    @Override
    public void applyToMob(EntityPlayer player, Entity target, int boost, int cost) {
    }

    @Override
    public void onUpdateTick(ItemStack stack, World world, Entity entity, int par4, boolean par5, int boost, int cost, boolean bool) {
    }

    @Override
    public String getUnlocalizedName() {
        return ModLibs.HARVEST;
    }

    @Override
    public int cost() {
        return 30;
    }

}
