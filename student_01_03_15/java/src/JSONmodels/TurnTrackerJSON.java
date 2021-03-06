package JSONmodels;

import models.Index;
import models.Status;
import models.TurnTracker;

import com.google.gson.Gson;

public class TurnTrackerJSON 
{
	private int currentTurn; //Whose turn it is (0-3).
	private String status; //What's happening now.
	private int longestRoad; //The index of who has the longest road.
	private int largestArmy; //The index of who has the largest army. (Has to be 3 or more).
	
	public TurnTrackerJSON(TurnTracker turnTracker) 
	{
		this.currentTurn = turnTracker.currentTurn().value();
		this.status = turnTracker.status().toString();
		this.longestRoad = turnTracker.longestRoad().value();
		this.largestArmy = turnTracker.largestArmy().value();
	}

	/**
	 * Creates a TurnTracker object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New TurnTracker object
	 */
	public static TurnTrackerJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, TurnTrackerJSON.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the currentTurn
	 */
	public int getCurrentTurn()
	{
		return currentTurn;
	}

	/**
	 * @return the status
	 */
	public String getStatus() 
	{
		return status;
	}

	/**
	 * @return the longestRoad
	 */
	public int getLongestRoad() 
	{
		return longestRoad;
	}

	/**
	 * @return the largestArmy
	 */
	public int getLargestArmy() 
	{
		return largestArmy;
	}
	
	public models.TurnTracker getModelTurnTracker()
	{
		models.TurnTracker t = null;
		try 
		{
			t = new models.TurnTracker(new Index(currentTurn), Status.valueOf(status.toUpperCase()), new Index(longestRoad), new Index(largestArmy));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return t;
	}
}
