package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String id;
	private String playerName;
	private int points;
	private int bet;
	private int result;

	public SimplePlayer(String id, String playerName, int points) {
		this.id = id;
		this.playerName = playerName;
		this.points = points;
		bet = 0;
		result = 0;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {

		return this.points;
	}

	@Override
	public void setPoints(int points) {

		this.points = points;
	}

	@Override
	public String getPlayerId() {

		return this.id;
	}

	@Override
	public boolean setBet(int bet) {

		if ((bet > 0) && (bet <= points)) // points
		{
			this.bet = bet;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public int getResult() {
		return this.result;
	}

	@Override
	public void setResult(int result) {
		// TODO Auto-generated method stub
		// result - the result of the most recent hand
		// (updated from the GameEngine via GameEngine.dealPlayer(Player, int))

		this.result = result;

	}

	@Override
	public boolean equals(Player player) {
		return id.equals(player.getPlayerId());
	}

	@Override
	public boolean equals(Object player) {
		if (player instanceof SimplePlayer) {
			// code duplications
			SimplePlayer simpleplayer = (SimplePlayer) player;
			return id.equals(simpleplayer.getPlayerId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public int compareTo(Player player) {
		return id.compareTo(player.getPlayerId());
	}

	@Override
	public String toString() {

		return String.format("Player: ID=%s, Name=%s, Bet=%s, Points=%s, Result=%s", id, playerName, bet, points,
				result);
	}
}
