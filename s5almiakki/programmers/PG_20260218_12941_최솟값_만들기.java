import java.util.*;

public class PG_20260218_12941_최솟값_만들기 {

    class Solution {

        public int solution(int[] A, int[] B) {
            Arrays.sort(A);
            Arrays.sort(B);
            int bIdx = B.length - 1;
            int answer = 0;
            for (int i = 0; i < A.length; i++) {
                answer += A[i] * B[bIdx - i];
            }
            return answer;
        }

    }

}
