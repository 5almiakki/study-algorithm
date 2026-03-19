import java.io.*;

public class BOJ_20260317_5373_큐빙 {

    public static class Main {

        static final String LN = System.lineSeparator();

        static final int UP = 0;
        static final int DOWN = 1;
        static final int FRONT = 2;
        static final int BACK = 3;
        static final int LEFT = 4;
        static final int RIGHT = 5;

        // 상 하 전 후 좌 우
        static final char[][][] INITIAL_CUBE = {
                { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } },
                { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } },
                { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } },
                { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } },
                { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } },
                { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } },
        };

        //static final char[][][] INITIAL_CUBE = new char[6][3][3];
        //
        //static {
        //    String[] c = {
        //            "UUU UUU UUU", // UP
        //            "DDD DDD DDD", // DOWN
        //            "FFF FFF FFF", // FRONT
        //            "BBB BBB BBB", // BACK
        //            "LLL LLL LLL", // LEFT
        //            "RRR RRR RRR" // RIGHT
        //    };
        //    for (int side = 0; side < 6; side++) {
        //        String[] s = c[side].split(" ");
        //        for (int row = 0; row < 3; row++) {
        //            for (int col = 0; col < 3; col++) {
        //                INITIAL_CUBE[side][row][col] = s[row].charAt(col);
        //            }
        //        }
        //    }
        //}

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int testCaseCount = Integer.parseInt(br.readLine());
            StringBuilder answer = new StringBuilder();
            char[][][] cube = new char[6][3][3];
            for (int testCaseNum = 0; testCaseNum < testCaseCount; testCaseNum++) {
                initCube(cube);
                br.readLine();
                String[] commands = br.readLine().split(" ");
                for (String command : commands) {
                    rotate(cube, command);
                }
                //for (int side = 0; side < 6; side++) {
                //    writeSide(answer, cube[side]);
                //    answer.append(LN);
                //}
                writeSide(answer, cube[UP]);
            }
            System.out.print(answer);
        }

        static void initCube(char[][][] cube) {
            for (int side = 0; side < 6; side++) {
                for (int row = 0; row < 3; row++) {
                    System.arraycopy(INITIAL_CUBE[side][row], 0, cube[side][row], 0, 3);
                }
            }
        }

        static void rotate(char[][][] cube, String command) {
            char t0;
            char t1;
            char t2;
            switch (command) {
                case "U+":
                    rotateSideClockwise(cube[UP]);
                    // 전 < 우, 우 < 후, 후 < 좌, 좌 < 전
                    t0 = cube[FRONT][0][0];
                    t1 = cube[FRONT][0][1];
                    t2 = cube[FRONT][0][2];
                    for (int i = 0; i < 3; i++) {
                        cube[FRONT][0][i] = cube[RIGHT][0][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[RIGHT][0][i] = cube[BACK][0][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[BACK][0][i] = cube[LEFT][0][i];
                    }
                    cube[LEFT][0][0] = t0;
                    cube[LEFT][0][1] = t1;
                    cube[LEFT][0][2] = t2;
                    break;
                case "U-":
                    rotateSideCounterClockwise(cube[UP]);
                    // 전 < 좌, 좌 < 후, 후 < 우, 우 < 전
                    t0 = cube[FRONT][0][0];
                    t1 = cube[FRONT][0][1];
                    t2 = cube[FRONT][0][2];
                    for (int i = 0; i < 3; i++) {
                        cube[FRONT][0][i] = cube[LEFT][0][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[LEFT][0][i] = cube[BACK][0][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[BACK][0][i] = cube[RIGHT][0][i];
                    }
                    cube[RIGHT][0][0] = t0;
                    cube[RIGHT][0][1] = t1;
                    cube[RIGHT][0][2] = t2;
                    break;
                case "D+":
                    rotateSideClockwise(cube[DOWN]);
                    // 전 < 좌, 좌 < 후, 후 < 우, 우 < 전
                    t0 = cube[FRONT][2][0];
                    t1 = cube[FRONT][2][1];
                    t2 = cube[FRONT][2][2];
                    for (int i = 0; i < 3; i++) {
                        cube[FRONT][2][i] = cube[LEFT][2][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[LEFT][2][i] = cube[BACK][2][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[BACK][2][i] = cube[RIGHT][2][i];
                    }
                    cube[RIGHT][2][0] = t0;
                    cube[RIGHT][2][1] = t1;
                    cube[RIGHT][2][2] = t2;
                    break;
                case "D-":
                    rotateSideCounterClockwise(cube[DOWN]);
                    // 전 < 우, 우 < 후, 후 < 좌, 좌 < 전
                    t0 = cube[FRONT][2][0];
                    t1 = cube[FRONT][2][1];
                    t2 = cube[FRONT][2][2];
                    for (int i = 0; i < 3; i++) {
                        cube[FRONT][2][i] = cube[RIGHT][2][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[RIGHT][2][i] = cube[BACK][2][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[BACK][2][i] = cube[LEFT][2][i];
                    }
                    cube[LEFT][2][0] = t0;
                    cube[LEFT][2][1] = t1;
                    cube[LEFT][2][2] = t2;
                    break;
                case "F+":
                    rotateSideClockwise(cube[FRONT]);
                    // 상 < 좌, 좌 < 하, 하 < 우, 우 < 상
                    t0 = cube[UP][2][0];
                    t1 = cube[UP][2][1];
                    t2 = cube[UP][2][2];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][2][i] = cube[LEFT][2 - i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[LEFT][i][2] = cube[DOWN][0][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][0][i] = cube[RIGHT][2 - i][0];
                    }
                    cube[RIGHT][0][0] = t0;
                    cube[RIGHT][1][0] = t1;
                    cube[RIGHT][2][0] = t2;
                    break;
                case "F-":
                    rotateSideCounterClockwise(cube[FRONT]);
                    // 상 < 우, 우 < 하, 하 < 좌, 좌 < 상
                    t0 = cube[UP][2][0];
                    t1 = cube[UP][2][1];
                    t2 = cube[UP][2][2];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][2][i] = cube[RIGHT][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[RIGHT][i][0] = cube[DOWN][0][2 - i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][0][i] = cube[LEFT][i][2];
                    }
                    cube[LEFT][2][2] = t0;
                    cube[LEFT][1][2] = t1;
                    cube[LEFT][0][2] = t2;
                    break;
                case "B+":
                    rotateSideClockwise(cube[BACK]);
                    // 상 < 우, 우 < 하, 하 < 좌, 좌 < 상
                    t0 = cube[UP][0][0];
                    t1 = cube[UP][0][1];
                    t2 = cube[UP][0][2];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][0][i] = cube[RIGHT][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[RIGHT][i][2] = cube[DOWN][2][2 - i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][2][i] = cube[LEFT][i][0];
                    }
                    cube[LEFT][2][0] = t0;
                    cube[LEFT][1][0] = t1;
                    cube[LEFT][0][0] = t2;
                    break;
                case "B-":
                    rotateSideCounterClockwise(cube[BACK]);
                    // 상 < 좌, 좌 < 하, 하 < 우, 우 < 상
                    t0 = cube[UP][0][0];
                    t1 = cube[UP][0][1];
                    t2 = cube[UP][0][2];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][0][i] = cube[LEFT][2 - i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[LEFT][i][0] = cube[DOWN][2][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][2][i] = cube[RIGHT][2 - i][2];
                    }
                    cube[RIGHT][0][2] = t0;
                    cube[RIGHT][1][2] = t1;
                    cube[RIGHT][2][2] = t2;
                    break;
                case "L+":
                    rotateSideClockwise(cube[LEFT]);
                    // 상 < 후, 후 < 하, 하 < 전, 전 < 상
                    t0 = cube[UP][0][0];
                    t1 = cube[UP][1][0];
                    t2 = cube[UP][2][0];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][i][0] = cube[BACK][2 - i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[BACK][2 - i][2] = cube[DOWN][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][i][0] = cube[FRONT][i][0];
                    }
                    cube[FRONT][0][0] = t0;
                    cube[FRONT][1][0] = t1;
                    cube[FRONT][2][0] = t2;
                    break;
                case "L-":
                    rotateSideCounterClockwise(cube[LEFT]);
                    // 상 < 전, 전 < 하, 하 < 후, 후 < 상
                    t0 = cube[UP][0][0];
                    t1 = cube[UP][1][0];
                    t2 = cube[UP][2][0];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][i][0] = cube[FRONT][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[FRONT][i][0] = cube[DOWN][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][i][0] = cube[BACK][2 - i][2];
                    }
                    cube[BACK][2][2] = t0;
                    cube[BACK][1][2] = t1;
                    cube[BACK][0][2] = t2;
                    break;
                case "R+":
                    rotateSideClockwise(cube[RIGHT]);
                    // 상 < 전, 전 < 하, 하 < 후, 후 < 상
                    t0 = cube[UP][0][2];
                    t1 = cube[UP][1][2];
                    t2 = cube[UP][2][2];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][i][2] = cube[FRONT][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[FRONT][i][2] = cube[DOWN][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][i][2] = cube[BACK][2 - i][0];
                    }
                    cube[BACK][2][0] = t0;
                    cube[BACK][1][0] = t1;
                    cube[BACK][0][0] = t2;
                    break;
                case "R-":
                    rotateSideCounterClockwise(cube[RIGHT]);
                    // 상 < 후, 후 < 하, 하 < 전, 전 < 상
                    t0 = cube[UP][0][2];
                    t1 = cube[UP][1][2];
                    t2 = cube[UP][2][2];
                    for (int i = 0; i < 3; i++) {
                        cube[UP][i][2] = cube[BACK][2 - i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[BACK][2 - i][0] = cube[DOWN][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[DOWN][i][2] = cube[FRONT][i][2];
                    }
                    cube[FRONT][0][2] = t0;
                    cube[FRONT][1][2] = t1;
                    cube[FRONT][2][2] = t2;
                    break;
            }
        }

        static void rotateSideClockwise(char[][] side) {
            char t = side[0][0];
            side[0][0] = side[2][0];
            side[2][0] = side[2][2];
            side[2][2] = side[0][2];
            side[0][2] = t;
            t = side[0][1];
            side[0][1] = side[1][0];
            side[1][0] = side[2][1];
            side[2][1] = side[1][2];
            side[1][2] = t;
        }

        static void rotateSideCounterClockwise(char[][] side) {
            char t = side[0][0];
            side[0][0] = side[0][2];
            side[0][2] = side[2][2];
            side[2][2] = side[2][0];
            side[2][0] = t;
            t = side[0][1];
            side[0][1] = side[1][2];
            side[1][2] = side[2][1];
            side[2][1] = side[1][0];
            side[1][0] = t;
        }

        static void writeSide(StringBuilder sb, char[][] side) {
            for (char[] row : side) {
                for (char cell : row) {
                    sb.append(cell);
                }
                sb.append(LN);
            }
        }

    }

}
