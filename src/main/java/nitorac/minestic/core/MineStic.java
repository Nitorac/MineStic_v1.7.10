package nitorac.minestic.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import nitorac.minestic.common.CreativeTab.PlasticTab;
import nitorac.minestic.core.blocks_fluids.BlockCompPlastic;
import nitorac.minestic.core.blocks_fluids.BlockDirtPlastic;
import nitorac.minestic.core.blocks_fluids.BlockGrassPlastic;
import nitorac.minestic.core.blocks_fluids.BlockPlastic;
import nitorac.minestic.core.blocks_fluids.BlockPlasticBlock;
import nitorac.minestic.core.blocks_fluids.BlockPlasticChaud;
import nitorac.minestic.core.blocks_fluids.BlockPlasticCoalOre;
import nitorac.minestic.core.blocks_fluids.BlockPlasticFence;
import nitorac.minestic.core.blocks_fluids.BlockPlasticFluid;
import nitorac.minestic.core.blocks_fluids.BlockPlasticLight;
import nitorac.minestic.core.blocks_fluids.BlockPlasticLightOff;
import nitorac.minestic.core.blocks_fluids.BlockPlasticStone;
import nitorac.minestic.core.blocks_fluids.PlasticExtractor;
import nitorac.minestic.core.blocks_fluids.PlasticFire;
import nitorac.minestic.core.blocks_fluids.PlasticFlintAndSteel;
import nitorac.minestic.core.blocks_fluids.PlasticPortal;
import nitorac.minestic.core.blocks_fluids.PlasticSlabs;
import nitorac.minestic.core.blocks_fluids.cakes.RottenCakeCuit;
import nitorac.minestic.core.enchantments.EnchantmentSpeedBoost;
import nitorac.minestic.core.entity.PEntities;
import nitorac.minestic.core.events.BucketEvent;
import nitorac.minestic.core.events.OnPlayerStartWorld;
import nitorac.minestic.core.events.PickupPlasticEvent;
import nitorac.minestic.core.events.SmeltPlasticEvent;
import nitorac.minestic.core.handlers.FuelHandler;
import nitorac.minestic.core.items.ItemBlockPlasticSlab;
import nitorac.minestic.core.items.ItemPlasticBlock;
import nitorac.minestic.core.items.ItemPlasticBucket;
import nitorac.minestic.core.items.ItemPlasticDust;
import nitorac.minestic.core.items.ItemPlasticIngot;
import nitorac.minestic.core.items.ItemPlasticMediKit;
import nitorac.minestic.core.items.ItemPlasticSpeed;
import nitorac.minestic.core.items.ItemRottenCake;
import nitorac.minestic.core.proxy.ServeurProxy;
import nitorac.minestic.dimension.PlasticWorldProvider;
import nitorac.minestic.dimension.biomes.PlasticBiomeGen;
import nitorac.minestic.worldgen.PlasticGen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "minestic", name = "Mine Stic", version = "2.1.9", acceptedMinecraftVersions = "[1.7.2,)", canBeDeactivated = true)

public class MineStic
{
	@SidedProxy(clientSide = "nitorac.minestic.core.proxy.ClientProxy", serverSide = "nitorac.minestic.core.proxy.ServeurProxy")
	public static ServeurProxy proxy;

	@Instance("MineStic")
	public static MineStic modInstance;
	
	public static String modid = "minestic";
	public static String name = "Mine Stic";
	public static String version = "2.1.9";

	// declaration des blocs - blocks statement
	public static Achievement PlasticMine, PlasticSmelt;
	public static Block Test, Plastic, PlasticChaud, PlasticBlock, PlasticFence, MineSlab, DoubleMineSlab, RottenCakeCuit;
	public static Block Plastic_Lamp, Plastic_Lamp_light;
	
	public static Block plasticPortal, CompPlastic, plasticFire, plasticStone, plasticGrass, plasticDirt;
	public static Block plasticCoal_ore;
	public static Block PlasticExtractor;
	public static Block PlasticFluidBlock;
	
	public static Item PlasticCoal, PlasticDust, PlasticIngot, RottenCuite, PlasticMediKit, ItemRottenCake;
	public static Item PlasticFlintAndSteel, PlasticBucket, PlasticSpeed;
	
	public static Fluid PlasticFluid;
	
	public static BiomeGenBase plasticBiome;
	
	public static int PlasticDimID = 29;
	
	protected static final BiomeGenBase.Height High_Plateau = new BiomeGenBase.Height(-0.1F, 2.4F);
	
	// declaration des tables creatives
	public static CreativeTabs PlasticTab = new PlasticTab("PlasticTab");
	
