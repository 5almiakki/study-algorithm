import java.util.*;

public class PG_20260118_64063_호텔_방_배정 {

    class Solution {

        public long[] solution(long k, long[] room_number) {
            long[] requests = room_number;
            Map<Long, Long> requestToRoomMap = new HashMap<>();
            long[] answer = new long[requests.length];
            for (int i = 0; i < requests.length; i++) {
                answer[i] = findAvailableRoom(requestToRoomMap, requests[i]);
                // System.out.println(requestToRoomMap);
            }
            return answer;
        }

        private Long findAvailableRoom(Map<Long, Long> requestToRoomMap, long request) {
            Long req = request;
            Long room = requestToRoomMap.get(req);
            if (room == null) {
                requestToRoomMap.put(req, request);
                return request;
            }
            room = findAvailableRoom(requestToRoomMap, room + 1);
            requestToRoomMap.put(req, room);
            return room;
        }

    }

}
