package JSONmodels;

import shared.definitions.PortType;
import shared.locations.VertexDirection;

import com.google.gson.Gson;

public class Port 
{
	private String resource; //What type resource this port trades for. If it's omitted, then it's for any resource.
	private HexLocation location; //What hex this port is on. This shows the ocean/non-existent hex to draw the port on.
	private String direction; //Which edge this port is on.
	private int ratio; //The ratio for trade in (ie., if this is 2, then it's a 2:1 port)
	
	/**
	 * Creates a Port object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Port object
	 */
	public static Port fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, Port.class);
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
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @return the location
	 */
	public HexLocation getLocation() {
		return location;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @return the ratio
	 */
	public int getRatio() {
		return ratio;
	}
	
	public models.Port getModelPort()
	{
		return new models.Port(PortType.valueOf(resource), location.getModelHexLocation(), VertexDirection.valueOf(direction), ratio);
	}
}
