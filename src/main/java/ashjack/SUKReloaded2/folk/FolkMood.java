package ashjack.SUKReloaded2.folk;

import java.util.ArrayList;

import ashjack.SUKReloaded2.folk.moodbuffs.AbstractBuff;

public class FolkMood 
{
	public ArrayList<AbstractBuff> buffs = new ArrayList<AbstractBuff>();
	
	public int angerLevel;
	public int sadnessLevel;
	public int happinessLevel;
	
	public void addMoodBuff(AbstractBuff buff, float time)
	{
		buffs.add(buff);
		angerLevel += buff.angerOffset;
		sadnessLevel += buff.sadnessOffset;
		happinessLevel += buff.happinessOffset;
	}
	
	public void removeMoodBuff(AbstractBuff buff)
	{
		buffs.remove(buff);
		angerLevel -= buff.angerOffset;
		sadnessLevel -= buff.sadnessOffset;
		happinessLevel -= buff.happinessOffset;
	}
}
