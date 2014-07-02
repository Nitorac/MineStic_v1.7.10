package nitorac.minestic.core.events;

import net.minecraft.item.ItemStack;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;


public class PickupPlasticEvent 
{
	@SubscribeEvent
	public void whenPlayerPickUpPlastic(PlayerEvent.ItemPickupEvent e)
	{
		if(e.pickedUp.getEntityItem().isItemEqual(new ItemStack(MineStic.PlasticDust)))
		{
			e.player.addStat(MineStic.PlasticMine, 1);
			
		}
	}
}
