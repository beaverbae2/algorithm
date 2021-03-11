package A2021_03_12;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 65MIN
 * 오래 걸린 이유 : map[i][j] == '1' 이라고 해야되는데 map[i][j] == 1 이라고 해버림
 * 풀이 요령 : 3개의 좌표의 가운데 좌표로 좌표를 대표하고, 대신 수직인지 수평인지를 나타내는 status라는 변수 사용
 * @author beaverbae
 *
 */

public class BOJ_1938_통나무_옮기기 {
	static int N;
	static char[][] map;
	static int sr, sc, er, ec;// 시작과 끝 '가운데' 좌표
	static int start_status, end_status;// status : 0 -> 가로, 1-> 세로
	
	// 이동시 검사해야 하는 좌표 : 가로 일때 세로 일때와 각각 상하좌우 이동
	static int[][][][] dirs = {{{{-1,-1},{-1,0},{-1,1}},
							    {{1,-1},{1,0},{1,1}},
							    {{0,0},{0,1},{0,2}},
							    {{0,-2},{0,-1},{0,0}}},
							 	
							   {{{-2,0},{-1,0},{0,0}},
								{{0,0},{1,0},{2,0}},
								{{-1,-1},{0,-1},{1,-1}},
								{{-1,1},{0,1},{1,1}}}};
	
	// 회전할 때 검사해야 하는 좌표 : 8방 좌표
	static int[][] r_dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		List<Pair> start_list = new ArrayList<>();
		List<Pair> end_list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == 'B') {
					start_list.add(new Pair(i, j));
					map[i][j] = '0';
				} else if (map[i][j] == 'E') {
					end_list.add(new Pair(i,j));
					map[i][j] = '0';
				}
			}
		}
		
		Pair p = start_list.get(1);
		
		if (start_list.get(0).r == p.r) start_status = 0;
		else start_status = 1;
		
		sr = p.r;
		sc = p.c;
		
		p = end_list.get(1);
		
		if (end_list.get(0).r == p.r) end_status = 0;
		else end_status = 1;
		
		er = p.r;
		ec = p.c;
	
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][N][2];
		
		q.offer(new Node(sr, sc, start_status, 0));
		visited[sr][sc][start_status] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int status = node.status;
			int depth = node.depth;
			
			// 종료 조건
			if (r == er && c == ec && status == end_status) return depth;
			
			// 이동
			for (int i = 0; i < 4; i++) {
				boolean isMove = true;
				// 다음에 이동할 '가운데' 좌표
				int next_r = r + dirs[status][i][1][0];
				int next_c = c + dirs[status][i][1][1];
				
				// 다음 '가운데' 좌표가 이동불가 하거나, 이미 방문한 경우 pass
				if (!isInMap(next_r, next_c) || visited[next_r][next_c][status]) continue;
				
				// 통나무가 위치할 다음 세 좌표 구함
				for (int j = 0; j < 3; j++) {
					int nr = r + dirs[status][i][j][0];
					int nc = c + dirs[status][i][j][1];
					
					// 이동할 세 좌표 중 한 곳이라도 갈 수 없으면 이동 불가
					if (!isInMap(nr, nc) || map[nr][nc] == '1') {
						isMove = false;
						break;
					}
				}
				
				if (!isMove) continue; 
				
				// 이동 가능한 경우
				visited[next_r][next_c][status] = true;
				q.offer(new Node(next_r, next_c, status, depth+1));
			}
			
			// 회전
			boolean isRotate = true;
			int next_status = (status+1) % 2;// 다음 상태 구하기
			
			// 이미 방문한 경우 pass
			if (visited[r][c][next_status]) continue;
			
			for (int d = 0; d < r_dirs.length; d++) {
				int nr = r + r_dirs[d][0];
				int nc = c + r_dirs[d][1];
				
				// 여덟 좌표중 한 곳이라도 이동 못하는 경우 회전도 불가
				if (!isInMap(nr, nc) || map[nr][nc] == '1') {
					isRotate = false;
					break;
				}
			}
			
			if (!isRotate) continue;
			
			// 회전이 가능한 경우
			visited[r][c][next_status] = true;
			q.offer(new Node(r, c, next_status, depth+1));
		}
		
		return 0;// 이동 불가한 경우
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}


	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class Node {
		int r, c, status, depth;

		public Node(int r, int c, int status, int depth) {
			this.r = r;
			this.c = c;
			this.status = status;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", status=" + status + ", depth=" + depth + "]";
		}
	}
}
