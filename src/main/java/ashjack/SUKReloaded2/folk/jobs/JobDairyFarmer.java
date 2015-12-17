package ashjack.SUKReloaded2.folk.jobs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.AxisAlignedBB;
import ashjack.SUKReloaded2.core.SUK2World;
import ashjack.SUKReloaded2.core.util.V3;
import ashjack.SUKReloaded2.folk.FolkData;
import ashjack.SUKReloaded2.folk.FolkUtils;

public class JobDairyFarmer extends Job{

	public FolkData theFolk;
	public boolean isMeatFarm;
	
	public Stage theStage;
    transient public int runDelay = 1000;
    transient public long timeSinceLastRun = 0;
	
	private String[] cowNames=new String[6];
	
	public JobDairyFarmer(FolkData folk) 
	{
        theFolk = folk;
	}
	
	private void onArrived() 
	{
		
	}
	
	@Override
    public void onUpdate()
    {
	 if (cowNames[0]==null || cowNames[0].contentEquals("")) {
		 createCowNames();
	 }
	 
	 super.onUpdate();

        if (!SUK2World.isDayTime())
        {
            theStage = Stage.IDLE;
        }

        super.onUpdateGoingToWork(theFolk);

        if (theStage == Stage.WAITINGFORMILKING)
        {
            runDelay = 40000;
        } else {
        	runDelay=10000;
        }

        if (System.currentTimeMillis() - timeSinceLastRun < runDelay)
        {
            return;
        }

        timeSinceLastRun = System.currentTimeMillis();

        // ////////////////IDLE
        if (theStage == Stage.IDLE && SUK2World.isDayTime())
        {
        }
        else if (theStage == Stage.ARRIVEDATFARM)
        {
            stageArrived();
        }
        else if (theStage == Stage.WAITINGFORMILKING)
        {
            stageWaiting();
        }
        else if (theStage == Stage.MILKING)
        {
            stageMilking();
        }
        else if (theStage== Stage.STORINGMILK) {
        	stageStoringMilk();
        }
        else if (theStage == Stage.CANTWORK)
        {
            stageCantWork();
        }
    }
	
	int count;
	List<EntityCow> cows;

	private void stageArrived()
	{
		count = getAnimalCountInPen(theFolk.employedAt, EntityCow.class);
		
		if(count < 6)
		{
			spawnCows(theFolk.employedAt, 6 - count);
		}
		
		cows = getAnimalsInPen(theFolk.employedAt, EntityCow.class);
	}
	
	private void stageWaiting()
	{
		
	}
	
	private void stageMilking()
	{
		boolean hasMilkedCurrentCow = false;
		
		for(EntityCow c : cows)
		{
			while(!hasMilkedCurrentCow)
			{
				theFolk.moveToEntity(c);
				double Distance = Math.sqrt(Math.pow(c.posX-theFolk.theEntity.posX, 2)+Math.pow(c.posY-theFolk.theEntity.posY, 2)+Math.pow(c.posZ-theFolk.theEntity.posZ, 2));
				if(Distance < 16)
				{
					c.setFire(5);
				}
			}
		}
	}
	
	private void stageStoringMilk()
	{
		
	}
	
	private void stageCantWork()
	{
		
	}
	
	@Override
	public void resetJob() 
	{
		
	}

	public enum Stage
    {
        IDLE, ARRIVEDATFARM, WAITINGFORMILKING, MILKING, STORINGMILK, CANTWORK;
    }
	
	private void createCowNames() 
	{
		for(int i=0;i<6;i++) 
		{
			cowNames[i]=FolkUtils.generateName(1, true, "");
		}
	}
	
	public int getAnimalCountInPen(V3 controlBox, Class animal)
    {
        List list = jobWorld.getEntitiesWithinAABB(animal, AxisAlignedBB.getBoundingBox(
                        controlBox.x, controlBox.y, controlBox.z, controlBox.x + 1.0D, 
                        controlBox.y + 1.0D,controlBox.z + 1.0D).expand(3D, 2D, 3D));

        if (list == null)
        {
            return 0;
        }
        else
        {
            return list.size();
        }
    }
	
	public List getAnimalsInPen(V3 controlBox, Class animal)
    {
        List list = jobWorld.getEntitiesWithinAABB(animal, AxisAlignedBB.getBoundingBox(
                        controlBox.x, controlBox.y, controlBox.z, controlBox.x + 1.0D, 
                        controlBox.y + 1.0D,controlBox.z + 1.0D).expand(3D, 2D, 3D));

        if (list == null)
        {
            return null;
        }
        else
        {
            return list;
        }
    }
	
	private void spawnCows(V3 controlBox, int count)
    {
        EntityAnimal newAnimal = null;

        for (int c = 1; c <= count; c++)
        {
          
            newAnimal = new EntityCow(jobWorld);
            newAnimal.setLocationAndAngles(controlBox.x+1, controlBox.y + 1, controlBox.z, 0f, 0f);

            if (!jobWorld.isRemote)
            {
                jobWorld.spawnEntityInWorld(newAnimal);
            }
        }
    }

	@Override
	public void onArrivedAtWork() 
	{
		
	}
}
