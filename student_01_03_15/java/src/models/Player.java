package models;

import java.util.ArrayList;
import java.util.List;

import server.IUser;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Player implements IPlayer
{	
	private CatanColor mColor; //The color of this player
	private String mName;
	private Index mPlayerIndex = null; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private int mPlayerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList mResources; //The resource cards this player has.
	private int mRoadCount = 15;
	private int mSettlementCount = 5; //How many settlements this player has left to play.
	private int mCityCount = 4; //How many cities this player has left to play.
	private int mSoldierCount = 0;
	private int mVictoryPointCount = 0;
	private boolean mDiscarded = false;
	private boolean mHasPlayedDevCard = false;
	private int mMonuments = 0;
	private IUser mUser;
	
	private List<Road> mRoads;
	private List<Building> mSettlements;
	private List<Building> mCities;
	private List<DevCard> mDevCards;
	
	/**
	 * Constructs a player Object with few arguments
	 * @param color
	 * @param name
	 * @param index
	 * @param playerID
	 */
	public Player(CatanColor color, String name, Index index, int playerID)
	{
		this.mColor = color;
		this.mName = name;
		this.mPlayerIndex = index;
		this.mPlayerID = playerID;
		
		this.mResources = new ResourceList();
		this.mRoads = new ArrayList<Road>();
		this.mSettlements = new ArrayList<Building>();
		this.mCities = new ArrayList<Building>();
		this.mDevCards = new ArrayList<DevCard>();
	}
	
	/**
	 * Constructs a player Object with many arguments
	 * @param color
	 * @param discarded
	 * @param monuments
	 * @param name
	 * @param newDevCards
	 * @param oldDevCards
	 * @param playerIndex
	 * @param playerID
	 * @param resources
	 * @param soldiers
	 * @param victoryPoints
	 * @param numSettlements
	 * @param numCities
	 * @param numRoads
	 * @param playedDevCard
	 */
	public Player(CatanColor color, boolean discarded, Number monuments, 
			String name, List<DevCard> newDevCards, List<DevCard> oldDevCards, Index playerIndex, 
			int playerID, ResourceList resources, int soldiers, int victoryPoints, int numSettlements,int numCities, int numRoads,
			boolean playedDevCard)
	{
		this.mColor = color;
		this.mName = name;
		this.mPlayerIndex = playerIndex;
		this.mPlayerID = playerID;
		this.mResources = resources;
		this.mSoldierCount = soldiers;
		this.mVictoryPointCount = victoryPoints;
		this.mRoadCount = numRoads;
		this.mSettlementCount = numSettlements;
		this.mCityCount = numCities;
		this.mRoads = new ArrayList<Road>();
		this.mSettlements = new ArrayList<Building>();
		this.mCities = new ArrayList<Building>();
		this.mDevCards = new ArrayList<DevCard>();
		this.mDevCards.addAll(newDevCards);
		this.mDevCards.addAll(oldDevCards);
		this.mHasPlayedDevCard = playedDevCard;
	}
	
	
	
	public IUser getUser() {
		return mUser;
	}

	public void setUser(IUser user) {
		this.mUser = user;
	}

	/**
	 * Adds a road to the players list of roads
	 * Pre: player is not null, player has joined a game
	 * @param r Road to be added
	 */
	public void addRoad(Road r)
	{
		mRoads.add(r);
	}
	
	/**
	 * Adds a city to the players list of cities
	 * Pre: player is not null, player has joined a game
	 * @param b city (inheriting from Building) object to be added
	 */
	public void addCity(Building b)
	{
		mCities.add(b);
	}
	
	/**
	 * Adds a settlement to the players list of settlements
	 * Pre: player is not null, player has joined a game
	 * @param b settlement (inheriting from Building) object to be added
	 */
	public void addSettlement(Building b)
	{
		mSettlements.add(b);
	}
	
	/**
	 * Gets the number of soldiers this player has
	 * @return the number of soldiers this player has
	 */
	public int soldierCount()
	{
		return mSoldierCount;
	}
	/**
	 * Gets the number of victory points this player has
	 * @return the number of victory points this player has
	 */
	public int victoryPointCount()
	{
		return mVictoryPointCount;
	}
	
	/**
	 * Gets the number of roads this player has left to build
	 * @return the number of roads this player has left to build
	 */
	public int roadCount()
	{
		return mRoadCount;
	}
	
	/**
	 * Gets the number of settlements this player has left to build
	 * @return the number of settlements this player has left to build
	 */
	public int settlementCount()
	{
		return mSettlementCount;
	}
	
	/**
	 * Gets the number of cities this player has left to build
	 * @return the number of cities this player has left to build
	 */
	public int cityCount()
	{
		return mCityCount;
	}
	
	/**
	 * Gets the color corresponding to this player
	 * @return the color corresponding to this player
	 */
	public CatanColor color()
	{
		return mColor;
	}
	
	/**
	 * Checks to see if this player has discarded
	 * @return true if the player has discarded, false otherwise
	 */
	public boolean hasDiscarded()
	{
		return mDiscarded;
	}
	
	/**
	 * Gets the number of monuments this player has
	 * @return the number of monuments this player has
	 */
	public int monumentCount()
	{
		return mMonuments;
	}
	
	/**
	 * Gets the name associated with this player
	 * @return the name associated with this player
	 */
	public String name()
	{
		return mName;
	}
	
	/**
	 * Gets this player's list of dev cards
	 * @return the list of dev cards belonging to this player
	 */
	public List<DevCard> devCards()
	{
		return mDevCards;
	}
	
	/**
	 * Gets the Index (for turn taking purposes) corresponding to this player
	 * @return the Index object corresponding to this player
	 */
	public Index playerIndex()
	{
		return mPlayerIndex;
	}
	
	/**
	 * Checks to see whether a player has already played a dev card this round
	 * @return true if the player has already played a dev card this round, false otherwise
	 */
	public boolean hasPlayedDevCard()
	{
		return mHasPlayedDevCard;
	}
	
	/**
	 * Gets the unique id associated with a player
	 * @return the unique id associated with this player
	 */
	public int playerID()
	{
		return mPlayerID;
	}
	
	/**
	 * Gets the resources belonging to this player
	 * @return the list of resources belonging to this player
	 */
	public ResourceList resources()
	{
		return mResources;
	}
	
	/**
	 * Gets the list of roads belonging to this player (that have been built)
	 * @return the list of road objects belonging to this player
	 */
	public List<Road> roads()
	{
		return mRoads;
	}
	
	/**
	 * Gets the list of settlements belonging to this player (that have been built)
	 * @return the list of settlement objects belonging to this player
	 */
	public List<Building> settlements()
	{
		return mSettlements;
	}
	
	/**
	 * Gets the list of cities belonging to this player (that have been built)
	 * @return the list of city objects belonging to this player
	 */
	public List<Building> cities()
	{
		return mCities;
	}
	
	
	
	//Functions
	/**
	 * Checks all preconditions for road building
	 * @pre Player is logged in, has joined a game, has a road, has 1 wood 1 brick, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a road can be built, false otherwise
	 */
	public boolean canAffordRoad() 
	{
		ResourceList r = this.resources();
		if (
				this.roadCount() < 1 || // Checks that the player has roads left to build
				r.brick() < 1 || r.wood() < 1) // Checks that player has sufficient resources
		{
			return false;
		}
		return true;
	}

	/** 
	 * This is to be called during the regular playing rounds of the game (See the overloaded function below for special first and 
	 * second round cases)
	 * Checks to see that this road to be built is next to a road already belonging to a player
	 * @param loc the edge location we are hoping to build a road on
	 * @return true if this road to be built is next to a road belonging to the player and 
	 * the location is not already taken by another player, false otherwise
	 */
	public boolean canPlaceRoad(EdgeLocation loc) 
	{
		//Check if we are in the sea first
		if(loc.isInSea())
		{
			return false;
		}

		loc = loc.getNormalizedLocation();
		switch (loc.getDir().getLengthendDirection())
		{
		case NorthWest:
			if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SouthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.North)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), EdgeDirection.South)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.N)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NW), EdgeDirection.NE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NW), EdgeDirection.S))
			)
			{
				return true;
			}
			break;
		case North:
			if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NorthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.North), EdgeDirection.SouthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.North), EdgeDirection.SouthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.N), EdgeDirection.SW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.N), EdgeDirection.SE))
			)
			{
				return true;
			}
			break;
		case NorthEast:
			if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.North)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SouthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast), EdgeDirection.NorthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast), EdgeDirection.South)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.N)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NE), EdgeDirection.NW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NE), EdgeDirection.S))
			)
			{
				return true;
			}
			break;
		default:
			//System.out.println("Invalid Edge Direction");
		}
		return false;
	}
	
	/**
	 * Looks through a players roads to see if this location is touching another of the player's road (in order for it to be built)
	 * @param loc the edge location to check for an existing adjacent road by.
	 * @return true if this location is next to one of the player's existing roads, false otherwise
	 */
	private boolean checkForRoad(EdgeLocation loc)
	{
		loc = loc.getNormalizedLocation();
		for (Road road : this.roads())
		{
			EdgeLocation edge = road.location().getNormalizedLocation();
			if (loc.equals(edge))
			{
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * This is to be called on the first and second rounds of the game (Special Cases)
	 * Checks to see that this road to be built is next to a settlement belonging to the player
	 * @param loc the edge location we are hoping to build a road on
	 * @param the location of the last settlement built by this player (because the road needs to be next to it)
	 * @return true if this road to be built is next to the given settlement and 
	 * the edge location is not already taken by another player, false otherwise
	 */
	public boolean canPlaceRoad(EdgeLocation loc, VertexLocation settlement) 
	{
		loc = loc.getNormalizedLocation();
		
		//Check if we are in the sea first
		if(loc.isInSea())
		{
			return false;
		}
		
		//Check if we are attached to a settlement
		settlement = settlement.getNormalizedLocation();
		switch (settlement.getDir().getLengthenedDirection())
		{
			case NorthWest:
				if (
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.North)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NorthWest)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),EdgeDirection.NorthEast)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.N)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NW)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NW),EdgeDirection.NE))
				)
				{
					return true;
				}
				break;
			case NorthEast:
				if (
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.North)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NorthEast)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),EdgeDirection.NorthWest)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.N)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NE)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NE),EdgeDirection.NW))
				)
				{
					return true;
				}
				break;
			default:
				break;
		}
			
		return false;
	}
	
	/**
	 * Decreases a player's brick and wood by 1 each
	 * Pre: loc is not null, player has joined a game
	 * Post: decreases the player's resources accordingly
	 * adds a road at specified location to player's list of roads
	 * @param loc
	 */
	public void buildRoad(EdgeLocation loc) 
	{
		ResourceList r = this.resources();
		r.addBrick(-1);
		r.addWood(-1);
		this.mRoadCount -= 1;
		this.mRoads.add(new Road(this.mPlayerIndex, loc));
	}
	
	/**
	 * Checks all preconditions for building a new settlement
	 * @pre Player is logged in, has joined a game, has a settlement, 1 wood 1 brick 1 wheat 1 sheep, a valid place to build, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a settlement can be built, false otherwise
	 */
	public boolean canAffordSettlement() 
	{
		ResourceList r = this.resources();
		if (
				this.settlementCount() < 1 || // Checks that the player has settlements left to build
				r.brick() < 1 || r.wood() < 1 || r.sheep() < 1 || r.wheat() < 1) // Checks that player has sufficient resources
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Checks to see if a player can place a settlement on a given vertex location
	 * Pre: player can afford to buy a settlement, player still has at least one settlement left to build
	 * Post: checks if the settlement can be built on the given spot
	 * @param loc the place to potentially build a settlement
	 * @return true if the location doesn't already have a settlement, is connecting to one of the player's roads, false otherwise
	 */
	public boolean canPlaceSettlement(VertexLocation loc) 
	{
		loc = loc.getNormalizedLocation();
		switch (loc.getDir().getLengthenedDirection())
		{
			case NorthWest:
				if 
				(
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.North))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NorthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.N))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NW),EdgeDirection.NE))
				)
				{
					return true;
				}
				break;
			case NorthEast:
				if 
				(
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.North))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),EdgeDirection.NorthWest)) ||		
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.N))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NE),EdgeDirection.NW))
				)
				{
					return true;
				}
				break;
			default:
				break;
		}
			
		return false;	
	}
	
	/**
	 * Builds a settlement in the players color on the given location
	 * Pre: loc is not null, player is not null, player has joined game, location doesn't already have a settlement,
	 * is touching a road belonging to the player, player can afford to buy a settlement
	 * Post: builds a settlement in the given location
	 * @param loc the location to build a settlement
	 */
	public void buildSettlement(VertexLocation loc)
	{
		ResourceList r = this.resources();
		r.addBrick(-1);
		r.addWood(-1);
		r.addSheep(-1);
		r.addWheat(-1);
		this.mSettlementCount -= 1;
		this.mSettlements.add(new Building(this.mPlayerIndex, loc));
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * @pre Player is logged in, has joined a game, has a city, 3 ore 2 wheat, a settlement to build on, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return	true if a city can be built, false otherwise
	 */
	public boolean canAffordCity() 
	{
		ResourceList r = this.resources();
		if (
				this.cityCount() < 1 || // Checks that you have cities left to build
				this.settlements().isEmpty() || // Checks that there is a settlement to build on
				r.ore() < 3 || r.wheat() < 2) // Checks that there are enough resources
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Checks to see if a player can place a city on a given vertex location
	 * Pre: player can afford to buy a city, player still has at least one city left to build
	 * Post: checks if the city can be built on the given spot
	 * @param loc the place to potentially build a city
	 * @return true if the player already has a settlement on the given location, false otherwise
	 */
	public boolean canPlaceCity(VertexLocation loc) 
	{
		//Return true for locations where a settlement is already placed
		loc = loc.getNormalizedLocation();
		for(Building settlement : this.mSettlements)
		{
			VertexLocation vertex = settlement.location().getNormalizedLocation();
			if(loc.equals(vertex))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Builds a city in the players color on the given location
	 * Pre: loc is not null, player is not null, player has joined game, location already has a settlement
	 * on in belonging to the player, player can afford to buy a city
	 * Post: builds a city in the given location
	 * @param loc the location to build a city
	 */
	public void buildCity(VertexLocation loc)
	{
		loc = loc.getNormalizedLocation();
		
		ResourceList r = this.resources();
		r.addWheat(-2);
		r.addOre(-3);
		for(Building settlement : mSettlements)
		{
			VertexLocation vertex = settlement.location().getNormalizedLocation();
			if(loc.equals(vertex))
			{
				//Remove settlement, change to a city, and add to the cities
				this.mSettlements.remove(settlement);
				this.mSettlementCount += 1;
				settlement.setBuildingTypeToCity();
				this.mCityCount -= 1;
				this.mCities.add(settlement);
				break;
			}
		}		
	}

	/**
	 * Checks all preconditions for buying a development card.
	 * @pre Player is logged in, joined a game, has 1 sheep 1 wheat 1 ore, it is their turn, and there are development cards in bank. 
	 * 		Dice have also already been rolled.
	 * @post none 
	 * @return true if a card can be bought, false otherwise
	 */
	public boolean canBuyDevCard()
	{
		ResourceList r = this.resources();
		if (
				r.sheep() < 1 || r.wheat() < 1 || r.ore() < 1) // Checks that there are enough resources
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks all preconditions for playing a development card.
	 * @pre Player is logged in, in a game, it is their turn, they own a dev card, they have not played a dev card this turn
	 * 		other than victory points, they didn't buy the dev card this turn. Dice have also already been rolled.
	 * @post none
	 * @return true if a card can be played, false otherwise
	 */
	
	public boolean canPlayDevCard() 
	{
		if (this.hasMonument())
		{
			return true;
		}
		if (
				this.devCards().isEmpty() || // Checks that this player has a dev card
				this.hasPlayedDevCard() // Checks that player hasn't already played a dev card
		)
		{
			return false;
		}
		
		for (DevCard d : this.devCards())
		{
			if (!d.isNew())
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if a player has a monument in their dev cards
	 * Pre: player is not null, has joined game
	 * Post checks to see if a player has a monument
	 * @return true if a player has a monument, false otherwise
	 */
	public boolean hasMonument()
	{
		for (DevCard d : this.devCards())
		{
			if (d.type() == DevCardType.MONUMENT)
			{
				return true;
			}
		}
		return false;
	}

	public void buyDevCard()		// needs to be filled correctly
	{
		//Takes players resource cards
		//asks DevCard Bank to return 1 DevCard
		//adds returned DevCard to players list
		
		
		//this.addDevCard(m);
	}
	
	/**
	 * Adds a given dev card to this players list of dev cards
	 * @param card the card to be added to the player's list of dev cards
	 */
	public void addDevCard(DevCard card)
	{
		this.mDevCards.add(card);
	}
	
	/**
	 * Returns whether this player needs to discard when a 7 is rolled
	 * 
	 * @return false if they already have discarded or if they don't have more than 7 cards
	 */
	public boolean canDiscard()		//does this do anything more than check the num of resource cards?
	{
		if(this.resources().getTotal() <= 7)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Discard function
	 * Does nothing
	 * @return returns true
	 */
	public boolean discard()		//what does this dooooooo
	{
		return true;
	}
	
	/**
	 * Checks if the player is eligible to play a Year of Plenty Card
	 * 
	 * @return False if they have already played a dev card this turn or do not have an old Year of Plenty Card
	 */
	public boolean canPlayYearOfPlenty()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.YEAR_OF_PLENTY 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Does nothing ;)
	 */
	public void playYearOfPlenty()
	{
		
	}
	
	/**
	 * Returns whether a player can play a road builder card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a road builder card
	 */
	public boolean canPlayRoadBuilder()
	{
		if(this.hasPlayedDevCard())
		{
			//System.out.println("hasPlayedDevCard");
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.ROAD_BUILD 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed()
					&& mRoadCount > 1)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Does nothing ;)
	 */
	public void playRoadBuilder()
	{
		
	}
	
	/**
	 * Returns whether a player can play a soldier card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a soldier card
	 */
	public boolean canPlaySoldier()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.SOLDIER 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed())
			{
				return true;
			}
		}
		return false;
	}
	public void playSoldier()
	{
		
	}
	
	/**
	 * Returns whether a player can play a monopoly card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a monopoly card
	 */
	public boolean canPlayMonopoly()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.MONOPOLY 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed())
			{
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Does nothing
	 */
	public void playMonopoly()
	{
		
	}
	
	/**
	 * Returns whether a player can play a Monument card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a monument card
	 */
	public boolean canPlayMonument()
	{
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.MONUMENT
					&& !devCard.hasBeenPlayed())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Does nothing ;)
	 */
	public void playMonument()
	{
		
	}
	
	
	/**
	 * Adds given resources to a player's resource list
	 * Pre: Player is not null, has joined a game, resources below are not null
	 * Post: Adds given resources to a player's resource list
	 * @param brick
	 * @param ore
	 * @param sheep
	 * @param wheat
	 * @param wood
	 */
	public void addResourcesToList(int brick, int ore, int sheep, int wheat, int wood)
	{
		this.mResources = this.resources().updateResourceList(brick, ore, sheep, wheat, wood);
	}
	
	
	/**
	 * Checks to see if a player can accept a trade given the amount of resources they have
	 * Pre: Player has joined game, tradeOffer is not null
	 * Post: tells whether a player has the requested resources
	 * @param tradeOffer the resources that another player has offered as well as what they are requesting
	 * @return true if the player has the requested resources, false otherwise
	 */
	public boolean canAcceptTrade(Trade tradeOffer)
	{
		if (tradeOffer.offer().isEmpty())
		{
			return false;
		}
		
		return mResources.brick() >= -tradeOffer.offer().brick()
				&& mResources.ore() >= -tradeOffer.offer().ore()
				&& mResources.sheep() >= -tradeOffer.offer().sheep()
				&& mResources.wheat() >= -tradeOffer.offer().wheat()
				&& mResources.wood() >= -tradeOffer.offer().wood();
	}
	
	/**
	 * Checks to see if the person can trade at a given port, given the amount of
	 * resources they have
	 * Pre: Player is not null, has joined game, port type is one of the resource types
	 * or three
	 * Post: tells whether a player has enough resources
	 * 
	 * @param type trading type for this port
	 * @return true if they have enough of at least one resource to trade in, false otherwise
	 */
	public boolean haveResourceAmount(PortType type)
	{
		boolean toReturn = false;
		if(type != null)
		{
			switch (type)
			{
			case BRICK:
				toReturn = mResources.brick() >= 2;
				break;
			case ORE:
				toReturn = mResources.ore() >= 2;
				break;
			case WOOD:
				toReturn = mResources.wood() >= 2;
				break;
			case SHEEP:
				toReturn = mResources.sheep() >= 2;
				break;
			case WHEAT:
				toReturn = mResources.wheat() >= 2;
				break;
			case THREE:
				toReturn = mResources.brick() >= 3
						|| mResources.ore() >= 3
						|| mResources.sheep() >= 3
						|| mResources.wheat() >= 3
						|| mResources.wood() >= 3;
				break;
			}
		}
		return toReturn;
	}

	/**
	 * Checks whether a player has resources enough to maritime trade
	 * @param ports the ports that the player has
	 * @return true player has enough resources, false otherwise
	 */
	public boolean canMaritimeTrade(List<Port> ports)
	{			
		if(mResources.brick() >= 4
				|| mResources.ore() >= 4
				|| mResources.sheep() >= 4
				|| mResources.wheat() >= 4
				|| mResources.wood() >= 4)
		{
			return true;
		}

		boolean toReturn = false;
		
		for(Building b : mCities)
		{
			Port port = b.getAttachedPort(ports);
			if(port != null)
			{
				PortType portType = port.resource();
				toReturn = haveResourceAmount(portType);
				if(toReturn)
				{
					return toReturn;
				}
			}
		}
		
		for(Building b : mSettlements)
		{
			Port port = b.getAttachedPort(ports);
			if(port != null)
			{
				PortType portType = port.resource();
				toReturn = haveResourceAmount(portType);
				if(toReturn)
				{
					return toReturn;
				}
			}
		}
			
		return toReturn;
	}

	public void endTurn()
	{
		for(DevCard d: mDevCards)
		{
			d.setNew(false);
		}
	}

	/**
	 * Checks to see if a given settlement is attached to a road
	 * @param settlement
	 * @return true if it is attached to a road, false otherwise
	 */
	public boolean doesSettlementHaveRoadAttached(Building settlement) {
		for(Road road : this.roads())
		{
			if(this.canPlaceRoad(road.location(), settlement.location()))
			{
				return true;
			}
		}		
		return false;
	}

	
	/**
	 * Checks whether this player is a potential victim to be robbed,
	 * given a hex location where the robber sits
	 * Pre: game is in play, this player has joined, robberHexLoc is part of the board
	 * Post: find out if this player can be robbed given the location
	 * @param robberHexLoc the location of the robber
	 * @return true if this player has a settlement/city touching the robber location hex, false otherwise
	 */
	public boolean isVictim(HexLocation robberHexLoc)
	{
		for(Building b : mSettlements)
		{
			HexLocation sLoc = b.location().getNormalizedLocation().getHexLoc();
			if(robberHexLoc.equals(sLoc) || robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.N)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthWest 
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NorthWest)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthEast
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NE)))
			{
				return true;
			}
			
		}
		for(Building b : mCities)
		{
			HexLocation sLoc = b.location().getNormalizedLocation().getHexLoc();
			if(robberHexLoc.equals(sLoc) || robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.N)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthWest 
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NorthWest)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthEast
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NE)))
			{
				return true;
			}			
		}
		return false;
	}

	public CatanColor getColor() {
		return mColor;
	}

	public void setColor(CatanColor mColor) {
		this.mColor = mColor;
	}

	
}
