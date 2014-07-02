package nitorac.minestic.core.blocks_fluids;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlastic extends Block {

	public BlockPlastic() 
	{
		super(Material.rock);
		this.setCreativeTab(MineStic.PlasticTab);
		this.setTickRandomly(true);
		this.setHarvestLevel("pickaxe", 1, 0);
	}

	    /**
	     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	     * coordinates.  Args: blockAccess, x, y, z, side
	     */
	    
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (p_149674_1_.getSavedLightValue(EnumSkyBlock.Block, p_149674_2_, p_149674_3_, p_149674_4_) > 11 - this.getLightOpacity())
        {
        	if (p_149674_1_.provider.dimensionId == 1)
        	{
        		p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, MineStic.PlasticBlock);
                return;
        	}
        	if (p_149674_1_.provider.isHellWorld)
            {
                p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, MineStic.PlasticChaud);
                return;
            }
            p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, MineStic.PlasticChaud);
        }
    }
    public int getMobilityFlag()
    {
        return 0;
    }
    
    //Drop des items
    public int quantityDropped(Random p_149745_1_)
    {
        return 2 + p_149745_1_.nextInt(3);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return MineStic.PlasticDust;
    }

    public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.blueColor;
    }
}
