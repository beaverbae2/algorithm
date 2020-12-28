package A2020_12_25;

import java.util.*;

public class PGS_방문길이 {
	private int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};//UDRL
	private HashMap<Character,Integer> map;
	private boolean[][][][] visited;
	
	public int solution(String dirs) {
		//초기화
		visited = new boolean[11][11][11][11];
		map = new HashMap<>();
		
		map.put('U', 0);
		map.put('D', 1);
		map.put('R', 2);
		map.put('L', 3);
		
		
		int answer = 0;
		int r = 5, c = 5;//시작좌표
		for (int i = 0; i < dirs.length(); i++) {
			int d = map.get(dirs.charAt(i));
			
			int nr = r+directions[d][0];
			int nc = c+directions[d][1];
			
			if (nr<0||nr>10||nc<0||nc>10) continue;
			
			if (!visited[r][c][nr][nc] && !visited[nr][nc][r][c]) {
				visited[r][c][nr][nc] = true; 
				visited[nr][nc][r][c] = true; 
				
				answer++;
			}
			
			r = nr;
			c = nc;
		}
		
		
		return answer;
	}

	public static void main(String[] args) {
		PGS_방문길이 a = new PGS_방문길이();
		System.out.println(a.solution("ULURRDLLU"));
		a = new PGS_방문길이();
		System.out.println(a.solution("LULLLLLLU"));
	}
}
