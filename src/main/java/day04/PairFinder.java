package day04;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PairFinder {

    public int findPairsAdamsVersion(int[]arr) {
        Map<Integer, Integer> pairs = new TreeMap<>();
        for (int actual : arr){
            if(!pairs.containsKey(actual)){
                pairs.put(actual,1);
            }else {
                pairs.put(actual, pairs.get(actual)+1);
            }
        }
        return pairs
                .values()
                .stream()
                .mapToInt(i->i/2)
                .sum();
    }
    public static void main(String[] args) {
        PairFinder pairFinder = new PairFinder();
        System.out.println(pairFinder.findPairsAdamsVersion(new int[]{7, 1, 5, 7, 3, 3, 5, 7, 6, 7}));
    }


}
