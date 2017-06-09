package dp;

// https://discuss.leetcode.com/topic/5934/is-it-best-solution-with-o-n-o-1
// only 2 transactions
// haven't figured out this

public class bestTimeToBuyAndSellStock3 {

	public static void main(String[] args) {
	
		maxProfit(new int[]{7,1,5,3,4,6});
	}
    public static int maxProfit(int [] prices){
	    int maxProfit1 = 0; 
	    int maxProfit2 = 0; 
	    int lowestBuyPrice1 = Integer.MAX_VALUE;
	    int lowestBuyPrice2 = Integer.MAX_VALUE;
	   
	    for(int p:prices){
	    	maxProfit2 = Math.max(maxProfit2, p-lowestBuyPrice2);
	    	lowestBuyPrice2 = Math.min(lowestBuyPrice2, p-maxProfit1);
	    	maxProfit1 = Math.max(maxProfit1, p-lowestBuyPrice1);
	    	lowestBuyPrice1 = Math.min(lowestBuyPrice1, p);
	    	System.out.print(maxProfit2 + " " +lowestBuyPrice2 +" " +maxProfit1+" " + lowestBuyPrice1+"\n");
	    }
	    return maxProfit2;
    }
}
