package nitorac.minestic.core.blocks_fluids;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlasticChaud extends Block {

	public BlockPlasticChaud() 
	{
		super(Material.iron);
		this.setTickRandomly(true);
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z)
	{
		if(!this.canBlockStay(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
		}
	}
	
    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlock(x, y - 1, z).getMaterial().isLiquid();
	}
	
	public void updateTick(World worldObj, int x, int y, int z, Random random)
    {
		if (worldObj.getBlock(x, y + 1, z) == Blocks.water || worldObj.getBlock(x, y + 1, z) == Blocks.flowing_water || 
            	worldObj.getBlock(x + 1, y, z) == Blocks.water || worldObj.getBlock(x + 1, y, z) == Blocks.flowing_water || 
            	worldObj.getBlock(x - 1, y, z) == Blocks.water || worldObj.getBlock(x - 1, y, z) == Blocks.flowing_water || 
            	worldObj.getBlock(x, y, z+1) == Blocks.water || worldObj.getBlock(x, y, z+1) == Blocks.flowing_water || 
            	worldObj.getBlock(x, y, z-1) == Blocks.water || worldObj.getBlock(x, y, z-1) == Blocks.flowing_water)
            {
                worldObj.setBlock(x, y, z, MineStic.Plastic);
                worldObj.playSound((double)((float)x + 0.25F), (double)((float)y + 0.25F), (double)((float)z + 0.25F), "random.breath", 1.0F + random.nextFloat(), random.nextFloat() * 0.35F + 0.15F, false);
            }
       
        if(worldObj.canBlockSeeTheSky(x, y + 1, z) && (worldObj.isRaining() || worldObj.isThundering()))
        {
        	 worldObj.setBlock(x, y, z, MineStic.Plastic);
        	 worldObj.playSound((double)((float)x + 0.25F), (double)((float)y + 0.25F), (double)((float)z + 0.25F), "random.breath", 1.0F + random.nextFloat(), random.nextFloat() * 0.35F + 0.15F, false);
        }        
    }
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        float f = 0.0015F;
        return AxisAlignedBB.getBoundingBox((double)((float)p_149668_2_ + f), (double)p_149668_3_, (double)((float)p_149668_4_ + f), (double)((float)(p_149668_2_ + 1) - f), (double)((float)(p_149668_3_ + 1) - f), (double)((float)(p_149668_4_ + 1) - f));
    }
    
	public void onEntityCollidedWithBlock(World worldObj, int x, int y, int z, Entity p_149670_5_)
    {
        p_149670_5_.attackEntityFrom(DamageSource.inFire, 2.5F);
    }

    public int getMobilityFlag()
    {
        return 0;
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
    
