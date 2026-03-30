import java.util.*;

public class PG_20260330_1839_튜브의_소개팅 {

    class Solution {

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        public int[] solution(int m, int n, int s, int[][] time_map) {
            int[][] grid = time_map;
            int[][] distances = new int[m][n];
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    distances[row][col] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<Moment> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.talkDuration, o2.talkDuration));
            pq.add(new Moment(0, 0, 0, 0));
            distances[0][0] = 0;
            int[] answer = { Integer.MAX_VALUE, Integer.MAX_VALUE };
            do {
                Moment moment = pq.remove();
                if (moment.row == m - 1 && moment.col == n - 1) {
                    if (moment.distance < answer[0]
                            || moment.distance == answer[0] && moment.talkDuration < answer[1]) {
                        answer[0] = moment.distance;
                        answer[1] = moment.talkDuration;
                    }
                    continue;
                }

                int newDistance = moment.distance + 1;
                for (int[] delta : DELTAS) {
                    int newRow = moment.row + delta[0];
                    int newCol = moment.col + delta[1];
                    if (isOutOfBounds(grid, newRow, newCol)
                            || grid[newRow][newCol] == -1
                            || distances[newRow][newCol] <= newDistance
                            || s - grid[newRow][newCol] < moment.talkDuration) {
                        continue;
                    }
                    pq.add(new Moment(newRow, newCol, newDistance, grid[newRow][newCol] + moment.talkDuration));
                    distances[newRow][newCol] = newDistance;
                }
            } while (!pq.isEmpty());

            return answer;
        }

        boolean isOutOfBounds(int[][] grid, int row, int col) {
            return row < 0 || grid.length <= row
                    || col < 0 || grid[row].length <= col;
        }

        static class Moment {

            final int row;
            final int col;
            final int distance;
            final int talkDuration;

            Moment(int row, int col, int distance, int talkDuration) {
                this.row = row;
                this.col = col;
                this.distance = distance;
                this.talkDuration = talkDuration;
            }

        }

    }

}
