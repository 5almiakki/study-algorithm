import java.util.*;

public class PG_20260406_17678_1차_셔틀버스 {

    class Solution {

        public String solution(int n, int t, int m, String[] timetable) {
            int[] crewTimes = new int[timetable.length];
            for (int i = 0; i < timetable.length; i++) {
                crewTimes[i] = toIntTime(timetable[i]);
            }
            Arrays.sort(crewTimes);

            int[] passengerCounts = new int[n];
            int beginShuttleTime = toIntTime("09", "00");
            int shuttleTime = beginShuttleTime;
            int totalPassengerCount = 0;
            for (int i = 0; i < n; i++) {
                int passengerCount = 0;
                for (int j = totalPassengerCount; j < crewTimes.length; j++) {
                    if (shuttleTime < crewTimes[j]) {
                        break;
                    }
                    passengerCount++;
                    if (m <= passengerCount) {
                        break;
                    }
                }
                passengerCounts[i] = passengerCount;
                totalPassengerCount += passengerCount;
                shuttleTime += t;
            }

            if (passengerCounts[n - 1] != m) {
                return toStringTime(beginShuttleTime + t * (n - 1));
            }
            return toStringTime(crewTimes[totalPassengerCount - 1] - 1);
        }

        int toIntTime(String hm) {
            return toIntTime(hm.split(":"));
        }

        int toIntTime(String[] hm) {
            return toIntTime(hm[0], hm[1]);
        }

        int toIntTime(String h, String m) {
            return Integer.parseInt(h) * 60 + Integer.parseInt(m);
        }

        String toStringTime(int time) {
            return String.format("%02d", time / 60) + ":" + String.format("%02d", time % 60);
        }

    }

}
