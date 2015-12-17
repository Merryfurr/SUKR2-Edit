package ashjack.SUKReloaded2.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ashjack.SUKReloaded2.core.SUK2;
import ashjack.SUKReloaded2.core.SUK2World;
import ashjack.SUKReloaded2.core.util.V3;
import ashjack.SUKReloaded2.folk.FolkData;
import ashjack.SUKReloaded2.folk.jobs.JobDairyFarmer;
import ashjack.SUKReloaded2.registry.SUK2Tabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockConstructorBlock extends Block
{

	public BlockConstructorBlock() 
	{
		super(Material.wood);
		setBlockName("SUK2_ConstructorBlock");
		setBlockTextureName(SUK2.MODID + ":" + "constructorBlock");
		setCreativeTab(SUK2Tabs.tabSUK2);
	}
	
	/** string containing +x -x +z -z  */
	public String buildDirection = "";

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[1];
		icons[0] = par1IconRegister.registerIcon("ashjacksimukraftreloaded:blockConstruction");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)      // getBlockTextureFromSideAndMetadata
	{
		return icons[0];
	}






	
	
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean onBlockActivated(World world, int x, int y,
			int z, EntityPlayer thePlayer, int side, float xoff,
			float yoff, float zoff)
	{
		SUK2World.theFolks.get(0).theEntity.getNavigator().tryMoveToXYZ(x+0.5, y+1, z+0.5, 0.6D);
		SUK2World.theFolks.get(0).isWorking = true;
		//SUK2World.theFolks.get(0).theEntity.getLookHelper().setLookPosition(p_75650_1_, p_75650_3_, p_75650_5_, p_75650_7_, p_75650_8_);
		SUK2World.theFolks.get(0).job = new JobDairyFarmer(SUK2World.theFolks.get(0));
		return true;
	}
	
	
	
	
	
	

	/*@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
			int par4, int par5)
	{
		if (!par1World.isRemote)
		{
			par1World.playSoundEffect(par2, par3, par4, "ashjacksimukraftreloaded:powerdown", 1f, 1f);
		}

		FolkData theFolk = FolkData.getFolkByEmployedAt(new V3((double)par2, (double)par3, (double)par4
				, par1World.provider.dimensionId));

		if (theFolk != null)
		{
			theFolk.selfFire();
		}

		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			par1World.playSoundEffect(par2, par3, par4, "ashjacksimukraftreloaded:constructoractivated", 1f, 1f);
		}

		super.onBlockAdded(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer thePlayer, int par6, float par7,
			float par8, float par9)
	{
		par1World.playSoundEffect(par2, par3, par4, "ashjacksimukraftreloaded:computer", 1f, 1f);
		int px = (int) Math.floor(thePlayer.posX);
		int py = (int) Math.floor(thePlayer.posY);
		int pz = (int) Math.floor(thePlayer.posZ);
		int ex = par2;
		int ey = par3;
		int ez = par4;

		if (par4 == pz)  //first block should be on X axis
		{
			if (px < par2)
			{
				buildDirection = "-x";
			}
			else
			{
				buildDirection = "+x";
			}
		}
		else if (par2 == px)    //first block should be on Z axis
		{
			if (pz < par4)
			{
				buildDirection = "-z";
			}
			else
			{
				buildDirection = "+z";
			}
		}

		V3 loc = new V3((double)par2, (double)par3, (double)par4, thePlayer.dimension);
		Minecraft mc = Minecraft.getMinecraft();
		GuiBuildingConstructor ui = new GuiBuildingConstructor(loc, buildDirection, null); // xyz of box and xyz of 1st build block
		mc.displayGuiScreen(ui);
		return true;
	}*/

}
