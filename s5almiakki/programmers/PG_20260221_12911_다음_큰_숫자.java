
public class PG_20260221_12911_다음_큰_숫자 {

    class Solution {

        public int solution(int n) {
            int initialBitCount = Integer.bitCount(n);
            int mask = 1;
            while ((n & mask) == 0) {
                mask <<= 1;
            }
            n += mask;
            int bitCountDiff = initialBitCount - Integer.bitCount(n);
            mask = (1 << bitCountDiff) - 1;
            return n + mask;
        }

    }

}
