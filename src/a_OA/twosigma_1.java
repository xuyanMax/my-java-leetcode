package a_OA;

import java.util.*;

public class twosigma_1 {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Integer> list3 = new ArrayList<Integer>();
        List<Integer> list4 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(3);
        list1.add(1);
        list1.add(9866);
        list2.add(2);
        list2.add(1);
        list2.add(2);
        list2.add(5258);
        list3.add(3);
        list3.add(2);
        list3.add(4);
        list3.add(5788);
        list4.add(4);
        list4.add(2);
        list4.add(4);
        list4.add(6000);
        List<List<Integer>> bids = new ArrayList<>();
        bids.add(list1);
        bids.add(list2);
        bids.add(list3);
        bids.add(list4);

        getResults(bids, 3);

    }

    public static List<Integer> getResults(List<List<Integer>> bids, int totalShares) {
        int n = bids.size();
        if (bids == null || n == 0 || bids.get(0).size() == 0)
            return null;

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(n, new BidComparator());

        Map<Integer, List<Integer>> pricesToBidIndex = new HashMap<>();
        Map<Integer, Integer> bidIndexToUserId = new HashMap<>();
        int bidIndex = 0;

        for (List<Integer> bid : bids) {
            queue.add(bid);
            bidIndexToUserId.put(bidIndex, bid.get(0));
            if (pricesToBidIndex.containsKey(bid.get(2)))
                pricesToBidIndex.get(bid.get(2)).add(bidIndex);
            else {
                pricesToBidIndex.put(bid.get(2), new ArrayList<Integer>());
                pricesToBidIndex.get(bid.get(2)).add(bidIndex);
            }

            System.out.println(pricesToBidIndex.get(bid.get(2)));

            bidIndex++;
        }

        System.out.println("queue size " + queue.size());


        List<Integer> results = new ArrayList<>();
        while (!queue.isEmpty() && totalShares > 0) {
            List<Integer> currBid = queue.poll();
            System.out.println("curr id " + currBid.get(0));

            // get users that bid same prices.
            List<Integer> bidIndexes = null;
            if (pricesToBidIndex.containsKey(currBid.get(2))) {
                bidIndexes = pricesToBidIndex.get(currBid.get(2));
            }
            if (bidIndexes != null && bidIndexes.size() > 1) {

                if (bidIndexes.size() < totalShares) {
                    boolean flag = true;
                    for (int bidInd : bidIndexes) {
                        List<Integer> bid = bids.get(bidIndex);
                        bid.set(1, bid.get(1) - 1);
                        totalShares--;
                    }

                }
            } else if (bidIndexes != null && bidIndexes.size() == 1) {
                if (currBid.get(1) <= totalShares) {
                    totalShares -= currBid.get(1);
                } else {
                    totalShares = 0;
                }
                bids.remove(currBid);
                System.out.println("removed - " + currBid.get(0));
            }

        }
        for (List<Integer> bid : bids)
            results.add(bid.get(0));

        return results;
    }

    static class BidComparator implements Comparator<List<Integer>> {
        // uid, share, price, timestamp
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            // descending price
            if (o1.get(2) < o2.get(2))
                return 1;
            else if (o1.get(2) == o2.get(2)) {
                // ascending timestamp
                if (o1.get(3) > o2.get(3))
                    return 1;
                else if (o1.get(3) < o2.get(3))
                    return -1;
                else return 0;
            } else return -1;
        }
    }
}
