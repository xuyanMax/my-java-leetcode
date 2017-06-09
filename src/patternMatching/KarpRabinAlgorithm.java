package patternMatching;
/**
 * 
 * @author xu
 * 
 * references
 * 
 * https://www.youtube.com/watch?v=H4VrKHVG5qI
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/string/RabinKarpSearch.java
 * 
 *
 */
public class KarpRabinAlgorithm {
	final static long prime = 101;
	
	public static void main(String[] args) {
		
		System.out.println(patternSearch("asdasd".toCharArray(),"das".toCharArray()));
	}
	
	static int patternSearch(char[] text, char[] pattern) {

		int tLength = text.length;
		int pLength = pattern.length;
		
		long pHash = createHash(pattern, pLength);
		long tHash = createHash(text, pLength);// initial tHash is based on the first three chars 
		
		
		for (int i=0;i<tLength-pLength+1;i++) {
			if (tHash == pHash && checkEqual(text, i, i + pLength - 1, pattern))
				return i;

			// if tHash != pHash    0-123
			if (i + pLength < tLength) //a-bcd
				tHash = reCalculateHash(text, tHash, i, i + pLength, pLength);
		}
		// return -1 indicates mismatch.
		return -1;
	}
	/* create hash functions
	 * hash(str) = str[0]*prime^0 + str[1]*prime^1 + str[2]*prime^2...
	 * */
	static long createHash(char[] str, int length) {
		int hashCode = 0;
		
		for (int i=0;i<length;i++) 
			hashCode += str[i] * Math.pow(prime,i);
		
		return hashCode;
	}
	/*
	 * for example, 
	 * pime =3;
	 * a->1
	 * b->2
	 * c->3
	 * d->4
	 * e->5
	 * ...
	 * 
	 * x = oldHash - val(oldChar)
	 * x = x/prime
	 * newHash = x+prime^(len-1)
	 * 
	 * if pattern length - 3
	 * oldHash = a*prime(0)+b*prime(1)+c*prime(2)
	 * x = oldHash-a = b*prime(1)+c*prime(2)
	 * x = x/prime = ( b*prime(1)+c*prime(2) )/prime =b*prime(0)+c*prime(1)
	 * newHash = b*prime(0)+c*prime(1) + d*prime(2)
	 */
	static long reCalculateHash(char[] text, long oldHash, int oldIndex, int newIndex, int patterLength){
		long newHash = oldHash-text[oldIndex];
		newHash = newHash/prime;
		newHash += text[newIndex] * Math.pow(prime, patterLength-1);
		return newHash;
		
	}
	
	static boolean checkEqual(char[]textc, int start, int end, char[]pattern) {
	
		String text = new String(textc);
		if (text.substring(start, end+1).equals(new String(pattern)) )
				return true;
		else
			return false;
	}

}
