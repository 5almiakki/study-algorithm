
public class PG_20260329_42747_H_Index {

    class Solution {

        public int solution(int[] citations) {
            int[] counts = new int[10001];
            for (int citation : citations) {
                counts[citation]++;
            }
            int answer = 0;
            for (int i = 9999; i >= 0; i--) {
                counts[i] += counts[i + 1];
                if (counts[i] >= i) {
                    answer = i;
                    break;
                }
            }
            return answer;
        }

    }

}
