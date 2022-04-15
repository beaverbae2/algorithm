package A2022_04_15;

import java.util.*;
import java.io.*;

/**
 * Simulation(BFS)
 * @author beaverbae
 * @see https://yabmoons.tistory.com/97
 *
 */

public class BOJ_9328_열쇠 {
	static char[][] board;
	static boolean[] keys;
	static int R, C;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			board = new char[R+2][C+2];
			keys = new boolean[26];
			
			for (int r = 1; r < R+1; r++) {
				String src = br.readLine();
				for (int c = 1; c < C+1; c++) {
					board[r][c] = src.charAt(c-1);
				}
			}
			
			String inputkeys = br.readLine();
			for (int i = 0; i < inputkeys.length(); i++) {
				char ch = inputkeys.charAt(i);
				if (ch == '0') continue;
				keys[ch-'a'] = true;
			}
			
			bfs(0, 0);
			sb.append(bfs(0, 0)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(int sr, int sc) {
		int ans = 0;
		Queue<Node> q = new LinkedList<>();
		Queue<Node>[] door = new Queue[26];
		for (int i = 0; i < door.length; i++) {
			door[i] = new LinkedList<>();
		}
		boolean[][] visited = new boolean[R+2][C+2];
		
		q.offer(new Node(sr, sc));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (nr < 0 || nr >= R+2 || nc < 0 || nc >= C+2 || board[nr][nc] == '*' || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				
				if (board[nr][nc] >= 'A' && board[nr][nc] <= 'Z') {
					if (keys[board[nr][nc] -'A']) q.offer(new Node(nr, nc));
					else door[board[nr][nc] -'A'].offer(new Node(nr, nc));
				} else if (board[nr][nc] >= 'a' && board[nr][nc] <= 'z') {
					q.offer(new Node(nr, nc));
					if (!keys[board[nr][nc]-'a']) {
						keys[board[nr][nc]-'a'] = true;
						while (!door[board[nr][nc]-'a'].isEmpty()) {
							q.offer(door[board[nr][nc]-'a'].poll());
						}
					}
				} else {
					q.offer(new Node(nr, nc));
					if (board[nr][nc] == '$') ans++;
				}
			}
		}
		
		return ans;
	}
	
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}
	}
}
