package client.resources;

import java.util.*;

import models.Status;
import states.*;
import client.base.*;
import facade.MasterManager;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, Observer 
{

	private Map<ResourceBarElement, IAction> elementActions;
	private MasterManager master;
	private IState state;
//	private IMapView mapView;
	
	public ResourceBarController(IResourceBarView view) 
	{

		super(view);
		state = new SetupState();
		elementActions = new HashMap<ResourceBarElement, IAction>();
		master = MasterManager.getInstance();
		master.getModelManager().addObserver(this);
	}

	@Override
	public IResourceBarView getView() 
	{
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) 
	{

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() 
	{
		if (state.canAffordCity())
		{
			executeElementAction(ResourceBarElement.ROAD);
			
		}
		
	}

	@Override
	public void buildSettlement() 
	{
		if (state.canAffordSettlement())
		{
			executeElementAction(ResourceBarElement.SETTLEMENT);
		}
		
	}

	@Override
	public void buildCity() 
	{
		if (state.canAffordCity()) 
		{
			executeElementAction(ResourceBarElement.CITY);	
		}
		
	}

	@Override
	public void buyCard() 
	{
		if (state.canBuyDevCard())
		{
			executeElementAction(ResourceBarElement.BUY_CARD);			
		}
		
	}

	@Override
	public void playCard() 
	{
		if (state.canPlayDevCard())
		{
			executeElementAction(ResourceBarElement.PLAY_CARD);
		}
	}
	
	private void executeElementAction(ResourceBarElement element) 
	{
		
		if (elementActions.containsKey(element)) 
		{
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		if(master.hasJoinedGame)
		{

			Status status = master.getCurrentModel().turnTracker().status();
			switch(status)
			{
			case ROBBING:
				state = new RobbingState();
				break;
			case PLAYING:
				state = new PlayingState();
				break;
			case DISCARDING:
				state = new DiscardingState();
				break;
			case ROLLING:
				state = new RollingState();
				break;
			case FIRSTROUND:
				state = new SetupState();
				break;
			case SECONDROUND:
				state = new SetupState();
				break;
			default:
				System.out.println("ResourceBarController update() should never get here.");
			}
			System.out.println("WE HIT THE RESOURCE CONTROLLER'S OBSERVER UPDATE METHOD");
			
			// SETTING BUILD BUTTONS ENABLED OR NOT, DEPENDING ON IF PLAYER CAN AFFORD THEM
			if (state.canAffordRoad())
			{
				this.getView().setElementEnabled(ResourceBarElement.ROAD, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.ROAD, false);
			}
			//----------------------------------------------
			if (state.canAffordSettlement())
			{
				this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
			}
			//----------------------------------------------
			if (state.canAffordCity())
			{
				this.getView().setElementEnabled(ResourceBarElement.CITY, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.CITY, false);
			}
			//-----------------------------------------------
			if (state.canBuyDevCard())
			{
				this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
			}
			
		}
	}

}

