package nitorac.minestic.core.items;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import nitorac.minestic.core.MineStic;

public class ItemPlasticSpeed extends Item
{
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		Random rand = new Random();
		int x = (int)player.posX;
		int y = (int)player.posY;
		int z = (int)player.posZ;
		player.addPotionEffect(new PotionEffect(1, 1200, 2));
		int i = MathHelper.floor_double(x);
        int j = MathHelper.floor_double(y);
        int k = MathHelper.floor_double(z);

        for (i = 0; i < 7; ++i)
        {
            j = MathHelper.floor_double(x) + rand.nextInt(6) + 1;
            k = MathHelper.floor_double(y) + rand.nextInt(5);
            int l = MathHelper.floor_double(z) + rand.nextInt(6) + 1;
            
            int j1 = MathHelper.floor_double(x+2) + rand.nextInt(6) + 1;
            int l1 = MathHelper.floor_double(z+2) + rand.nextInt(6) + 1;
            
            int j2 = MathHelper.floor_double(x-2) + rand.nextInt(6) + 1;
            int l2 = MathHelper.floor_double(z-2) + rand.nextInt(6) + 1;

          
                world.setBlock(j+1, k, l-1, MineStic.plasticFire);
                world.setBlock(j+1, k, l+1, MineStic.plasticFire);
                world.setBlock(j-1, k, l-1, MineStic.plasticFire);
                world.setBlock(j-1, k, l+1, MineStic.plasticFire);
                world.setBlock(j1+1, k, l1-1, MineStic.plasticFire);
                world.setBlock(j1+1, k, l1+1, MineStic.plasticFire);
                world.setBlock(j1-1, k, l1-1, MineStic.plasticFire);
                world.setBlock(j1-1, k, l1+1, MineStic.plasticFire);
                world.setBlockToAir(x, y, z);
          }
		return stack;
	}	
}
