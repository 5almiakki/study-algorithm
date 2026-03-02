import java.util.*;

public class PG_20260302_138476_귤_고르기 {

    class Solution {

        public int solution(int k, int[] tangerine) {
            Map<Integer, Integer> sizeToCountMap = new HashMap<>();
            for (Integer size : tangerine) {
                sizeToCountMap.put(size, sizeToCountMap.getOrDefault(size, 0) + 1);
            }

            int[] counts = sizeToCountMap.values().stream()
                    .mapToInt(i -> i)
                    .sorted()
                    .toArray();

            int answer = 0;
            for (int i = counts.length - 1; i >= 0; i--) {
                answer++;
                k -= counts[i];
                if (k <= 0) {
                    break;
                }
            }

            return answer;
        }

    }

}
