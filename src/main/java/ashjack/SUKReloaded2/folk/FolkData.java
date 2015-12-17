package ashjack.SUKReloaded2.folk;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import ashjack.SUKReloaded2.core.SUK2World;
import ashjack.SUKReloaded2.core.util.V3;
import ashjack.SUKReloaded2.entity.EntityFolk;
import ashjack.SUKReloaded2.folk.jobs.Job;

public class FolkData 
{
	public EntityFolk theEntity;
	
	public boolean isWorking; // TEMPORARY
	public V3 employedAt = null;
	
	public FolkIdentity identity = new FolkIdentity();
	public FolkNeeds needs = new FolkNeeds();
	public FolkRace race = new FolkRace();
	
	public double credits;
	
	public Job job;
	
	public ArrayList<FolkRelationships> relationships = new ArrayList<FolkRelationships>();
	
	public FolkData(World world)
	{
		new FolkUtils().generateFolk(this, world);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//FUNCTIONS
	
	public void onUpdate()
	{
		job.onUpdate();
	}
	
	/**
	 * used for spawning so entity can get a reference to the data, returns null
	 * if it can't find it
	 */
	public static FolkData getFolkDataByEntityId(int id) 
	{
		for (int i = 0; i < SUK2World.theFolks.size(); i++) 
		{
			FolkData fd = SUK2World.theFolks.get(i);

			if (fd.theEntity != null) 
			{
				if (fd.theEntity.getEntityId() == id) 
				{
					return fd;
				}
			}
		}

		return null;
	}
	
	public void moveToXYZ(double x, double y, double z)
	{
		this.theEntity.getNavigator().tryMoveToXYZ(x, y, z, 0.6D);
	}
	
	public void moveToXYZ(V3 v3)
	{
		this.theEntity.getNavigator().tryMoveToXYZ(v3.x, v3.y, v3.z, 0.6D);
	}
	
	public void moveToEntity(Entity entity)
	{
		this.theEntity.getNavigator().tryMoveToEntityLiving(entity, 0.6D);
	}
}