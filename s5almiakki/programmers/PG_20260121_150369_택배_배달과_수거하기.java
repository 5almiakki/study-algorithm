import java.util.*;

public class PG_20260121_150369_택배_배달과_수거하기 {

    class Solution {

        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            int remainingDeliveryCount = 0;
            int remainingPickupCount = 0;
            SortedMap<Integer, Integer> distanceToDeliveryCountMap = new TreeMap<>();
            SortedMap<Integer, Integer> distanceToPickupCountMap = new TreeMap<>();
            for (int i = 0; i < deliveries.length; i++) {
                remainingDeliveryCount += deliveries[i];
                remainingPickupCount += pickups[i];
                if (deliveries[i] != 0) {
                    distanceToDeliveryCountMap.put(i + 1, deliveries[i]);
                }
                if (pickups[i] != 0) {
                    distanceToPickupCountMap.put(i + 1, pickups[i]);
                }
            }

            long answer = 0L;
            while (remainingDeliveryCount > 0 || remainingPickupCount > 0) {
                int truckBoxCount = Math.min(cap, remainingDeliveryCount);

                // 배달
                Integer maxDeliveryDistance = distanceToDeliveryCountMap.isEmpty()
                        ? 0 : distanceToDeliveryCountMap.lastKey();
                while (!distanceToDeliveryCountMap.isEmpty() && truckBoxCount > 0) {
                    Integer house = distanceToDeliveryCountMap.lastKey();
                    int deliveryCount = distanceToDeliveryCountMap.get(house);
                    if (truckBoxCount < deliveryCount) {
                        distanceToDeliveryCountMap.put(house, deliveryCount - truckBoxCount);
                        remainingDeliveryCount -= truckBoxCount;
                        truckBoxCount = 0;
                        break;
                    }
                    distanceToDeliveryCountMap.remove(house);
                    remainingDeliveryCount -= deliveryCount;
                    truckBoxCount -= deliveryCount;
                }

                // 회수
                Integer maxPickupDistance = distanceToPickupCountMap.isEmpty()
                        ? 0 : distanceToPickupCountMap.lastKey();
                while (!distanceToPickupCountMap.isEmpty() && truckBoxCount < cap) {
                    Integer house = distanceToPickupCountMap.lastKey();
                    int pickupCount = distanceToPickupCountMap.get(house);
                    int remainingCap = cap - truckBoxCount;
                    if (remainingCap < pickupCount) {
                        distanceToPickupCountMap.put(house, pickupCount - remainingCap);
                        remainingPickupCount -= remainingCap;
                        truckBoxCount = cap;
                        break;
                    }
                    distanceToPickupCountMap.remove(house);
                    remainingPickupCount -= pickupCount;
                    truckBoxCount += pickupCount;
                }

                answer += Math.max((long) maxDeliveryDistance, (long) maxPickupDistance) << 1L;
            }

            return answer;
        }

    }

}
