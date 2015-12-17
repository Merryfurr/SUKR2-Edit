package ashjack.SUKReloaded2.client.gui;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ashjack.SUKReloaded2.entity.EntityFolk;
import ashjack.SUKReloaded2.folk.inventory.MyContainer;
import ashjack.SUKReloaded2.folk.inventory.MyEntityGui;
import ashjack.SUKReloaded2.folk.inventory.MyInventory;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler 
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
    {       
        if(id==5){
            Entity ent = world.getEntityByID(x);
            if(ent!=null && ent instanceof EntityFolk){
                return new MyContainer(player.inventory, ((EntityFolk)ent).inventory);
            }}
        return null;
    }


    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
    {
        
        if(id==5){
            Entity ent = world.getEntityByID(x);
            if(ent!=null && ent instanceof EntityFolk){
                return new MyEntityGui(player.inventory, new MyInventory("MyInventory", ((EntityFolk) ent).howManySlots()));
            }}

        return null;

    }
}