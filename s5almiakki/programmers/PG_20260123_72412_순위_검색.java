import java.util.*;

public class PG_20260123_72412_순위_검색 {

    class Solution {

        private static final List<List<String>> CONDITIONS = List.of(
                List.of("cpp", "java", "python"),
                List.of("backend", "frontend"),
                List.of("junior", "senior"),
                List.of("chicken", "pizza"));

        private Map<Integer, Integer> scoreCompressionMap;

        public int[] solution(String[] info, String[] query) {
            SortedSet<Integer> scores = new TreeSet<>();
            for (String i : info) {
                scores.add(Integer.parseInt(i.split(" ")[4]));
            }
            for (String q : query) {
                scores.add(Integer.parseInt(q.replaceAll("and ", "").split(" ")[4]));
            }
            scoreCompressionMap = new HashMap<>();
            for (Integer score : scores) {
                scoreCompressionMap.put(score, scoreCompressionMap.size());
            }

            Map<Key, int[]> db = new HashMap<>();
            for (String i : info) {
                String[] splitted = i.split(" ");
                Key key = new Key(splitted[0], splitted[1], splitted[2], splitted[3]);
                int[] counts = db.get(key);
                if (counts == null) {
                    counts = new int[scoreCompressionMap.size()];
                    db.put(key, counts);
                }
                int score = scoreCompressionMap.get(Integer.parseInt(splitted[4]));
                counts[score]++;
            }
            // 누적합
            for (int[] counts : db.values()) {
                for (int i = counts.length - 2; i >= 0; i--) {
                    counts[i] += counts[i + 1];
                }
            }

            int[] answer = new int[query.length];
            String[] selectedConditions = new String[5];
            for (int i = 0; i < query.length; i++) {
                String q = query[i];
                String[] conditions = q.replaceAll("and ", "").split(" ");
                selectedConditions[4] = conditions[4];
                answer[i] = dfs(db, conditions, selectedConditions, 0);
            }
            return answer;
        }

        private int dfs(Map<Key, int[]> db, String[] conditions, String[] selectedConditions, int depth) {
            if (depth == 4) {
                Key key = new Key(selectedConditions[0], selectedConditions[1], selectedConditions[2], selectedConditions[3]);
                int[] counts = db.get(key);
                if (counts == null) {
                    return 0;
                }
                int score = scoreCompressionMap.get(Integer.parseInt(selectedConditions[4]));
                return counts[score];
            }

            if (!conditions[depth].equals("-")) {
                selectedConditions[depth] = conditions[depth];
                return dfs(db, conditions, selectedConditions, depth + 1);
            }
            int result = 0;
            for (String condition : CONDITIONS.get(depth)) {
                selectedConditions[depth] = condition;
                result += dfs(db, conditions, selectedConditions, depth + 1);
            }
            return result;
        }

        private static class Key {

            public final String language;
            public final String position;
            public final String career;
            public final String food;

            private final int hashCode;

            public Key(String language, String position, String career, String food) {
                this.language = language;
                this.position = position;
                this.career = career;
                this.food = food;

                hashCode = Objects.hash(language, position, career, food);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Key)) {
                    return false;
                }
                Key other = (Key) o;
                return Objects.equals(language, other.language)
                        && Objects.equals(position, other.position)
                        && Objects.equals(career, other.career)
                        && Objects.equals(food, other.food);
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

        }

    }


}
