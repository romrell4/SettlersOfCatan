package server.commands;

import shared.locations.VertexLocation;
import models.IGame;
import models.Index;

public class BuildSettlementCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	private VertexLocation vertexLocation;
	private boolean free;
	
	public BuildSettlementCommand(IGame game, Index playerIndex, VertexLocation vertexLocation, boolean free)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = free;
	}

	@Override
	public void execute()
	{
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " built a settlement";
		this.game.addActionToLog(playerIndex, message);
		game.buildSettlement(playerIndex, vertexLocation, free);
	}
}
