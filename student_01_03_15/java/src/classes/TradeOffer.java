package classes;

public class TradeOffer 
{
	private Index mSender; //The index of the person offering the trade.
	private Index mReceiver; //The index of the person the trade was offered to.
	private ResourceList mOffer; //Positive numbers are resources being offered. Negative are resources being asked for.
	
	public TradeOffer(Index sender, Index receiver, ResourceList offer)
	{
		this.mSender = sender;
		this.mReceiver = receiver;
		this.mOffer = offer;
	}
	
	public Index sender()
	{
		return mSender;
	}
	public Index receiver()
	{
		return mReceiver;
	}
	public ResourceList offer()
	{
		return mOffer;
	}
}