package nitorac.minestic.core.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;

public class ItemPlasticDust extends Item
{
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		
		if(player.capabilities.isCreativeMode == true)
		{
			player.setGameType(GameType.SURVIVAL);
		}
		
		else if(player.capabilities.isCreativeMode == false)
		{
			player.setGameType(GameType.CREATIVE);
		}
		
		return stack;
	}	
}