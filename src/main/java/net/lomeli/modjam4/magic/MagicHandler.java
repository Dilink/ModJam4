package net.lomeli.modjam4.magic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.lomeli.modjam4.Rings;
import net.lomeli.modjam4.lib.ModLibs;
import net.lomeli.modjam4.network.PacketHandler;
import net.lomeli.modjam4.network.PacketUpdatePlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MagicHandler {
    private List<ISpell> registeredSpells = new ArrayList<ISpell>();
    private HashMap<ISpell, Object[]> spellRecipes = new HashMap<ISpell, Object[]>();

    public MagicHandler() {
        this.registerSpell(new DebugSpell(), Items.apple);
    }

    public static MagicHandler getMagicHandler() {
        return Rings.proxy.magicHandler;
    }

    public static ISpell getSpellLazy(int index) {
        return getMagicHandler().getSpell(index);
    }

    public static void modifyPlayerMP(EntityPlayer player, int cost) {
        if (player.getEntityData().hasKey(ModLibs.PLAYER_DATA)) {
            NBTTagCompound tag = player.getEntityData().getCompoundTag(ModLibs.PLAYER_DATA);
            int mp = tag.getInteger(ModLibs.PLAYER_MP), max = tag.getInteger(ModLibs.PLAYER_MAX);
            mp += cost;
            if (mp > max)
                mp = max;
            if (mp < 0)
                mp = 0;
            PacketHandler.sendEverywhere(new PacketUpdatePlayerMP(player, mp, max));
        }
    }

    public static void modifyPlayerMaxMP(EntityPlayer player, int newMax) {
        if (player.getEntityData().hasKey(ModLibs.PLAYER_DATA)) {
            NBTTagCompound tag = player.getEntityData().getCompoundTag(ModLibs.PLAYER_DATA);
            int mp = tag.getInteger(ModLibs.PLAYER_MP);
            if (mp > newMax)
                mp = newMax;
            PacketHandler.sendEverywhere(new PacketUpdatePlayerMP(player, mp, newMax));
        }
    }

    public static boolean canUse(EntityPlayer player, int cost) {
        if (player.getEntityData().hasKey(ModLibs.PLAYER_DATA)) {
            NBTTagCompound tag = player.getEntityData().getCompoundTag(ModLibs.PLAYER_DATA);
            return tag.getInteger(ModLibs.PLAYER_MP) >= cost;
        }
        return false;
    }

    public void registerSpell(ISpell spell, Object...obj) {
        if (this.registeredSpells.contains(spell))
            return;
        this.registeredSpells.add(spell);
        this.spellRecipes.put(spell, obj);
    }

    public ISpell getSpell(int index) {
        if (index < this.registeredSpells.size())
            return this.registeredSpells.get(index);
        return null;
    }
    
    public Object[] getSpellRecipe(int index) {
        ISpell spell = getSpell(index);
        return spell == null ? null : this.spellRecipes.get(spell);
    }

}