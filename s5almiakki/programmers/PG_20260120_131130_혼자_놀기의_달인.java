import java.util.*;

public class PG_20260120_131130_혼자_놀기의_달인 {

    class Solution {

        public int solution(int[] cards) {
            for (int i = 0; i < cards.length; i++) {
                cards[i]--;
            }

            boolean[] visited = new boolean[cards.length];
            List<Map<Integer, Integer>> groups = new ArrayList<>();
            for (int box = 0; box < cards.length; box++) {
                if (visited[box]) {
                    continue;
                }

                Map<Integer, Integer> boxToCardMap = new HashMap<>();
                visited[box] = true;
                int prevCard = cards[box];
                boxToCardMap.put(box, prevCard);
                do {
                    visited[prevCard] = true;
                    int card = cards[prevCard];
                    boxToCardMap.put(prevCard, card);
                    if (boxToCardMap.containsKey(card)) {
                        break;
                    }
                    prevCard = card;
                } while (true);
                groups.add(boxToCardMap);
            }

            if (groups.size() <= 1) {
                return 0;
            }

            groups.sort((o1, o2) -> Integer.compare(o2.size(), o1.size()));
            return groups.get(0).size() * groups.get(1).size();
        }

    }

}
