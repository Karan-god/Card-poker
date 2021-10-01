package model;

import model.interfaces.PlayingCard;


public class PlayingCardImpl implements PlayingCard

{
	private Suit suit;
	private Value value;
	

	public PlayingCardImpl(Suit suit, Value value) 
	{
		
	    this.suit = suit;
	    this.value = value;
	     
		
   }

	@Override
	public Suit getSuit() 
	{
		
		return  suit;
	}

	@Override
	public Value getValue()
	{
		
		return value;
	}

	@Override
	public int getScore() 
	{
		int score = 0;
		switch (value) {
		case EIGHT:
			
			score = 8;
			
			break;
		
		case NINE:
			
			score = 9;
			
			break;
		case TEN:
		case JACK:
		case QUEEN:
		case KING:	
			
			score = 10;
				
		break;
		case ACE:
			
			score = 11;	
			
		break;	
		default:
			score=0;
			break;
		}
		return score;
	}
	

	@Override
	public String toString()
	{
		String suitt=suit.name().substring(0,1)+suit.name().substring(1,suit.name().length()).toLowerCase();
		String valuee=value.name().substring(0,1)+value.name().substring(1,value.name().length()).toLowerCase();
		String result=String.format("Suit : %s,Value: %s, Score: %s",suitt,valuee,getScore());
		return result;
		
	}

	@Override
	public boolean equals(PlayingCard card)
	{
		return suit.equals(card.getSuit()) && this.value.equals(card.getValue()) ;
	}
	
	@Override
	public boolean equals(Object card)
	{
		return equals((PlayingCard)card);
	}
	
	@Override
	public  int hashCode()
	{
		String card_type=suit.name()+value.name();
		return card_type.hashCode();
		
	}
	

}
