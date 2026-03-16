import java.util.*;

public class PG_20260316_43164_여행경로 {

    class Solution {

        String[] answer;
        int size = 1;
        Map<String, SortedMap<String, Integer>> adjList;

        public String[] solution(String[][] tickets) {
            answer = new String[tickets.length + 1];
            adjList = new HashMap<>();
            for (String[] ticket : tickets) {
                SortedMap<String, Integer> adjCities = adjList.get(ticket[0]);
                if (adjCities == null) {
                    adjCities = new TreeMap<>();
                    adjList.put(ticket[0], adjCities);
                }
                adjCities.put(ticket[1], adjCities.getOrDefault(ticket[1], 0) + 1);
            }

            answer[0] = "ICN";
            dfs("ICN");
            return answer;
        }

        void dfs(String currentCity) {
            if (answer.length == size) {
                return;
            }
            SortedMap<String, Integer> adjCities = adjList.get(currentCity);
            if (adjCities == null) {
                return;
            }
            for (Map.Entry<String, Integer> adjCity : adjCities.entrySet()) {
                Integer remainingTicketCount = adjCity.getValue();
                if (remainingTicketCount == 0) {
                    continue;
                }
                String city = adjCity.getKey();
                adjCities.put(city, remainingTicketCount - 1);
                answer[size] = city;
                size++;
                dfs(city);
                if (size == answer.length) {
                    break;
                }
                adjCities.put(city, remainingTicketCount);
                size--;
            }
        }

    }

}
