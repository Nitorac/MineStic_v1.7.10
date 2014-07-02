package nitorac.minestic.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingManager 
{
	public static void mainRecAndSmelt()
	{
		recipies();
		smeltings();
	}
	
	public static void recipies()
	{
		GameRegistry.addRecipe(new ItemStack(MineStic.MineSlab, 6, 0), "   ", "   ", "xxx", 'x', MineStic.PlasticIngot);
		GameRegistry.addRecipe(new ItemStack(MineStic.Plastic_Lamp), " x ", "xyx", " x ", 'x', MineStic.PlasticIngot, 'y', Blocks.glowstone);
		GameRegistry.addRecipe(new ItemStack(MineStic.ItemRottenCake), "AAA", "xyx", "BBB", 'A', Items.milk_bucket, 'y', Items.egg, 'x', Items.sugar, 'B', Items.rotten_flesh);
		GameRegistry.addRecipe(new ItemStack(MineStic.PlasticFlintAndSteel), "x ", " y", 'x', MineStic.PlasticIngot, 'y', Items.flint);
	}
	
	public static void smeltings()
	{
		GameRegistry.addSmelting(MineStic.PlasticDust, new ItemStack(MineStic.PlasticIngot), 10.0F);
		GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(MineStic.RottenCuite), 4.0F);

	}
}
