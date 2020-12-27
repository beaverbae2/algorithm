package A2020_12_27;

import java.util.*;

public class PGS_삼각달팽이 {
	
	public int[] solution(int n) {
		int[][] map = new int[n][n];
		int[][] dirs = {{1,0},{0,1},{-1,-1}};
		List<Integer> list = new ArrayList<>();
		
		int cnt = 0;
		int r = -1;
		int c = 0;
		int d = 0;
		int limit = (n*(n+1))/2;
		
		while(true) {
			if(cnt == limit) break;
			
			while(true) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
				
				if (nr<0||nr>=n||nc<0||nc>=n||map[nr][nc]!=0) break; 
				r = nr;
				c = nc;
				cnt++;
				map[nr][nc] = cnt;
				if(cnt==limit) break;
			}
			d++;
			if (d == 3) d = 0;
		}
		
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j]!=0) {
					list.add(map[i][j]);
				}else break;
			}
		}
		
		int[] answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
	}

	public static void main(String[] args) {
		PGS_삼각달팽이 a = new PGS_삼각달팽이();
		System.out.println(Arrays.toString(a.solution(4)));
		a = new PGS_삼각달팽이();
		System.out.println(Arrays.toString(a.solution(5)));
		a = new PGS_삼각달팽이();
		System.out.println(Arrays.toString(a.solution(6)));
	}
}
