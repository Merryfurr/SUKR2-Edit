package ashjack.SUKReloaded2.folk.trait;

public class TraitNocturnal extends AbstractTrait
{
	public TraitNocturnal() 
	{
		setTraitName("Nocturnal");
		setTraitDesc("Nocturnal Folks sleep during the day and work at night. Nocturnal Folks make good soldiers"
				+ "as they are well rested for a night shift of fighting mobs");
	}

	@Override
	public void onNightTime() 
	{

	}
}
