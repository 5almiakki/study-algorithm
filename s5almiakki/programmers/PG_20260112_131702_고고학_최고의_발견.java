
public class PG_20260112_131702_고고학_최고의_발견 {

    class Solution {

        private static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        private int answer = Integer.MAX_VALUE;

        public int solution(int[][] clockHands) {
            dfs(clockHands, 0, 0);
            return answer;
        }

        private void dfs(int[][] clockHands, int col, int modifyCount) {
            if (modifyCount >= answer) {
                return;
            }

            if (col == clockHands[0].length) {
                int[][] clockHandsCopy = new int[clockHands.length][clockHands.length];
                for (int row = 0; row < clockHands.length; row++) {
                    System.arraycopy(clockHands[row], 0, clockHandsCopy[row], 0, clockHands[row].length);
                }
                computeTotalModifyCount(clockHandsCopy, modifyCount);
                return;
            }

            for (int i = 0; i < 4; i++) {
                dfs(clockHands, col + 1, modifyCount + i);
                modify(clockHands, 0, col);
            }
        }

        private void computeTotalModifyCount(int[][] clockHands, int modifyCount) {
            for (int row = 1; row < clockHands.length; row++) {
                for (int col = 0; col < clockHands[row].length; col++) {
                    int upCell = clockHands[row - 1][col];
                    if (upCell == 0) {
                        continue;
                    }
                    for (int i = upCell; i < 4; i++) {
                        modify(clockHands, row, col);
                        modifyCount++;
                        if (modifyCount >= answer) {
                            return;
                        }
                    }
                }
            }

            for (int row = 0; row < clockHands.length; row++) {
                for (int c = 0; c < clockHands.length; c++) {
                    if (clockHands[row][c] != 0) {
                        return;
                    }
                }
            }

            answer = modifyCount;
        }

        private void modify(int[][] clockHands, int row, int col) {
            clockHands[row][col] = (clockHands[row][col] + 1) % 4;
            for (int[] delta : DELTAS) {
                int newRow = row + delta[0];
                int newCol = col + delta[1];
                if (!isOutOfBounds(clockHands, newRow, newCol)) {
                    clockHands[newRow][newCol] = (clockHands[newRow][newCol] + 1) % 4;
                }
            }
        }

        private boolean isOutOfBounds(int[][] clockHands, int row, int col) {
            return row < 0 || clockHands.length <= row
                    || col < 0 || clockHands[row].length <= col;
        }

    }

}
