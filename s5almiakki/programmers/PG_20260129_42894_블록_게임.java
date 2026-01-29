import java.util.*;

public class PG_20260129_42894_블록_게임 {

    class Solution {

        static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };
        static final Point[][] POINTS = new Point[50][50];
        static final List<Block> REMOVABLE_BLOCKS;

        static {
            for (int row = 0; row < 50; row++) {
                for (int col = 0; col < 50; col++) {
                    POINTS[row][col] = new Point(row, col);
                }
            }

            REMOVABLE_BLOCKS = List.of(
                    new Block(POINTS[1][0], POINTS[1][1], POINTS[1][2], POINTS[0][0]),
                    new Block(POINTS[1][0], POINTS[1][1], POINTS[1][2], POINTS[0][1]),
                    new Block(POINTS[1][0], POINTS[1][1], POINTS[1][2], POINTS[0][2]),
                    new Block(POINTS[2][0], POINTS[2][1], POINTS[0][0], POINTS[1][0]),
                    new Block(POINTS[2][0], POINTS[2][1], POINTS[0][1], POINTS[1][1]));
        }

        public int solution(int[][] board) {
            // for (int[] row : board) {
            //     System.out.println(Arrays.toString(row));
            // }
            // System.out.println();

            Queue<Point> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[board.length][board[0].length];
            SortedSet<Block> removableBlocks = new TreeSet<>();
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] == 0 || visited[row][col]) {
                        continue;
                    }
                    Block b = bfs(board, queue, visited, row, col);
                    if (isRemovable(b)) {
                        removableBlocks.add(b);
                    }
                }
            }

            int answer = 0;
            do {
                boolean removed = false;
                Iterator<Block> it = removableBlocks.iterator();
                while (it.hasNext()) {
                    Block b = it.next();
                    // System.out.println(b);
                    if (!isBlockPresentOver(board, b)) {
                        // System.out.println("remove");
                        for (Point point : b.points) {
                            board[point.row + b.offset.row][point.col + b.offset.col] = 0;
                        }
                        it.remove();
                        removed = true;
                        answer++;
                        break;
                    }
                }
                if (!removed) {
                    break;
                }

                // for (int[] row : board) {
                //     System.out.println(Arrays.toString(row));
                // }
                // System.out.println();
            } while (true);
            return answer;
        }

        boolean isRemovable(Block block) {
            for (Block removableBlock : REMOVABLE_BLOCKS) {
                if (removableBlock.hasSameShape(block)) {
                    return true;
                }
            }
            return false;
        }

        boolean isBlockPresentOver(int[][] board, Block block) {
            Point base = block.offset;
            Queue<Point> queue = new ArrayDeque<>();
            for (int rowOffset = 0; rowOffset < block.height; rowOffset++) {
                for (int colOffset = 0; colOffset < block.width; colOffset++) {
                    int row = base.row + rowOffset;
                    int col = base.col + colOffset;
                    if (block.points.contains(POINTS[rowOffset][colOffset])) {
                        continue;
                    }
                    if (board[row][col] != 0) {
                        // System.out.println("checkpoint");
                        return true;
                    }
                    queue.add(POINTS[row][col]);
                }
            }
            // System.out.println("queue=" + queue);
            do {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Point p = queue.remove();
                    int newRow = p.row - 1;
                    if (newRow < 0) {
                        continue;
                    }
                    if (board[newRow][p.col] != 0) {
                        return true;
                    }
                    queue.add(POINTS[newRow][p.col]);
                }
            } while (!queue.isEmpty());
            return false;
        }

        Block bfs(int[][] board, Queue<Point> queue, boolean[][] visited, int beginRow, int beginCol) {
            int blockNum = board[beginRow][beginCol];
            Set<Point> blockPoints = new HashSet<>();
            queue.add(POINTS[beginRow][beginCol]);
            visited[beginRow][beginCol] = true;
            blockPoints.add(POINTS[beginRow][beginCol]);
            do {
                Point point = queue.remove();
                for (int[] direction : DIRECTIONS) {
                    int newRow = point.row + direction[0];
                    int newCol = point.col + direction[1];
                    if (isOutOfBounds(board, newRow, newCol)
                            || board[newRow][newCol] != blockNum
                            || visited[newRow][newCol]) {
                        continue;
                    }
                    queue.add(POINTS[newRow][newCol]);
                    visited[newRow][newCol] = true;
                    blockPoints.add(POINTS[newRow][newCol]);
                }
            } while (!queue.isEmpty());
            return new Block(blockPoints);
        }

        boolean isOutOfBounds(int[][] board, int row, int col) {
            return row < 0 || board.length <= row
                    || col < 0 || board[row].length <= col;
        }

        static class Point implements Comparable<Point> {

            final int row;
            final int col;

            private final int hashCode;

            Point(int row, int col) {
                this.row = row;
                this.col = col;

                hashCode = Objects.hash(row, col);
            }

            @Override
            public int compareTo(Point other) {
                if (row != other.row) {
                    return Integer.compare(row, other.row);
                }
                return Integer.compare(col, other.col);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Point)) {
                    return false;
                }
                Point other = (Point) o;
                return row == other.row && col == other.col;
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

            @Override
            public String toString() {
                return "(" + row + "," + col + ")";
            }

        }

        static class Block implements Comparable<Block> {

            final Point offset;
            final int height;
            final int width;
            final Set<Point> points;

            private final int hashCode;

            Block(Point... points) {
                int minRow = Integer.MAX_VALUE;
                int minCol = Integer.MAX_VALUE;
                int maxRow = 0;
                int maxCol = 0;
                for (Point point : points) {
                    minRow = Math.min(minRow, point.row);
                    minCol = Math.min(minCol, point.col);
                    maxRow = Math.max(maxRow, point.row);
                    maxCol = Math.max(maxCol, point.col);
                }
                offset = Solution.POINTS[minRow][minCol];
                height = maxRow - minRow + 1;
                width = maxCol - minCol + 1;

                this.points = new HashSet<>();
                for (Point point : points) {
                    this.points.add(Solution.POINTS[point.row - minRow][point.col - minCol]);
                }

                hashCode = Objects.hash(offset, width, points);
            }

            Block(Set<Point> points) {
                int minRow = Integer.MAX_VALUE;
                int minCol = Integer.MAX_VALUE;
                int maxRow = 0;
                int maxCol = 0;
                for (Point point : points) {
                    minRow = Math.min(minRow, point.row);
                    minCol = Math.min(minCol, point.col);
                    maxRow = Math.max(maxRow, point.row);
                    maxCol = Math.max(maxCol, point.col);
                }
                offset = Solution.POINTS[minRow][minCol];
                height = maxRow - minRow + 1;
                width = maxCol - minCol + 1;

                this.points = new HashSet<>();
                for (Point point : points) {
                    this.points.add(Solution.POINTS[point.row - minRow][point.col - minCol]);
                }

                hashCode = Objects.hash(offset, width, points);
            }

            boolean hasSameShape(Block other) {
                return Objects.equals(points, other.points);
            }

            @Override
            public int compareTo(Block other) {
                return offset.compareTo(other.offset);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Block)) {
                    return false;
                }
                Block other = (Block) o;
                return Objects.equals(offset, other.offset)
                        && Objects.equals(points, other.points);
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

            @Override
            public String toString() {
                return offset + " " + points;
            }

        }


    }

}
