import java.util.*;

public class PG_20260226_118670_행렬과_연산 {

    class Solution {

        public int[][] solution(int[][] rc, String[] operations) {
            int maxRowIdx = rc.length - 1;
            int maxColIdx = rc[0].length - 1;

            Deque<Integer> leftCol = new ArrayDeque<>();
            Deque<Integer> rightCol = new ArrayDeque<>();
            Deque<Deque<Integer>> rows = new ArrayDeque<>();
            for (int rowIdx = 0; rowIdx <= maxRowIdx; rowIdx++) {
                leftCol.addLast(rc[rowIdx][0]);
                rightCol.addLast(rc[rowIdx][maxColIdx]);

                Deque<Integer> row = new ArrayDeque<>();
                for (int colIdx = 1; colIdx < maxColIdx; colIdx++) {
                    row.addLast(rc[rowIdx][colIdx]);
                }
                rows.addLast(row);
            }

            for (String operation : operations) {
                if (operation.charAt(0) == 'S') {
                    leftCol.addFirst(leftCol.removeLast());
                    rightCol.addFirst(rightCol.removeLast());
                    if (!rows.isEmpty()) {
                        rows.addFirst(rows.removeLast());
                    }
                } else {
                    Deque<Integer> firstRow = rows.peekFirst();
                    Deque<Integer> lastRow = rows.peekLast();

                    lastRow.addLast(rightCol.removeLast());
                    firstRow.addFirst(leftCol.removeFirst());
                    rightCol.addFirst(firstRow.removeLast());
                    leftCol.addLast(lastRow.removeFirst());
                }
            }

            int[][] answer = new int[rc.length][rc[0].length];
            do {
                int element = leftCol.removeLast();
                answer[leftCol.size()][0] = element;
            } while (!leftCol.isEmpty());
            do {
                int element = rightCol.removeLast();
                answer[rightCol.size()][maxColIdx] = element;
            } while (!rightCol.isEmpty());
            while (!rows.isEmpty()) {
                Deque<Integer> row = rows.removeLast();
                while (!row.isEmpty()) {
                    int element = row.removeLast();
                    answer[rows.size()][row.size() + 1] = element;
                }
            }
            return answer;
        }

    }

}