	public static final Enchantment speedBoost = new EnchantmentSpeedBoost(84, 5);
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{		
		//Fluids
		PlasticFluid = new Fluid("PlasticFluid").setDensity(8000).setViscosity(1500).setTemperature(900).setLuminosity(1).setUnlocalizedName("PlasticFluid").setBlock(PlasticFluidBlock);
		FluidRegistry.registerFluid(PlasticFluid);
		
		proxy.registerTileEntity();
		proxy.registerRenderThings();

		//Blocks
		plasticGrass = new BlockGrassPlastic().setHardness(0.4F).setResistance(2.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(PlasticTab).setBlockName("PlasticGrass").setBlockTextureName("minestic:plasticgrass");
		plasticDirt = new BlockDirtPlastic().setHardness(0.3F).setResistance(1.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(PlasticTab).setBlockName("PlasticDirt").setBlockTextureName("minestic:plasticdirt");
		CompPlastic = new BlockCompPlastic().setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(PlasticTab).setBlockName("CompPlastic").setBlockTextureName("minestic:CompPlastic");
		Plastic = new BlockPlastic().setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(PlasticTab).setBlockName("Plastic").setBlockTextureName("minestic:Plastic").setLightOpacity(2);
		PlasticChaud = new BlockPlasticChaud().setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeMetal).setCreativeTab(PlasticTab).setBlockName("PlasticChaud").setBlockTextureName("minestic:PlasticChaud").setLightOpacity(2).setLightLevel(12);
		PlasticBlock = new BlockPlasticBlock().setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("PlasticBlock").setCreativeTab(PlasticTab);
		plasticStone = new BlockPlasticStone().setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("PlasticStone").setCreativeTab(PlasticTab).setBlockTextureName("minestic:plasticstone");
		PlasticFence = new BlockPlasticFence("minestic:PlasticChaud", Material.iron).setBlockName("PlasticFence").setCreativeTab(MineStic.PlasticTab);
		DoubleMineSlab = new PlasticSlabs(true, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("DoubleMineSlab");
		RottenCakeCuit = new RottenCakeCuit().setHardness(0.5F).setStepSound(Block.soundTypeCloth).setBlockName("RottenCake");
		MineSlab = new PlasticSlabs(false, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("MineSlab");
		Plastic_Lamp = new BlockPlasticLightOff(false).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("plasticLight_off").setCreativeTab(MineStic.PlasticTab).setBlockTextureName("minestic:plastic_lamp_off");
		Plastic_Lamp_light = new BlockPlasticLight(true).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("plasticLight_on").setBlockTextureName("minestic:plastic_lamp_on");
		plasticPortal = new PlasticPortal().setBlockName("plasticPortal");
		plasticFire = new PlasticFire().setBlockName("PlasticFire").setBlockTextureName("minestic:black_fire").setLightLevel(1.0F);
		PlasticExtractor = new PlasticExtractor().setBlockName("PlasticMachine").setCreativeTab(PlasticTab);
		PlasticFluidBlock = new BlockPlasticFluid(PlasticFluid, Material.lava).setBlockName("PlasticFluidBlock");
		plasticCoal_ore = new BlockPlasticCoalOre().setHardness(6.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(PlasticTab).setBlockName("PlasticCoalOre").setBlockTextureName("minestic:PlasticCoalOre");

		GameRegistry.registerBlock(Plastic, Plastic.getUnlocalizedName());
		GameRegistry.registerBlock(PlasticChaud, PlasticChaud.getUnlocalizedName());
		GameRegistry.registerBlock(PlasticBlock, ItemPlasticBlock.class, "PlasticBlock");
		GameRegistry.registerBlock(PlasticFence, PlasticFence.getUnlocalizedName());
		GameRegistry.registerBlock(MineSlab, ItemBlockPlasticSlab.class, MineSlab.getUnlocalizedName());
		GameRegistry.registerBlock(DoubleMineSlab, ItemBlockPlasticSlab.class, DoubleMineSlab.getUnlocalizedName());
		GameRegistry.registerBlock(RottenCakeCuit, RottenCakeCuit.getUnlocalizedName());
		GameRegistry.registerBlock(Plastic_Lamp, Plastic_Lamp.getUnlocalizedName());
		GameRegistry.registerBlock(Plastic_Lamp_light, Plastic_Lamp_light.getUnlocalizedName());
		GameRegistry.registerBlock(plasticPortal, plasticPortal.getUnlocalizedName());
		GameRegistry.registerBlock(CompPlastic, CompPlastic.getUnlocalizedName());
		GameRegistry.registerBlock(plasticFire, plasticFire.getUnlocalizedName());
		GameRegistry.registerBlock(plasticStone, plasticStone.getUnlocalizedName());
		GameRegistry.registerBlock(plasticGrass, plasticGrass.getUnlocalizedName());
		GameRegistry.registerBlock(plasticDirt, plasticDirt.getUnlocalizedName());
		GameRegistry.registerBlock(PlasticExtractor, PlasticExtractor.getUnlocalizedName());
		GameRegistry.registerBlock(PlasticFluidBlock, PlasticFluidBlock.getUnlocalizedName());
		GameRegistry.registerBlock(plasticCoal_ore, plasticCoal_ore.getUnlocalizedName());
		
		//Dimension
		DimensionManager.registerProviderType(PlasticDimID, PlasticWorldProvider.class, true);
		DimensionManager.registerDimension(PlasticDimID, PlasticDimID);
		
		//Items
		PlasticDust = new ItemPlasticDust().setUnlocalizedName("PlasticDust").setTextureName("minestic:PlasticDust").setCreativeTab(PlasticTab);
		RottenCuite = new ItemFood(6, 0.6F, false).setUnlocalizedName("RottenCuite").setTextureName("minestic:RottenCuite").setCreativeTab(PlasticTab);
		PlasticIngot = new ItemPlasticIngot().setUnlocalizedName("PlasticIngot").setTextureName("minestic:PlasticIngot").setCreativeTab(PlasticTab);
		PlasticMediKit = new ItemPlasticMediKit().setUnlocalizedName("PlasticMediKit").setTextureName("minestic:PlasticMediKit").setCreativeTab(PlasticTab);
		ItemRottenCake = new ItemRottenCake().setUnlocalizedName("RottenCakeItem").setTextureName("minestic:RottenCake");
		PlasticCoal = new Item().setUnlocalizedName("PlasticCoal").setTextureName("minestic:PlasticCoal").setCreativeTab(PlasticTab);
		PlasticFlintAndSteel = new PlasticFlintAndSteel().setUnlocalizedName("PlasticFlintAndSteel").setTextureName("minestic:PlasticFlintAndSteel").setCreativeTab(PlasticTab);
		PlasticBucket = new ItemPlasticBucket(PlasticFluidBlock).setUnlocalizedName("PlasticBucket").setTextureName("minestic:PlasticBucket").setCreativeTab(PlasticTab).setContainerItem(Items.bucket);
		PlasticSpeed = new ItemPlasticSpeed().setUnlocalizedName("PlasticSpeed").setTextureName("minestic:PlasticSpeed").setCreativeTab(PlasticTab);
		
		GameRegistry.registerItem(PlasticDust, PlasticDust.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(RottenCuite, RottenCuite.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(PlasticIngot, PlasticIngot.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(PlasticMediKit, PlasticMediKit.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(PlasticCoal, PlasticCoal.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(ItemRottenCake, ItemRottenCake.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(PlasticFlintAndSteel, PlasticFlintAndSteel.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(PlasticBucket, PlasticBucket.getUnlocalizedName(), "minestic");
		GameRegistry.registerItem(PlasticSpeed, PlasticSpeed.getUnlocalizedName(), "minestic");

		
		plasticBiome = new PlasticBiomeGen(51).setHeight(High_Plateau).setColor(33535).setBiomeName("Plastic's Lands");
		
		BiomeManager.addSpawnBiome(plasticBiome);
        BiomeDictionary.registerBiomeType(plasticBiome, Type.MAGICAL);
        BiomeManager.addSpawnBiome(plasticBiome);
       
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{				
		CraftingManager.mainRecAndSmelt();
		PlasticMine = new Achievement("achievement.plasticmine", "plasticmine", 0, 0, new ItemStack(MineStic.PlasticDust), (Achievement)null).initIndependentStat().registerStat().setSpecial();
		PlasticSmelt = new Achievement("achievement.plasticsmelt", "plasticsmelt", 1, -2, new ItemStack(MineStic.PlasticIngot), PlasticMine).registerStat().setSpecial();
		
		GameRegistry.registerWorldGenerator(new PlasticGen(), 1);
		
		GameRegistry.addSmelting(MineStic.PlasticDust, new ItemStack(MineStic.PlasticIngot), 15.0F);
		GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(MineStic.RottenCuite), 5.0F);
		
		GameRegistry.registerFuelHandler(new FuelHandler());
		
		FMLCommonHandler.instance().bus().register(new PickupPlasticEvent());
		FMLCommonHandler.instance().bus().register(new SmeltPlasticEvent());
		FMLCommonHandler.instance().bus().register(new OnPlayerStartWorld());
		MinecraftForge.EVENT_BUS.register(new BucketEvent());
		
		PEntities.mainRegistry();
		proxy.registerRender();
		AchievementPage.registerAchievementPage(new AchievementPage("Plastic's Achievements", new Achievement[]{PlasticMine, PlasticSmelt}));
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
	
	}
	
}