package string;

/**
 * Created by xu on 07/09/2017.
 */
public class ReverseString_1 {


    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        for (int i=0; i<s.length(); i++){
            if (chars[i] != ' ') {
                int j = i + 1;
                while (j < s.length() && chars[j] != ' ')
                    j++;

                //j 回到末尾，而不是即将探索的位置
                j--;
                reverse(chars, i, j);
                i = j;
            }
        }
        return String.valueOf(chars);
//        return new String(chars);
    }
    public void reverse(char[]chars, int left, int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
//        for (; left<right; left++, right--){
//            char tmp = chars[left];
//            chars[left] = chars[right];
//            chars[right] = tmp;
//        }
    }


    public String reverseWords_2(String s) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        String space = "";
        for(String str: strs){
            sb.append(space);
            sb.append(reverse(str));
            space = " ";
        }
        return sb.toString();
    }

    String reverse(String str){
        char[] sc = str.toCharArray();
        int s = 0, e = sc.length - 1;
        while(s < e){
            char t = sc[s];
            sc[s] = sc[e];
            sc[e] = t;
            s++;
            e--;
        }
        return new String(sc);
    }
}
