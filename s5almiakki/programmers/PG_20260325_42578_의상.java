import java.util.*;

public class PG_20260325_42578_의상 {

    class Solution {

        public int solution(String[][] clothes) {
            Map<String, Integer> categoryToCountMap = new HashMap<>();
            for (String[] cloth : clothes) {
                String category = cloth[1];
                categoryToCountMap.put(category, categoryToCountMap.getOrDefault(category, 0) + 1);
            }
            int answer = 1;
            for (int value : categoryToCountMap.values()) {
                answer *= value + 1;
            }
            return answer - 1;
        }

    }

}
