import java.io.*;

public class BOJ_20260202_14499_주사위_굴리기 {

    public static class Main {

        static final String LN = System.lineSeparator();
        static final int[][] ROLL_VECTORS = {
                { 3, 2, 0, 1, 4, 5 }, // 동
                { 2, 3, 1, 0, 4, 5 }, // 서
                { 4, 5, 2, 3, 1, 0 }, // 북
                { 5, 4, 2, 3, 0, 1 }, // 남
        };
        static final int[][] DELTAS = {
                { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 }
        };

        static int height;
        static int width;
        // 상 하 좌 우 앞 뒤
        static int[] dice = { 0, 0, 0, 0, 0, 0 };
        static int[] tempDice = new int[6];
        static int diceRow;
        static int diceCol;
        static int[][] grid;

        public static void main(String[] args) throws IOException {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                String[] input = br.readLine().split(" ");
                height = Integer.parseInt(input[0]);
                width = Integer.parseInt(input[1]);
                diceRow = Integer.parseInt(input[2]);
                diceCol = Integer.parseInt(input[3]);
                int commandCount = Integer.parseInt(input[4]);

                grid = new int[height][width];
                for (int row = 0; row < height; row++) {
                    input = br.readLine().split(" ");
                    for (int col = 0; col < width; col++) {
                        grid[row][col] = Integer.parseInt(input[col]);
                    }
                }

                input = br.readLine().split(" ");
                StringBuilder answer = new StringBuilder();
                for (int i = 0; i < commandCount; i++) {
                    String command = input[i];
                    int direction = Integer.parseInt(command) - 1;

                    boolean rolled = rollDice(direction);
                    if (rolled) {
                        applyGridNum();
                        answer.append(dice[0]).append(LN);
                    }
                }
                System.out.print(answer);
            }
        }

        static boolean rollDice(int direction) {
            int newDiceRow = diceRow + DELTAS[direction][0];
            int newDiceCol = diceCol + DELTAS[direction][1];
            if (isOutOfBounds(newDiceRow, newDiceCol)) {
                return false;
            }

            diceRow = newDiceRow;
            diceCol = newDiceCol;

            int[] rollVector = ROLL_VECTORS[direction];
            for (int from = 0; from < 6; from++) {
                int to = rollVector[from];
                tempDice[to] = dice[from];
            }

            for (int i = 0; i < 6; i++) {
                dice[i] = tempDice[i];
            }
            return true;
        }

        static boolean isOutOfBounds(int row, int col) {
            return row < 0 || height <= row
                    || col < 0 || width <= col;
        }

        static void applyGridNum() {
            if (grid[diceRow][diceCol] == 0) {
                grid[diceRow][diceCol] = dice[1];
                return;
            }
            dice[1] = grid[diceRow][diceCol];
            grid[diceRow][diceCol] = 0;
        }

    }

}
