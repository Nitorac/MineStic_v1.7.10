package nitorac.minestic.core.events;

import net.minecraft.item.ItemStack;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;


public class SmeltPlasticEvent 
{
	@SubscribeEvent
	public void whenPlayerPickUpPlastic(PlayerEvent.ItemSmeltedEvent e)
	{
		if(e.smelting.getItem() == MineStic.PlasticIngot)
		{
			e.player.addStat(MineStic.PlasticSmelt, 1);
			
		}
	}
}
