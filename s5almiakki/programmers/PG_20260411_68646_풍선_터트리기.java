
public class PG_20260411_68646_풍선_터트리기 {

    class Solution {

        public int solution(int[] a) {
            int[] minFromLeft = new int[a.length];
            int[] minFromRight = new int[a.length];
            minFromLeft[0] = a[0];
            minFromRight[a.length - 1] = a[a.length - 1];
            for (int i = 1; i < a.length; i++) {
                minFromLeft[i] = Math.min(minFromLeft[i - 1], a[i]);
                minFromRight[a.length - 1 - i] = Math.min(minFromRight[a.length - i], a[a.length - 1 - i]);
            }

            int answer = a.length;
            for (int i = 0; i < a.length; i++) {
                if (minFromLeft[i] < a[i] && a[i] > minFromRight[i]) {
                    answer--;
                }
            }
            return answer;
        }

    }

}
