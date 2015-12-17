package ashjack.SUKReloaded2.building;

public class BuildingType 
{
	public EnumBuildingType type;
	
	public EnumResidentialSubtype resSub;
	public EnumCommercialSubtype comSub;
	public EnumIndustrialSubtype indSub;
	public EnumOtherSubtype othSub;
	public EnumSpecialSubtype specSb;
	
	//Building Types themselves
	public enum EnumBuildingType 
	{
		Residential,
		Commercial,
		Industrial,
		Other,
		Special
	}

	//Building Subtypes
	public enum EnumResidentialSubtype
	{
		Bungalow,
		House,
		Mansion,
		Portable,
		Other
	}
	
	public enum EnumCommercialSubtype
	{
		Restaurant,
		Shop,
		Service,
		Other
	}
	
	public enum EnumIndustrialSubtype
	{
		Manufacturing,
		Processing,
		Storage		
	}
	
	public enum EnumOtherSubtype
	{
		PublicAreas,
		Infrastructure,
		Decoration,
		Other
	}
	
	public enum EnumSpecialSubtype
	{
		Government,
		Other
	}
}
