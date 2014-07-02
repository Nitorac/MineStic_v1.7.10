package nitorac.minestic.core.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import nitorac.minestic.core.blocks_fluids.BlockPlasticBlock;

public class ItemPlasticBlock extends ItemBlock
{
	public ItemPlasticBlock(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int metadata)
	{
		return metadata;
	}

	public String getUnlocalizedName(ItemStack stack)
	{
		int metadata = stack.getItemDamage();
		if(metadata > BlockPlasticBlock.type.length || metadata < 0)
		{
			metadata = 0;
		}
		return super.getUnlocalizedName() + "." + BlockPlasticBlock.type[metadata];
	}
}
