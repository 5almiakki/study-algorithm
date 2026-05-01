import java.util.*;

public class PG_20260501_64065_튜플 {

    class Solution {

        public int[] solution(String s) {
            s = s.substring(2, s.length() - 2);
            String[] sets = s.split("\\},\\{");
            String[][] elements = new String[sets.length][];
            for (int i = 0; i < sets.length; i++) {
                elements[i] = sets[i].split(",");
            }
            Arrays.sort(elements, (o1, o2) -> Integer.compare(o1.length, o2.length));
            int[] answer = new int[elements[elements.length - 1].length];
            int idx = 0;
            boolean[] visited = new boolean[100_001];
            for (String[] es : elements) {
                for (String e : es) {
                    int i = Integer.parseInt(e);
                    if (visited[i]) {
                        continue;
                    }
                    answer[idx] = i;
                    idx++;
                    visited[i] = true;
                }
            }
            return answer;
        }

    }

}
