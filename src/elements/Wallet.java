package elements;

/**i
 * This class is used for keeping the track of traders financial status and accessing their current assets faster.
 * @author Selin ISIK
 *
 */
public class Wallet {
	/**
	 * The amount of dollars that trader has. 
	 */
	private double dollars;
	/**
	 * The amount of PQuins that the trader has.
	 */
	private double coins;
	/**
	 * Amount of dollars that currently in buying orders that wait for the transaction.
	 */
	private double blockedDollars;
	/**
	 * The amount of PQuins that currently in selling orders that wait for transaction.
	 */
	private double blockedCoins;
	
	/**
	 * This constructor creates a wallet for traders.
	 * @param dollars The amount of dollars that trader has.
	 * @param coins The amount of PQuins that the trader has.
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
	}

	public double getDollars() {
		return dollars;
	}

	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	public double getCoins() {
		return coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	public double getBlockedDollars() {
		return blockedDollars;
	}

	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}

	public double getBlockedCoins() {
		return blockedCoins;
	}

	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
	

}
