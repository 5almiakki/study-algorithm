import java.util.*;

public class PG_20260102_캠핑 {

    class Solution {

        public int solution(int n, int[][] data) {
            Arrays.sort(data, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return Integer.compare(o1[0], o2[0]);
                }
                return Integer.compare(o1[0], o2[0]);
            });

            int answer = 0;
            for (int i = 0; i < data.length - 1; i++) {
                int[] p1 = data[i];
                for (int j = i + 1; j < data.length; j++) {
                    int[] p2 = data[j];
                    if (p1[0] == p2[0] || p1[1] == p2[1]) {
                        continue;
                    }
                    int x1 = Math.min(p1[0], p2[0]);
                    int x2 = Math.max(p1[0], p2[0]);
                    int y1 = Math.min(p1[1], p2[1]);
                    int y2 = Math.max(p1[1], p2[1]);
                    boolean valid = true;
                    for (int k = i + 1; k < j; k++) {
                        int[] p3 = data[k];
                        if (x1 < p3[0] && p3[0] < x2 && y1 < p3[1] && p3[1] < y2) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        answer++;
                    }
                }
            }

            return answer;
        }
    }

}
