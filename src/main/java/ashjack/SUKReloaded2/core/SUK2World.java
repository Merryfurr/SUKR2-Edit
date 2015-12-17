package ashjack.SUKReloaded2.core;

import java.util.ArrayList;

import ashjack.SUKReloaded2.folk.FolkData;

public class SUK2World 
{
	/** all the folk's data (used to construct and maintain an EntityFolk) */
	public static ArrayList<FolkData> theFolks = new ArrayList<FolkData>();
	
	public static boolean isDayTime()
	{
		return true;
	}
}
