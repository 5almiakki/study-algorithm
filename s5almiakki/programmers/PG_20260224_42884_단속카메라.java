import java.util.*;

public class PG_20260224_42884_단속카메라 {

    class Solution {

        public int solution(int[][] routes) {
            Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));
            int prev = Integer.MIN_VALUE;
            int answer = 0;
            for (int[] route : routes) {
                if (prev < route[0]) {
                    prev = route[1];
                    answer++;
                }
            }
            return answer;
        }

    }

}
