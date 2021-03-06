package net.lomeli.ring.magic.spells;

import net.lomeli.ring.item.ModItems;
import net.lomeli.ring.lib.ModLibs;
import net.lomeli.ring.magic.ISpell;
import net.lomeli.ring.magic.MagicHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Rearm implements ISpell {

    @Override
    public boolean activateSpell(World world, EntityPlayer player, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int boost, int cost) {
        if (MagicHandler.canUse(player, cost())) {
            ItemStack stack = new ItemStack(ModItems.ghostSword);
            player.inventory.addItemStackToInventory(stack);
            MagicHandler.modifyPlayerMP(player, -cost());
        }
        return false;
    }

    @Override
    public void applyToMob(EntityPlayer player, Entity target, int boost, int cost) {
    }

    @Override
    public void onUpdateTick(ItemStack stack, World world, Entity entity, int par4, boolean par5, int boost, int cost, boolean enabled) {
    }

    @Override
    public String getUnlocalizedName() {
        return ModLibs.REARM;
    }

    @Override
    public int cost() {
        return 50;
    }

}
