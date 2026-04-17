
public class PG_20260417_132265_롤케이크_자르기 {

    class Solution {

        public int solution(int[] topping) {
            int[] toppingToCountMap1 = new int[10001];
            int toppingKindCount1 = 0;
            int[] toppingToCountMap2 = new int[10001];
            int toppingKindCount2 = 0;
            for (int t : topping) {
                toppingToCountMap2[t]++;
                if (toppingToCountMap2[t] == 1) {
                    toppingKindCount2++;
                }
            }

            int answer = 0;
            for (int t : topping) {
                toppingToCountMap1[t]++;
                toppingToCountMap2[t]--;
                if (toppingToCountMap1[t] == 1) {
                    toppingKindCount1++;
                }
                if (toppingToCountMap2[t] == 0) {
                    toppingKindCount2--;
                }
                if (toppingKindCount1 == toppingKindCount2) {
                    answer++;
                }
            }
            return answer;
        }

    }

}
