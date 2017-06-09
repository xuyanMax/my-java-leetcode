package basics;


public class StringToInt {

	public static void main(String[] args) {
		int num = 123456;
		int num2 =123;
		String snum = "123456";
		Integer.parseInt(snum);
		Integer.toString(num,3).matches("10*");
		Character.getNumericValue(snum.charAt(0));
		Math.sqrt(2);//expensive cost
		int i=0,j=0;
		int num3 =num>num2?i:j;

	}

}
