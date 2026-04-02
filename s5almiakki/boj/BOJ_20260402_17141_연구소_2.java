import java.io.*;
import java.util.*;

public class BOJ_20260402_17141_연구소_2 {

    public static class Main {

        static final int EMPTY = 0;
        static final int WALL = 1;
        static final int VIRUS = 2;

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            int size = Integer.parseInt(line[0]);
            int virusCount = Integer.parseInt(line[1]);

            int[][] grid = new int[size][size];
            int emptyCellCount = 0;
            List<int[]> virusPoints = new ArrayList<>();
            for (int row = 0; row < size; row++) {
                line = br.readLine().split(" ");
                for (int col = 0; col < size; col++) {
                    grid[row][col] = Integer.parseInt(line[col]);
                    switch (grid[row][col]) {
                        case EMPTY:
                            emptyCellCount++;
                            break;
                        case VIRUS:
                            virusPoints.add(new int[] { row, col });
                            emptyCellCount++;
                            break;
                    }
                }
            }

            //for (int[] row : grid) {
            //    System.out.println(Arrays.toString(row));
            //}

            dfs(virusCount, grid, emptyCellCount, virusPoints, new boolean[virusPoints.size()], Math.min(virusCount, virusPoints.size()), 0, 0);
            System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
        }

        static void dfs(
                int virusCount,
                int[][] grid,
                int emptyCellCount,
                List<int[]> virusPoints,
                boolean[] selected,
                int targetPointCount,
                int selectedPointCount,
                int beginIdx) {
            if (selectedPointCount == targetPointCount) {
                bfs(grid, emptyCellCount, virusPoints, selected);
                return;
            }

            for (int i = selected.length - targetPointCount + selectedPointCount; i >= beginIdx; i--) {
                selected[i] = true;
                dfs(virusCount, grid, emptyCellCount, virusPoints, selected, targetPointCount, selectedPointCount + 1, i + 1);
                selected[i] = false;
            }
        }

        static void bfs(int[][] grid, int emptyCellCount, List<int[]> virusPoints, boolean[] selected) {
            //System.out.println();
            //System.out.println(Arrays.toString(selected));
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[grid.length][grid.length];
            for (int i = 0; i < selected.length; i++) {
                if (!selected[i]) {
                    continue;
                }
                int[] virusPoint = virusPoints.get(i);
                //System.out.print(Arrays.toString(virusPoint));
                queue.add(new int[] { virusPoint[0], virusPoint[1], 0 });
                visited[virusPoint[0]][virusPoint[1]] = true;
                emptyCellCount--;
            }
            //System.out.println();

            int maxDuration = 0;
            do {
                int[] moment = queue.remove();
                if (maxDuration < moment[2]) {
                    maxDuration = moment[2];
                }
                int newDuration = moment[2] + 1;
                for (int[] delta : DELTAS) {
                    int newRow = moment[0] + delta[0];
                    int newCol = moment[1] + delta[1];
                    if (isOutOfBounds(grid, newRow, newCol)
                            || visited[newRow][newCol]
                                    || grid[newRow][newCol] == WALL) {
                        continue;
                    }
                    queue.add(new int[] { newRow, newCol, newDuration });
                    visited[newRow][newCol] = true;
                    emptyCellCount--;
                }
            } while (!queue.isEmpty());

            //System.out.println("emptyCellCount=" + emptyCellCount + ", maxDuration=" + maxDuration);

            if (emptyCellCount == 0 && maxDuration < answer) {
                answer = maxDuration;
            }
        }

        static boolean isOutOfBounds(int[][] grid, int row, int col) {
            return row < 0 || grid.length <= row
                    || col < 0 || grid[row].length <= col;
        }

    }

}
