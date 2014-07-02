package nitorac.minestic.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import nitorac.minestic.core.entity.EntityPlasticCow;
import nitorac.minestic.core.entity.EntityPlasticPig;
import nitorac.minestic.core.handlers.ServerTickHandler;
import nitorac.minestic.core.render.RenderPlasticCow;
import nitorac.minestic.core.render.RenderPlasticPig;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;


public class ClientProxy extends ServeurProxy
{
	public void registerRenderThings()
	{
		FMLCommonHandler.instance().bus().register(new ServerTickHandler(Minecraft.getMinecraft()));
	}
	
	@Override
	public void registerRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasticCow.class, new RenderPlasticCow(new ModelCow(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasticPig.class, new RenderPlasticPig(new ModelPig(), new ModelPig(0.5F), 0.7F));
	}
}