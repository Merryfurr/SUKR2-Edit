package ashjack.SUKReloaded2.folk;

public class FolkRelationships 
{
	public FolkData theFolk; //The folk with which the relationship is had
	public relationshipLVLs Relationship; //The type of relationship
	
	public enum relationshipLVLs
	{
		Nemesis, ArchEnemy, Enemy, Hated, Disliked, Acquaintance, Friend, GoodFriend, GreatFriend, BestFriend, BFF,
		Dating, Fiance, Couple, ParentChild, GrandparentGrandchild, SisterBrother, Cousin, NieceNephewAuntUncle,
		SisterSister, BrotherBrother
	};
}
