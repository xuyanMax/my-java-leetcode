package dfs.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 03/08/2017.
 * 638.
 * In LeetCode Store, there are some kinds of items to sell. Each item has a price.
 * <p>
 * However, there are some special offers, and a special offer consists of
 * one or more different kinds of items with a sale price.
 * <p>
 * You are given the each item's price, a set of special offers, and the number we need to buy for each item.
 * The job is to output the lowest price you have to pay for EXACTLY certain items as given,
 * where you could make optimal use of the special offers.
 * <p>
 * Each special offer is represented in the form of an arr, the last number represents the price you need
 * to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.
 * <p>
 * You could use any of special offers as many times as you want.
 * <p>
 * Example 1:
 * Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * Output: 14
 * <p>
 * Explanation:
 * There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 * In special offer 1, you can pay $5 for 3A and 0B
 * In special offer 2, you can pay $10 for 1A and 2B.
 * You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 * <p>
 * Example 2:
 * Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * Output: 11
 * <p>
 * Explanation:
 * The price of A is $2, and $3 for B, $4 for C.
 * You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
 * You want to buy 1A ,2B and 1C: so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
 * You cannot add more items, though only $9 for 2A ,2B and 1C.
 * <p>
 * Note:
 * There are at most 6 kinds of items, 100 special offers.
 * For each item, you need to buy at most 6 of them.
 * You are not allowed to buy more items than you want, even if that would lower the overall price.
 */
public class ShoppingOffers {

    // dfs solution
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

        //当前递归层内的返回值
        int result = Integer.MAX_VALUE;

        // 如果使用special offer
        //一个一个遍历 special offers
        for (List<Integer> list : special) {
            List<Integer> AOffer = list;
            boolean validOffer = true; //通过判断 offer中每一项的个数是否超出needs每一项个数的要求，来判断真假

            // 每一个offer下遍历 needs
            for (int item = 0; item < needs.size(); item++) {
                int remain = needs.get(item) - AOffer.get(item);
                // 更新 needs
                needs.set(item, remain);
                //如果offer中有超出needs数量
                if (validOffer && remain < 0) {
                    validOffer = false;
                    break;
                }
            }

            if (validOffer) {  //只有是validOffer, 才进一步递归
                int offerPrice = AOffer.get(AOffer.size() - 1);
                result = Math.min(result, offerPrice + shoppingOffers(price, special, needs));
            }

            //dfs unmake
            if (validOffer) {
                for (int item = 0; item < needs.size(); item++) {
                    int remain = needs.get(item) + AOffer.get(item);
                    needs.set(item, remain);
                }
            }
        }
        // 如果不使用特殊卷
        // (可能特殊卷无法"凑整")， 对比单个商品按需求购买所需的费用
        int noSpecial = 0;
        for (int i = 0; i < needs.size(); i++)
            noSpecial += needs.get(i) * price.get(i);

        // 返回 = min(使用卷，不使用卷)
        return Math.min(result, noSpecial);
    }

    // visit + dp
    //  子问题 overlapping，记录中间值
    public int shoppingOffers_2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

        // 存储的key 为hashCode(当前的needs对应的各个产品的数目); value 为此needs对应的花费
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        return dfs(price, special, needs, map);
    }

    public int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
                   Map<Integer, Integer> map) {
        int hashKey = hashCode(needs);
        if (map.containsKey(hashKey))
            return map.get(hashKey);

        // 不使用special 所需花费的费用
        int lowestPrice = 0;
        for (int i = 0; i < needs.size(); i++) {
            lowestPrice += needs.get(i) * price.get(i);
        }

        //遍历每一个可能的offer
        for (List<Integer> offer : special) {
            boolean validOffer = true;

            //复制 当前DFS层的copyneeds
            List<Integer> copyneeds = new ArrayList<>(needs);

            for (int j = 0; j < copyneeds.size(); j++) {
                if (copyneeds.get(j) >= offer.get(j)) //判断是否为valid offer 即数量 exactly 不能超
                    copyneeds.set(j, copyneeds.get(j) - offer.get(j));
                else {
                    validOffer = false;
                    break;
                }
            }
            // 如果这个offer是有效的，recursively
            if (validOffer) // min(non offer price, with offer price)
                lowestPrice = Math.min(lowestPrice,
                        dfs(price, special, copyneeds, map) // 这里传入copyneeds
                                + offer.get(offer.size() - 1));
        }
        // 在当前层，所有可能的情况全部遍历完成后，记录
        map.put(hashKey, lowestPrice);
        return lowestPrice;
    }

    public int hashCode(List<Integer> list) {
        int hash = 0;
        for (int num : list)
            hash = hash * 11 + num;
        return hash;
    }


}
