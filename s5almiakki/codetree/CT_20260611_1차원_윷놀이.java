import java.io.*;
import java.util.*;

public class CT_20260611_1차원_윷놀이 {

    public class Main {

        static int answer = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            int turnCount = Integer.parseInt(input[0]);
            int boardSize = Integer.parseInt(input[1]);
            int pieceCount = Integer.parseInt(input[2]);
            input = br.readLine().split(" ");
            int[] movements = new int[turnCount];
            for (int i = 0; i < turnCount; i++) {
                movements[i] = Integer.parseInt(input[i]);
            }
            int[] positions = new int[pieceCount];
            Arrays.fill(positions, 1);
            backtrack(boardSize, movements, positions, 0);
            System.out.print(answer);
        }

        static void backtrack(int boardSize, int[] movements, int[] positions, int depth) {
            if (depth == movements.length) {
                int score = 0;
                for (int position : positions) {
                    // System.out.print(position + " ");
                    if (position >= boardSize) {
                        score++;
                    }
                }
                // System.out.println();
                if (answer < score) {
                    answer = score;
                }
                return;
            }
            int movement = movements[depth];
            for (int i = 0; i < positions.length; i++) {
                positions[i] += movement;
                backtrack(boardSize, movements, positions, depth + 1);
                positions[i] -= movement;
            }
        }

    }

}
