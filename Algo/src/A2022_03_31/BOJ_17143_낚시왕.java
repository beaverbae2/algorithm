package A2022_03_31;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 81MIN
 * @author beaverbae
 * 
 */

public class BOJ_17143_낚시왕 {
	static PriorityQueue<Node>[][] board;
	static int[][] dirs = {{}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int R, C, M, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new PriorityQueue[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				board[r][c] = new PriorityQueue<>();
			}
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
		
			board[r][c].add(new Node(s, d, z));
		}
		
		exec();
		System.out.println(ans);
	}
	
	private static void exec() {
		int fisher = -1;
		while (true) {
			// 낚시왕 이동
			fisher++;
			
			if (fisher == C) break;
			// 상어 잡기
			catchShark(fisher);
			moveSharks();
			arrageSharks();
		}
	}

	private static void arrageSharks() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c].size() < 2) continue;
				
				PriorityQueue<Node> pq = new PriorityQueue<>();
				pq.add(board[r][c].poll());
				board[r][c] = pq;
			}
		}
	}

	private static void moveSharks() {
		PriorityQueue<Node>[][] nextBoard = new PriorityQueue[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				nextBoard[r][c] = new PriorityQueue<>();
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c].isEmpty()) continue;
				
				Node s = board[r][c].poll();
				int dist = 0;
				
				if (s.d <= 2) dist = s.s % ((R - 1) * 2);
				else dist = s.s % ((C - 1) * 2);
				
				int nr = r, nc = c, d = s.d;
				for (int k = 0; k < dist; k++) {
					nr += dirs[d][0];
					nc += dirs[d][1];
					
					if (!isIn(nr, nc)) {
						d = changeDir(d);
						nr += 2 * dirs[d][0];
						nc += 2 * dirs[d][1];
					}
				}
				
				nextBoard[nr][nc].add(new Node(s.s, d, s.z));
			}
		}
		
		board = nextBoard;
	}

	private static int changeDir(int d) {
		if (d == 1) return 2;
		else if (d == 2) return 1;
		else if (d == 3) return 4;
		return 3;
	}

	private static void catchShark(int c) {
		for (int r = 0; r < R; r++) {// 땅에서 가까운 순서부터
			if (board[r][c].isEmpty()) continue;// 상어가 없는 경우
			
			ans += board[r][c].poll().z;// 상어 사라지고, 상어 크기만큼 점수 획
			break;
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	static class Node implements Comparable<Node>{
		int s, d, z;

		public Node(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Node [s=" + s + ", d=" + d + ", z=" + z + "]";
		}
		
		@Override
		public int compareTo(Node o) {
			return o.z - this.z;
		}
	}
}
