package ashjack.SUKReloaded2.folk.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class MyEntityGui extends GuiContainer 
{
    private static final ResourceLocation txtr = new ResourceLocation("suk2:textures/gui/testgui.png");
    private int invW = 176;
    private int invH = 90;

    private int lootH = 90;
    private int lootW = 50;


    public MyEntityGui(InventoryPlayer inventoryPlayer,
                   MyInventory inv) {
        super(new MyContainer(inventoryPlayer, inv));
        this.xSize=300;
        this.ySize=90;
    }

    protected void drawGuiContainerForegroundLayer(int param1, int param2) {}


    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                                                   int par3) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(txtr);
        int x = (width - invW) / 2;
        int y = (height - invH) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, invW, invH);

        x = (width/ 2)-150;
        y = (height - lootH) / 2;
        this.drawTexturedModalRect(x, y, 176, 0, lootW, lootH);
    }

}