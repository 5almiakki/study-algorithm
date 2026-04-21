import java.io.*;
import java.util.*;

public class BOJ_20260420_21611_마법사_상어와_블리자드 {

    public static class Main {

        static final int LEFT = 0;
        static final int DOWN = 1;
        static final int RIGHT = 2;
        static final int UP = 3;
        static final int[][] DELTAS = {
                { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }
        };

        static int gridSize;
        static int magicCount;
        static int[][] grid;

        static int[] answer = new int[3];

        //static {
        //    List<Integer> marbles = IntStream.range(0, 10).mapToObj(i -> i).collect(Collectors.toList());
        //    List<int[]> ranges = Arrays.asList(
        //            new int[] { 0, 3 },
        //            new int[] { 4, 7 });
        //    explodeMarbles(marbles, ranges);
        //    System.out.println(marbles);
        //}

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            gridSize = Integer.parseInt(line[0]);
            magicCount = Integer.parseInt(line[1]);
            grid = new int[gridSize][gridSize];
            for (int row = 0; row < gridSize; row++) {
                line = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    grid[row][col] = Integer.parseInt(line[col]);
                }
            }
            for (int i = 0; i < magicCount; i++) {
                line = br.readLine().split(" ");
                throwIce(line[0], Integer.parseInt(line[1]));
                LinkedList<Integer> marbles = collectMarblesFromGrid();
                for (;;) {
                    //System.out.println(marbles);
                    List<int[]> explosionRanges = computeExplosionRanges(marbles);
                    if (explosionRanges.isEmpty()) {
                        break;
                    }
                    explodeMarbles(marbles, explosionRanges);
                    //for (int[] range : explosionRanges) {
                    //    System.out.print(Arrays.toString(range));
                    //}
                    //System.out.println();
                    //System.out.println(Arrays.toString(answer));
                }
                Queue<Integer> newMarbles = createNewMarbles(marbles);
                fillGrid(newMarbles);
            }
            System.out.print(answer[0] + (answer[1] << 1) + (answer[2] * 3));
        }

        //static void printGrid() {
        //    StringBuilder sb = new StringBuilder();
        //    String ln = System.lineSeparator();
        //    for (int row = 0; row < gridSize; row++) {
        //        for (int col = 0; col < gridSize; col++) {
        //            if (grid[row][col] == 0) {
        //                sb.append('.').append(' ');
        //            } else {
        //                sb.append(grid[row][col]).append(' ');
        //            }
        //        }
        //        sb.append(ln);
        //    }
        //    System.out.print(sb);
        //}

        static void throwIce(String direction, int distance) {
            int directionIdx = -1;
            switch (direction) {
                case "1":
                    directionIdx = UP;
                    break;
                case "2":
                    directionIdx = DOWN;
                    break;
                case "3":
                    directionIdx = LEFT;
                    break;
                case "4":
                    directionIdx = RIGHT;
                    break;
            }
            int row = gridSize >> 1;
            int col = gridSize >> 1;
            for (int i = 0; i < distance; i++) {
                row += DELTAS[directionIdx][0];
                col += DELTAS[directionIdx][1];
                grid[row][col] = 0;
            }
        }

        static LinkedList<Integer> collectMarblesFromGrid() {
            LinkedList<Integer> result = new LinkedList<>();
            int row = gridSize >> 1;
            int col = gridSize >> 1;
            int direction = LEFT;
            int distance = 1;
            for (;;) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < distance; j++) {
                        row += DELTAS[direction][0];
                        col += DELTAS[direction][1];
                        if (isOutOfBounds(row, col)) {
                            return result;
                        }
                        int marble = grid[row][col];
                        if (marble != 0) {
                            result.add(marble);
                            //System.out.println("result: " + result);
                        }
                    }
                    direction = (direction + 1) % 4;
                }
                distance++;
            }
        }

        static boolean isOutOfBounds(int row, int col) {
            return row < 0 || gridSize <= row
                    || col < 0 || gridSize <= col;
        }

        static List<int[]> computeExplosionRanges(List<Integer> marbles) {
            if (marbles.isEmpty()) {
                return new LinkedList<>();
            }
            List<int[]> explosionRanges = new LinkedList<>(); // beginInclusive, endExclusive
            Integer prevMarble = null;
            int idx = 0;
            int count = 1;
            for (Integer marble : marbles) {
                if (prevMarble == marble) {
                    count++;
                } else {
                    if (count >= 4) {
                        explosionRanges.add(new int[] { idx - count, idx });
                    }
                    prevMarble = marble;
                    count = 1;
                }
                idx++;
            }
            if (count >= 4) {
                explosionRanges.add(new int[] { idx - count, idx });
            }
            return explosionRanges;
        }

        static void explodeMarbles(List<Integer> marbles, List<int[]> explosionRanges) {
            ListIterator<int[]> rangeIterator = explosionRanges.listIterator(explosionRanges.size());
            while (rangeIterator.hasPrevious()) {
                int[] range = rangeIterator.previous();
                Iterator<Integer> marbleIterator = marbles.iterator();
                for (int i = 0; i < range[0]; i++) {
                    marbleIterator.next();
                }
                int marble = marbleIterator.next();
                marbleIterator.remove();
                for (int i = range[0] + 1; i < range[1]; i++) {
                    marbleIterator.next();
                    marbleIterator.remove();
                }
                answer[marble - 1] += range[1] - range[0];
            }
        }

        static Queue<Integer> createNewMarbles(Queue<Integer> marbles) {
            Queue<Integer> result = new ArrayDeque<>();
            if (marbles.isEmpty()) {
                return result;
            }
            Integer prevMarble = marbles.remove();
            int prevMarbleCount = 1;
            while (!marbles.isEmpty()) {
                Integer marble = marbles.remove();
                if (marble == prevMarble) {
                    prevMarbleCount++;
                    continue;
                }
                result.add(prevMarbleCount);
                result.add(prevMarble);
                if (result.size() >= gridSize * gridSize - 1) {
                    return result;
                }
                prevMarble = marble;
                prevMarbleCount = 1;
            }
            result.add(prevMarbleCount);
            result.add(prevMarble);
            return result;
        }

        static void fillGrid(Queue<Integer> queue) {
            int row = gridSize >> 1;
                int col = gridSize >> 1;
            int direction = LEFT;
            int distance = 1;
            for (;;) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < distance; j++) {
                        row += DELTAS[direction][0];
                        col += DELTAS[direction][1];
                        if (isOutOfBounds(row, col)) {
                            return;
                        }
                        if (queue.isEmpty()) {
                            grid[row][col] = 0;
                            continue;
                        }
                        grid[row][col] = queue.remove();
                    }
                    direction = (direction + 1) % 4;
                }
                distance++;
            }
        }

    }

}
