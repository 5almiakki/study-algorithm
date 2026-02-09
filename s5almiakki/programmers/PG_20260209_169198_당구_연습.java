import java.util.*;

public class PG_20260209_169198_당구_연습 {

    class Solution {

        public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
            int[] answer = new int[balls.length];
            Arrays.fill(answer, Integer.MAX_VALUE);
            for (int i = 0; i < balls.length; i++) {
                if (startY != balls[i][1] || startX < balls[i][0]) {
                    updateAnswer(answer, i, startX, startY, -balls[i][0], balls[i][1]);
                }
                if (startX != balls[i][0] || startY < balls[i][1]) {
                    updateAnswer(answer, i, startX, startY, balls[i][0], -balls[i][1]);
                }
                int diff = m - balls[i][0];
                if (startY != balls[i][1] || startX > balls[i][0]) {
                    updateAnswer(answer, i, startX, startY, m + diff, balls[i][1]);
                }
                diff = n - balls[i][1];
                if (startX != balls[i][0] || startY > balls[i][1]) {
                    updateAnswer(answer, i, startX, startY, balls[i][0], n + diff);
                }
            }
            return answer;
        }

        void updateAnswer(int[] answer, int idx, int startX, int startY, int targetX, int targetY) {
            int distanceSquare = computeDistanceSquare(startX, startY, targetX, targetY);
            answer[idx] = Math.min(answer[idx], distanceSquare);
        }

        int computeDistanceSquare(int x1, int y1, int x2, int y2) {
            int dx = x1 - x2;
            int dy = y1 - y2;
            return dx * dx + dy * dy;
        }

    }

}
