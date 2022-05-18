package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import elements.BuyingOrder;
import elements.Market;
import elements.SellingOrder;
import elements.Trader;

/**
 * This class is used for reading the data and processing it in the correct way and finally making necessary 
 * @author Selin ISIK
 *
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		DecimalFormat df = new DecimalFormat("0.00000");
		int A = in.nextInt();
		int B = in.nextInt();
		int C = in.nextInt();
		int D = in.nextInt();
		
		 Random myRandom = new Random(A);
		 Market market = new Market(B);
		 Market.addMarket(market);
		 
		 for (int i=0; i<C;i++) {
			 double dollarAmount = Double.parseDouble(in.next());
			 double PQuinAmount = Double.parseDouble(in.next());
			 Trader trader = new Trader(dollarAmount,PQuinAmount);
			 Trader.traders.add(trader);
		 }
	
		 for (int j=0; j<D; j++) {
			 int query = in.nextInt();
			 if (query == 10) {
				 int id = in.nextInt();
				 double price = Double.parseDouble(in.next());
				 double amount = Double.parseDouble(in.next());
				 Trader.traders.get(id).buy(amount, price, market);
				 
			 }
			 else if (query==11) {
				 int id = in.nextInt();
				 double amount = Double.parseDouble(in.next());
				 if(market.getSellingOrders().peek() != null) {
					 Trader.traders.get(id).buy(amount,market.getSellingOrders().peek().getPrice() , market);
				 }else {
					 Market.increaseNumOfInvalidQueries();
				 }
				 
			 }
			 else if (query==20) {
				 int id = in.nextInt();
				 double price = Double.parseDouble(in.next());
				 double amount = Double.parseDouble(in.next());
				 Trader.traders.get(id).sell(amount, price, market);
				 
			 }
			 else if (query==21) {
				 int id = in.nextInt();
				 double amount = Double.parseDouble(in.next());
				 if(market.getBuyingOrders().peek() != null) {
					 Trader.traders.get(id).sell(amount, market.getBuyingOrders().peek().getPrice(), market);
				 }else {
					 Market.increaseNumOfInvalidQueries();
				 }
			 }
			 else if (query==3) {
				 int id = in.nextInt();
				 double amount = Double.parseDouble(in.next());
				 Trader.traders.get(id).getWallet().setDollars(Trader.traders.get(id).getWallet().getDollars() + amount);
				 
			 }
			 else if (query==4) {
				 int id = in.nextInt();
				 double amount = Double.parseDouble(in.next());
				 if(Trader.traders.get(id).getWallet().getDollars() >= amount ) {
					 Trader.traders.get(id).getWallet().setDollars(Trader.traders.get(id).getWallet().getDollars() - amount);
				 }else {
					 Market.increaseNumOfInvalidQueries();
				 }
			 }
			 else if (query==5) {
				 int id = in.nextInt();
				 out.println("Trader "+id+": "+df.format(Trader.traders.get(id).getWallet().getDollars()+Trader.traders.get(id).getWallet().getBlockedDollars())+"$ "+df.format(Trader.traders.get(id).getWallet().getCoins()+Trader.traders.get(id).getWallet().getBlockedCoins())+"PQ");
				 
			 }
			 else if (query==777) {
				 for(int k=0;k<Trader.traders.size();k++) {
					 Trader.traders.get(k).getWallet().setCoins(Trader.traders.get(k).getWallet().getCoins() + myRandom.nextDouble()*10);
				 }
				 
			 }
			 else if (query==666) {
				 double price = Double.parseDouble(in.next());
				 market.makeOpenMarketOperation(price, Trader.traders);
				 
			 }
			 else if (query==500) {
				 double totalDollarBuying = 0;
				 double totalPQSelling = 0;
				 Iterator<BuyingOrder> dollars = market.getBuyingOrders().iterator();
				 Iterator<SellingOrder> pquins = market.getSellingOrders().iterator();
				  while (dollars.hasNext()) {
					  totalDollarBuying += dollars.next().getAmountandPrice();
			        }
				  while (pquins.hasNext()) {
					  totalPQSelling += pquins.next().getAmount();
			        }
				  out.println("Current market size: "+df.format(totalDollarBuying)+" "+df.format(totalPQSelling));

			 }
			 else if (query==501) {
				 out.println("Number of successful transactions: "+ Market.getTransactions().size());
				 
			 }
			 else if (query==502) {
				 out.println("Number of invalid queries: "+Market.getNumOfInvalidQueries());
				 
			 }
			 else if (query==505) {
				 if(market.getBuyingOrders().peek() == null) {
					 if(market.getSellingOrders().peek()== null) {
						 out.println("Current prices: 0.00000 0.00000 0.00000");
					 }
					 else {
						 out.println("Current prices: 0.00000 "+df.format(market.getSellingOrders().peek().getPrice())+" "+df.format(market.getSellingOrders().peek().getPrice()));
					 }
				 }
				 else {
					 if(market.getSellingOrders().peek()== null) {
						 out.println("Current prices: "+df.format(market.getBuyingOrders().peek().getPrice())+" 0.00000 "+df.format(market.getBuyingOrders().peek().getPrice()));
					 }
					 else {
						 out.println("Current prices: "+df.format(market.getBuyingOrders().peek().getPrice())+" "+df.format(market.getSellingOrders().peek().getPrice())+" "+df.format((market.getBuyingOrders().peek().getPrice()+market.getSellingOrders().peek().getPrice())/2));
					 }
					 
				 }
				 
			 }
			 else if (query==555) {
				 for(int k=0;k<Trader.traders.size();k++) {
					 out.println("Trader "+k+": "+df.format(Trader.traders.get(k).getWallet().getDollars()+Trader.traders.get(k).getWallet().getBlockedDollars())+"$ "+df.format(Trader.traders.get(k).getWallet().getCoins()+Trader.traders.get(k).getWallet().getBlockedCoins())+"PQ");
				 }
				 
			 }
			 	 
			Market.getMarkets().get(0).checkTransactions(Trader.traders);
		 }
	}
}


