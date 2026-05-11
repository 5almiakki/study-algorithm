import java.util.*;

public class PG_20260511_81303_표_편집 {

    class Solution {

        NavigableSet<Integer> rows;
        Deque<Integer> removedRows;
        Integer cursor;

        void init(int n, int k) {
            rows = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                rows.add(i);
            }
            removedRows = new ArrayDeque<>();
            cursor = k;
        }

        public String solution(int n, int k, String[] cmd) {
            init(n, k);
            for (String c : cmd) {
                switch (c.charAt(0)) {
                    case 'U':
                        moveUp(Integer.parseInt(c.substring(2)));
                        break;
                    case 'D':
                        moveDown(Integer.parseInt(c.substring(2)));
                        break;
                    case 'C':
                        remove();
                        break;
                    case 'Z':
                        recover();
                        break;
                }
            }
            char[] result = new char[n];
            Arrays.fill(result, 'O');
            for (int removedRow : removedRows) {
                result[removedRow] = 'X';
            }
            return new String(result);
        }

        void moveUp(int amount) {
            for (int i = 0; i < amount; i++) {
                cursor = rows.lower(cursor);
            }
        }

        void moveDown(int amount) {
            for (int i = 0; i < amount; i++) {
                cursor = rows.higher(cursor);
            }
        }

        void remove() {
            rows.remove(cursor);
            removedRows.push(cursor);
            Integer higher = rows.higher(cursor);
            if (higher == null) {
                cursor = rows.lower(cursor);
            } else {
                cursor = higher;
            }
        }

        void recover() {
            rows.add(removedRows.pop());
        }

    }

}
