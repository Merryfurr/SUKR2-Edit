package ashjack.SUKReloaded2.core;

import ashjack.SUKReloaded2.client.gui.GuiHandler;
import ashjack.SUKReloaded2.commands.CommandGenerateFolk;
import ashjack.SUKReloaded2.core.proxy.CommonProxy;
import ashjack.SUKReloaded2.entity.EntityFolk;
import ashjack.SUKReloaded2.registry.SUK2Blocks;
import ashjack.SUKReloaded2.registry.SUK2Recipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = SUK2.MODID, version = SUK2.VERSION)
public class SUK2
{
    public static final String MODID = "suk2";
    public static final String VERSION = "2.0.0";
    
    @Instance("SUK2")
    public static SUK2 instance = new SUK2();
    
    @SidedProxy(clientSide="ashjack.SUKReloaded2.core.proxy.ClientProxy", 
    		    serverSide="ashjack.SUKReloaded2.core.proxy.ServerProxy")
    
    public static CommonProxy proxy;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	SUK2Blocks.loadBlocks();
		SUK2Recipes.loadRecipes();
		this.proxy.preInit(event);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		EntityRegistry.registerGlobalEntityID(EntityFolk.class, "Folk", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFolk.class, "Folk", 1, this, 250, 2, true);    
	}
    
    public void postinit(FMLPostInitializationEvent event)
    {
    	this.proxy.postInit(event);
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
    	event.registerServerCommand(new CommandGenerateFolk());
    }
}
