package nitorac.minestic.core.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRottenCake extends Item
{
	
    public ItemRottenCake()
    {
        this.setCreativeTab(MineStic.PlasticTab);
    }
    
    public boolean onItemUse(ItemStack ItemStack, EntityPlayer entityplayer, World world, int x, int y, int z, int par7, float hitX, float hitY, float hitZ)
    {
        if (world.getBlock(x, y, z) != Blocks.snow)
        {
            if (par7 == 0)
            {
                --y;
            }

            if (par7 == 1)
            {
                ++y;
            }

            if (par7 == 2)
            {
                --z;
            }

            if (par7 == 3)
            {
                ++z;
            }

            if (par7 == 4)
            {
                --x;
            }

            if (par7 == 5)
            {
                ++x;
            }

            if (!world.isAirBlock(x, y, z))
            {
                return false;
            }
        }

        if (!entityplayer.canPlayerEdit(x, y, z, par7, ItemStack))
        {
            return false;
        }
        else
        {
            if (Blocks.redstone_wire.canPlaceBlockAt(world, x, y, z))
            {
                --ItemStack.stackSize;
                world.setBlock(x, y, z, MineStic.RottenCakeCuit);
				String placesound = MineStic.RottenCakeCuit.stepSound.getStepResourcePath();
				float volume = MineStic.RottenCakeCuit.stepSound.getVolume();
				float pitch = MineStic.RottenCakeCuit.stepSound.getPitch();
				world.playSoundEffect(x, y, z, placesound, volume, pitch);
            }
            return true;
        }
    }
}