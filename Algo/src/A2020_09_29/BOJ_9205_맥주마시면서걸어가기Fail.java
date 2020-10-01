package A2020_09_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기Fail {
	static boolean[] visited;
	static Pair[] map;
	static int N;
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new Pair[N+2];
			int x1 = 0;
			int y1 = 0;
			
			for (int i = 0; i < N+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(i==0) {
					x1 = x;
					y1 = y;
				}
				
				map[i] = new Pair(x, y);
			}
			
			System.out.println(x1+", "+y1);
			int index = 0;
			while(true) {
				System.out.println(index);
				if(index==N+1) {
					sb.append("happy").append('\n');
					break;
				}
				
				int min = 987654321;
				int min_index = 0;
				int x2 = 0, y2 = 0;
				
				for (int i = 0; i < map.length; i++) {
					if(i==index) continue;
					
					int distance = Math.abs(x1-map[i].x)+Math.abs(y1-map[i].y);
					if(distance<min) {
						min = distance;
						min_index = i;
						x2 = map[i].x;
						y2 = map[i].y;
					}
				}
				
				if(min>1000) {
					sb.append("sad").append('\n');
					break;
				}else {
					x1 = x2;
					y1 = y2;
					index = min_index;
				}
			}
		}
		System.out.println(sb);
	}
}
