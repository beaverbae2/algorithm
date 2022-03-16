package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 18MIN
 * @author beaverbae
 * - 완전이진트리의 특징
 * - 후위순회 -> 트리 만들기 
 */

public class BOJ_9934_완전_이진_트리 {
	static int N, K;
	static int[] tree;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = (int) Math.pow(2, K) - 1;
		tree = new int[N];
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int v = 0; v < N; v++) {
			tree[v] = Integer.parseInt(st.nextToken());
		}
		
		bfs();
		System.out.println(sb);
	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, N-1, 0});
		
		int l = 0;
		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int v = (arr[0] + arr[1]) / 2;
			if (arr[2] > l) {
				sb.append("\n");
				l = arr[2];
			}
			
			sb.append(tree[v]).append(" ");
			if (arr[0] == arr[1]) continue;
			
			q.offer(new int[] {arr[0], v-1, l+1});
			q.offer(new int[] {v+1, arr[1], l+1});
		}
	}
}
