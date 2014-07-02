package nitorac.minestic.core.blocks_fluids;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlasticSlabs extends BlockSlab
{
	public static final String[] StepTypes = new String[] {"plastic", "plasticchaud", "diamond", "gold", "dirt"};

	public PlasticSlabs(boolean isdouble, Material material)
	{
		super(isdouble, material);
		this.setCreativeTab(MineStic.PlasticTab);
		if(!this.field_150004_a)
		{
			this.setLightOpacity(0);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		int k = metadata & 7;
		return k == 0 ? MineStic.Plastic.getBlockTextureFromSide(side) : k == 1 ? MineStic.PlasticChaud.getBlockTextureFromSide(side) : k == 2 ? Blocks.diamond_block.getBlockTextureFromSide(side) : k == 3 ? Blocks.gold_block.getBlockTextureFromSide(side) : Blocks.dirt.getBlockTextureFromSide(side);
	}

	@SideOnly(Side.CLIENT)
	private static boolean func_150003_a(Block block)
	{
		return block == MineStic.MineSlab;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return func_150003_a(this) ? Item.getItemFromBlock(MineStic.MineSlab) : Item.getItemFromBlock(MineStic.DoubleMineSlab);
	}

	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.stone_slab);
	}

	protected ItemStack createStackedBlock(int metadata)
	{
		return new ItemStack(MineStic.MineSlab, 2, metadata & 7);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		if(item != Item.getItemFromBlock(MineStic.DoubleMineSlab))
		{
			for(int i = 0; i < StepTypes.length; i++)
			{
				list.add(new ItemStack(item, 1, i));
			}
		}
	}

	@Override
	public String func_150002_b(int metadata)
	{
		if(metadata < 0 || metadata >= StepTypes.length)
		{
			metadata = 0;
		}
		return super.getUnlocalizedName() + "." + StepTypes[metadata];
	}
}