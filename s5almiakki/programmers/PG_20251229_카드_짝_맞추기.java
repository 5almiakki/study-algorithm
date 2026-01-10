import java.util.*;

public class PG_20251229_카드_짝_맞추기 {

    class Solution {

        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        private Card[][] board;

        // private StringBuilder log = new StringBuilder();

        public int solution(int[][] board, int r, int c) {
            // for (int[] row : board) {
            //     log.append(Arrays.toString(row)).append('\n');
            // }

            this.board = new Card[board.length][board[0].length];
            Set<Card> initialRemainingCards = new HashSet<>();
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] != 0) {
                        Card card = new Card(row, col);
                        this.board[row][col] = card;
                        initialRemainingCards.add(card);
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            Queue<Moment> queue = new ArrayDeque<>();
            Map<Moment, Integer> momentToMoveCountMap = new HashMap<>();
            Moment initialMoment = new Moment(r, c, initialRemainingCards, null);
            queue.add(initialMoment);
            momentToMoveCountMap.put(initialMoment, 0);

            do {
                Moment moment = queue.remove();
                // log.append("removed:\n").append(moment).append('\n');
                Set<Card> remainingCards = moment.remainingCards;
                if (remainingCards.isEmpty()) {
                    answer = Math.min(answer, momentToMoveCountMap.get(moment));
                    continue;
                }
                Card activeCard = moment.activeCard;
                Card pointedCard = this.board[moment.row][moment.col];
                int newMoveCount = momentToMoveCountMap.get(moment) + 1;

                // enter
                // log.append("  enter:\n");
                if (pointedCard != null) {
                    if (activeCard == null) {
                        compareAndPut(
                                queue,
                                momentToMoveCountMap,
                                new Moment(moment.row, moment.col, remainingCards, pointedCard),
                                newMoveCount);
                    } else if (!activeCard.equals(pointedCard)
                            && board[activeCard.row][activeCard.col] == board[pointedCard.row][pointedCard.col]) {
                        Set<Card> newRemainingCards = new HashSet<>(remainingCards);
                        newRemainingCards.remove(activeCard);
                        newRemainingCards.remove(pointedCard);
                        compareAndPut(
                                queue,
                                momentToMoveCountMap,
                                new Moment(moment.row, moment.col, newRemainingCards, null),
                                newMoveCount);
                    }
                }

                // arrow
                // log.append("  arrow:\n");
                for (int[] direction : DIRECTIONS) {
                    int newRow = moment.row + direction[0];
                    int newCol = moment.col + direction[1];
                    if (isOutOfBounds(newRow, newCol)) {
                        continue;
                    }
                    compareAndPut(
                            queue,
                            momentToMoveCountMap,
                            new Moment(newRow, newCol, remainingCards, activeCard),
                            newMoveCount);
                }

                // ctrl arrow
                // log.append("  ctrl arrow:\n");
                for (int[] direction : DIRECTIONS) {
                    int newRow = moment.row + direction[0];
                    int newCol = moment.col + direction[1];
                    while (!isOutOfBounds(newRow, newCol)) {
                        Card card = new Card(newRow, newCol);
                        if (moment.remainingCards.contains(card)) {
                            break;
                        }
                        newRow += direction[0];
                        newCol += direction[1];
                    }
                    if (isOutOfBounds(newRow, newCol)) {
                        compareAndPut(
                                queue,
                                momentToMoveCountMap,
                                new Moment(newRow - direction[0], newCol - direction[1], remainingCards, activeCard),
                                newMoveCount);
                        continue;
                    }
                    compareAndPut(
                            queue,
                            momentToMoveCountMap,
                            new Moment(newRow, newCol, remainingCards, activeCard),
                            newMoveCount);
                }
            } while (!queue.isEmpty());

            // System.out.println(log);
            return answer;
        }

        private void compareAndPut(
                Queue<Moment> queue,
                Map<Moment, Integer> newMomentToMoveCountMap,
                Moment newMoment,
                int newMoveCount) {
            Integer moveCount = newMomentToMoveCountMap.get(newMoment);
            if (moveCount == null || moveCount.compareTo(newMoveCount) > 0) {
                // log.append("  ").append(newMoment).append('\n');
                queue.add(newMoment);
                newMomentToMoveCountMap.put(newMoment, newMoveCount);
            }
        }

        private boolean isOutOfBounds(int row, int col) {
            return row < 0 || board.length <= row
                    || col < 0 || board[row].length <= col;
        }

        private static class Card {

            public final int row;
            public final int col;

            private final int hashCode;

            public Card(int row, int col) {
                this.row = row;
                this.col = col;
                this.hashCode = Objects.hash(row, col);
            }

            @Override
            public boolean equals(Object o) {
                if (!(o instanceof Card)) {
                    return false;
                }
                if (this == o) {
                    return true;
                }
                Card other = (Card) o;
                return row == other.row && col == other.col;
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

            // @Override
            // public String toString() {
            //     return "[" + row
            //         + "," + col
            //         + "]";
            // }

        }

        private static class Moment {

            public final int row;
            public final int col;
            public final Set<Card> remainingCards;
            public final Card activeCard;

            private final int hashCode;

            public Moment(int row, int col, Set<Card> remainingCards, Card activeCard) {
                this.row = row;
                this.col = col;
                this.remainingCards = remainingCards;
                this.activeCard = activeCard;
                this.hashCode = Objects.hash(this.row, this.col, this.remainingCards, this.activeCard);
            }

            @Override
            public boolean equals(Object o) {
                if (!(o instanceof Moment)) {
                    return false;
                }
                if (this == o) {
                    return true;
                }
                Moment other = (Moment) o;
                return row == other.row && col == other.col
                        && Objects.equals(remainingCards, other.remainingCards)
                        && Objects.equals(activeCard, other.activeCard);
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

            // @Override
            // public String toString() {
            //     return "[" + row
            //         + "," + col
            //         + ",remainingCards=" + remainingCards
            //         + ",active=" + activeCard
            //         + "]";
            // }

        }

    }


}
