import java.io.*;
import java.util.*;

public class BOJ_20260411_17142_연구소_3 {

    public static class Main {

        static final int EMPTY = 0;
        static final int WALL = 1;
        static final int VIRUS = 2;
        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        static int activeVirusCount;
        static int totalVirusCount = 0;
        static List<int[]> virusPoints = new ArrayList<>();
        static int gridSize;
        static int[][] grid;
        static int emptyCellCount = 0;
        static int answer = Integer.MAX_VALUE;

        //static StringBuilder log = new StringBuilder();
        //static String LN = System.lineSeparator();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            gridSize = Integer.parseInt(line[0]);
            activeVirusCount = Integer.parseInt(line[1]);
            grid = new int[gridSize][gridSize];
            for (int row = 0; row < gridSize; row++) {
                line = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    grid[row][col] = Integer.parseInt(line[col]);
                    switch (grid[row][col]) {
                        case EMPTY:
                            emptyCellCount++;
                            break;
                        case VIRUS:
                            virusPoints.add(new int[] { row, col });
                            totalVirusCount++;
                    }
                }
            }

            //for (int[] point : virusPoints) {
            //    log.append(Arrays.toString(point));
            //}
            //log.append(LN).append(LN);

            backtrack(new ArrayDeque<>(), 0);

            //System.out.print(log);
            System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
        }

        static void backtrack(Deque<int[]> chosenVirusPoints, int beginIdx) {
            if (chosenVirusPoints.size() == activeVirusCount) {
                //for (int[] point : chosenVirusPoints) {
                //    log.append(Arrays.toString(point));
                //}
                //log.append(LN);
                bfs(chosenVirusPoints);
                return;
            }

            int endIdx = virusPoints.size() - activeVirusCount + chosenVirusPoints.size();
            for (int i = beginIdx; i <= endIdx; i++) {
                chosenVirusPoints.push(virusPoints.get(i));
                backtrack(chosenVirusPoints, i + 1);
                chosenVirusPoints.pop();
            }
        }

        static void bfs(Iterable<int[]> chosenVirusPoints) {
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[gridSize][gridSize];
            for (int[] point : chosenVirusPoints) {
                //log.append(Arrays.toString(point));
                queue.add(new int[] { point[0], point[1], 0 });
                visited[point[0]][point[1]] = true;
            }
            //log.append(LN);

            int maxCost = 0;
            int filledCellCount = 0;
            while (filledCellCount != emptyCellCount && !queue.isEmpty()) {
                int[] moment = queue.remove();
                //log.append("moment: ").append(Arrays.toString(moment)).append(LN);
                int newCost = moment[2] + 1;
                for (int[] delta : DELTAS) {
                    int newRow = moment[0] + delta[0];
                    int newCol = moment[1] + delta[1];
                    if (isOutOfBounds(newRow, newCol) || visited[newRow][newCol] || grid[newRow][newCol] == WALL) {
                        continue;
                    }
                    if (answer <= newCost) {
                        return;
                    }
                    queue.add(new int[] { newRow, newCol, newCost });
                    visited[newRow][newCol] = true;
                    if (grid[newRow][newCol] != VIRUS) {
                        if (maxCost < newCost) {
                            maxCost = newCost;
                        }
                        filledCellCount++;
                    }
                    if (filledCellCount == emptyCellCount) {
                        break;
                    }
                }
            }

            //log.append("filledCell: ").append(filledCellCount).append(LN)
            //        .append("emptyCell: ").append(emptyCellCount).append(LN);
            if (filledCellCount == emptyCellCount && maxCost < answer) {
                answer = maxCost;
            }
        }

        static boolean isOutOfBounds(int row, int col) {
            return row < 0 || gridSize <= row
                    || col < 0 || gridSize <= col;
        }

    }

}
