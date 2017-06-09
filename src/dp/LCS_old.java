package dp;

import java.util.Iterator;
import java.util.Stack;

public class LCS_old {

	private String a;
	private String b;
	private int[][]c;
	private String[][] flag;
	
	public LCS_old(String a, String b){
	
		this.a = a;
		this.b = b;
		c = new int[a.length()+1][b.length()+1];
		flag = new String[a.length()+1][b.length()+1];
	}
	
	public void LCS_Compute(){
		
		int n = a.length();
		int m = b.length();
		
		//初始化边界
		for (int i=0;i<=n;i++)
			c[i][0] =0;
		for (int j=0;j<=m;j++)
			c[0][j]=0;
		
		for(int i=1;i<=n;i++){			
			for(int j=1;j<=m;j++){
				if(a.charAt(i-1) == b.charAt(j-1))
					c[i][j] = c[i-1][j-1] + 1;
				else 
					c[i][j] = Math.max(c[i-1][j], c[i][j-1]);
			}
		}
		
	}

	// trace back LCS
	public void LCS_Compute_flag(){
		
		int n = a.length();
		int m = b.length();
		
		//初始化边界
		for (int i=0;i<=n;i++){
			c[i][0] =0;
			flag[i][0] = "";
		}
		for (int j=0;j<=m;j++) {
			c[0][j]=0;
			flag[0][j]= "";
		}
		
		for(int i=1;i<=n;i++){
			
			for(int j=1;j<=m;j++){
				
				if(a.charAt(i-1) == b.charAt(j-1)){
					
					c[i][j] = c[i-1][j-1] + 1;
					flag[i][j] = "left_up";
					
				}else if (c[i-1][j] >= c[i][j-1]){
					c[i][j]= c[i-1][j];
					flag[i][j]="up";
				}
				else if(c[i-1][j]<c[i][j-1]) { 
					c[i][j]=c[i][j-1];
					flag[i][j]="left";
				}
			}
		}
		
	}
	public void  display_LCS(int i, int j){
		Stack<String> output = new Stack<String>();
		
		if (i==1 || j ==1 ){
			return;
		}
		if(flag[i][j].equals("left_up")){
			
			output.push(String.valueOf(a.charAt(i-1)));
			display_LCS(i-1, j-1);
			
		}
		if (flag[i][j].equals("left")) {
			display_LCS(i, j-1);
		}
		if(flag[i][j].equals("up")) {
			display_LCS(i-1, j);
		}
//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝		
		Iterator<String> it = output.iterator();
		while(it.hasNext()) {
			System.out.print(it.next());
		}
	}
	public int[][] getC(){
		return c;
	}
	
	public String[][] getFlag(){
		return flag;
	}
	public static void main(String[] args) {
		
		String a = "bblong";
		String b = "cnbbongs";
		
		LCS_old lcs = new LCS_old(a, b);
//		lcs.LCS_Compute();
//		int[][] c = lcs.getC();
//		
//		for(int i=0;i<=a.length();i++) {
//			for (int j=0;j<=b.length();j++){
//				System.out.print(c[i][j]+" ");
//			}
//			System.out.println("");
//		}
		lcs.LCS_Compute_flag();
		String[][] flag = lcs.getFlag();
		for(int i=0;i<=a.length();i++) {
			for (int j=0;j<=b.length();j++){
			System.out.print(String.format("%7c", flag[i][j]));
		}
		System.out.println("");
	}
		lcs.display_LCS(a.length(),b.length());

	}

}
