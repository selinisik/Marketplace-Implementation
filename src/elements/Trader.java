package elements;

import java.util.ArrayList;

/**
 * This class is used for Trader based operations such as creating a new trader, selling some coins and buying some coins. 
 * @author Selin ISIK
 *
 */
public class Trader {
	/**
	 * ID number of traders.
	 */
	private int id;
	/**
	 * Wallet of the traders.
	 */
	private Wallet wallet;
	/**
	 * List of traders transacting in the market.
	 */
	public static ArrayList<Trader>traders = new ArrayList<Trader>();
	/**
	 * Number of users in the market.
	 */
	public static int numberOfUsers = 0;
	
	/**
	 * Constructor that creates Traders.
	 * @param dollars The amount of dollars that seller has.
	 * @param coins The amount of PQuins that seller has.
	 */
	public Trader(double dollars, double coins) {
		this.id = numberOfUsers;
		numberOfUsers++;
		this.wallet = new Wallet(dollars,coins);
		
	}
	/**
	 * This method  makes the necessary operations to sell the amount of POuin that the traders wants.
	 * @param amount The amount of PQuin that trader wants to sell.
	 * @param price The price at which the seller is willing to sell the PQuins
	 * @param market The market in which the trader wants to make his operation.
	 * @return Returns 1 if this operation is possible. Returns 0 if this operation is not possible due to traders property.
	 */
	public int sell(double amount, double price, Market market) {
		if(this.id != 0) {
			if(amount<= this.wallet.getCoins()) {
				wallet.setCoins(wallet.getCoins()-amount);
				wallet.setBlockedCoins(wallet.getBlockedCoins() +amount);
				SellingOrder sellorder = new SellingOrder(this.id,amount,price);
				market.giveSellOrder(sellorder);
				return 1;
			}
			else {
				Market.increaseNumOfInvalidQueries();
				return 0;
			}
				
		}else {
			wallet.setCoins(wallet.getCoins()-amount);
			wallet.setBlockedCoins(wallet.getBlockedCoins() +amount);
			SellingOrder sellorder = new SellingOrder(this.id,amount,price);
			market.giveSellOrder(sellorder);
			return 1;
		}
	}
	/**
	 * This method  makes the necessary operations to buy the amount of POuin that the traders wants.
	 * @param amount The amount of PQuin that trader wants to buy. 
	 * @param price The price at which the buyer is willing to buy the PQuins
	 * @param market The market in which the trader wants to make his operation.
	 * @return Returns 1 if this operation is possible. Returns 0 if this operation is not possible due to traders property.
	 */
	public int buy(double amount, double price, Market market) {
		if(this.id != 0) {
			if (amount*price <= this.wallet.getDollars()) {
				wallet.setDollars(wallet.getDollars()-amount*price);
				wallet.setBlockedDollars(wallet.getBlockedDollars() +price*amount);
				BuyingOrder buyorder = new BuyingOrder(this.id,amount,price);
				market.giveBuyOrder(buyorder);
				return 1;
			}
			else {
				Market.increaseNumOfInvalidQueries();
				return 0;
			}
		}else {
			wallet.setDollars(wallet.getDollars()-amount*price);
			wallet.setBlockedDollars(wallet.getBlockedDollars() +price*amount);
			BuyingOrder buyorder = new BuyingOrder(this.id,amount,price);
			market.giveBuyOrder(buyorder);
			return 1;
		}
	}
	public Wallet getWallet() {
		return wallet;
	}
	
}
