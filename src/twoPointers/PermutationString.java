package twoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 19/08/2017.
 */
/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.

Example 1:

Input:s1 = "ab" s2 = "eidbaooo"

Output:True

Explanation: s2 contains one permutation of s1 ("ba").

Example 2:

Input:s1= "ab" s2 = "eidboaoo"

Output: False

Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
*/
public class PermutationString {
    // 两个HashMap 一个抽象出t中所有char的组合到一个图中 Character->count, e.g., abba->(a:2, b:2)
    // 另一个 seen 记录在范围内遇到的Character个数
    // 同时s2 包含 s1的某种组合，表明sliding window 的长度为s1的长度

// test case 102 导致TLE
// "trfhcbogglim"
//"amwfpqwfwkarvhfcisywzaahtbdbuicxmjseeoudwfcdxetbmacayfikolbdxkocezhalf
// hxabwvuddcyazwiqiwefgolzvrpdxcuskpsmwhslpeygjrvvajajafppcwkqhxwkigemfullbqk
// vuqwfnqnhxiltyfcpfdyumfwyelmtzbdccmbvxedgfimmsqwxmopvxmuonuzyzlhpeunailpydcqybg
// hdwvqxrpautsvrhfxprdqlgzownvincoxjnjwrqrdgpegtgvlifbbautkfqbhqiftbmxadvorqjnqlsc
// euctazxgofmchypspqvwyzoeejqfkvvwftvagajafmafvytslubpzalnahjknarjswkxmzzlmlokrifio
// pjcamvynmmuegnzvveetssuucqclbzxgjwbsflyelpdsvzicdnlenuxggcsrckfdixsqcjrzsbztgvxbpkt
// lbdqrcqoatgxqhwehqiuqjnldursyzplwlcdvwrmlknviqtexwgqovwbcdugdblakufxcapvkvhraacetowtc
// ypfxlvwmwdafesfgqezspbvqzxicblrdsmmdzunpcmzvysgbnspuldkppwlrsrnnewwjquhzrodmsbgbycvrzmtn
// skyuqqoqkxyakojewbbtntbdjuncpgbwgrtvewyscyujnqtpuaulrnjqmdujxydwzfyqfnxmjqogibxqeuqdxsdjj
// pootpzmhcvoeyvdspktyjzadkfwcdulsuktottgpvptjfydvpdxoznzhbkmitaiywqklwrktmzsyndnqmtapaaadzky
// nfxiwqxtekcbkmcwhwwdylvoxosxcexeceavpfptdlkyinhdobrnxfdbtuomjojmzeytlntkundrydqmkiayounnbhfxr
// lriuatzumgfcyniicwhtsaffhnxamwjtgbxvewtgovvrjvblrlvrghyoiicgvyorzjgecmxqeiwpuubfrnkmpynwywqczqd
// peinebgfyrhouvifthoaariadurpbrexbfnuwgkbmgowjuaysnmidptzetckscxvxttdogpywxdvaktmkispgyghfazxyxsl
// yjhqorndzpjepmwiuisfhvacnpkthbfrasndrfkfuhpetlnfugmqhqpvtvlwumhxduxxmugstcbksvqholothhftzungtxdysu
// dnixkzekpdlgddnvyfuitcedxvjfsjxhbcrenufafxzdrumeavumdbvvgpodgtsjzznxkpbfltchmogigordwcpcanomjznfmsxp
// zqgxigjpybooxsgyiuxskowkdpypnzpgebowqefomcpmfilixgzvoffvmcypgyrwhwaelfpclzaoldlaimtnszckziyqewrtewpfyhp
// hxruytifwtodznvxmxwoibqvtmynpqshnmiymrayaenoiknjqzwoltqhaganjdwzkncathqrgcigaguimqgznupmsikurxjltfydqioz
// mddxydgtsvwoloqtlqhryfqmcsfetvtjkauyjgillobotqfhzsyjtcjsiqxhwoaucluagbltdwroayydlwzytpqlsxkbrgcavvaqvlggew
// skeflsejklqexjvcudzaanxrgnkwygokcuxkvypsh"

    public boolean checkInclusion_1 (String t, String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c: t.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        Map<Character, Integer> seen = null;
        int len_s1 = t.length();
        int len_s2 = s.length();
        int count;
        for (int i=0; i<len_s2 - len_s1 + 1; i++) {
            // unmake seen and count
            count = 0;
            seen = new HashMap<>();

            for (int j=0; j<len_s1; j++) {
                if (map.containsKey(s.charAt(j))) {
                    seen.put(s.charAt(j), seen.getOrDefault(s.charAt(j), 0) + 1);
                    if (map.get(s.charAt(j)) >= seen.get(s.charAt(j))) {
                        count++;
                    } else {
                        break;
                    }

                    if (count == len_s1)
                        return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    // 创建好map后，如何确定s2中包含s1的组合形式，首先sliding window 的长度可以确定为s1的长度，
    // 其次 该window从beginning一直移动到s2的末尾，当有新的字符右侧移动进入window时，我们将对应的字符个数减少1；当有的字符从左侧
    // 移出window时，我们将对应的字符个数加上1。每次移动一个单位，移动完毕后调用方法allZeros，判断是否符合。

    public boolean checkInclusion_2(String t, String s) {
        int[] map = new int[26];

        //记录 Character->count 的同时匹配 t 与s.substring(0,t.length);
        for (int i=0; i<t.length(); i++) {
            map[t.charAt(i) - 'a']++;
            map[s.charAt(i) - 'a']--;
        }

        //测试
        if (allZeros(map))
            return true;

        for (int i=t.length(); i<s.length(); i++) {
            // seen 新遇到的char -1
            map[s.charAt(i)-'a']--;
            // seen 左侧window外的 char + 1
            map[s.charAt(i - t.length()) - 'a']++;

            if (allZeros(map))
                return true;
        }
        return false;

    }
    public boolean allZeros(int[] nums) {
        for (int num:nums)
            if (num != 0)
                return false;
        return true;
    }
}
