package patternMatching;
public class SimpleMatch {
	private String tString;
	private String sString;
	//简单的模式匹配算法
	public SimpleMatch(String S, String T) {
		this.tString = T;
		this.sString = S;
		
	}
	public int index(int pos) {
		int i=pos;
		int j=0;
		while(i<this.sString.length() && j<this.tString.length()) {
		if(sString.charAt(i) == tString.charAt(j)) {
			i++;j++;
		}
		else {
			i = i-j+1;// i go back to the (sString) next of the previous match start.
			j=0; // j go back to the start of tString
		}
		
		}
		if(j>=tString.length()) return i-tString.length();
		else return 0;
		
	}
	
	public static void main(String[] args) {
		String S = new String("goodgoogle");
		String T = new String("google");
		SimpleMatch sm = new SimpleMatch(S, T);
		System.out.println(sm.index(1));
		

	}

}
