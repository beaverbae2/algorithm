package A2020_11_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17141_연구소2 {
	static int N,M, answer,blank;
	static int[][] map;
	static LinkedList<int[]> allViruses;
	static LinkedList<int[]> selectedViruses;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		allViruses = new LinkedList<>();
		selectedViruses = new LinkedList<>();
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					allViruses.add(new int[] {i,j});
					blank++;
				}else if(map[i][j]==0) blank++;
			}
		}
		
		combi(0,0);
		if(answer==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else System.out.println(answer);
	}

	private static void combi(int start, int r) {
		if(r==M) {
			//바이러스 퍼트리기
			answer = Math.min(bfs(), answer);
			
//			for(int[] elem : selectedViruses) {
//				System.out.print("["+elem[0]+", "+elem[1]+"] ");
//			}
//			System.out.println();
			return;
		}
		
		for (int i = start; i < allViruses.size(); i++) {
			selectedViruses.addLast(allViruses.get(i));
			combi(i+1, r+1);
			selectedViruses.removeLast();
		}
	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int local_blank = blank;
		
		for (int[] virus : selectedViruses) {
			q.offer(new int[] {virus[0], virus[1], 0});
			visited[virus[0]][virus[1]] = true;
			local_blank--;
		}
		
		if(local_blank==0) return 0;//check!!!
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int time = elem[2];
		
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
				
				if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]) continue;
			
				if(map[nr][nc]!=1) {
					q.offer(new int[] {nr, nc, time+1});
					visited[nr][nc] = true;
					local_blank--;
				}
			}
			
			if(local_blank==0) return time+1;
		}
		
		return Integer.MAX_VALUE;
	}
	
}
