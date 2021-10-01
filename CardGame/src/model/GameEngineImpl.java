package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private List<Player> players = new ArrayList<>();
	private Deque<PlayingCard> deck = getShuffledHalfDeck();

	private List<GameEngineCallback> callbacks = new ArrayList<>();


	public GameEngineImpl() {

	}

	@Override

	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		int result = 0;
		PlayingCard card = null;
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
			if (delay < 0 || delay > 1000) {
				throw new IllegalArgumentException("Delay cannot be more than 1000 or less than 0 mill sec");
			} else {
				while (result <= BUST_LEVEL) {
					if (deck.isEmpty()) {
						deck = getShuffledHalfDeck();
					}
					card = deck.pop();
					Thread.sleep(delay);

					if (result + card.getScore() <= BUST_LEVEL) {

						result = result + card.getScore();
						for (GameEngineCallback callback : callbacks) {
							callback.nextCard(player, card, this);
						}

					} else {
						break;
					}

				}

			}
			for (GameEngineCallback callback : callbacks) {
				callback.bustCard(player, card, this);

				callback.result(player, result, this);

			}
			player.setResult(result);

		} catch (IllegalArgumentException e) {// delay value mismatch
			e.printStackTrace();
		} catch (InterruptedException e) {// delay interrupted
			e.printStackTrace();
		}

	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		int result = 0;

		PlayingCard card = null;
		try {
			TimeUnit.MILLISECONDS.sleep(delay);

			if (delay < 0) {
				throw new IllegalArgumentException("Delay cannot be less than 0 mill sec");
			} else {
                     
				while (result <= BUST_LEVEL) {
					if (deck.isEmpty()) {
						deck = getShuffledHalfDeck();
					}
					card = deck.pop();
					Thread.sleep(delay);

					if (result + card.getScore() <= BUST_LEVEL) {

						result = result + card.getScore();
						for (GameEngineCallback gameEngineCallback : callbacks) {
							gameEngineCallback.nextHouseCard(card, this);
						}
					} else {
						for (GameEngineCallback gameEngineCallback : callbacks) {
							gameEngineCallback.houseBustCard(card, this);
							
						}
						break;
					}
				}
				for (Player player : players) {
					applyWinLoss(player, result);
				}
				for (GameEngineCallback gameEngineCallback : callbacks) {
					gameEngineCallback.houseResult(result, this);
				}
				players.forEach((player) -> {
					player.resetBet();
				});
			}
			
		}

		catch (IllegalArgumentException e)// delay value mismatch
		{
			e.printStackTrace();
		}

		catch (InterruptedException e)// delay interrupted
		{
			e.printStackTrace();
		}

	}

	@Override
	public void applyWinLoss(Player player, int houseResult) {

		if (player.getResult() > houseResult) {
			player.setPoints(player.getPoints() + player.getBet());
		} else if(player.getResult() < houseResult){
			player.setPoints(player.getPoints() - player.getBet());
		}
		else {
			player.setPoints(player.getPoints());
		}

	}

	@Override
	public void addPlayer(Player player) {
			boolean same = true;
		if (players.isEmpty()) {
			players.add(player);
		} else {
			
			
			for (int i = 0; i < players.size(); i++) {
				{
					if (player.getPlayerId() == (players.get(i).getPlayerId())) {
						players.get(i).setPlayerName(player.getPlayerName());
						players.get(i).setPoints(player.getPoints());
						same = false;
					}
					

				}
			}
			if(same) {
			players.add(player);
			}
		}

	}

	@Override
	public Player getPlayer(String id) {
		for (Player player : players) {
			if (player.getPlayerId().equals(id))

				return player;
			
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
//		boolean player_exist = false;
//		for (Player playeri : players) {
//			if (player.getPlayerId().equals(playeri.getPlayerId())) {
//				players.remove(player);
//				player_exist = true;
//			}
//		}
//		return player_exist;
		if (player != null) {
			players.remove(player);
			return true;
			}
			return false;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if (callbacks.contains(gameEngineCallback)) {
			callbacks.remove(gameEngineCallback);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {

		Collections.sort(this.players);
		return this.players;
	}

	@Override
	public Deque<PlayingCard> getShuffledHalfDeck() {
		Deque<PlayingCard> shuffledDeck = new LinkedList<PlayingCard>();
		// generating 7*4 = 28 size deck
		for (Suit suit : PlayingCard.Suit.values()) {
			for (Value value : PlayingCard.Value.values()) {
				shuffledDeck.add(new PlayingCardImpl(suit, value));

			}
		}
		// shuffling of the deck which is Deque type so we type cast it
		Collections.shuffle((List<?>) shuffledDeck);
		
		return shuffledDeck;

	}

}
 