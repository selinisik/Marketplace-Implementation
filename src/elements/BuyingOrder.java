package elements;

/**
 * This class is used for creating buying orders and sorting them in correct way.
 * @author Selin ISIK
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder>{

	/**
	 * Constructor that creates a Buying Order with given parameters.
	 * @param traderID ID of the trader who gives the buying order.  
	 * @param amount Amount of PQuins to be purchased.
	 * @param price The price at which the buyer is willing to buy the PQuins
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	/**
	 *This compare to method sorts the buying orders according to their prices.
	 * If the prices are equal, method makes the comparison according to the amount of PQuins to be purchased.
	 * If the prices and amounts are equal, method makes the comparison according to the ID of traders.
	 */
	public int compareTo(BuyingOrder e){
		if (e.price > this.price) {
			 
            return 1;
        }
        else if (e.price < this.price) {

            return -1;
        }
        else {
        	if(e.amount > this.amount) {
        		return 1;
        	}else if(e.amount < this.amount) {
        		return -1;
        	}else {
        		if(e.traderID < this.traderID) {
        			return 1;
        		}else {
        			return -1;
        		}
        	}
        }
		
	}
}
