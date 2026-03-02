import java.util.*;
import java.util.stream.*;

public class PG_20260302_42579_베스트앨범 {

    class Solution {

        public int[] solution(String[] genres, int[] plays) {
            Map<String, GenreSong> genreToSongsMap = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                GenreSong genreSong = genreToSongsMap.get(genres[i]);
                if (genreSong == null) {
                    genreSong = new GenreSong();
                    genreToSongsMap.put(genres[i], genreSong);
                }
                genreSong.playCountSum += plays[i];
                genreSong.songs.add(new int[] { i, plays[i] });
            }

            List<GenreSong> genreSongs = genreToSongsMap.values().stream()
                    .sorted((o1, o2) -> Integer.compare(o2.playCountSum, o1.playCountSum))
                    .collect(Collectors.toList());
            List<Integer> answer = new ArrayList<>();
            for (GenreSong genreSong : genreSongs) {
                List<int[]> songs = genreSong.songs;
                songs.sort((o1, o2) -> Integer.compare(o2[1], o1[1]));
                int bound = Math.min(2, songs.size());
                for (int i = 0; i < bound; i++) {
                    answer.add(songs.get(i)[0]);
                }
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }

        static class GenreSong {

            int playCountSum = 0;
            List<int[]> songs = new ArrayList<>();

        }

    }

}
