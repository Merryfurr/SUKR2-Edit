package ashjack.SUKReloaded2.folk.trait;

public abstract class AbstractTrait 
{
	String traitName;
	String traitDesc;
	
	public void setTraitName(String name)
	{
		traitName = name;
	}
	
	public void setTraitDesc(String desc)
	{
		traitDesc = desc;
	}
	
	public String getTraitName()
	{
		return traitName;
	}
	
	public String getTraitDesc()
	{
		return traitDesc;
	}
	
	public abstract void onNightTime();
}
