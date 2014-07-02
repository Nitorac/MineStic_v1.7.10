package nitorac.minestic.core.blocks_fluids;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlasticFluid extends BlockFluidClassic
{
	private IIcon stillIcon, flowingIcon;

	public BlockPlasticFluid(Fluid fluid, Material material)
	{
		super(fluid, material);
		this.setTickRandomly(true);
	}

	public void onEntityCollidedWithBlock(World worldObj, int x, int y, int z, Entity p_149670_5_)
    {
		Random rand = new Random();
        p_149670_5_.playSound("fire.ignite", rand.nextFloat() - 0.1F, rand.nextFloat() + 0.5F);
    }
	
	public IIcon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldObj, int x, int y, int z, Random random)
	{
		if(worldObj.getBlock(x, y + 1, z) == Blocks.air)
		{
	          double d0 = x + random.nextFloat();
	          double d1 = y + 0.5F + random.nextFloat();
	          double d2 = z + random.nextFloat();
	          double d3 = 0.0D;
	          double d4 = 0.0D;
	          double d5 = 0.0D;
	          int i1 = random.nextInt(2) * 2 - 4;
	          d3 = 0.0D;
	          d4 = 0.1D;
	          d5 = 0.14524D;
	          worldObj.spawnParticle("flame", d0, d1 + 1, d2, d3, d4, d5);
		}
	}
	
	public void registerBlockIcons(IIconRegister register)
	{
		stillIcon = register.registerIcon("minestic:plasticfluid_still");
		flowingIcon = register.registerIcon("minestic:plasticfluid_flow");
	}

	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if(world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if(world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}
}