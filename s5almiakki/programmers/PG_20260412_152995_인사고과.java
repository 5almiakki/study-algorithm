import java.util.*;

public class PG_20260412_152995_인사고과 {

    class Solution {

        public int solution(int[][] scores) {
            int[] targetScore = scores[0];
            int targetSum = targetScore[0] + targetScore[1];
            Arrays.sort(
                    scores,
                    (o1, o2) -> o1[0] != o2[0]
                            ? Integer.compare(o2[0], o1[0])
                            : Integer.compare(o1[1], o2[1]));
            int answer = 1;
            int maxCoworkerScore = 0;
            for (int[] score : scores) {
                if (score[1] < maxCoworkerScore) {
                    if (score == targetScore) {
                        answer = -1;
                        break;
                    }
                    continue;
                }
                maxCoworkerScore = score[1];
                if (score[0] + score[1] > targetSum) {
                    answer++;
                }
            }
            return answer;
        }

    }


}
