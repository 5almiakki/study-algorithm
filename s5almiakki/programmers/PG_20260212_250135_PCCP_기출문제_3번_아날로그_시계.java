
public class PG_20260212_250135_PCCP_기출문제_3번_아날로그_시계 {

    class Solution {

        public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
            int count1 = computeCountFromMidnight(h1, m1, s1);
            if ((h1 == 0 || h1 == 12) && m1 == 0 && s1 == 0) {
                count1--;
            }
            int count2 = computeCountFromMidnight(h2, m2, s2);

            return count2 - count1;
        }

        int computeCountFromMidnight(int h, int m, int s) {
            // 초침 한 칸을 720으로 계산
            // 한 바퀴는 43200 = 720 * 60 = 60 * 12 * 60 = 3600 * 12
            // 1도는 12
            int hDegree = (h % 12) * 5 * 720 + m * 60 + s;
            int mDegree = m * 720 + s * 12;
            int sDegree = s * 720;

            int count = -1;
            if (mDegree <= sDegree) {
                count++;
            }
            if (hDegree <= sDegree) {
                count++;
            }

            count += ((h * 60 + m) << 1) - h;
            if (h >= 12) {
                count -= 2;
            }

            return count;
        }

    }

    /*
    속도
    초침:분침=60:1=720:12
    분침:시침=12:1
    초침:분침:시침=720:12:1
     */

}
