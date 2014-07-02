package nitorac.minestic.core.entity;

import net.minecraft.entity.EntityList;
import nitorac.minestic.core.MineStic;
import cpw.mods.fml.common.registry.EntityRegistry;

public class PEntities 
{
	public static void mainRegistry()
	{
		registerEntities();
	}
	
	public static void registerEntities()
	{
		createEntities(EntityPlasticCow.class, "PlasticCow", 0x5687, 0x1238);
		createEntities(EntityPlasticPig.class, "PlasticPig", 0x1739, 0x3879);
	}
	
	public static void createEntities(Class entityClass, String entityName, int solidColour, int spotColour)
	{
		int randomId = EntityRegistry.findGlobalUniqueEntityId();	
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, MineStic.modid, 64, 1, true);
		createEgg(randomId, solidColour, spotColour);
	}

	private static void createEgg(int randomId, int solidColour, int spotColour)
	{
		EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColour, spotColour));
	}
}

