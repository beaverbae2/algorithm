package A2021_01_18;

import java.util.*;
import java.io.*;
/**
 * Simulation (Combination + BFS)
 * @author beaverbae
 * 
 */

public class BOJ_17135_캐슬디펜스 {
	static int R, C, D;
	static int answer;
	static int[][] init_map;
	static int[][] map;
	static int[][] next_map;
	static int[][] dirs = {{0,-1},{-1,0},{0,1}};//왼, 위, 오
	static LinkedList<Integer> bowers;//궁수
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	
		init_map = new int[R+1][C];
		bowers = new LinkedList<>();
		answer = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				init_map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0, 0);
		System.out.println(answer);
	}
	
	private static void combination(int start, int r) {
		if (r == 3) {// 궁수 3명 뽑음
//			System.out.println(bowers); 잘 뽑혔는지 확인
			game();// 게임 시작
			return;
		}
		
		for (int i = start; i < C; i++) {
			bowers.addLast(i);
			combination(i+1, r+1);
			bowers.removeLast();
		}
		
	}

	private static void game() {
		map = new int[R+1][C];
		deepcopy(init_map, map);
		int killed = 0;// 죽인 적의 수
		
		// R초 동안 반복
		for (int time = 0; time < R; time++) {
			// 사전 작업
			next_map = new int[R+1][C];
			deepcopy(map, next_map);
			
			// 궁수의 공격
			for (int bower_c : bowers) {
				killed += attack(bower_c);
			}
			
			// 이동
			map = next_map;
			for (int r = R-2; r >= 0; r--) {
				for (int c = 0; c < C; c++) {
					map[r+1][c] = map[r][c];
					map[r][c] = 0;
				}
			}
		}
		
		// 최종적으로 죽인 적의 수 구하기
		answer = Math.max(answer, killed);
	}

	private static int attack(int bower_c) {
		int bower_r = R;
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R + 1][C];
		q.offer(new Node(bower_r, bower_c, 0));
		visited[bower_r][bower_c] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int depth = node.depth;

			if (depth == D)
				return 0;

			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];

				if (!isInMap(nr, nc) || visited[nr][nc])
					continue;

				if (map[nr][nc] == 1) {// 적 찾음
					if (next_map[nr][nc] == 1) {// 아직 살아있는 적
						next_map[nr][nc] = 0;
						return 1;
					}else {// 이미 다른 궁수가 죽인 적
						return 0;
					}
				}

				q.offer(new Node(nr, nc, depth + 1));
				visited[nr][nc] = true;
			}
		}
		
		return 0;
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr < R+1 && nc >= 0 && nc < C;
	}

	private static void deepcopy(int[][] src, int[][] dest) {
		for (int i = 0; i < R+1; i++) {
			for (int j = 0; j < C; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}

	static class Node {
		int r, c, depth;

		public Node(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", depth=" + depth + "]";
		}
	}
}
