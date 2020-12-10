package A2020_12_09;

import java.util.*;

public class PGS_베스트앨범 {
	public static int[] solution(String[] genres, int[] plays) {
        HashMap<String,Integer> genreMap = new HashMap<>();
        Genre[] genreTotalArr;
        List<Song>[] genreSongList;
        
        //전체 장르개수 계산
        int genre_num = 0;//장르번호
        for (String genre : genres) {
			if(genreMap.get(genre) == null) {
				genreMap.put(genre, genre_num);
				genre_num++;
			}
		}
        genreSongList = new List[genreMap.size()];
        genreTotalArr = new Genre[genreMap.size()];
        
        for (int i = 0; i < genreSongList.length; i++) {
			genreSongList[i] = new ArrayList<>();
		}
        
        //진행
        for (int i = 0; i < genres.length; i++) {
			int num = i;
			String genre = genres[i];
			genre_num = genreMap.get(genre);
			int play = plays[i];
			
			//genre의 총 재생횟수 계산
			if(genreTotalArr[genre_num] == null) {
				genreTotalArr[genre_num] = new Genre(genre_num, play);
			}else {
				int temp = genreTotalArr[genre_num].play;
				genreTotalArr[genre_num] = new Genre(genre_num, temp+play);
			}
			
			//노래 리스트 계산
			genreSongList[genre_num].add(new Song(num, play));
		}
        
        Arrays.sort(genreTotalArr, new Comparator<Genre>() {

			@Override
			public int compare(Genre o1, Genre o2) {
				return Integer.compare(o2.play, o1.play);
			}
		});
        
        for (int i = 0; i < genreSongList.length; i++) {
        	Collections.sort(genreSongList[i], new Comparator<Song>() {

				@Override
				public int compare(Song o1, Song o2) {
					if(o1.play!=o2.play) {
						return Integer.compare(o2.play, o1.play);
					}else return Integer.compare(o1.num, o2.num);
				}
			});
        }
//        for (int i = 0; i < genreTotalArr.length; i++) {
//			System.out.println(genreTotalArr[i]);
//		}
//        for (int i = 0; i < genreSongList.length; i++) {
//			System.out.println(genreSongList[i]);
//		}
        
        ArrayList<Integer> selected_song = new ArrayList<>();
        for (int i = 0; i < genreTotalArr.length; i++) {
			Genre g = genreTotalArr[i];
			int g_num = g.num;
			
			if(genreSongList[g_num].size()<2) {//2개미만
				for (int j = 0; j < genreSongList[g_num].size(); j++) {
					selected_song.add(genreSongList[g_num].get(j).num);
				}
			}else {
				for (int j = 0; j < 2; j++) {
					selected_song.add(genreSongList[g_num].get(j).num);
				}
			}
		}
        
        int[] answer = new int[selected_song.size()];
        for (int i = 0; i < answer.length; i++) {
			answer[i] = selected_song.get(i);
		} 
        return answer;
    }
	
	static class Song{
		int num;//고유번호
		int play;//플레이 횟수
		
		public Song(int num, int play) {
			this.num = num;
			this.play = play;
		}

		@Override
		public String toString() {
			return "Song [num=" + num + ", play=" + play + "]";
		}
	}
	
	static class Genre{
		int num;
		int play;
		
		public Genre(int num, int play) {
			this.num = num;
			this.play = play;
		}

		@Override
		public String toString() {
			return "Genre [num=" + num + ", play=" + play + "]";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 150, 800, 2500})));
	}
}
