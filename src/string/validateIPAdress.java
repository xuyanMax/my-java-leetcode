package string;
/**
 * 
 * 
 * @author xu
 * 
 * https://leetcode.com/problems/validate-ip-address/?tab=Description
 * https://discuss.leetcode.com/topic/71444/java-simple-solution/8
 *
 */
public class validateIPAdress {

	public static void main(String[] args) {
		// 测试 ： split -1
		String[] strs0 = "as:sd:".split(":");// len:2 ["as", "sd"]
		String[] strs = ":as:sd:".split(":");// len:3 [" ", "as", "sd"]
		String[] strs2 = ":as:sd:".split(":",-1);//len:4 [" ", "as", "sd", " "]
		
		// 测试 . split -1
		String[] strs3 = ".as.sd.".split("\\.");// len: 3 [" ", "as", "sd"]
		String[] strs4 = ".as.sd.".split("\\.",-1);// len: 4 [" ", "as", "sd", " "]
		
		//输出测试结果
		for (String str:strs)
			System.out.println(str);
		System.out.println(strs.length);//3
		
		for (String str:strs2)
			System.out.println(str);
		System.out.println(strs2.length);//4
		
		System.out.println(strs3.length);
		System.out.println(strs4.length);
		
		System.out.println("1.1.1.1".length());
		
		String s = "001";
		try {
			System.out.println(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			System.out.println("error");
		}
		
		System.out.println(Integer.parseInt("0"));
		for (int i=48;i<=57;i++)
			System.out.print((char)i);
		for (int i=65;i<=70;i++)
			System.out.print((char)i);
		for (int i=97;i<=102;i++)
			System.out.print((char)i);
		System.out.println("");
		validateIPAdress test = new validateIPAdress();
		String IP = "0.0.0.0";
		System.out.println(test.validIPAddress(IP));
		
	}
	
	  public String validIPAddress(String IP) {
	        
		  if (isValidIPv4(IP))
			  return "IPv4";
		  else if (isValidIPv6(IP))
			  return "IPv6";
		  else return "Neither";
		  
		}
	   /*
		 * AT LEAST: 1.1.1.1 （4+3）=7个字符
		 *   典型错误
		 *   .1.1.1   | tokens.len =4 
		 *   1.1.1.1. | tokens.len =5
		 *   1.1.1.   | tokens.len =4
		 *   1.1.1..1 | tokens.len =5
		 *   1.11.233.| tokens.len =4
		 *   
		 *   isValidIPv4Token()
		 *   检测每一个token 在0-255之间
		 *   token 01 不允许（0开头，字符长度大于1不允许）， 0可以 
		 */
		public boolean isValidIPv4(String ip) {
			if (ip.length()<7) return false;
			String[] tokens = ip.split("\\.",-1);// force to include empty string 
			
			if (tokens.length!=4) // 
				return false;
			for (String token:tokens) {
				if (!isValidIPv4Token(token))
					return false;
			}
			return true;
		}
		public boolean isValidIPv4Token(String token) {
			if (token.startsWith("0") && token.length()>1) 
				return false;
			try {
				int tokenInt = Integer.parseInt(token);
				if (tokenInt<0 || tokenInt>255) 
					return false;
				if (token.startsWith("0") && token.charAt(0)!='0')  //"1.1.1.-0" "1.1.1.+0"; Parse int "-0" or "+0" -> 0
					return false;
			} catch (NumberFormatException e) {
				return false;// parseInt " " empty string will cause numberFormatException
			}

			return true;
		}
		/*
		 * at least 15 characters
		 * 1.1.1.1.1.1.1.1 (8+7)=15
		 * tokens[].length == 8 其中包括空string
		 * token contains 0-9 a-e A-E
		 * token length <=4 且不能为空
		 * 
		 */
		
		public boolean isValidIPv6(String ip) {
			if (ip.length()<15) return false;	// at least 15 characters
			String[] tokens = ip.split(":",-1); // force to include empty string
			if (tokens.length!=8) return false; //tokens[].length == 8
			for (String token:tokens) 
				if (!isValidIPv6Token(token)) return false;
			
			return true;
		}
		public boolean isValidIPv6Token(String token) {
			if(token.length()>4 || token.length()==0) return false;
			for (char c: token.toCharArray()){
				// c至少是0-9， ABCDE，abcde 其中一个
				// char 48-57 0-9
				boolean isDigit = c>=48 && c<=57;
				// char 65-70 ABCDE
				boolean isUppercase = c>=65 && c<=70;
				// char 96-102 abcde
				boolean isLowercase = c>=97 && c<=102;
				if (!isDigit && !isUppercase && !isLowercase)
					return false;
			} 
				
			
			return true;
		}

}
