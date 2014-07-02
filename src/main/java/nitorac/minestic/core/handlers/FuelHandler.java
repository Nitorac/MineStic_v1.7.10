package nitorac.minestic.core.handlers;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.toString().contains("PlasticCoal"))
		{
			return 4000;
		}
		return 0;
	}
	
}
