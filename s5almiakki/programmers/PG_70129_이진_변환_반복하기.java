
public class PG_70129_이진_변환_반복하기 {

    class Solution {

        public int[] solution(String s) {
            int[] counts = new int[2];
            int[] answer = new int[2];
            while (!s.equals("1")) {
                int length = s.length();
                for (int i = 0; i < length; i++) {
                    char number = s.charAt(i);
                    counts[number - '0']++;
                }
                answer[0]++;
                answer[1] += counts[0];

                s = Integer.toString(counts[1], 2);
                counts[0] = 0;
                counts[1] = 0;
            }
            return answer;
        }

    }

}
