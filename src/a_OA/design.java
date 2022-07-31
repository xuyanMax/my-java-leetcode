package a_OA;


import java.util.*;

public class design {

    public ArrayList<Integer> invertedIndex(ArrayList<Integer> ID, ArrayList<String> content, String word) {

        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < ID.size(); i++) {
            map.put(content.get(i), ID.get(i));
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (String key : map.keySet()) {
            if (key.contains(word))
                res.add(map.get(key));

        }
        return res;
    }

    List<int[]> SLB = new ArrayList<>();
    Map<Integer, int[]> idToStatus = new HashMap<>();
    ArrayList<Integer> ret = new ArrayList<>();

    public ArrayList<Integer> SLB(ArrayList<ArrayList<Integer>> operators) {

        for (ArrayList<Integer> operation : operators) {
            if (operation.get(0) == 1) {
                add(operation);
            } else if (operation.get(0) == 2) {
                delete(operation);
            } else if (operation.get(0) == 3) {
                select();
            } else if (operation.get(0) == 4) {
                release(operation);
            }
        }
        return ret;
    }

    public void add(ArrayList<Integer> operation) {
        if (idToStatus.containsKey(operation.get(1)))
            return;
        int[] new_item = new int[]{operation.get(1), 0};

        idToStatus.put(operation.get(1), new_item);
        SLB.add(new_item);

    }

    public void delete(ArrayList<Integer> operation) {
        if (idToStatus.containsKey(operation.get(1))) {
            int[] toDel = idToStatus.get(operation.get(1));
            idToStatus.remove(operation.get(1));
            SLB.remove(toDel);
        }

    }

    public void select() {
        for (int[] status : SLB) {
            if (status[1] == 0) {
                status[0] = 1;
                ret.add(status[0]);
                return;
            }
        }
        ret.add(SLB.size());

    }

    public void release(ArrayList<Integer> operation) {
        if (idToStatus.containsKey(operation.get(1))) {
            int[] item = idToStatus.get(operation.get(1));
            item[1] = 0;
        }
    }

    //添加s1, 查询s2...

    public ArrayList<Integer> BloomFilter(ArrayList<String> s1, ArrayList<String> s2, int n) {
        // write code here
        ArrayList<Integer> result = new ArrayList<>();
        int[] bitmap = new int[20000];

        for (int i = 0; i < s1.size(); i++) {
            calculateHash(s1.get(i), n, bitmap, true);
        }

        for (int i = 0; i < s2.size(); i++) {
            if (calculateHash(s2.get(i), n, bitmap, false)) {
                result.add(1);
            } else {
                result.add(0);
            }
        }

        return result;
    }

    public boolean calculateHash(String string, int n, int[] bitmap, boolean insertFlag) {
        boolean flag = true;

        for (int i = 1; i <= n; i++) {
            int key = 0;

            for (int j = 0; j < string.length(); j++) {
                key = ((i * key) + string.charAt(j)) % 15000;
            }
            System.out.println(string + "@@@@" + key);
            if (insertFlag) {
                bitmap[key] = 1;
            } else {
                if (bitmap[key] == 0) {
                    flag = false;
                }
            }
        }

        return flag;
    }

    private final int MOD = 15000;

    public ArrayList<Integer> BloomFilter2(ArrayList<String> s1, ArrayList<String> s2, int n) {
        ArrayList<Integer> ret = new ArrayList<>();
        int[] bitmap = new int[20000];
        for (String str : s1)
            add(str, bitmap, n);
        for (String str : s2) {
            if (contains(str, bitmap, n))
                ret.add(1);
            else ret.add(0);
        }
        return ret;
    }

    public void add(String str, int[] bitmap, int N) {

        boolean flag = true;
        for (int i = 1; i <= N; i++) {
            int key = 0;
            for (int j = 0; j < str.length(); j++)
                key = ((i * key) + str.charAt(j)) % MOD;

            System.out.println(str + "@@@@" + key);
            bitmap[key] = 1;
        }

    }

    public boolean contains(String str, int[] bitmap, int N) {
        for (int i = 1; i < N; i++) {
            int key = 0;
            for (int j = 0; j < str.length(); j++)
                key = ((key * i) + str.charAt(j)) % MOD;
            if (bitmap[key] != 1)
                return false;
        }
        return true;
    }

    //auto complete

    /**
     * 输入：
     * ["Niu Niu","Niu Mei","Niu Neng","Nowcoder"],["Niu", "Now"]
     * 复制
     * 返回值：
     * [["Niu Niu","Niu Mei","Niu Neng"],["Nowcoder"]]
     */
    public ArrayList<ArrayList<String>> typeahead(ArrayList<String> dict, ArrayList<String> strings) {

        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        ArrayList<String> list;
        for (String str : strings) {
            list = new ArrayList<>();
            for (String d : dict) {
                if (d.startsWith(str)) {
                    list.add(d);
                }
            }
            ret.add(new ArrayList<>(list));
        }
        return ret;
    }


    //SP3 某度短网址
    Map<String, String> shortToLong = new HashMap<>();
    Map<Long, String> keyToLong = new HashMap<>();
    public long NUM = 56800235584L;
    char[] keyChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * 输入：
     * ["http:www.baidu.com", "http://www.nowcoder.com"],["http://tiny.urleNm26h"]
     * 复制
     * 返回值：
     * ["http://tiny.urleNm26h","http://tiny.urlJc7hPD","http:www.baidu.com"]
     */
    public ArrayList<String> urlTransformation(ArrayList<String> long_url, ArrayList<String> short_url) {
        ArrayList<String> ret = new ArrayList<>();
        for (String lon : long_url) {
            String shortURI = getShortURI(lon);
            shortToLong.put(shortURI, lon);
            //添加缩略版地址到输出
            ret.add(shortURI);
        }


        for (String sho : short_url) {
            String lon = shortToLong.get(sho);
            //添加正常地址到输出
            ret.add(lon);
        }

        return ret;

    }

    public String getShortURI(String longURI) {
        Long key = 0l;
        char[] chars = longURI.toCharArray();
        for (Character ch : chars)
            key = (key * 64 + ch) % NUM;
        while (keyToLong.containsKey(key)) {
            key = (key + 1) % NUM;
        }
        keyToLong.put(key, longURI);
        return makeKeyToShort(key);
    }

    public String makeKeyToShort(long key) {
        StringBuilder shortURI = new StringBuilder();
        while (key > 0) {
            shortURI.append(keyChar[(int) (key % 62)]);
            key = key / 62;
        }

        while (shortURI.length() < 6)
            shortURI.append("0");
        return "http://tiny.url" + shortURI.reverse().toString();
    }


}
