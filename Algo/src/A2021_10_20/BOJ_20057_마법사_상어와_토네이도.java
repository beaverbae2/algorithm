package A2021_10_20;

import java.util.*;
import java.io.*;

/**
 * 66MIN
 * Simulation
 * @author beaverbae
 *
 */

public class BOJ_20057_마법사_상어와_토네이도 {
	static int N;
	static int[][] board;
	static ArrayList<Node> path = new ArrayList<>();
	static int[][] dirs = {{0,-1}, {1,0}, {0,1}, {-1,0}};
	static HashMap<String, Double>[] map;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new StringReader(src));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		makePath();
		makeMap();
		
		for (int i = 0; i < path.size(); i++) {
			Node cur = path.get(i);
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;
			
			storm(r, c, d);
		}
		
		System.out.println(ans);
	}
	
	private static void makeMap() {
		map = new HashMap[4];
		for (int i = 0; i < map.length; i++) {
			map[i] = new HashMap<>();
		}
		
		for (int d = 0; d < dirs.length; d++) {
			if (d == 0) {
				map[d].put(-2+" "+0, 0.02);
				
				map[d].put(-1+" "+-1, 0.1);
				map[d].put(-1+" "+0, 0.07);
				map[d].put(-1+" "+1, 0.01);
				
				map[d].put(0+" "+(-2), 0.05);
				map[d].put(0+" "+(-1), 0.55);
				
				map[d].put(1+" "+-1, 0.1);
				map[d].put(1+" "+0, 0.07);
				map[d].put(1+" "+1, 0.01);

				map[d].put(2+" "+0, 0.02);
			} else if (d == 1) {// 아래
				map[d].put(-1+" "+(-1), 0.01);
				map[d].put(-1+" "+1, 0.01);
				
				map[d].put(0+" "+(-2), 0.02);
				map[d].put(0+" "+(-1), 0.07);
				map[d].put(0+" "+1, 0.07);
				map[d].put(0+" "+2, 0.02);
				
				map[d].put(1+" "+(-1), 0.1);
				map[d].put(1+" "+0, 0.55);
				map[d].put(1+" "+1, 0.1);
				
				map[d].put(2+" "+0, 0.05);
			} else if (d == 2) {
				map[d].put(-2+" "+0, 0.02);
				
				map[d].put(-1+" "+-1, 0.01);
				map[d].put(-1+" "+0, 0.07);
				map[d].put(-1+" "+1, 0.1);

				map[d].put(0+" "+2, 0.05);
				map[d].put(0+" "+1, 0.55);
				
				map[d].put(1+" "+-1, 0.01);
				map[d].put(1+" "+0, 0.07);
				map[d].put(1+" "+1, 0.1);
				
				map[d].put(2+" "+0, 0.02);
			} else {// 위
				map[d].put(1+" "+(-1), 0.01);
				map[d].put(1+" "+1, 0.01);
				
				map[d].put(0+" "+(-2), 0.02);
				map[d].put(0+" "+(-1), 0.07);
				map[d].put(0+" "+1, 0.07);
				map[d].put(0+" "+2, 0.02);
				
				map[d].put(-1+" "+(-1), 0.1);
				map[d].put(-1+" "+0, 0.55);
				map[d].put(-1+" "+1, 0.1);
				
				map[d].put(-2+" "+0, 0.05);
			}
		}
	}

	private static void storm(int r, int c, int d) {
		int sand = board[r][c];
		int sum = 0;
		int alpha_r = -1;
		int alpha_c = -1;
		int alpha = 0;
		
		board[r][c] = 0;
		HashMap<String, Double> temp_map = map[d];
		for (String str : temp_map.keySet()) {
			int nr, nc;
			double v;
			String[] s = str.split(" ");
			nr = r + Integer.parseInt(s[0]);
			nc = c + Integer.parseInt(s[1]);
			v = temp_map.get(str);
			
			if (v == 0.55) {
				alpha_r = nr;
				alpha_c = nc;
				continue;
			}
			
			int move_sand = (int) (sand * v);
			if (!isIn(nr, nc)) ans += move_sand;
			else board[nr][nc] += move_sand;
			
			sum += move_sand;
		}
		
		alpha = sand - sum;
		if (!isIn(alpha_r, alpha_c)) {
			ans += alpha;
		} else {
			board[alpha_r][alpha_c] += alpha;
		}
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static void makePath() {
		int r = N/2;
		int c = N/2;
		int d = 0;
		int n = 1;
		int cnt = 0;
		
		while (true) {
			r += dirs[d][0];
			c += dirs[d][1];
			
			if (c == -1) break;
		
			path.add(new Node(r, c, d));
			cnt++;
			
			if (cnt == n) {
				d++;
				cnt = 0;
				if (d % 2 == 0) n++;
				if (d == dirs.length) d = 0;
			}
		}
	}

	private static void print() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
	
	static class Node {
		int r, c, d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
	}
	
	static String src = "5\r\n"
			+ "0 0 0 0 0\r\n"
			+ "0 0 0 0 0\r\n"
			+ "0 10 0 0 0\r\n"
			+ "0 0 0 0 0\r\n"
			+ "0 0 0 0 0";
}
