package basics;

public class intLongFloatDouble {

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE-(-Integer.MAX_VALUE)); //内存溢出
		int a = 100000*2;
		System.out.println(a);
		System.out.println(Integer.MAX_VALUE); //2的31次方-1,10个数位，正的20亿左右,用在钱上面不一定够 
		System.out.println(Integer.MIN_VALUE); //负的2的31次方 
		System.out.println(Long.MAX_VALUE); //2的64次方-1,19个数位，很大了,可放心用在钱上面 
		System.out.println(Long.MIN_VALUE); //负的2的64次方 
		System.out.println(Float.MAX_VALUE); //2的128次方-1,38个数位，比long多了一倍,这个主要用来做简单数学精确运算使用 
		System.out.println(Float.MIN_VALUE); //2的-149次方 
		System.out.println(Double.MAX_VALUE); //2的1024次方-1,308个数位，是float数位的10倍，主要用来做复杂运算和天文运算 
		System.out.println(Double.MIN_VALUE); //2的-1074次方 


	}

}
