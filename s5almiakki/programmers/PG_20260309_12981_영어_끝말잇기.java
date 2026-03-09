import java.util.*;

public class PG_20260309_12981_영어_끝말잇기 {

    class Solution {

        public int[] solution(int n, String[] words) {
            String previousWord = words[0];
            int answer = 0;
            Set<String> visitedWords = new HashSet<>();
            visitedWords.add(words[0]);
            for (int i = 1; i < words.length; i++) {
                String currentWord = words[i];
                // System.out.println(currentWord);
                // System.out.println("char 비교: " + (previousWord.charAt(previousWord.length() - 1) != currentWord.charAt(0)));
                // System.out.println("방문 비교: " + visitedWords.contains(currentWord));
                // System.out.println();
                if (previousWord.charAt(previousWord.length() - 1) != currentWord.charAt(0)
                        || visitedWords.contains(currentWord)) {
                    answer = i;
                    break;
                }

                visitedWords.add(currentWord);
                previousWord = currentWord;
            }
            if (answer == 0) {
                return new int[] { 0, 0 };
            }
            return new int[] { (answer % n) + 1, (answer / n) + 1 };
        }

    }

}
