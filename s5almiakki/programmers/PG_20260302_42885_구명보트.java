import java.util.*;

public class PG_20260302_42885_구명보트 {

    class Solution {

        public int solution(int[] people, int limit) {
            Arrays.sort(people);
            int minIdx = 0;
            int maxIdx = people.length - 1;
            int answer = 0;
            do {
                answer++;
                int min = people[minIdx];
                int max = people[maxIdx];
                maxIdx--;
                if (min + max <= limit) {
                    minIdx++;
                }
            } while (minIdx < maxIdx);
            if (minIdx == maxIdx) {
                answer++;
            }
            return answer;
        }

    }

}
