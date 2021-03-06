package server.commands;

import shared.locations.EdgeLocation;
import models.IGame;
import models.Index;

public class RoadBuildingCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	private EdgeLocation spot1;
	private EdgeLocation spot2;
	
	public RoadBuildingCommand(IGame game, Index playerIndex, EdgeLocation spot1, EdgeLocation spot2)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.spot1 = spot1;
		this.spot2 = spot2;
	}

	@Override
	public void execute()
	{
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " played a road building card";
		this.game.addActionToLog(playerIndex, message);
		game.playRoadBuilding(playerIndex, spot1, spot2);
	}
}
