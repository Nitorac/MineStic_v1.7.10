package nitorac.minestic.core.blocks_fluids;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockPlasticFence extends BlockFence
{

	public BlockPlasticFence(String p_i45406_1_, Material p_i45406_2_) 
	{
		super("minestic:PlasticChaud", Material.iron);
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldObj, int xPos, int yPos, int zPos, Random p_149734_5_)
    {
    	for (int l = 0; l < 1; l++)
        {
          double d0 = xPos + p_149734_5_.nextFloat();
          double d1 = yPos + p_149734_5_.nextFloat();
          double d2 = zPos + p_149734_5_.nextFloat();
          double d3 = 0.0D;
          double d4 = 0.0D;
          double d5 = 0.0D;
          int i1 = p_149734_5_.nextInt(2) * 2 - 1;
          d3 = (p_149734_5_.nextFloat() - 0.7D) * 1.000000001490116D;
          d4 = (p_149734_5_.nextFloat() - 1.0D) * 1.000000001490116D;
          d5 = (p_149734_5_.nextFloat() - 0.7D) * 1.000000001490116D;
          worldObj.spawnParticle("lava", d0, d1, d2, d3, d4, d5);
        }
    	
    	for (int l = 0; l < 3; l++)
        {
          double d0 = xPos + p_149734_5_.nextFloat();
          double d1 = yPos + p_149734_5_.nextFloat();
          double d2 = zPos + p_149734_5_.nextFloat();
          double d3 = 0.0D;
          double d4 = 0.0D;
          double d5 = 0.0D;
          int i1 = p_149734_5_.nextInt(2) * 2 - 1;
          d3 = (p_149734_5_.nextFloat() - 0.7D) * 1.000000001490116D;
          d4 = (p_149734_5_.nextFloat() - 1.0D) * 1.000000001490116D;
          d5 = (p_149734_5_.nextFloat() - 0.7D) * 1.000000001490116D;
          worldObj.spawnParticle("crit", d0, d1, d2, d3, d4, d5);
        }
    }

}
