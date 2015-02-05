package JUnitTests;

import static org.junit.Assert.*;
import models.Building;
import models.Index;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.BuildingType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class BuildingTester 
{

	//this tester also includes the index testing.
	//after all is done, Building, Index, VertexLocation, HexLocation, and Vertex Direction will all be tested
	
	private Index indexZero;
	private Index indexOne;
	private Building buildingZero;
	private Building buildingSettle;
	private Building buildingCity;
	private VertexLocation locationZero;
	private HexLocation hexZero = new HexLocation(1,1);
	private HexLocation hexOne = new HexLocation(2,2);
	private VertexLocation locationOne;
	private VertexLocation locationTwo;
	
	
	@Before
	public void initialize() 
	{
		try 
		{
			indexZero = new Index(0);
			indexOne = new Index(1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		locationZero = new VertexLocation(hexZero, VertexDirection.NorthEast);
		locationOne = new VertexLocation(hexZero, VertexDirection.SouthWest);
		locationTwo = new VertexLocation(hexOne, VertexDirection.East);
		buildingZero = new Building(indexZero, locationZero);
		buildingCity = new Building(indexZero, locationOne);
		buildingSettle = new Building(indexOne, locationTwo);
		
		buildingCity.setBuildingTypeToCity();
		
		
	}
	
	
	
	
	@Test
	public void testBuildings() 
	{
		//testings buildingType
		assertTrue(buildingZero.buildingType().equals(BuildingType.SETTLEMENT));
		assertTrue(buildingSettle.buildingType().equals(BuildingType.SETTLEMENT));
		assertTrue(buildingCity.buildingType().equals(BuildingType.CITY));
		//testing buildingLocation
		assertTrue(buildingZero.location().equals(locationZero));
		assertFalse(buildingCity.location().equals(locationZero));
		assertTrue(buildingSettle.location().equals(locationTwo));
		//testing building owner
		assertTrue(buildingZero.owner().equals(indexZero));
		assertFalse(buildingCity.owner().equals(indexOne));
		assertTrue(buildingSettle.owner().equals(indexOne));
		
		
	}

	
	
	
	
	
	@After
	public void tearDown()
	{
		return;
	}
	
	
}
