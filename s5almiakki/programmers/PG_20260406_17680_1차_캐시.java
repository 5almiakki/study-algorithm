import java.util.*;

public class PG_20260406_17680_1차_캐시 {

    class Solution {

        public int solution(int cacheSize, String[] cities) {
            if (cacheSize == 0) {
                return cities.length * 5;
            }

            int currentTime = 0;
            Map<String, Integer> cityToLastUsageTimeMap = new HashMap<>();
            for (String city : cities) {
                city = city.toLowerCase();
                // System.out.println("\n" + cityToLastUsageTimeMap);
                // System.out.println("city=" + city);
                if (cityToLastUsageTimeMap.keySet().contains(city)) {
                    cityToLastUsageTimeMap.put(city, currentTime);
                    currentTime++;
                    // System.out.println("cache hit");
                    continue;
                }
                if (cityToLastUsageTimeMap.size() < cacheSize) {
                    cityToLastUsageTimeMap.put(city, currentTime);
                    currentTime += 5;
                    // System.out.println("cache miss, space remaining");
                    continue;
                }
                Map.Entry<String, Integer> lruEntry = null;
                for (Map.Entry<String, Integer> entry : cityToLastUsageTimeMap.entrySet()) {
                    if (lruEntry == null || entry.getValue() < lruEntry.getValue()) {
                        lruEntry = entry;
                    }
                }
                // System.out.println("lruEntry=" + lruEntry);
                if (lruEntry != null) {
                    cityToLastUsageTimeMap.remove(lruEntry.getKey());
                }
                cityToLastUsageTimeMap.put(city, currentTime);
                currentTime += 5;
                // System.out.println("cache miss, no space");
            }
            return currentTime;
        }

    }

}
