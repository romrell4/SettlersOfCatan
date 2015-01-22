package models;

import shared.definitions.CatanColor;

public class Player implements IPlayer
{
		
	private CatanColor mColor; //The color of this player
	private String mName;
	private Index mPlayerIndex; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private int mPlayerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList mResources; //The resource cards this player has.
	private int mNumRoads;
	private int mNumSettlements; //How many settlements this player has left to play.
	private int mNumCities; //How many cities this player has left to play.
	private int mSoldiers;
	private int mVictoryPoints;
	
	private List mRoads;
	private List mSettlements;
	private List mCities;
	private List DevCards;
	
	
	public Player(CatanColor color, boolean discarded, Number monuments, String name, DevCardList newDevCards, DevCardList oldDevCards, Index playerIndex, boolean playerDevCard, int playerID, ResourceList resources, int roads, int settlements, int cities, int soldiers, int victoryPoints)
	{
		this.mColor = color;
		this.mName = name;
		this.mPlayerIndex = playerIndex;
		this.mPlayerID = playerID;
		this.mResources = resources;
		this.mRoads = roads;
		this.mSettlements = settlements;
		this.mCities = cities;
		this.mSoldiers = soldiers;
		this.mVictoryPoints = victoryPoints;
	}
	public boolean discard()
	{
		
	}
	public int getSoldierCount()
	{
		
	}
	public int getVictoryPoints()
	{
		return mVictoryPoints;
	}
	
	
	
	
	public CatanColor color()
	{
		return mColor;
	}
	public boolean discarded()
	{
		return mDiscarded;
	}
	public Number monuments()
	{
		return mMonuments;
	}
	public String name()
	{
		return mName;
	}
	public DevCardList newDevCards()
	{
		return mNewDevCards;
	}
	public DevCardList oldDevCards()
	{
		return mOldDevCards;
	}
	public Index playerIndex()
	{
		return mPlayerIndex;
	}
	public boolean playerDevCard()
	{
		return mPlayerDevCard;
	}
	public int playerID()
	{
		return mPlayerID;
	}
	public ResourceList resources()
	{
		return mResources;
	}
	public int roads()
	{
		return mRoads;
	}
	public int settlements()
	{
		return mSettlements;
	}
	public Number cities()
	{
		return mCities;
	}
	public int soldiers()
	{
		return mSoldiers;
	}

}
