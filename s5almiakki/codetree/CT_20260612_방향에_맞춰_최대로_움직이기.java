import java.io.*;

public class CT_20260612_방향에_맞춰_최대로_움직이기 {

    public class Main {

        static final int[][] DELTAS = {
            { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 },
            { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }
        };

        static int answer = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int boardSize = Integer.parseInt(br.readLine());
            int[][][] board = new int[boardSize][boardSize][2];
            for (int row = 0; row < boardSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < boardSize; col++) {
                    board[row][col][0] = Integer.parseInt(input[col]);
                }
            }
            for (int row = 0; row < boardSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < boardSize; col++) {
                    board[row][col][1] = Integer.parseInt(input[col]) - 1;
                }
            }

            String[] input = br.readLine().split(" ");
            int beginRow = Integer.parseInt(input[0]) - 1;
            int beginCol = Integer.parseInt(input[1]) - 1;
            backtrack(boardSize, board, beginRow, beginCol, 0);

            System.out.print(answer);
        }

        static void backtrack(int boardSize, int[][][] board, int row, int col, int movementCount) {
            if (answer < movementCount) {
                answer = movementCount;
            }
            // StringBuilder sb = new StringBuilder();
            // for (int i = 0; i < movementCount; i++) {
            //     sb.append("  ");
            // }
            // sb.append("board[").append(row).append("][").append(col).append("]: ")
            //         .append(board[row][col][0]).append(", ").append(board[row][col][1]);
            // System.out.println(sb);
            int currentNum = board[row][col][0];
            int[] delta = DELTAS[board[row][col][1]];
            for (int distance = 1; distance < boardSize; distance++) {
                int newRow = row + delta[0] * distance;
                int newCol = col + delta[1] * distance;
                if (isOutOfBounds(boardSize, newRow, newCol) || currentNum > board[newRow][newCol][0]) {
                    continue;
                }
                backtrack(boardSize, board, newRow, newCol, movementCount + 1);
            }
        }

        static boolean isOutOfBounds(int boardSize, int row, int col) {
            return row < 0 || boardSize <= row
                    || col < 0 || boardSize <= col;
        }

    }

}
