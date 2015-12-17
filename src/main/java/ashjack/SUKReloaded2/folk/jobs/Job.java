package ashjack.SUKReloaded2.folk.jobs;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import ashjack.SUKReloaded2.folk.FolkData;

public abstract class Job 
{
	public World jobWorld = null;
	
	public void onUpdateGoingToWork(FolkData theFolk)
    {
        if (jobWorld == null)
        {
            try
            {
                jobWorld = MinecraftServer.getServer().worldServerForDimension(theFolk.employedAt.theDimension);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
    }
	
	public abstract void onArrivedAtWork();
	
	public void onUpdate()
	{
		
	}
	
	public abstract void resetJob();
}
