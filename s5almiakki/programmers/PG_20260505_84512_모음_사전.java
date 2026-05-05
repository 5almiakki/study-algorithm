
public class PG_20260505_84512_모음_사전 {

    class Solution {

        public int solution(String word) {
            int length = word.length();
            if (word.compareTo("AAAAA") <= 0) {
                return length;
            }
            int[] intervals = { 781, 156, 31, 6, 1 };
            int[] letterToIdxMap = new int['Z' - 'A' + 1];
            letterToIdxMap['A' - 'A'] = 0;
            letterToIdxMap['E' - 'A'] = 1;
            letterToIdxMap['I' - 'A'] = 2;
            letterToIdxMap['O' - 'A'] = 3;
            letterToIdxMap['U' - 'A'] = 4;
            int answer = length;
            for (int i = 0; i < length; i++) {
                char letter = word.charAt(i);
                answer += intervals[i] * letterToIdxMap[letter - 'A'];
            }
            return answer;
        }

    }


}
