package nitorac.minestic.core.blocks_fluids;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlasticBlock extends Block
{
	public static String[] type = new String[] {"block", "chiseled", "hardened"};

	private IIcon Icon1, Icon2, Icon3;

	public BlockPlasticBlock()
	{
		super(Material.iron);
		this.setHarvestLevel("pickaxe", 2, 0);
	}
	
	public void registerBlockIcons(IIconRegister iconregister)
	{
		Icon1 = iconregister.registerIcon("minestic:PlasticBlock");
		Icon2 = iconregister.registerIcon("minestic:PlasticBlockChiseled");
		Icon3 = iconregister.registerIcon("minestic:PlasticBlock");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for(int metadata = 0; metadata < type.length; metadata++)
		{
			list.add(new ItemStack(item, 1, metadata));
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		switch(metadata)
		{
		case 0:
			return Icon1;
		case 1:
			return Icon2;
		case 2:
			return Icon3;
		default:
			return blockIcon;
		}
	}

	public int damageDropped(int metadata)
	{
		return metadata;
	}

}