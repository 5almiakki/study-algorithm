import java.util.*;

public class PG_20260224_12987_숫자_게임 {

    class Solution1 {

        public int solution(int[] A, int[] B) {
            NavigableMap<Integer, Integer> bToCountMap = new TreeMap<>();
            for (Integer b : B) {
                bToCountMap.put(b, bToCountMap.getOrDefault(b, 0) + 1);
            }

            int answer = 0;
            for (int a : A) {
                Integer b = bToCountMap.ceilingKey(a + 1);
                if (b != null) {
                    decrement(bToCountMap, b);
                    answer++;
                }
            }

            return answer;
        }

        void decrement(Map<Integer, Integer> map, Integer key) {
            int count = map.get(key);
            if (count == 1) {
                map.remove(key);
            } else {
                map.put(key, count - 1);
            }
        }

    }

    class Solution {

        public int solution(int[] A, int[] B) {
            Arrays.sort(A);
            Arrays.sort(B);
            int highestIdx = B.length - 1;
            int answer = 0;
            for (int i = A.length - 1; i >= 0; i--) {
                if (A[i] < B[highestIdx]) {
                    highestIdx--;
                    answer++;
                }
            }
            return answer;
        }

    }

}
