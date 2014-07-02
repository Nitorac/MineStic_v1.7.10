package nitorac.minestic.core.blocks_fluids.cakes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RottenCakeCuit extends Block
{
	private IIcon cakeTopIcon, cakeBottomIcon, cakeInnerIcon;

	public RottenCakeCuit()
	{
		super(Material.cake);
		this.setTickRandomly(true);
	}

	public IIcon getIcon(int icon1, int icon2)
	{
		return icon1 == 1 ? this.cakeTopIcon : (icon1 == 0 ? this.cakeBottomIcon : (icon2 > 0 && icon1 == 4 ? this.cakeInnerIcon : this.blockIcon));
	}

	public void registerBlockIcons(IIconRegister iconregister)
	{
		this.blockIcon = iconregister.registerIcon("minestic:rottencake_side");
		this.cakeInnerIcon = iconregister.registerIcon("minestic:rottencake_inner");
		this.cakeTopIcon = iconregister.registerIcon("minestic:rottencake_top");
		this.cakeBottomIcon = iconregister.registerIcon("minestic:rottencake_bottom");
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int dir012, int dir3, int y)
	{
		int l = par1IBlockAccess.getBlockMetadata(dir012, dir3, y);
		float f = 0.0625F;
		float f1 = (float)(1 + l * 2) / 16.0F;
		float f2 = 0.5F;
		this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int dir012, int dir3, int y)
	{
		int l = world.getBlockMetadata(dir012, dir3, y);
		float f = 0.0625F;
		float f1 = (float)(1 + l * 2) / 16.0F;
		float f2 = 0.5F;
		return AxisAlignedBB.getBoundingBox((double)((float)dir012 + f1), (double)dir3, (double)((float)y + f), (double)((float)(dir012 + 1) - f), (double)((float)dir3 + f2 - f), (double)((float)(y + 1) - f));
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int dir012, int dir3, int y)
	{
		int l = world.getBlockMetadata(dir012, dir3, y);
		float f = 0.0625F;
		float f1 = (float)(1 + l * 2) / 16.0F;
		float f2 = 0.5F;
		return AxisAlignedBB.getBoundingBox((double)((float)dir012 + f1), (double)dir3, (double)((float)y + f), (double)((float)(dir012 + 1) - f), (double)((float)dir3 + f2), (double)((float)(y + 1) - f));
	}

	public void setBlockBoundsForItemRender()
	{
		float f = 0.0625F;
		float f1 = 0.5F;
		this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        this.func_150036_b(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_);
        return true;
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_)
    {
        this.func_150036_b(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
    }

    private void func_150036_b(World p_150036_1_, int p_150036_2_, int p_150036_3_, int p_150036_4_, EntityPlayer p_150036_5_)
    {
        if (p_150036_5_.canEat(false))
        {
            p_150036_5_.getFoodStats().addStats(2, 0.1F);
            int l = p_150036_1_.getBlockMetadata(p_150036_2_, p_150036_3_, p_150036_4_) + 1;

            if (l >= 6)
            {
                p_150036_1_.setBlockToAir(p_150036_2_, p_150036_3_, p_150036_4_);
            }
            else
            {
                p_150036_1_.setBlockMetadataWithNotify(p_150036_2_, p_150036_3_, p_150036_4_, l, 2);
            }
        }
    }

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return !super.canPlaceBlockAt(world, x, y, z) ? false : this.canBlockStay(world, x, y, z);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z)
	{
		if(!this.canBlockStay(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
		}
	}

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlock(x, y - 1, z).getMaterial().isSolid();
	}

	public int quantityDropped(Random random)
	{
		return 0;
	}

	public int idDropped(int id, Random random, int par3)
	{
		return 0;
	}

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return MineStic.ItemRottenCake;
    }
}