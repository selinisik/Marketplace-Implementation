package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;


/**
 * This class is used for market based operations such as creating a new market, checking the buying and selling orders and making open market operations.
 * @author Selin ISIK
 *
 */
public class Market {
	/**
	 * This Priority Queue stores the buying orders and sort them according to the given conditions.
	 */
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<>();
	/**
	 * This Priority Queue stores the selling orders and sort them according to the given conditions.
	 */
	private PriorityQueue<BuyingOrder> buyingOrders = new PriorityQueue<>();
	/**
	 * This ArrayList keeps the record of every transaction that has been completely made.
	 */
	private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	/**
	 * The fee that market cuts from the seller in every successful selling order.
	 */
	int fee;
	/**
	 * This field stores the number of unsuccessful selling or buying orders
	 */
	static int numOfInvalidQueries = 0;
	/**
	 * List of the markets that has been created.
	 *  Not really used in this case but it will be useful in cases which there are more than one markets.
	 */
	private static ArrayList<Market> markets = new ArrayList<Market>();
	
	/**
	 * Constructor that creates a new market with given parameters.
	 * @param fee The fee that market cuts from the seller in every successful selling order.
	 */
	public Market(int fee) {
		this.fee=fee;
		
	}
	
	/**
	 * This method adds the given selling order to the selling order Priority Queue.
	 * @param order The order which will be added to the selling order Priority Queue. 
	 */
	public void giveSellOrder(SellingOrder order) {
		this.sellingOrders.add(order);
		
	}
	
	/**
	 * This method adds the given buying order to the buying order Priority Queue.
	 * @param order The order which will be added to the buying order Priority Queue
	 */
	public void giveBuyOrder(BuyingOrder order) {
		this.buyingOrders.add(order);
		
	}
	
	/**
	 * This method sets the price of PQuin to the given price by creating the selling orders or buying orders which is necessary
	 * @param The price at which PQuin is wanted to be fixed.
	 * @param traderlist List of traders transacting in the market.
	 */
	public void makeOpenMarketOperation(double price , ArrayList<Trader>traderlist) {
		if( buyingOrders.peek() != null) {
			if(price <= buyingOrders.peek().price) {
				while(price <= buyingOrders.peek().price) {
					Trader.traders.get(0).sell(buyingOrders.peek().amount, buyingOrders.peek().price, markets.get(0));
					markets.get(0).checkTransactions(traderlist);
					if( buyingOrders.peek() == null) {
						break;
					}
	
				}
			}
		}
		if(sellingOrders.peek() != null ) {
			if(price >= sellingOrders.peek().price) {
				while(price >= sellingOrders.peek().price) {
					Trader.traders.get(0).buy(sellingOrders.peek().amount, sellingOrders.peek().price , markets.get(0));
					markets.get(0).checkTransactions(traderlist);
					if(sellingOrders.peek() != null ) {
						break;
					}
				}
			}
		}
	}
	
	/**
	 * This function checks both sellingOrders and buyingOrders and looks for the overlap.
	 * If there's a overlap, it makes the necessary transactions and updates the wallets of traders, sellingOrders queue and buyingOrders queue. 
	 * @param traders Uses the list of traders to access their wallets.
	 */
	public void checkTransactions(ArrayList<Trader>traders) {
		if(sellingOrders.peek() != null && buyingOrders.peek() != null) {
			while(sellingOrders.peek().price <= buyingOrders.peek().price) {
				Transaction transaction = new Transaction(sellingOrders.peek(), buyingOrders.peek());
				transactions.add(transaction);
				if(sellingOrders.peek().price < buyingOrders.peek().price) {
					traders.get(buyingOrders.peek().traderID).getWallet().setBlockedDollars(buyingOrders.peek().getAmountandPrice());
					traders.get(buyingOrders.peek().traderID).getWallet().setDollars(traders.get(buyingOrders.peek().traderID).getWallet().getDollars()+(buyingOrders.peek().getAmountandPrice()-sellingOrders.peek().getAmountandPrice()));
					traders.get(buyingOrders.peek().traderID).getWallet().setCoins(traders.get(buyingOrders.peek().traderID).getWallet().getCoins()+sellingOrders.peek().amount);
					traders.get(sellingOrders.peek().traderID).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().traderID).getWallet().getBlockedCoins() - sellingOrders.peek().amount );
					traders.get(sellingOrders.peek().traderID).getWallet().setDollars(traders.get(sellingOrders.peek().traderID).getWallet().getDollars()+(sellingOrders.peek().getAmountandPrice()*(1-(double)((double)(markets.get(0).fee)/(double)1000))));
					if(sellingOrders.peek().amount < buyingOrders.peek().amount) {
						buyingOrders.peek().setAmount( buyingOrders.peek().amount - sellingOrders.peek().amount );
						sellingOrders.poll();
					}else if (sellingOrders.peek().amount == buyingOrders.peek().amount) {
						sellingOrders.poll();
						buyingOrders.poll();
						
					}else if(sellingOrders.peek().amount > buyingOrders.peek().amount) {
						sellingOrders.peek().setAmount( sellingOrders.peek().amount - buyingOrders.peek().amount);
						buyingOrders.poll();
					}
				}
				else if(sellingOrders.peek().price == buyingOrders.peek().price) {
					traders.get(buyingOrders.peek().traderID).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().traderID).getWallet().getBlockedDollars() - buyingOrders.peek().getAmountandPrice());
					traders.get(buyingOrders.peek().traderID).getWallet().setCoins(traders.get(buyingOrders.peek().traderID).getWallet().getCoins()+sellingOrders.peek().amount);
					traders.get(sellingOrders.peek().traderID).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().traderID).getWallet().getBlockedCoins() - sellingOrders.peek().amount );
					traders.get(sellingOrders.peek().traderID).getWallet().setDollars(traders.get(sellingOrders.peek().traderID).getWallet().getDollars()+(sellingOrders.peek().getAmountandPrice()*(1-(double)((double)(markets.get(0).fee)/(double)1000))));
					if(sellingOrders.peek().amount < buyingOrders.peek().amount) {
						buyingOrders.peek().setAmount( buyingOrders.peek().amount - sellingOrders.peek().amount );
						sellingOrders.poll();
					}else if (sellingOrders.peek().amount == buyingOrders.peek().amount) {
						sellingOrders.poll();
						buyingOrders.poll();
						
					}else if(sellingOrders.peek().amount > buyingOrders.peek().amount) {
						sellingOrders.peek().setAmount( sellingOrders.peek().amount - buyingOrders.peek().amount);
						buyingOrders.poll();
					}
				}
				if(sellingOrders.peek() == null || buyingOrders.peek() == null) {
					break;
				}
			}
		}	
	}

	public static ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public static int getNumOfInvalidQueries() {
		return numOfInvalidQueries;
	}

	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}

	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}

	public static ArrayList<Market> getMarkets() {
		return markets;
	}

	/**
	 * This method adds the created markets to the markets ArrayList.
	 * @param market The market that will be added to the list.
	 */
	public static void addMarket(Market market) {
		markets.add(market);
	}

	/**
	 * In cases of invalid transactions, this method is called and increase the number of invalid transactions.
	 */
	public static void increaseNumOfInvalidQueries() {
		Market.numOfInvalidQueries += 1;
	}


}


