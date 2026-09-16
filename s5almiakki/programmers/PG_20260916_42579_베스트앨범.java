import java.util.*;

public class PG_20260916_42579_베스트앨범 {

	class Solution {

		public int[] solution(String[] genres, int[] plays) {
			Map<String, Stat> genreMap = new HashMap<>();
			for (int id = 0; id < genres.length; id++) {
				Stat s = genreMap.get(genres[id]);
				if (s == null) {
					s = new Stat();
					genreMap.put(genres[id], s);
				}
				s.sum += plays[id];
				s.songs.add(new int[] { id, plays[id] });
			}
			return genreMap.values().stream()
					.sorted((stat1, stat2) -> Integer.compare(stat2.sum, stat1.sum))
					.flatMapToInt(stat -> stat.songs.stream()
							.sorted((song1, song2) -> Integer.compare(song2[1], song1[1]))
							.limit(2)
							.mapToInt(song -> song[0]))
					.toArray();
		}

		static class Stat {

			int sum = 0;
			List<int[]> songs = new ArrayList<>();

		}

	}

}
