import java.util.*;

public class PG_20260325_67259_카카오_인턴_경주로_건설 {

    class Solution {

        static final int[][] DELTAS = {
                { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }
        };

        public int solution(int[][] board) {
            Queue<int[]> queue = new ArrayDeque<>();
            int[][][] costSums = new int[board.length][board.length][4];
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    Arrays.fill(costSums[row][col], Integer.MAX_VALUE);
                }
            }
            queue.add(new int[] { 0, 0, 1, 0 }); // row, col, direction, costSum
            queue.add(new int[] { 0, 0, 2, 0 }); // row, col, direction, costSum
            costSums[0][0][1] = 0;
            costSums[0][0][2] = 0;
            do {
                int[] moment = queue.remove();
                if (costSums[moment[0]][moment[1]][moment[2]] < moment[3]) {
                    continue;
                }

                // 같은 방향
                addNewMoment(board, queue, costSums, moment, moment[2]);

                // 반시계 방향으로 꺾기
                addNewMoment(board, queue, costSums, moment, (moment[2] + 3) % 4);

                // 시계 방향으로 꺾기
                addNewMoment(board, queue, costSums, moment, (moment[2] + 1) % 4);
            } while (!queue.isEmpty());

            int[] answers = costSums[costSums.length - 1][costSums.length - 1];
            int answer = answers[0];
            for (int direction = 1; direction < 4; direction++) {
                if (answers[direction] < answer) {
                    answer = answers[direction];
                }
            }
            return answer;
        }

        void addNewMoment(
                int[][] board,
                Queue<int[]> queue,
                int[][][] costSums,
                int[] moment,
                int newDirection) {
            int newRow = moment[0] + DELTAS[newDirection][0];
            int newCol = moment[1] + DELTAS[newDirection][1];
            if (isOutOfBounds(board, newRow, newCol) || board[newRow][newCol] == 1) {
                return;
            }
            int newCostSum = moment[3] + (moment[2] == newDirection ? 100 : 600);
            if (costSums[newRow][newCol][newDirection] <= newCostSum) {
                return;
            }
            queue.add(new int[] { newRow, newCol, newDirection, newCostSum });
            costSums[newRow][newCol][newDirection] = newCostSum;
        }

        boolean isOutOfBounds(int[][] board, int row, int col) {
            return row < 0 || board.length <= row
                    || col < 0 || board[row].length <= col;
        }

    }

}
