import java.io.*;
import java.util.*;

public class BOJ_20260225_13460_구슬_탈출 {

    public static class Main {

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        static int holeRow;
        static int holeCol;

        public static void main(String[] args) throws IOException {
            try (InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr)) {
                String[] input = br.readLine().split(" ");
                int height = Integer.parseInt(input[0]);
                int width = Integer.parseInt(input[1]);

                holeRow = -1;
                holeCol = -1;
                int[][] points = new int[2][2];
                boolean[][] blocked = new boolean[height][width];
                for (int row = 0; row < height; row++) {
                    String line = br.readLine();
                    for (int col = 0; col < width; col++) {
                        char cell = line.charAt(col);
                        switch (line.charAt(col)) {
                            case 'O':
                                holeRow = row;
                                holeCol = col;
                                break;
                            case 'R':
                                points[0][0] = row;
                                points[0][1] = col;
                                break;
                            case 'B':
                                points[1][0] = row;
                                points[1][1] = col;
                                break;
                            default:
                                blocked[row][col] = cell == '#';
                                break;
                        }
                    }
                }

                int[][][][] moveCountHistory = new int[height][width][height][width];
                for (int[][][] i1 : moveCountHistory) {
                    for (int[][] i2 : i1) {
                        for (int[] i3 : i2) {
                            Arrays.fill(i3, 11);
                        }
                    }
                }
                int answer = dfs(blocked, moveCountHistory, points, 0);
                System.out.print(answer == 11 ? -1 : answer);
            }
        }

        static int dfs(
                boolean[][] blocked, int[][][][] moveCountHistory,
                int[][] points, int moveCount) {
            //StringBuilder log = new StringBuilder();
            //log.append('\n');
            //for (int row = 0; row < blocked.length; row++) {
            //    for (int col = 0; col < blocked[row].length; col++) {
            //        if (blocked[row][col]) {
            //            log.append('#');
            //            continue;
            //        }
            //        if (holeRow == row && holeCol == col) {
            //            log.append('O');
            //            continue;
            //        }
            //        int[] point = points[0];
            //        if (point[0] == row && point[1] == col) {
            //            log.append('R');
            //            continue;
            //        }
            //        point = points[1];
            //        if (point[0] == row && point[1] == col) {
            //            log.append('B');
            //            continue;
            //        }
            //        log.append(' ');
            //    }
            //    log.append('\n');
            //}
            //System.out.print(log);

            if (moveCountHistory[points[0][0]][points[0][1]][points[1][0]][points[1][1]] <= moveCount) {
                return 11;
            }
            moveCountHistory[points[0][0]][points[0][1]][points[1][0]][points[1][1]] = moveCount;
            moveCount++;

            int result = 11;
            for (int[] delta : DELTAS) {
                //System.out.println(Arrays.toString(delta));

                // delta가 가리키는 방향에 더 가까운 구슬 계산
                int sum = delta[0] * (points[1][0] - points[0][0]) + delta[1] * (points[1][1] - points[0][1]);
                int[] marbles = new int[2];
                marbles[0] = sum < 0 ? 0 : 1;
                marbles[1] = 1 - marbles[0];

                // 새 구슬 좌표 초기화
                int[][] newPoints = new int[2][2];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        newPoints[i][j] = points[i][j];
                    }
                }

                // 새 구슬 좌표 계산
                boolean redGone = false;
                boolean blueGone = false;
                for (int marble : marbles) {
                    int[] newPoint = newPoints[marble];
                    for (;;) {
                        newPoint[0] += delta[0];
                        newPoint[1] += delta[1];
                        if (holeRow == newPoint[0] && holeCol == newPoint[1]) {
                            // 파란 구슬이 빠지면 안 됨
                            if (marble == 1) {
                                blueGone = true;
                                break;
                            }
                            redGone = true;
                        }
                        int[] otherNewPoint = newPoints[1 - marble];
                        if (blocked[newPoint[0]][newPoint[1]] || newPoint[0] == otherNewPoint[0] && newPoint[1] == otherNewPoint[1]) {
                            //System.out.println("checkpoint");
                            newPoint[0] -= delta[0];
                            newPoint[1] -= delta[1];
                            break;
                        }
                    }
                    if (blueGone) {
                        break;
                    }
                }

                //for (int[] point : newPoints) {
                //    System.out.print(Arrays.toString(point));
                //}
                //System.out.println();

                // 파란 구슬이 빠지면 안 됨
                if (blueGone) {
                    continue;
                }
                // 빨간 구슬이 빠지면 더 안 굴림
                if (redGone) {
                    if (moveCount < result) {
                        result = moveCount;
                    }
                    continue;
                }

                // 구슬 더 굴리기
                int r = dfs(blocked, moveCountHistory, newPoints, moveCount);
                if (r < result) {
                    result = r;
                }
            }
            return result;
        }

    }

}


/*
4 22
######################
#B#...#...#.O.#...#..#
#R..#...#...#...#...##
######################
 */
