import java.util.*;

public class PG_20260309_67258_카카오_인턴_보석_쇼핑 {

    class Solution {

        public int[] solution(String[] gems) {
            Set<String> uniqueGems = new HashSet<>();
            for (String gem : gems) {
                uniqueGems.add(gem);
            }
            int uniqueGemCount = uniqueGems.size();
            // System.out.println("uniqueGemCount=" + uniqueGemCount);

            GemToCountMap gemToCountMap = new GemToCountMap();
            gemToCountMap.add(gems[0]);
            int[] answer = new int[2];
            int low = 0;
            int high = 0;
            int minRangeLength = gems.length;
            for (;;) {
                // System.out.println();
                // System.out.println("[" + low + ' ' + high + "]");
                // for (int i = low; i <= high; i++) {
                //     System.out.print(gems[i] + ' ');
                // }
                // System.out.println();
                // System.out.println(gemToCountMap.gemToCountMap);
                int currentUniqueGemCount = gemToCountMap.size();
                if (currentUniqueGemCount < uniqueGemCount) {
                    high++;
                    if (high == gems.length) {
                        break;
                    }
                    gemToCountMap.add(gems[high]);
                    continue;
                }

                if (high - low < minRangeLength) {
                    minRangeLength = high - low;
                    answer[0] = low;
                    answer[1] = high;
                }
                // System.out.println("range=" + Arrays.toString(answer));
                gemToCountMap.remove(gems[low]);
                low++;
                if (low > high) {
                    break;
                }
            }

            answer[0]++;
            answer[1]++;
            return answer;
        }

        static class GemToCountMap {

            final Map<String, Integer> gemToCountMap = new HashMap<>();

            void add(String gem) {
                gemToCountMap.put(gem, gemToCountMap.getOrDefault(gem, 0) + 1);
            }

            void remove(String gem) {
                Integer count = gemToCountMap.get(gem);
                if (count == null) {
                    return;
                }
                if (count == 1) {
                    gemToCountMap.remove(gem);
                    return;
                }
                gemToCountMap.put(gem, count - 1);
            }

            int size() {
                return gemToCountMap.size();
            }

        }

    }

}
