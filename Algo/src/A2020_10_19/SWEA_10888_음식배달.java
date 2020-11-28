package A2020_10_19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10888_음식배달 {
	static int[][] map;
	static boolean[][] selected;
	static ArrayList<int[]> restaurants;
	static int N,answer,rest_num;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	
	static String src = "3\r\n" + 
			"5\r\n" + 
			"0 0 0 0 0\r\n" + 
			"0 1 1 1 0\r\n" + 
			"0 1 10 1 0\r\n" + 
			"0 1 1 1 0  \r\n" + 
			"0 0 0 0 0\r\n" + 
			"8\r\n" + 
			"0 0 0 0 0 0 0 0\r\n" + 
			"0 1 1 1 0 0 0 0\r\n" + 
			"0 1 10 1 0 0 0 0\r\n" + 
			"0 1 1 1 0 0 0 0\r\n" + 
			"0 0 0 0 1 1 1 0\r\n" + 
			"0 0 0 0 1 10 1 0\r\n" + 
			"0 0 0 0 1 1 1 0\r\n" + 
			"0 0 0 0 0 0 0 0\r\n" + 
			"8\r\n" + 
			"0 0 0 0 0 0 0 0\r\n" + 
			"0 1 1 1 0 0 0 0\r\n" + 
			"0 1 20 1 0 0 0 0\r\n" + 
			"0 1 1 1 0 0 0 0\r\n" + 
			"0 0 0 0 1 1 1 0\r\n" + 
			"0 0 0 0 1 30 1 0\r\n" + 
			"0 0 0 0 1 1 1 0\r\n" + 
			"0 0 0 0 0 0 0 0\r\n" + 
			"";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new StringReader(src));
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			selected = new boolean[N][N];
			restaurants = new ArrayList<>();
			answer = Integer.MAX_VALUE;
			rest_num = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>=2) {
						restaurants.add(new int[] {i,j});
					}
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			
			select(0);//음식 배달점 선택
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void select(int cnt) {
		if(cnt==restaurants.size()) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if(selected[i][j]) {
//						System.out.print("("+i+", "+j+"), ");
//					}
//				}
//			}
//			System.out.println();
			calcDistance();
			
			return;
		}
		
		int[] elem = restaurants.get(cnt);
		int r = elem[0];
		int c = elem[1];
		selected[r][c] = true;
		select(cnt+1);
		selected[r][c] = false;
		select(cnt+1);
		
	}

	private static void calcDistance() {
		int sum = 0;
		boolean[][] count = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1) {
					Queue<int[]> q = new LinkedList<>();
					boolean[][] visited = new boolean[N][N];
					q.offer(new int[] {i,j,0});
					visited[i][j] = true;
				
					while(!q.isEmpty()) {
						int[] elem = q.poll();
						int r = elem[0];
						int c = elem[1];
						int dist = elem[2];
					
						if(selected[r][c]) {
							sum+=dist;
							if(!count[r][c]) count[r][c] = true;
							break;
						}
						
						for (int d = 0; d < dirs.length; d++) {
							int nr = r+dirs[d][0];
							int nc = c+dirs[d][1];
						
							if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]) continue;
						
							q.offer(new int[] {nr, nc,dist+1});
							visited[nr][nc] = true;
						}
						
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(count[i][j]) {
					sum+=map[i][j];
				}
			}
		}
		
		if(sum!=0) {
			answer = Math.min(answer, sum);
		}
	}
}
