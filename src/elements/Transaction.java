package elements;

/**
 * This class is only used for creating the transaction object to store transactions.
 * @author Selin ISIK
 *
 */
public class Transaction {
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	
	/**
	 * The constructor that creates a transaction(a successful purchase and sale).
	 * @param sellingOrder The selling order that fits with the buying order. 
	 * @param buyingOrder The buying order that fits with the selling order.
	 */
	public Transaction(SellingOrder sellingOrder , BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}

}
