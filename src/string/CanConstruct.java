package string;

public class CanConstruct {

	public static void main(String[] args) {
		char a ='a';
		int[] arr = new int[100];
		arr[a]=123;
		int i=0;
		
		for (int n:arr)
			System.out.println(i++ +" "+ n);

		System.out.println(solutionB("aadsda","aaddfas"));
	}
	/**
	 * 建立2个哈希表hash1、hash2，分别统计每个字符串出现的次数
	 * 最后比较hash1、hash2.
	 */
	static boolean solution(String ransomeNote, String magazine) {
		
		if (ransomeNote.length()>magazine.length())
			return false;
		int[] hashA = new int[256];
		int[] hashB = new int[256];
		
		for (int i=0;i<ransomeNote.length();i++) 
			hashA[ransomeNote.charAt(i)]++;
		for (int i=0;i<magazine.length();i++)
			hashB[magazine.charAt(i)]++;
		
		for (int i=0;i<256;i++) {
			if (hashA[i]>hashB[i])
				return false;
		}
		
		return true;
	}
	/*
	 * arr[256]
	 */
	
	static boolean solutionB (String ransomeNote, String magazine) {
		if (ransomeNote.length()>magazine.length())
			return false;
		int[] hashA = new int[256];
		
		for (int i=0;i<magazine.length();i++) 
			hashA[magazine.charAt(i)]++; //与 hashA[ransomeNote.charAt(i)-'a']一样
		for (int i=0;i<ransomeNote.length();i++)
			if(--hashA[ransomeNote.charAt(i)] < 0)
				return false;
		
		return true;
	}
	/*
	 * arr[26]
	 */
	static boolean solutionC (String ransomNote, String magazine) {
		if (ransomNote.length()>magazine.length())
			return false;
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) 
            arr[magazine.charAt(i) - 'a']++;
        
        for (int i = 0; i < ransomNote.length(); i++) 
            if(--arr[ransomNote.charAt(i)-'a'] < 0) 
                return false;
            
        
        return true;
	}
	
	

}
