package ashjack.SUKReloaded2.folk;

import java.util.Random;

import ashjack.SUKReloaded2.core.SUK2Log;

public class FolkIdentity
{
	public String folkName = "";
	public int folkAge = 18;
	public int folkGender = 0;
	
	Random rand = new Random();
	
	public FolkIdentity()
	{
		this.folkGender = Math.abs(rand.nextInt(2));
	}
}
