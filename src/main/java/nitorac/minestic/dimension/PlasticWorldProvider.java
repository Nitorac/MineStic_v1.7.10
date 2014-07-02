package nitorac.minestic.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import nitorac.minestic.core.MineStic;
import nitorac.minestic.dimension.fx.PlasticSkyRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlasticWorldProvider extends WorldProvider {
	IRenderHandler skyRenderer;
	
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(MineStic.plasticBiome, MineStic.PlasticDimID);
		this.hasNoSky = false;
	}

	public IChunkProvider createChunkGenerator() {
		return new PlasticChunkProvider(this.worldObj, this.worldObj.getSeed(), hasNoSky);
	}

	public int getAverageGroundLevel() {
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer()
    {
            return new PlasticSkyRenderer();
    }

	public String getDimensionName() {
		return "Plastic's Lands";
	}
	
	public String getWelcomeMessage()
    {
        return "Entering in Plastic Dimension ...";
    }

	public boolean canRespawnHere() {
		return false;
	}
	
	@Override
	public boolean isSkyColored()
	{
	         return true;
	}

	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
	{
	         return Vec3.createVectorHelper(0.1, 0.3, 1.0);
	}
	
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 182.0F;
	}


	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	public ChunkCoordinates getEntrancePortalLocation() {
		return new ChunkCoordinates(50, 5, 0);
	}
}