package bit;

/**
 * Created by xu on 2017/5/28.
 * input number of 32 bits unsigned integer
 */
public class PowerOfFour {
     public static void main(String[] args){

     }
     public boolean sol1(int num){
     //1. greater than 0
     //2. only have one bit in their binary notation,so we use x&(x-1) to delete the lowest '1',
     //   and if then it becomes 0,it proves that there is only one '1' bit
     //3. the only '1' bit should be located at the odd location, for example,16.
     //   It's binary is 00010000.So we can use '0x55555555', 0101, to check if the '1' bit is in the right place
         return num>0 && (num&(num-1))==0 && (num& 0x55555555)!=0;
     }
}
