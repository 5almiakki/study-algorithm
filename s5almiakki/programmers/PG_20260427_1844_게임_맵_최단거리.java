import java.util.*;

public class PG_20260427_1844_게임_맵_최단거리 {

    class Solution {

        public int solution(int[][] maps) {
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[maps.length][maps[0].length];
            queue.add(new int[] { 0, 0, 1 });
            visited[0][0] = true;
            int[][] deltas = {
                    { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }
            };
            do {
                int[] moment = queue.remove();
                int newCost = moment[2] + 1;
                for (int[] delta : deltas) {
                    int newRow = moment[0] + delta[0];
                    int newCol = moment[1] + delta[1];
                    if (isOutOfBounds(maps, newRow, newCol)
                            || maps[newRow][newCol] == 0
                            || visited[newRow][newCol]) {
                        continue;
                    }
                    if (newRow == maps.length - 1 && newCol == maps[newRow].length - 1) {
                        return newCost;
                    }
                    queue.add(new int[] { newRow, newCol, newCost });
                    visited[newRow][newCol] = true;
                }
            } while (!queue.isEmpty());
            return -1;
        }

        boolean isOutOfBounds(int[][] map, int row, int col) {
            return row < 0 || map.length <= row
                    || col < 0 || map[row].length <= col;
        }

    }

}
