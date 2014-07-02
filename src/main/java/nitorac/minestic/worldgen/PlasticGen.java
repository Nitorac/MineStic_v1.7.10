package nitorac.minestic.worldgen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.common.IWorldGenerator;

public class PlasticGen implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
		case -1:
			this.generateEnd(world, chunkX * 16, chunkZ * 16, random);
		case 0:
			this.generateSurface(world, chunkX * 16, chunkZ * 16, random);
		case 1:
			this.generateNether(world, chunkX * 16, chunkZ * 16, random);
		case 29:
			this.generatePlasticDim(world, chunkX * 16, chunkZ * 16, random);
		}
	}

	private void generatePlasticDim(World world, int x, int z, Random rand) 
	{
		for(int i = 0; i < 15; i++)
		{
			new WorldGenMinable(MineStic.plasticCoal_ore, 4, 20, MineStic.plasticStone).generate(world, rand, x + rand.nextInt(16), rand.nextInt(200), z + rand.nextInt(16));
		}	
		
		for(int i = 0; i < 15; i++)
		{
			new WorldGenMinable(Blocks.iron_ore, 3, 15, MineStic.plasticStone).generate(world, rand, x + rand.nextInt(16), rand.nextInt(130), z + rand.nextInt(16));
		}	
		
		for(int i = 0; i < 15; i++)
		{
			new WorldGenMinable(Blocks.redstone_ore, 4, 10, MineStic.plasticStone).generate(world, rand, x + rand.nextInt(16), rand.nextInt(90), z + rand.nextInt(16));
		}	
		
		for(int i = 0; i < 15; i++)
		{
			new WorldGenMinable(Blocks.diamond_ore, 3, 5, MineStic.plasticStone).generate(world, rand, x + rand.nextInt(16), rand.nextInt(25), z + rand.nextInt(16));
		}	
	}

	private void generateSurface(World world, int x, int z, Random rand)
	{
		for(int i = 0; i < 2; i++)
		{
			(new WorldGenMinable(MineStic.Plastic, 5, 12, Blocks.stone)).generate(world, rand, x + rand.nextInt(16), rand.nextInt(32), z + rand.nextInt(16));
		}

		if(world.getBiomeGenForCoords(x, z) == (BiomeGenBase.extremeHills))
		{
			for(int i = 0; i < 8; i++)
			{
				(new WorldGenMinable(MineStic.Plastic, 7, 14, Blocks.stone)).generate(world, rand, x + rand.nextInt(16), 16 + rand.nextInt(32), z + rand.nextInt(16));
			}
		}
		
		if(world.getBiomeGenForCoords(x, z) == (BiomeGenBase.extremeHillsEdge))
		{
			for(int i = 0; i < 8; i++)
			{
				(new WorldGenMinable(MineStic.Plastic, 7, 14, Blocks.stone)).generate(world, rand, x + rand.nextInt(16), 16 + rand.nextInt(32), z + rand.nextInt(16));
			}
		}
		
		if(world.getBiomeGenForCoords(x, z) == (BiomeGenBase.extremeHillsPlus))
		{
			for(int i = 0; i < 8; i++)
			{
				(new WorldGenMinable(MineStic.Plastic, 7, 14, Blocks.stone)).generate(world, rand, x + rand.nextInt(16), 16 + rand.nextInt(32), z + rand.nextInt(16));
			}
		}
		
	}

	private void generateEnd(World world, int x, int z, Random rand)
	{
		for(int i = 0; i < 4; i++)
		{
			(new WorldGenMinable(MineStic.PlasticBlock, 3, 12, Blocks.end_stone)).generate(world, rand, x + rand.nextInt(16), rand.nextInt(128), z + rand.nextInt(16));
		}
	}

	private void generateNether(World world, int x, int z, Random rand)
	{
		for(int i = 0; i < 20; i++)
		{
			(new WorldGenMinable(MineStic.PlasticChaud, 2, 12, Blocks.soul_sand)).generate(world, rand, x + rand.nextInt(16), rand.nextInt(128), z + rand.nextInt(16));
		}

		for(int i = 0; i < 10; i++)
		{
			(new WorldGenMinable(MineStic.PlasticChaud, 3, 12, Blocks.netherrack)).generate(world, rand, x + rand.nextInt(16), rand.nextInt(128), z + rand.nextInt(16));
		}
	}
}