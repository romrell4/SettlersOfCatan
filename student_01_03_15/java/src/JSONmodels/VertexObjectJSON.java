package JSONmodels;

import models.Building;
import models.Index;

import com.google.gson.Gson;

public class VertexObjectJSON 
{
	private int owner; //The index (not id) of the player who owns the piece (0-3).
	private VertexLocationJSON location; //The location of this object.
	
	public VertexObjectJSON(Building building) 
	{
		this.owner = building.owner().value();
		this.location = new VertexLocationJSON(building.location());
	}

	/**
	 * Creates a VertexObject object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New VertexObject object
	 */
	public static VertexObjectJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, VertexObjectJSON.class);
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
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * @return the location
	 */
	public VertexLocationJSON getLocation() {
		return location;
	}
	
	public Building getModelSettlement()
	{
		Building b = null;
		try
		{
			b = new Building(new Index(owner),location.getModelVertexLocation());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public Building getModelCity()
	{
		Building b = null;
		try
		{
			b = new Building(new Index(owner),location.getModelVertexLocation());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
}
