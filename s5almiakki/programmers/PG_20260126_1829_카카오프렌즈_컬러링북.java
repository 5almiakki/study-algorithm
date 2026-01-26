import java.util.*;

public class PG_20260126_1829_카카오프렌즈_컬러링북 {

    class Solution {

        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        public int[] solution(int m, int n, int[][] picture) {
            List<Integer> areas = new ArrayList<>();
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[m][n];
            for (int row = 0; row < picture.length; row++) {
                for (int col = 0; col < picture[row].length; col++) {
                    if (visited[row][col] || picture[row][col] == 0) {
                        continue;
                    }
                    areas.add(bfs(picture, queue, visited, row, col));
                }
            }
            areas.sort(Comparator.reverseOrder());
            return new int[] { areas.size(), areas.get(0) };
        }

        private int bfs(int[][] picture, Queue<int[]> queue, boolean[][] visited, int beginRow, int beginCol) {
            int beginColor = picture[beginRow][beginCol];
            int area = 0;
            queue.add(new int[] { beginRow, beginCol });
            visited[beginRow][beginCol] = true;
            do {
                int[] point = queue.remove();
                area++;
                for (int[] direction : DIRECTIONS) {
                    int newRow = point[0] + direction[0];
                    int newCol = point[1] + direction[1];
                    if (!canVisit(visited, newRow, newCol) || beginColor != picture[newRow][newCol]) {
                        continue;
                    }
                    queue.add(new int[] { newRow, newCol });
                    visited[newRow][newCol] = true;
                }
            } while (!queue.isEmpty());
            return area;
        }

        private boolean canVisit(boolean[][] visited, int row, int col) {
            return 0 <= row && row < visited.length
                    && 0 <= col && col < visited[row].length
                    && !visited[row][col];
        }

    }

}
