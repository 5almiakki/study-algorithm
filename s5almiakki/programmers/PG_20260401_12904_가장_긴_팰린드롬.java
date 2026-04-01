
public class PG_20260401_12904_가장_긴_팰린드롬 {

    class Solution {

        public int solution(String s) {
            int length = s.length();
            for (int answer = length; answer > 1; answer--) {
                for (int base = 0; base + answer <= length; base++) {
                    boolean isPalindrome = true;
                    for (int offset = (answer + 1) >> 1; offset < answer; offset++) {
                        if (s.charAt(base + offset) != s.charAt(base + answer - offset - 1)) {
                            isPalindrome = false;
                            break;
                        }
                    }
                    if (isPalindrome) {
                        return answer;
                    }
                }
            }
            return 1;
        }

    }

}
