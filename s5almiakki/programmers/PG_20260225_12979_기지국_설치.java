
public class PG_20260225_12979_기지국_설치 {

    class Solution {

        public int solution(int n, int[] stations, int w) {
            int answer = 0;
            int range = (w << 1) + 1;
            int prevRight = 0;
            for (int station : stations) {
                int left = station - w;
                int interval = left - prevRight - 1;
                if (interval > 0) {
                    answer += (interval + range - 1) / range;
                }
                prevRight = station + w;
            }
            int interval = n - prevRight;
            if (interval > 0) {
                answer += (interval + range - 1) / range;
            }
            return answer;
        }

    }

}
