import java.util.*;

public class PG_20260201_1835_단체사진_찍기 {

    class Solution {

        static final Set<Character> PEOPLE = Set.of('A', 'C', 'F', 'J', 'M', 'N', 'R', 'T');

        public int solution(int n, String[] data) {
            Map<Character, Map<Character, Set<String>>> conditions = new HashMap<>();
            for (Character p1 : PEOPLE) {
                Map<Character, Set<String>> c = new HashMap<>();
                for (Character p2 : PEOPLE) {
                    c.put(p2, new HashSet<>());
                }
                conditions.put(p1, c);
            }

            for (String datum : data) {
                Character c1 = datum.charAt(0);
                Character c2 = datum.charAt(2);
                conditions.get(c1).get(c2).add(datum.substring(3));
                conditions.get(c2).get(c1).add(datum.substring(3));
            }

            return dfs(conditions, new Character[8], 0);
        }

        int dfs(
                Map<Character, Map<Character, Set<String>>> conditions,
                Character[] people,
                int pos) {
            if (pos == 8) {
                return 1;
            }

            int caseCount = 0;
            for (Character person : PEOPLE) {
                boolean valid = true;
                for (int otherPos = 0; otherPos < pos; otherPos++) {
                    if (pos == otherPos) {
                        continue;
                    }
                    Character otherPerson = people[otherPos];
                    if (person.equals(otherPerson)) {
                        valid = false;
                        break;
                    }
                    int distance = Math.abs(pos - otherPos) - 1;
                    if (!isValid(distance, conditions.get(person).get(otherPerson))) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) {
                    continue;
                }
                people[pos] = person;
                caseCount += dfs(conditions, people, pos + 1);
                people[pos] = null;
            }

            return caseCount;
        }

        boolean isValid(int distance, Set<String> conditions) {
            for (String c : conditions) {
                if (!isValid(distance, c)) {
                    return false;
                }
            }
            return true;
        }

        boolean isValid(int distance, String condition) {
            int targetDistance = condition.charAt(1) - '0';
            switch (condition.charAt(0)) {
                case '<':
                    return distance < targetDistance;
                case '>':
                    return distance > targetDistance;
                default:
                    return distance == targetDistance;
            }
        }

    }

}
