package client.maritime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import models.Building;
import models.Player;
import models.Port;
import shared.definitions.*;
import client.base.*;
import facade.MasterManager;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, Observer {

	private IMaritimeTradeOverlay tradeOverlay;
	private MasterManager master;
	private Map<ResourceType,Integer> mTradePrice = new HashMap<>();
	ResourceType mGetType;
	ResourceType mGiveType;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay)
	{
		
		super(tradeView);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		setTradeOverlay(tradeOverlay);
		initMap();
	}
	
	public IMaritimeTradeView getTradeView() 
	{
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay()
	{
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay)
	{
		this.tradeOverlay = tradeOverlay;
	}
	
	public void initMap()
	{
		mTradePrice.put(ResourceType.BRICK, new Integer(4));
		mTradePrice.put(ResourceType.ORE, new Integer(4));
		mTradePrice.put(ResourceType.WOOD, new Integer(4));
		mTradePrice.put(ResourceType.WHEAT, new Integer(4));
		mTradePrice.put(ResourceType.SHEEP, new Integer(4));		
	}

	@Override
	public void startTrade()
	{
		tradeOverlay.setCancelEnabled(true);
		tradeOverlay.setTradeEnabled(false);
		
		mGiveType = null;
		mGetType = null;
		
		Player player = master.getPlayer();
		ArrayList<Building> temp = new ArrayList<>();
		temp.addAll(player.cities());
		temp.addAll(player.settlements());
		for(Building b : temp)
		{
			Port p = b.port();
			if(p != null)
			{
				PortType pt = p.resource();
				switch(pt)
				{
				case BRICK:
					mTradePrice.put(ResourceType.BRICK, new Integer(2));
					break;
				case WOOD:
					mTradePrice.put(ResourceType.WOOD, new Integer(2));
					break;
				case WHEAT:
					mTradePrice.put(ResourceType.WHEAT, new Integer(2));
					break;
				case ORE:
					mTradePrice.put(ResourceType.ORE, new Integer(2));
					break;
				case SHEEP:
					mTradePrice.put(ResourceType.SHEEP, new Integer(2));
					break;
				case THREE:
					mTradePrice.put(ResourceType.WOOD, new Integer(3));
					mTradePrice.put(ResourceType.BRICK, new Integer(3));
					mTradePrice.put(ResourceType.ORE, new Integer(3));
					mTradePrice.put(ResourceType.SHEEP, new Integer(3));
					mTradePrice.put(ResourceType.WHEAT, new Integer(3));
					break;
				default:
					System.out.println("MaritimeTradeController startTrade() it should never get here.");	
				}
			}
		}
		tradeOverlay.showGiveOptions(this.availableTradeResources());
		getTradeOverlay().showModal();
	}

	public ResourceType[] availableTradeResources()
	{
		ArrayList<ResourceType> resources = new ArrayList<ResourceType>();
		
		for (Map.Entry<ResourceType,Integer>entry : mTradePrice.entrySet())
		{
			if (entry.getValue() <= master.getPlayer().resources().getResource(entry.getKey()))
			{
				resources.add(entry.getKey());
			}
		}
		if (resources.isEmpty())
		{
			return null;
		}
		return (ResourceType[]) resources.toArray();
	}
	
	
	@Override
	public void makeTrade()
	{
		master.executeMaritimeTrade(master.getPlayerIndex(), mTradePrice.get(mGiveType).intValue(), mGiveType, mGetType);
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade()
	{
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource)
	{
		mGetType = resource;
		if(mGetType != null && mGiveType != null)
		{
			tradeOverlay.setTradeEnabled(true);
		}
	}

	@Override
	public void setGiveResource(ResourceType resource) 
	{
		mGiveType = resource;
		if(mGetType != null && mGiveType != null)
		{
			tradeOverlay.setTradeEnabled(true);
		}
	}

	@Override
	public void unsetGetValue()
	{
		mGetType = null;
		tradeOverlay.setTradeEnabled(false);
	}

	@Override
	public void unsetGiveValue() 
	{
		mGiveType = null;
		tradeOverlay.setTradeEnabled(false);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		
	}
}

