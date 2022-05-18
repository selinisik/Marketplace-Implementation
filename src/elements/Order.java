package elements;

/**
 * This class is used as a superior of BuyingOrders and SellingOrders classes and makes the common operations of both classes. 
 * @author Selin ISIK
 *
 */
public class Order {
	/**
	 * Amount of PQuins to be purchased or sold.
	 */
	double amount;
	/**
	 * The price at which the buyer/seller is willing to buy/sell the PQuins.
	 */
	double price;
	/**
	 * ID of the trader who gives the order.
	 */
	int traderID;
	
	/**
	 * @param traderID ID of the trader who gives the order.
	 * @param amount Amount of PQuins to be purchased or sold.
	 * @param price The price at which the buyer/seller is willing to buy/sell the PQuins.
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public double getPrice() {
		return price;
	}

	/**
	 * This method used for total price calculations.
	 * @return This method returns the product of price and amount value
	 */
	public double getAmountandPrice() {
		return price*amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}


