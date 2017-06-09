package string;

import java.util.HashMap;
/**
 * 
 * @author xu
 * 
 * https://leetcode.com/problems/integer-to-roman/?tab=Description
 * https://discuss.leetcode.com/topic/533/how-to-improve-code/46
 *
 */

public class Integer2Roman {
	
	private static HashMap<Integer, String> map = new HashMap<>();
	static {
		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");
		
		
		
		
	}

	public static void main(String[] args) {
		System.out.println(solution(3999));
	}
	
	/**
	 * 1000 M
	 * 900 CM
	 * 500 D
	 * 400 CD
	 * 100 C
	 * 90 XC
	 * 50 L
	 * 40 XL
	 * 10 X
	 * 9 IX
	 * 5 V
	 * 4 IV
	 * 1 I
	 */
	static String solution(int num) {
		
		if (num>=1000) return map.get(1000)+solution(num-1000);
		else if (num>=900) return map.get(900)+solution(num-900);
		else if (num>=500) return map.get(500)+solution(num-500);
		else if (num>=400) return map.get(400)+solution(num-400);
		else if (num>=100) return map.get(100)+solution(num-100);
		else if (num>=90) return map.get(90)+solution(num-90);
		else if (num>=50) return map.get(50)+solution(num-50);
		else if (num>=40) return map.get(40)+solution(num-40);
		else if (num>=10) return map.get(10)+solution(num-10);
		else if (num>=9) return map.get(9)+solution(num-9);
		else if (num>=5) return map.get(5)+solution(num-5);
		else if (num>=4) return map.get(4)+solution(num-4);
		else if (num>=1) return map.get(1)+solution(num-1);
		
		return "";
	}
    static String intToRoman(int num) {
        if(num>=1000) return "M"+intToRoman(num-1000);
        else if(num>=900) return "CM"+intToRoman(num-900);
        else if(num>=500) return "D"+intToRoman(num-500);
        else if(num>=400) return "CD"+intToRoman(num-400);
        else if(num>=100) return "C"+intToRoman(num-100);
        else if(num>=90) return "XC"+intToRoman(num-90);
        else if(num>=50) return "L"+intToRoman(num-50);
        else if(num>=40) return "XL"+intToRoman(num-40);
        else if(num>=10) return "X"+intToRoman(num-10);
        else if(num>=9) return "IX"+intToRoman(num-9);
        else if(num>=5) return "V"+intToRoman(num-5);
        else if(num>=4) return "IV"+intToRoman(num-4);
        else if(num>=1) return "I"+intToRoman(num-1);
        return "";
    }

}
