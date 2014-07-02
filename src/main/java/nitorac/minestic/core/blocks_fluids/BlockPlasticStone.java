package nitorac.minestic.core.blocks_fluids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class BlockPlasticStone extends Block
{

	public BlockPlasticStone() 
	{
		super(Material.rock);
	}
	
	 public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_)
	    {
	        p_149636_2_.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
	        p_149636_2_.addExhaustion(0.025F);

	        if (this.canSilkHarvest(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_) && EnchantmentHelper.getSilkTouchModifier(p_149636_2_))
	        {
	            ItemStack itemstack = this.createStackedBlock(p_149636_6_);

	            if (itemstack != null)
	            {
	                this.dropBlockAsItem(p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, itemstack);
	            }
	        }
	        else
	        {
	            int i1 = EnchantmentHelper.getFortuneModifier(p_149636_2_);
	            this.dropBlockAsItem(p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_, i1);
	            Material material = p_149636_1_.getBlock(p_149636_3_, p_149636_4_, p_149636_5_).getMaterial();

	            if (material.blocksMovement() || material.isLiquid())
	            {
	                p_149636_1_.setBlock(p_149636_3_, p_149636_4_, p_149636_5_, Blocks.flowing_water);
	            }
	        }
	    }
    


}
