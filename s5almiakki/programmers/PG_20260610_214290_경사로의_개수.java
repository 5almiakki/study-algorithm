public class PG_20260610_214290_경사로의_개수 {

    class Solution {

        final long divisor = 1_000_000_007L;
        final int[][] pointDeltas = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        int[][] grid;
        int rowCount;
        int colCount;
        int[] d;
        int k;
        String kBinaryString;
        int[][][][][] dp; // [beginRow][beginCol][endRow][endCol][hop]
        long[][][][][] routeCounts; // [beginRow][beginCol][endRow][endCol][lg(repeatCount)]
        long[][] destinationCaseCounts; // [endRow][endCol][lg(repeatCount)]

        void init(int[][] grid, int[] d, int k) {
            this.grid = grid;
            this.d = d;
            this.k = k;
            kBinaryString = Integer.toBinaryString(k);
            rowCount = grid.length;
            colCount = grid[0].length;
            dp = new int[rowCount][colCount][rowCount][colCount][d.length];
            int length = kBinaryString.length();
            routeCounts = new long[rowCount][colCount][rowCount][colCount][length];
            destinationCaseCounts = new long[rowCount][colCount];
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    destinationCaseCounts[row][col] = 1L;
                }
            }
        }

        public int solution(int[][] grid, int[] d, int k) {
            init(grid, d, k);

            // hop = 0
            for (int beginRow = 0; beginRow < rowCount; beginRow++) {
                for (int beginCol = 0; beginCol < colCount; beginCol++) {
                    dp(beginRow, beginCol);
                }
            }
            // hop > 0
            for (int hop = 1; hop < d.length; hop++) {
                for (int beginRow = 0; beginRow < rowCount; beginRow++) {
                    for (int beginCol = 0; beginCol < colCount; beginCol++) {
                        dp(beginRow, beginCol, hop);
                    }
                }
            }

            for (int beginRow = 0; beginRow < rowCount; beginRow++) {
                for (int beginCol = 0; beginCol < colCount; beginCol++) {
                    for (int endRow = 0; endRow < rowCount; endRow++) {
                        for (int endCol = 0; endCol < colCount; endCol++) {
                            routeCounts[beginRow][beginCol][endRow][endCol][0] = dp[beginRow][beginCol][endRow][endCol][d.length
                                    - 1];
                        }
                    }
                }
            }

            countRepeatedRoutes();
            computeDestinationCaseCounts();

            long answer = 0;
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    answer = (answer + destinationCaseCounts[row][col]) % divisor;
                }
            }
            return (int) answer;
        }

        boolean isOutOfBounds(int[][] grid, int row, int col) {
            return row < 0 || rowCount <= row
                    || col < 0 || colCount <= col;
        }

        void dp(int beginRow, int beginCol) {
            for (int[] pointDelta : pointDeltas) {
                int newRow = beginRow + pointDelta[0];
                int newCol = beginCol + pointDelta[1];
                if (isOutOfBounds(grid, newRow, newCol)
                        || grid[newRow][newCol] - grid[beginRow][beginCol] != d[0]) {
                    continue;
                }
                dp[beginRow][beginCol][newRow][newCol][0] = 1;
            }
        }

        void dp(int beginRow, int beginCol, int hop) {
            for (int[] pointDelta : pointDeltas) {
                int newRow = beginRow + pointDelta[0];
                int newCol = beginCol + pointDelta[1];
                if (isOutOfBounds(grid, newRow, newCol)
                        || grid[newRow][newCol] - grid[beginRow][beginCol] != d[hop]) {
                    continue;
                }
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        dp[row][col][newRow][newCol][hop] = (dp[row][col][newRow][newCol][hop]
                                + dp[row][col][beginRow][beginCol][hop - 1]) % (int) divisor;
                    }
                }
            }
        }

        void countRepeatedRoutes() {
            int cellCount = rowCount * colCount;
            int length = kBinaryString.length();
            for (int lgRepeatCount = 1; lgRepeatCount < length; lgRepeatCount++) {
                for (int begin = 0; begin < cellCount; begin++) {
                    int beginRow = begin / colCount;
                    int beginCol = begin % colCount;
                    for (int end = 0; end < cellCount; end++) {
                        int endRow = end / colCount;
                        int endCol = end % colCount;
                        for (int transit = 0; transit < cellCount; transit++) {
                            int transitRow = transit / colCount;
                            int transitCol = transit % colCount;
                            routeCounts[beginRow][beginCol][endRow][endCol][lgRepeatCount] = (routeCounts[beginRow][beginCol][endRow][endCol][lgRepeatCount]
                                    + (routeCounts[beginRow][beginCol][transitRow][transitCol][lgRepeatCount - 1]
                                            * routeCounts[transitRow][transitCol][endRow][endCol][lgRepeatCount - 1])
                                            % divisor)
                                    % divisor;
                        }
                    }
                }
            }
        }

        void computeDestinationCaseCounts() {
            int length = kBinaryString.length();
            for (int shiftAmt = 0; shiftAmt < length; shiftAmt++) {
                if (((1 << shiftAmt) & k) == 0) {
                    continue;
                }
                long[][] newDestinationCaseCounts = new long[rowCount][colCount];
                int cellCount = rowCount * colCount;
                for (int begin = 0; begin < cellCount; begin++) {
                    int beginRow = begin / colCount;
                    int beginCol = begin % colCount;
                    for (int end = 0; end < cellCount; end++) {
                        int endRow = end / colCount;
                        int endCol = end % colCount;
                        newDestinationCaseCounts[endRow][endCol] = (newDestinationCaseCounts[endRow][endCol]
                                + (destinationCaseCounts[beginRow][beginCol]
                                        * routeCounts[beginRow][beginCol][endRow][endCol][shiftAmt]) % divisor)
                                % divisor;
                    }
                }
                destinationCaseCounts = newDestinationCaseCounts;
            }
        }

    }

}
