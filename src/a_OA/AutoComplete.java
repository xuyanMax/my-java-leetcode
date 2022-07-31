package a_OA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoComplete {

    public ArrayList<ArrayList<String>> typeahead(ArrayList<String> dict, ArrayList<String> strings) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        List<String> list = new ArrayList<>();

        for (String str : strings) {
            for (String d : dict) {
                if (d.contains(str))
                    list.add(d);
            }
//            res.add(new ArrayList<>(list));//wrong
            res.add(new ArrayList(list));
            list.clear();
        }

        return res;
    }

    public static void loadMap(ArrayList<String> dict, ArrayList<String> strings) {
        for (String str : strings) {
            for (int i = 0; i < dict.size(); i++) {
                if (str.contains(" ")) {
                    String sub_str = dict.get(i).split(" ")[0];
                    if (str.startsWith(sub_str)) {
                        keyToIndex.put(str, i);
                    }
                } else {
                    keyToIndex.put(str, i);
                }
            }
        }
    }

    public static Map<String, Integer> keyToIndex = new HashMap<>();

    public static ArrayList<ArrayList<String>> typeahead_2(ArrayList<String> dict, ArrayList<String> strings) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();


        ArrayList<String> list = new ArrayList<>();
        for (String str : strings) {
            if (keyToIndex.containsKey(str)) {
                list.add(dict.get(keyToIndex.get(str)));
            }
            res.add(new ArrayList(list));
            list.clear();
        }
        return res;
    }
}
