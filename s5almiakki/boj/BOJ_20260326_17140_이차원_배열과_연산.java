import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ_20260326_17140_이차원_배열과_연산 {

    public static class Main {

        //static final String LN = System.lineSeparator();
        //
        //static StringBuilder log = new StringBuilder();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            int targetRow = Integer.parseInt(line[0]) - 1;
            int targetCol = Integer.parseInt(line[1]) - 1;
            int targetValue = Integer.parseInt(line[2]);
            int[][] grid = new int[3][3];
            for (int[] row : grid) {
                line = br.readLine().split(" ");
                for (int col = 0; col < 3; col++) {
                    row[col] = Integer.parseInt(line[col]);
                }
            }

            int answer = -1;
            for (int i = 0; i <= 100; i++) {
                if (!isOutOfBounds(grid, targetRow, targetCol) && grid[targetRow][targetCol] == targetValue) {
                    answer = i;
                    break;
                }
                grid = (grid.length >= grid[0].length) ? doRComputation(grid) : doCComputation(grid);
                //for (int[] row : grid) {
                //    log.append(Arrays.toString(row)).append(LN);
                //}
                //log.append(LN);
            }
            //System.out.println(log);
            System.out.print(answer);
        }

        static boolean isOutOfBounds(int[][] grid, int row, int col) {
            return grid.length <= row || grid[row].length <= col;
        }

        static int[][] doRComputation(int[][] grid) {
            List<Map<Integer, Integer>> numToCountMaps = new ArrayList<>();
            int maxLength = 0;
            for (int row = 0; row < grid.length; row++) {
                Map<Integer, Integer> numToCountMap = new TreeMap<>();
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == 0) {
                        continue;
                    }
                    Integer num = grid[row][col];
                    Integer count = numToCountMap.get(num);
                    if (count == null) {
                        numToCountMap.put(num, 1);
                    } else {
                        numToCountMap.put(num, count + 1);
                    }
                }
                maxLength = Math.max(maxLength, numToCountMap.size() << 1);
                numToCountMaps.add(numToCountMap);
            }
            if (maxLength > 100) {
                maxLength = 100;
            }

            int[][] newGrid = new int[grid.length][maxLength];
            for (int row = 0; row < newGrid.length; row++) {
                int[] newRow = convertToSortedArray(numToCountMaps.get(row), maxLength);
                System.arraycopy(newRow, 0, newGrid[row], 0, newRow.length);
            }
            return newGrid;
        }

        static int[][] doCComputation(int[][] grid) {
            List<Map<Integer, Integer>> numToCountMaps = new ArrayList<>();
            int maxLength = 0;
            for (int col = 0; col < grid[0].length; col++) {
                Map<Integer, Integer> numToCountMap = new TreeMap<>();
                for (int row = 0; row < grid.length; row++) {
                    if (grid[row][col] == 0) {
                        continue;
                    }
                    Integer num = grid[row][col];
                    Integer count = numToCountMap.get(num);
                    if (count == null) {
                        numToCountMap.put(num, 1);
                    } else {
                        numToCountMap.put(num, count + 1);
                    }
                }
                maxLength = Math.max(maxLength, numToCountMap.size() << 1);
                numToCountMaps.add(numToCountMap);
            }
            if (maxLength > 100) {
                maxLength = 100;
            }

            int[][] newGrid = new int[maxLength][grid[0].length];
            for (int col = 0; col < newGrid[0].length; col++) {
                int[] newCol = convertToSortedArray(numToCountMaps.get(col), maxLength);
                for (int row = 0; row < newCol.length; row++) {
                    newGrid[row][col] = newCol[row];
                }
            }
            return newGrid;
        }

        static int[] convertToSortedArray(Map<Integer, Integer> numToCountMap, int maxLength) {
            return numToCountMap.entrySet().stream()
                    .sorted((e1, e2) -> {
                        int value1 = e1.getValue();
                        int value2 = e2.getValue();
                        if (value1 != value2) {
                            return Integer.compare(value1, value2);
                        }
                        return Integer.compare(e1.getKey(), e2.getKey());
                    })
                    .flatMapToInt(e -> IntStream.of(e.getKey(), e.getValue()))
                    .limit(maxLength)
                    .toArray();
        }

    }

}
