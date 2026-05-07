import java.util.*;

public class PG_20260507_17677_1차_뉴스_클러스터링 {

    class Solution {

        public int solution(String str1, String str2) {
            Map<String, Integer> map1 = makeSubstringToCountMap(str1);
            Map<String, Integer> map2 = makeSubstringToCountMap(str2);
            int intersectionSize = 0;
            int unionSize = 0;
            for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                Integer value1 = entry.getValue();
                Integer value2 = map2.get(entry.getKey());
                if (value2 != null) {
                    int count1 = value1;
                    int count2 = value2;
                    if (count1 < count2) {
                        intersectionSize += count1;
                        unionSize += count2;
                    } else {
                        intersectionSize += count2;
                        unionSize += count1;
                    }
                } else {
                    unionSize += value1;
                }
            }
            for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                if (!map1.containsKey(entry.getKey())) {
                    unionSize += entry.getValue();
                }
            }
            if (unionSize == 0) {
                return 65536;
            }
            return 65536 * intersectionSize / unionSize;
        }

        Map<String, Integer> makeSubstringToCountMap(String s) {
            Map<String, Integer> result = new HashMap<>();
            for (int i = s.length() - 2; i >= 0; i--) {
                char c1 = s.charAt(i);
                char c2 = s.charAt(i + 1);
                if (!Character.isAlphabetic(c1) || !Character.isAlphabetic(c2)) {
                    continue;
                }
                if (!Character.isLowerCase(c1)) {
                    c1 = Character.toLowerCase(c1);
                }
                if (!Character.isLowerCase(c2)) {
                    c2 = Character.toLowerCase(c2);
                }
                String substring = new StringBuilder().append(c1).append(c2).toString();
                Integer count = result.get(substring);
                result.put(substring, count == null ? 1 : count + 1);
            }
            return result;
        }

    }

}
