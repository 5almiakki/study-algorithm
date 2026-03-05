import java.util.*;
import java.util.regex.*;

public class PG_20260305_64064_불량_사용자 {

    class Solution {

        Set<Set<String>> visitedMoments = new HashSet<>();
        int answer = 0;

        public int solution(String[] user_id, String[] banned_id) {
            String[] bannedIdRegexes = Arrays.stream(banned_id)
                    .map(bannedId -> bannedId.replaceAll("\\*", "."))
                    .toArray(String[]::new);
            dfs(user_id, bannedIdRegexes, new HashSet<>(), 0);
            return answer;
        }

        void dfs(String[] userIds, String[] bannedIdRegexes, Set<String> bannedIds, int depth) {
            if (visitedMoments.contains(bannedIds)) {
                return;
            }
            visitedMoments.add(new HashSet<>(bannedIds));

            if (depth == bannedIdRegexes.length) {
                answer++;
                return;
            }

            for (String userId : userIds) {
                if (bannedIds.contains(userId)
                        || !Pattern.matches(bannedIdRegexes[depth], userId)) {
                    continue;
                }
                bannedIds.add(userId);
                dfs(userIds, bannedIdRegexes, bannedIds, depth + 1);
                bannedIds.remove(userId);
            }
        }

    }

}
