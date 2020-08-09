package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */



        Map<Integer, Integer> bucket2Num = new HashMap<>();
        for (Oomage oomage : oomages) {
            int hashCode = oomage.hashCode();
            int bucket = (hashCode & 0x7FFFFFFF) % M;
            if (bucket2Num.containsKey(bucket)) {
                int num = bucket2Num.get(bucket);
                bucket2Num.put(bucket, num + 1);
            } else {
                bucket2Num.put(bucket, 1);
            }
        }

        int N = oomages.size();
        for (int bucket : bucket2Num.keySet()) {
            int num = bucket2Num.get(bucket);
            if (num >= N / 2.5 || num <= N / 50) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // hash map only has one key and one value, cannot have duplicate keys

        Map<Integer, Integer> bucket2Num = new HashMap<>();

        bucket2Num.put(1,1);
        bucket2Num.put(1,2);
        bucket2Num.put(1,3);

        // result ---> 1 key, 1 value (value = 3) has been updated.
    }
}
