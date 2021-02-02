package A2021_02_02;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */
public class BOJ_17780_새로운_게임 {
	static int[][] map;
	static List<Node>[][] board;
	static int N, K;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static Pair[] position;
	static boolean overSize;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		board = new List[N][N];
		position = new Pair[K + 1];
		overSize = false;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;

			board[r][c].add(new Node(i + 1, dir));
			position[i + 1] = new Pair(r, c);
		}

		int answer = 0;
		while (true) {

			if (answer > 1000) {
				answer = -1;
				break;
			}

			if (overSize)
				break;

			game();
			answer++;
		}

		System.out.println(answer);
	}

	private static void game() {
		for (int n = 1; n <= K; n++) {// 전체 말에 대해서 진행
			Pair p = position[n];
			int r = p.r;
			int c = p.c;

			Node node = board[r][c].get(0);
			int num = node.num;
			if (num != n)
				continue; // 바닥에 말이 없는 경우 pass

			int dir = node.dir;
			int nr = r + dirs[dir][0];
			int nc = c + dirs[dir][1];

			if (isTurn(nr, nc)) {// 방향 바꾸는 경우
				dir = turn(dir); // 방향 변환
				nr = r + dirs[dir][0];
				nc = c + dirs[dir][1];

				if (isTurn(nr, nc)) {// 방향 바꿨는데도 또 걸리는 경우
					node.dir = dir;
				} else {
					node.dir = dir;// 여기도 변환을...ㅠㅠ
					move(r, c, nr, nc, dir);
					if (checkSize(nr, nc)) {
						overSize = true;
						break;
					}
				}
			} else {
				move(r, c, nr, nc, dir);
				if (checkSize(nr, nc)) {
					overSize = true;
					break;
				}
			}

		}
	}

	public static boolean checkSize(int r, int c) {
		return board[r][c].size() >= 4;
	}

	public static void move(int r, int c, int nr, int nc, int dir) {
		ArrayList<Node> moveNodes = new ArrayList<>();

		if (map[nr][nc] == 0) {// 흰
			for (int i = 0; i < board[r][c].size(); i++) {
				Node node = board[r][c].get(i);
				moveNodes.add(new Node(node.num, node.dir));
				position[node.num] = new Pair(nr, nc);
			}
			board[nr][nc].addAll(moveNodes);

		} else if (map[nr][nc] == 1) {// 빨
			for (int i = board[r][c].size() - 1; i >= 0; i--) {
				Node node = board[r][c].get(i);
				moveNodes.add(new Node(node.num, node.dir));
				position[node.num] = new Pair(nr, nc);
			}
			board[nr][nc].addAll(moveNodes);
		}

		// 정리
		board[r][c] = new ArrayList<>();

	}

	// 방향 전환 : 맵 밖 or 파란색
	public static boolean isTurn(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 2) {
			return true;
		}
		return false;
	}

	public static int turn(int dir) {
		if (dir == 0) {
			dir = 1;
		} else if (dir == 1) {
			dir = 0;
		} else if (dir == 2) {
			dir = 3;
		} else if (dir == 3) {
			dir = 2;
		}
		return dir;
	}

	static class Node {
		int num, dir;// 번호, 방향

		public Node(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", dir=" + dir + "]";
		}
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
}
