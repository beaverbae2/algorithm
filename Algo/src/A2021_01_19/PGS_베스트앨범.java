package A2021_01_19;

import java.util.*;
/**
 * 
 * @author beaverbae
 *
 */

public class PGS_베스트앨범 {
	private HashMap<String, Integer> genre_num;
	private HashMap<Integer, Integer> genre_cnt;
	private List<Song>[] song_list;
	
	public int[] solution(String[] genres, int[] plays) {
		genre_num = new HashMap<>();
		genre_cnt = new HashMap<>();
		song_list  = new List[genres.length];
		for (int i = 0; i < song_list.length; i++) {
			song_list[i] = new ArrayList<>();
		}
		// <장르명, 장르 고유번호>
		for (int i = 0; i < genres.length; i++) {
			if (genre_num.get(genres[i]) == null) {
				genre_num.put(genres[i], i);
			}
			
			int num = genre_num.get(genres[i]);
			if (genre_cnt.get(num) == null) {
				genre_cnt.put(num, plays[i]);
			}else {
				int cnt = genre_cnt.get(num);
				genre_cnt.put(num, cnt+plays[i]);
			}
			
			song_list[num].add(new Song(i, plays[i]));
		}

		for (int i = 0; i < song_list.length; i++) {
			Collections.sort(song_list[i]);
		}	
		
		List<Genre> genre_list = new ArrayList<>();
		Iterator<Integer> iter = genre_cnt.keySet().iterator();
		
		while(iter.hasNext()) {
			int num = iter.next();
			int totalCnt = genre_cnt.get(num);
			
			genre_list.add(new Genre(num, totalCnt));
		}
		Collections.sort(genre_list);
		
		List<Integer> answer_list = new ArrayList<>();
		for (Genre g : genre_list) {
			int idx = g.num;
			
			if (song_list[idx].size() >=2) {
				answer_list.add(song_list[idx].get(0).num);
				answer_list.add(song_list[idx].get(1).num);
			}else if (song_list[idx].size() == 1) {
				answer_list.add(song_list[idx].get(0).num);
			}
		}
		
		int[] answer = new int[answer_list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = answer_list.get(i);
		}
		return answer;
	}
	
	static class Genre implements Comparable<Genre>{
		int num;
		int totalCnt;
		
		public Genre(int num, int totalCnt) {
			this.num = num;
			this.totalCnt = totalCnt;
		}

		@Override
		public String toString() {
			return "Genre [num=" + num + ", totalCnt=" + totalCnt + "]";
		}

		@Override
		public int compareTo(Genre o) {
			return Integer.compare(o.totalCnt, this.totalCnt);
		}
	}
	
	static class Song implements Comparable<Song>{
		int num;// 고유 번호
		int cnt;// 재생 수
		
		public Song(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Song [num=" + num + ", cnt=" + cnt + "]";
		}

		@Override
		public int compareTo(Song o) {
			if (this.cnt != o.cnt) return Integer.compare(o.cnt, this.cnt); 
			
			return Integer.compare(this.num, o.num);
		}
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new PGS_베스트앨범().solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 150, 800, 2500})));
	}
}
