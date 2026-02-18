public class PG_20260218_12909_올바른_괄호 {

    class Solution {

        boolean solution(String s) {
            int leftCount = 0;
            int length = s.length();
            for (int i = 0; i < length; i++) {
                switch (s.charAt(i)) {
                    case '(':
                        leftCount++;
                        break;
                    case ')':
                        if (leftCount == 0) {
                            return false;
                        }
                        leftCount--;
                        break;
                }
            }
            return leftCount == 0;
        }

    }

}
