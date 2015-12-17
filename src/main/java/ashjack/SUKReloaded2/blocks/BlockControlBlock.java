package ashjack.SUKReloaded2.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ashjack.SUKReloaded2.core.GameMode;
import ashjack.SUKReloaded2.core.util.V3;
import ashjack.SUKReloaded2.registry.SUK2Tabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockControlBlock //extends Block
{


	/*public BlockControlBlock()
	{
		super(Material.wood);
		setStepSound(Block.soundTypeWood);
		setHardness(10F);
		setResistance(1.0F);
		setBlockName("SUKcontrol");
		this.setCreativeTab(SUK2Tabs.tabSUK2);
	}


	@SideOnly(Side.CLIENT)
	private IIcon[] icons;


	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[5];
		icons[0] = par1IconRegister.registerIcon("ashjacksimukraftreloaded:blockControlTop");
		icons[1] = par1IconRegister.registerIcon("ashjacksimukraftreloaded:blockControlSide");
		icons[2] = par1IconRegister.registerIcon("ashjacksimukraftreloaded:blockATM");
		icons[3] = par1IconRegister.registerIcon("ashjacksimukraftreloaded:blockControlTopOther");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		switch (par2)
		{
		case 0:
			return icons[0];
		case 1:
		{
			switch (par1)
			{
			case 0:
				return icons[1];
			case 1:
				return icons[2];
			case 84:
				return icons[0];
			default:
				return icons[3];
			}
		}
		default:
		{
			//System.out.println("Invalid metadata for " + this.getUnlocalizedName());
			return icons[0];
		}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer,
			int par6, float par7, float par8, float par9)
	{
		if(world.isRemote)
		{
			world.playSoundEffect(i, j, k, "ashjacksimukraftreloaded:computer", 1f, 1f);
		}
		GuiControlBox ui = null;
		GuiBankATM ui2 = null;
		Minecraft mc = Minecraft.getMinecraft();
		mc.setIngameNotInFocus();

		if (world.getBlockMetadata(i, j, k) == 0 || world.getBlockMetadata(i, j, k) == 2)
		{
			ui = new GuiControlBox(new V3((double)i, (double)j, (double)k, entityplayer.dimension), entityplayer);
			mc.displayGuiScreen(ui);
		}
		else
		{
			if (GameMode.gameMode == GameMode.GAMEMODES.CREATIVE)
			{
				mc.displayGuiScreen(null);
				entityplayer.addChatComponentMessage(new ChatComponentText("The Bank is not active when in Creative Mode (as there's no money!)"));
			}
			else
			{
				ui2 = new GuiBankATM(new V3((double)i, (double)j, (double)k, entityplayer.dimension), entityplayer);
				mc.displayGuiScreen(ui2);
			}
		}


		return true;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;   // no recipe and no drop
	}*/
}
