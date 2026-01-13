import java.util.*;

public class PG_20260113_214289_에어컨 {

    class Solution {

        private int[] onboard;
        private int lowerBoundTemperature;
        private int upperBoundTemperature;
        private int turnOnCost;
        private int keepCost;

        public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
            if (t1 <= temperature && temperature <= t2) {
                return 0;
            }

            this.onboard = onboard;
            lowerBoundTemperature = t1;
            upperBoundTemperature = t2;
            turnOnCost = a;
            keepCost = b;

            int outdoorTemperature = temperature;
            int signToFarthestTemperature = 1;
            if (upperBoundTemperature < outdoorTemperature) {
                signToFarthestTemperature = -1;
            }

            Map<Moment, Integer> momentToCostMap = null;
            Map<Moment, Integer> newMomentToCostMap = new HashMap<>();
            putIfOptimal(newMomentToCostMap, 1, outdoorTemperature, Decision.TURN_OFF, 0, outdoorTemperature);
            putIfOptimal(newMomentToCostMap, 1, outdoorTemperature, Decision.TURN_ON, 0, outdoorTemperature + signToFarthestTemperature);
            // System.out.println("newMomentToCostMap=" + newMomentToCostMap + '\n');

            for (int time = 1; time < onboard.length - 1; time++) {
                momentToCostMap = newMomentToCostMap;
                newMomentToCostMap = new HashMap<>();
                for (Map.Entry<Moment, Integer> entry : momentToCostMap.entrySet()) {
                    Moment prevMoment = entry.getKey();
                    int prevDelta = outdoorTemperature - prevMoment.temperature;
                    int prevSignToOutdoorTemperature = prevDelta == 0 ? 0 : prevDelta / Math.abs(prevDelta);
                    int currentTemperature = prevMoment.temperature;
                    int currentCost = entry.getValue();
                    switch (prevMoment.decision) {
                        case TURN_OFF:
                            currentTemperature += prevSignToOutdoorTemperature;
                            break;
                        case KEEP:
                            currentCost += keepCost;
                            break;
                        case TURN_ON:
                            currentTemperature += signToFarthestTemperature;
                            currentCost += turnOnCost;
                            break;
                    }

                    int currentDelta = outdoorTemperature - currentTemperature;
                    int currentSignToOutdoorTemperature = currentDelta == 0 ? 0 : currentDelta / Math.abs(currentDelta);

                    // 끄기
                    putIfOptimal(newMomentToCostMap, time + 1, currentTemperature, Decision.TURN_OFF,
                            currentCost, currentTemperature + currentSignToOutdoorTemperature);

                    // 온도 유지
                    putIfOptimal(newMomentToCostMap, time + 1, currentTemperature, Decision.KEEP,
                            currentCost, currentTemperature);

                    // 실외 온도 반대 방향으로 온도 바꾸기
                    putIfOptimal(newMomentToCostMap, time + 1, currentTemperature, Decision.TURN_ON,
                            currentCost, currentTemperature + signToFarthestTemperature);
                }
                // System.out.println("newMomentToCostMap=" + newMomentToCostMap + '\n');
            }

            int answer = Integer.MAX_VALUE;
            for (Map.Entry<Moment, Integer> entry : newMomentToCostMap.entrySet()) {
                Moment prevMoment = entry.getKey();
                int currentCost = entry.getValue();
                switch (prevMoment.decision) {
                    case TURN_OFF:
                        break;
                    case KEEP:
                        currentCost += keepCost;
                        break;
                    case TURN_ON:
                        currentCost += turnOnCost;
                        break;
                }
                answer = Math.min(answer, currentCost);
            }
            return answer;
        }

        private void putIfOptimal(
                Map<Moment, Integer> momentToCostMap,
                int time,
                int currentTemperature,
                Decision currentDecision,
                int currentCost,
                int nextTemperature) {
            // System.out.print("temperature=" + currentTemperature + ' ' + currentDecision.name() + ' ' + "nextTemperature=" + nextTemperature + ' ');
            if ((onboard[time] == 1) && (nextTemperature < lowerBoundTemperature || upperBoundTemperature < nextTemperature)) {
                // System.out.println("쾌적하지 않음");
                return;
            }
            Moment currentMoment = new Moment(currentTemperature, currentDecision);
            Integer costHistory = momentToCostMap.get(currentMoment);
            if (costHistory == null || costHistory.compareTo(currentCost) > 0) {
                momentToCostMap.put(currentMoment, currentCost);
                // System.out.println("기록");
                // return;
            }
            // System.out.println("최소비용 아님");
        }

        private static class Moment {

            public final int temperature;
            public final Decision decision;

            private final int hashCode;

            public Moment(int temperature, Decision decision) {
                this.temperature = temperature;
                this.decision = decision;

                hashCode = Objects.hash(temperature, decision);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Moment)) {
                    return false;
                }
                Moment other = (Moment) o;
                return temperature == other.temperature
                        && decision == other.decision;
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

            @Override
            public String toString() {
                return "[t=" + temperature
                        + "," + decision.name()
                        + "]";
            }

        }

        private static enum Decision {

            TURN_OFF, KEEP, TURN_ON;

        }

    }

}
