package elements;

/**
 * This class is used for creating selling orders and sorting them in correct way.
 * @author Selin ISIK
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{

	/**
	 * @param traderID ID of the trader who gives the selling order.
	 * @param amount Amount of PQuins to be sold.
	 * @param price The price at which the seller is willing to sell the PQuins.
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
		
	}
	/**
	 *This compare to method sorts the selling orders according to their prices.
	 * If the prices are equal, method makes the comparison according to the amount of PQuins to be sold.
	 * If the prices and amounts are equal, method makes the comparison according to the ID of traders.
	 */
	public int compareTo(SellingOrder e){
		if (e.price < this.price ) {
			 
            return 1;
        }
        else if (e.price > this.price) {

            return -1;
        }
        else {
        	if(e.amount > this.amount) {
        		return 1;
        	}else if (e.amount < this.amount) {
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
