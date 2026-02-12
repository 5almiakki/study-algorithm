
public class PG_20260212_12939_최댓값과_최솟값 {

    class Solution {

        public String solution(String s) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (String e : s.split(" ")) {
                int num = Integer.parseInt(e);
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            return min + " " + max;
        }

    }

}
