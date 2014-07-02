package nitorac.minestic.dimension;

import net.minecraft.util.ChunkCoordinates;

public class PlasticPortalPosition extends ChunkCoordinates
{
public long field_85087_d;
final PlasticTeleporter field_85088_e;
public PlasticPortalPosition(PlasticTeleporter plasticTeleporter, int par2, int par3, int par4, long par5)
{
super(par2, par3, par4);
this.field_85088_e = plasticTeleporter;
this.field_85087_d = par5;
}
}