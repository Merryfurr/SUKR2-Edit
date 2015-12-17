package ashjack.SUKReloaded2.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockCityBlock extends Block
{
	public BlockCityBlock() 
	{
		super(Material.wood);
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int meta, float x, float y, float z)
	{
		//Open City Information GUI
		return false;
	}
	
}
