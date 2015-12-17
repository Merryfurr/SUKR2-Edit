package ashjack.SUKReloaded2.folk;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ashjack.SUKReloaded2.core.SUK2Log;
import ashjack.SUKReloaded2.core.SUK2World;
import ashjack.SUKReloaded2.entity.EntityFolk;

public class FolkUtils
{
	public void generateFolk(FolkData fd, World world)
	{
		Random rand = new Random();
		EntityFolk theFolk = new EntityFolk(world);
		fd.theEntity = theFolk;
		theFolk.theFolk = fd;
		//theFolk.theFolk.identity = new FolkIdentity();
		theFolk.theFolk.race = new FolkRace().assignRandomRace(theFolk.theFolk.identity.folkGender);
		theFolk.theFolk.identity.folkAge = theFolk.theFolk.race.raceMaturity;
		SUK2Log.log.info(theFolk.theFolk.race.maleNameSet.get(rand.nextInt(theFolk.theFolk.race.maleNameSet.size())));
		theFolk.theFolk.identity.folkName = theFolk.theFolk.race.maleNameSet.get(rand.nextInt(theFolk.theFolk.race.maleNameSet.size()-1));
		EntityPlayer thePlayer = (EntityPlayer) world.playerEntities.get(0);
		theFolk.setPosition(thePlayer.posX, thePlayer.posY, thePlayer.posZ); 
		SUK2Log.log.info(String.valueOf(thePlayer.posX + ", " + thePlayer.posY + ", " + thePlayer.posZ));
		world.spawnEntityInWorld(theFolk);
		SUK2World.theFolks.add(theFolk.theFolk);
	}
	
	public static String generateName(int gender, boolean firstNameOnly, String lastNameOptional)
	{
		if(firstNameOnly)
		{
			return "Daisy";
		}
		return "Folk Test";
	}
}
