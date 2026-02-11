import java.io.*;
import java.util.*;

public class BOJ_20260211_16985_Maaaaaaaaaze {

    public static class Main {

        static final int[][] DELTAS = {
                { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }
        };

        static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            boolean[][][] enterable = new boolean[5][5][5];
            try (InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr)) {
                for (int panelNum = 0; panelNum < 5; panelNum++) {
                    for (int row = 0; row < 5; row++) {
                        String[] input = br.readLine().split(" ");
                        for (int col = 0; col < 5; col++) {
                            enterable[panelNum][row][col] = input[col].equals("1");
                        }
                    }
                }
            }

            stackDfs(enterable, new boolean[5][][], 0);
            System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
        }

        static void stackDfs(boolean[][][] enterableSrc, boolean[][][] enterableDst, int from) {
            if (from == 5) {
                rotateDfs(enterableDst, 0);
                return;
            }

            for (int to = 0; to < 5; to++) {
                if (enterableDst[to] != null) {
                    continue;
                }
                enterableDst[to] = enterableSrc[from];
                stackDfs(enterableSrc, enterableDst, from + 1);
                enterableDst[to] = null;
            }
        }

        static void rotateDfs(boolean[][][] enterable, int floor) {
            if (floor == 5) {
                answer = Math.min(answer, bfs(enterable));
                return;
            }

            for (int rotationCount = 0; rotationCount < 4; rotationCount++) {
                rotateDfs(enterable, floor + 1);
                rotate(enterable[floor]);
            }
        }

        static int bfs(boolean[][][] enterable) {
            if (!(enterable[0][0][0] && enterable[4][4][4])) {
                return Integer.MAX_VALUE;
            }

            Queue<int[]> queue = new ArrayDeque<>(); // { floor, row, col, costSum }
            boolean[][][] visited = new boolean[5][5][5];
            queue.add(new int[] { 0, 0, 0, 0 });
            visited[0][0][0] = true;

            int minCostSum = Integer.MAX_VALUE;
            do {
                int[] moment = queue.remove();
                for (int[] delta : DELTAS) {
                    int newFloor = moment[0] + delta[0];
                    int newRow = moment[1] + delta[1];
                    int newCol = moment[2] + delta[2];
                    if (isOutOfBounds(newFloor, newRow, newCol)
                            || !enterable[newFloor][newRow][newCol]
                                    || visited[newFloor][newRow][newCol]) {
                        continue;
                    }
                    if (newFloor == 4 && newRow == 4 && newCol == 4) {
                        minCostSum = moment[3] + 1;
                        break;
                    }
                    queue.add(new int[] { newFloor, newRow, newCol, moment[3] + 1 });
                    visited[newFloor][newRow][newCol] = true;
                }
            } while (!queue.isEmpty());
            return minCostSum;
        }

        static void rotate(boolean[][] panel) {
            boolean[][] temp = new boolean[5][5];
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    temp[row][col] = panel[col][5 - row - 1];
                }
            }
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    panel[row][col] = temp[row][col];
                }
            }
        }

        static boolean isOutOfBounds(int floor, int row, int col) {
            return floor < 0 || 5 <= floor
                    || row < 0 || 5 <= row
                    || col < 0 || 5 <= col;
        }

    }

}
