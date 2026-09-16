import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class PG_20260916_42576_완주하지_못한_선수 {

	class Solution {

		public String solution(String[] participant, String[] completion) {
			Map<String, Integer> participantMap = toMap(participant);
			Map<String, Integer> completionMap = toMap(completion);
			String answer = null;
			for (Map.Entry<String, Integer> entry : participantMap.entrySet()) {
				if (!entry.getValue().equals(completionMap.get(entry.getKey()))) {
					answer = entry.getKey();
					break;
				}
			}
			return answer;
		}

		Map<String, Integer> toMap(String[] s) {
			return Arrays.stream(s)
					.collect(Collectors.toMap(
							Function.identity(),
							key -> 1,
							(oldValue, v) -> oldValue + 1));
		}

	}

}
