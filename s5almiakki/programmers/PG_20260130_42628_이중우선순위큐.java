import java.util.*;

public class PG_20260130_42628_이중우선순위큐 {

    class Solution {

        public int[] solution(String[] operations) {
            SortedMap<Integer, Integer> numToCountMap = new TreeMap<>();
            for (String operation : operations) {
                // System.out.println(operation);
                String[] splitted = operation.split(" ");
                Integer key;
                switch (splitted[0]) {
                    case "I":
                        key = Integer.parseInt(splitted[1]);
                        numToCountMap.put(key, numToCountMap.getOrDefault(key, 0) + 1);
                        break;
                    case "D":
                        if (numToCountMap.isEmpty()) {
                            continue;
                        }
                        key = null;
                        switch (splitted[1]) {
                            case "1":
                                key = numToCountMap.lastKey();
                                break;
                            case "-1":
                                key = numToCountMap.firstKey();
                                break;
                        }
                        int value = numToCountMap.get(key);
                        if (value == 1) {
                            numToCountMap.remove(key);
                        } else {
                            numToCountMap.put(key, value - 1);
                        }
                        break;
                }
                // System.out.println(numToCountMap);
            }
            return numToCountMap.isEmpty()
                    ? new int[] { 0, 0 }
            : new int[] { numToCountMap.lastKey(), numToCountMap.firstKey() };
        }

    }

}
