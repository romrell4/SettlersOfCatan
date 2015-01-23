package models;

import shared.definitions.DevCardType;

public interface IDevCard
{
	/** 
	 * Checks whether development card has been played
	 * 
	 * @return true if card has been played, false otherwise.
	 */
	boolean getmPlayed();
	/** 
	 * Gets development card type
	 * 
	 * @return one of the following development card type enum values: SOLDIER, YEAR_OF_PLENTY, MONOPOLY, ROAD_BUILD, MONUMENT
	 */
	DevCardType getmType();
	/** 
	 * Initiates playing of this card
	 * 
	 * @return one of the following development card type enum values: SOLDIER, YEAR_OF_PLENTY, MONOPOLY, ROAD_BUILD, MONUMENT
	 */
	void execute();
}