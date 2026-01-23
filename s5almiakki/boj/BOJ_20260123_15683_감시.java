import java.io.*;
import java.util.*;

public class BOJ_20260123_15683_감시 {

    public static class Main {

        // cctvType, cctvDirection, arrowDirection, dRow/dCol
        private static final int[][][][] DIRECTIONS = {
                {
                    { { -1, 0 } }, { { 1, 0 } }, { { 0, -1 } }, { { 0, 1 } }
                },
                {
                    { { -1, 0 }, { 1, 0 } }, { { 0, -1 }, { 0, 1 } }
                },
                {
                    { { -1, 0 }, { 0, -1 } }, { { -1, 0 }, { 0, 1 } },
                    { { 1, 0 }, { 0, -1 } }, { { 1, 0 }, { 0, 1 } }
                },
                {
                    { { -1, 0 }, { 0, -1 }, { 1, 0 } }, // ㅓ
                    { { -1, 0 }, { 0, 1 }, { 1, 0 } }, // ㅏ
                    { { -1, 0 }, { 0, -1 }, { 0, 1 } }, // ㅗ
                    { { 1, 0 }, { 0, -1 }, { 0, 1 } }, // ㅜ
                },
                {
                    { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }
                }
        };

        private static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            int rowCount = Integer.parseInt(input[0]);
            int colCount = Integer.parseInt(input[1]);
            String[][] grid = new String[rowCount][colCount];
            int zeroCount = 0;
            List<Cctv> cctvs = new ArrayList<>();
            for (int row = 0; row < rowCount; row++) {
                input = br.readLine().split(" ");
                for (int col = 0; col < colCount; col++) {
                    if (input[col].equals("6")) {
                        grid[row][col] = "6";
                        continue;
                    }
                    grid[row][col] = "0";
                    zeroCount++;
                    if (input[col].equals("0")) {
                        continue;
                    }
                    int cctvType = Integer.parseInt(input[col]) - 1;
                    cctvs.add(new Cctv(cctvType, row, col));
                }
            }
            br.close();

            if (cctvs.isEmpty()) {
                System.out.print(zeroCount);
                return;
            }
            dfs(zeroCount, grid, cctvs, cctvs.size() - 1);
            System.out.print(answer);
        }

        private static void dfs(int zeroCount, String[][] grid, List<Cctv> cctvs, int depth) {
            if (depth < 0) {
                computeZeroCount(zeroCount, grid, cctvs);
                return;
            }
            Cctv cctv = cctvs.get(depth);
            for (int[][] arrowDirections : DIRECTIONS[cctv.type]) {
                cctv.arrowDirections = arrowDirections;
                dfs(zeroCount, grid, cctvs, depth - 1);
            }
        }

        private static void computeZeroCount(int zeroCount, String[][] grid, List<Cctv> cctvs) {
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            for (Cctv cctv : cctvs) {
                for (int[] arrowDirection : cctv.arrowDirections) {
                    int row = cctv.row;
                    int col = cctv.col;
                    if (!visited[row][col]) {
                        visited[row][col] = true;
                        zeroCount--;
                    }
                    do {
                        if (isOutOfBounds(grid, row, col) || grid[row][col].equals("6")) {
                            break;
                        }
                        if (!visited[row][col]) {
                            visited[row][col] = true;
                            zeroCount--;
                        }
                        row += arrowDirection[0];
                        col += arrowDirection[1];
                    } while (true);
                }
            }

            answer = Math.min(answer, zeroCount);
        }

        private static boolean isOutOfBounds(String[][] grid, int row, int col) {
            return row < 0 || grid.length <= row
                    || col < 0 || grid[row].length <= col;
        }

        private static class Cctv {

            public final int type;
            public final int row;
            public final int col;
            public int[][] arrowDirections;

            public Cctv(int type, int row, int col) {
                this.type = type;
                this.row = row;
                this.col = col;
            }

        }

    }

}
