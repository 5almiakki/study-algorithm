/*
1. 현재 칸이 아직 청소되지 않은 경우,
현재 칸을 청소한다.

2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.

3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
반시계 방향으로 90도 회전한다.
바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
1번으로 돌아간다.
*/

import java.io.*;
// import java.util.Arrays;

public class BOJ_14503_로봇_청소기 {

    public static class Main {

        static final int[][] DELTAS = {
                { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
        };

        public static void main(String[] args) throws IOException {
            try (InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr)) {
                String[] input = br.readLine().split(" ");
                int height = Integer.parseInt(input[0]);
                int width = Integer.parseInt(input[1]);

                input = br.readLine().split(" ");
                int robotRow = Integer.parseInt(input[0]);
                int robotCol = Integer.parseInt(input[1]);
                int robotDirection = Integer.parseInt(input[2]);

                boolean[][] blocked = new boolean[height][width];
                for (int row = 0; row < height; row++) {
                    input = br.readLine().split(" ");
                    for (int col = 0; col < width; col++) {
                        blocked[row][col] = input[col].equals("1");
                    }
                }
                boolean[][] cleaned = new boolean[height][width];

                int answer = 0;
                do {
                    // System.out.print(robotRow + "," + robotCol + ' ');
                    // switch (robotDirection) {
                    //     case 0:
                    //         System.out.println("↑");
                    //         break;
                    //     case 1:
                    //         System.out.println("→");
                    //         break;
                    //     case 2:
                    //         System.out.println("↓");
                    //         break;
                    //     case 3:
                    //         System.out.println("←");
                    //         break;
                    // }
                    // for (int row = 0; row < height; row++) {
                    //     for (int col = 0; col < width; col++) {
                    //         if (row == robotRow && col == robotCol) {
                    //             System.out.print('@');
                    //             continue;
                    //         }
                    //         if (blocked[row][col]) {
                    //             System.out.print('■');
                    //             continue;
                    //         }
                    //         if (!cleaned[row][col]) {
                    //             System.out.print('x');
                    //             continue;
                    //         }
                    //         System.out.print(' ');
                    //     }
                    //     System.out.println();
                    // }

                    // 1
                    if (!cleaned[robotRow][robotCol]) {
                        cleaned[robotRow][robotCol] = true;
                        answer++;
                    }

                    int newRobotDirection = computeNextEmptyDirtyDirection(blocked, cleaned, robotRow, robotCol, robotDirection);

                    // 2
                    if (newRobotDirection == -1) {
                        int newRobotRow = robotRow - DELTAS[robotDirection][0];
                        int newRobotCol = robotCol - DELTAS[robotDirection][1];
                        if (isBlocked(blocked, newRobotRow, newRobotCol)) {
                            break;
                        }
                        robotRow = newRobotRow;
                        robotCol = newRobotCol;
                        continue;
                    }

                    // 3
                    robotDirection = newRobotDirection;
                    // System.out.println("new direction " + robotDirection);
                    // System.out.println("delta " + Arrays.toString(DELTAS[robotDirection]));
                    robotRow += DELTAS[robotDirection][0];
                    robotCol += DELTAS[robotDirection][1];
                } while (true);

                System.out.print(answer);
            }
        }

        static int computeNextEmptyDirtyDirection(boolean[][] blocked, boolean[][] cleaned, int robotRow, int robotCol, int robotDirection) {
            for (int i = 0; i < 4; i++) {
                robotDirection = (robotDirection + 3) % 4;
                int newRow = robotRow + DELTAS[robotDirection][0];
                int newCol = robotCol + DELTAS[robotDirection][1];
                if (isBlocked(blocked, newRow, newCol)) {
                    continue;
                }
                if (!cleaned[newRow][newCol]) {
                    return robotDirection;
                }
            }
            return -1;
        }

        static boolean hasAdjEmptyDirtyCell(boolean[][] blocked, boolean[][] cleaned, int robotRow, int robotCol) {
            for (int[] delta : DELTAS) {
                int row = robotRow + delta[0];
                int col = robotCol + delta[1];
                if (isBlocked(blocked, row, col)) {
                    continue;
                }
                if (!cleaned[row][col]) {
                    return true;
                }
            }
            return false;
        }

        static boolean isBlocked(boolean[][] blocked, int row, int col) {
            return blocked[row][col] || isOutOfBounds(blocked, row, col);
        }

        static boolean isOutOfBounds(boolean[][] grid, int row, int col) {
            return row < 0 || grid.length <= row
                    || col < 0 || grid[row].length <= col;
        }

    }

}
