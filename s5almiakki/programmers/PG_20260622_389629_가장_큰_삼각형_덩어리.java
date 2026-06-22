public class PG_20260622_389629_가장_큰_삼각형_덩어리 {

    class Solution {

        static final Direction[][][] DELTAS = {
                // \
                {
                        // 좌
                        { Direction.DOWN, Direction.LEFT },
                        // 우
                        { Direction.UP, Direction.RIGHT }
                },
                // /
                {
                        // 좌
                        { Direction.UP, Direction.LEFT },
                        // 우
                        { Direction.DOWN, Direction.RIGHT }
                }
        };

        static final int[][] NEW_TRIANGLES = {
                { 0, 1 }, // UP, \, /
                { 1, 0 }, // DOWN, \, /
                { 1, 1 }, // LEFT, \, /
                { 0, 0 } // RIGHT, \, /
        };

        static final Direction[][] NEW_DELTAS = {
                { Direction.LEFT, Direction.RIGHT }, // UP, \, /
                { Direction.RIGHT, Direction.LEFT }, // DOWN, \, /
                { Direction.UP, Direction.DOWN }, // LEFT, \, /
                { Direction.DOWN, Direction.UP } // RIGHT, \, /
        };

        // // static final String[] TRIANGLE_S = { "L", "R" };

        public int solution(int[][] grid) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == -1) {
                        grid[row][col] = 0;
                    }
                    // grid[row][col] = (grid[row][col] + 1) >> 1;
                }
            }
            int[][][] groupNums = new int[grid.length][grid[0].length][2];
            int groupNum = 1;
            int answer = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    // triangle 0: 좌, 1: 우
                    for (int triangle = 0; triangle < 2; triangle++) {
                        if (groupNums[row][col][triangle] != 0) {
                            continue;
                        }
                        Direction delta1 = DELTAS[grid[row][col]][triangle][0];
                        Direction delta2 = DELTAS[grid[row][col]][triangle][1];
                        // System.out.println(" begin: " + row + "," + col + "," + TRIANGLE_S[triangle]
                        // + "," + delta1);
                        int triangleCount1 = search(grid, groupNums, groupNum, row, col, triangle, delta1);
                        // System.out.println(" begin: " + row + "," + col + "," + TRIANGLE_S[triangle]
                        // + "," + delta2);
                        int triangleCount2 = search(grid, groupNums, groupNum, row, col, triangle, delta2);
                        int result = triangleCount1 + triangleCount2 - 1;
                        if (answer < result) {
                            answer = result;
                        }
                        // System.out.println("result: " + result);
                        groupNum++;
                    }
                }
            }
            return answer;
        }

        int search(
                int[][] grid, int[][][] groupNums, int groupNum, int beginRow, int beginCol,
                int triangle, Direction delta) {
            int row = beginRow;
            int col = beginCol;
            groupNums[row][col][triangle] = groupNum;
            int triangleCount = 1;
            for (;;) {
                row += delta.getRow();
                col += delta.getCol();
                if (isOutOfBounds(grid, row, col) || groupNums[row][col][0] == groupNum
                        || groupNums[row][col][1] == groupNum) {
                    break;
                }
                triangle = NEW_TRIANGLES[delta.ordinal()][grid[row][col]];
                delta = NEW_DELTAS[delta.ordinal()][grid[row][col]];
                groupNums[row][col][triangle] = groupNum;
                triangleCount++;
                // System.out.println(" new: " + row + "," + col + "," + TRIANGLE_S[triangle] +
                // "," + delta);
            }
            // System.out.println(" triangleCount: " + triangleCount);
            return triangleCount;
        }

        boolean isOutOfBounds(int[][] grid, int row, int col) {
            return row < 0 || grid.length <= row
                    || col < 0 || grid[row].length <= col;
        }

        static enum Direction {

            UP(-1, 0),
            DOWN(1, 0),
            LEFT(0, -1),
            RIGHT(0, 1);

            private int row;
            private int col;

            private Direction(int row, int col) {
                this.row = row;
                this.col = col;
            }

            public int getRow() {
                return row;
            }

            public int getCol() {
                return col;
            }

        }

    }

}
