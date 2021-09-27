package A2021_07_13;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 51MIN
 * 백준 질문 검색 참고
 * 어려웠던 부분
 * - BFS를 문제인줄 몰랐음 : 단순 구현으로 접근했더니 시간초과 발생 (중복 제거 때문)
 * - 문제 조건 똑바로 파악 : 샘터의 위치만 -1억 ~ 1억임, 집의 위치는 제한이 나타나 있지 않음
 * - 정답이 long 타입 발생 가능
 * 
 * @author beaverbae
 *
 */

public class BOJ_18513_샘터 {
	static int[] arr;
	static int N, M;
	static HashSet<Integer> visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		visited = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		System.out.println(bfs());
	}
	
	private static long bfs() {
		long result = 0;
		int cnt = 0;
		Queue<Node> q = new LinkedList<>();
		for (int n : arr) {
			q.offer(new Node(n, 0));
			visited.add(n);
		}
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			int left = cur.v - 1;
			if (!visited.contains(left)) {
				q.offer(new Node(left, cur.w + 1));
				visited.add(left);
				cnt++;
				result += (long) (cur.w + 1);
			}
			
			if (cnt == M) break;
			
			int right = cur.v + 1;
			if (!visited.contains(right)) {
				q.offer(new Node(right, cur.w + 1));
				visited.add(right);
				cnt++;
				result += (long) (cur.w + 1);
			}
			
			if (cnt == M) break;
		}

		return result;
	}
	
	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
