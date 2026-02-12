
public class PG_20260212_12951_JadenCase_문자열_만들기 {

    class Solution {

        public String solution(String s) {
            StringBuilder answer = new StringBuilder();
            for (String word : s.split(" ", -1)) {
                if (word.length() == 0) {
                    answer.append(' ');
                    continue;
                }
                answer.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(' ');
            }

            return answer.deleteCharAt(answer.length() - 1).toString();
        }

    }

}
