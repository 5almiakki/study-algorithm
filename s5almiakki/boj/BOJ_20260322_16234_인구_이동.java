import java.io.*;
import java.util.*;

public class BOJ_20260322_16234_인구_이동 {

    public static class Main {

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] lineInput = br.readLine().split(" ");
            int size = Integer.parseInt(lineInput[0]);
            int minDifference = Integer.parseInt(lineInput[1]);
            int maxDifference = Integer.parseInt(lineInput[2]);
            int[][] grid = new int[size][size];
            for (int row = 0; row < size; row++) {
                lineInput = br.readLine().split(" ");
                for (int col = 0; col < size; col++) {
                    grid[row][col] = Integer.parseInt(lineInput[col]);
                }
            }

            Queue<int[]> queue = new ArrayDeque<>();
            int answer = 1;
            for (;;) {
                boolean[][] visited = new boolean[size][size];
                int groupCount = 0;
                for (int beginRow = 0; beginRow < size; beginRow++) {
                    for (int beginCol = 0; beginCol < size; beginCol++) {
                        if (visited[beginRow][beginCol]) {
                            continue;
                        }
                        groupCount++;
                        List<int[]> points = new ArrayList<>();
                        int popularity = 0;
                        int groupSize = 0;
                        queue.add(new int[] { beginRow, beginCol });
                        visited[beginRow][beginCol] = true;
                        do {
                            int[] point = queue.remove();
                            points.add(point);
                            popularity += grid[point[0]][point[1]];
                            groupSize++;
                            for (int[] delta : DELTAS) {
                                int newRow = point[0] + delta[0];
                                int newCol = point[1] + delta[1];
                                if (isOutOfBounds(size, newRow, newCol) || visited[newRow][newCol]) {
                                    continue;
                                }
                                int difference = Math.abs(grid[newRow][newCol] - grid[point[0]][point[1]]);
                                if (difference < minDifference || maxDifference < difference) {
                                    continue;
                                }
                                queue.add(new int[] { newRow, newCol });
                                visited[newRow][newCol] = true;
                            }
                        } while (!queue.isEmpty());
                        popularity /= groupSize;
                        for (int[] point : points) {
                            grid[point[0]][point[1]] = popularity;
                        }
                    }
                }

                if (groupCount == size * size) {
                    answer--;
                    break;
                }
                if (groupCount == 1) {
                    break;
                }
                answer++;
            }

            System.out.print(answer);
        }

        static boolean isOutOfBounds(int size, int row, int col) {
            return row < 0 || size <= row
                    || col < 0 || size <= col;
        }

    }

}
