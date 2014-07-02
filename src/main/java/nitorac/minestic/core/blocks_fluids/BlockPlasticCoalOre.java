package nitorac.minestic.core.blocks_fluids;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import nitorac.minestic.core.MineStic;

public class BlockPlasticCoalOre extends Block
{

	public BlockPlasticCoalOre() 
	{
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 2, 0);
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return MineStic.PlasticCoal;
    }
	
    protected boolean canSilkHarvest()
    {
        return true;
    }
	
    public int quantityDropped(Random p_149745_1_)
    {
        return 1 + p_149745_1_.nextInt(1);
    }
	
	private Random rand = new Random();
	@Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
        return 3 + rand.nextInt(1) + rand.nextInt(2);
    }
	
    public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.obsidianColor;
    }

}
