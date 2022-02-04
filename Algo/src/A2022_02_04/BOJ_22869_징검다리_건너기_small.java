package A2022_02_04;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 20MIN
 * @author beaverbae
 *
 */

public class BOJ_22869_징검다리_건너기_small {
	static int N, K;
	static int[] stones;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		stones = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
	}
	
	private static String bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		q.offer(0);
		visited[0] = true;
		
		while (!q.isEmpty()) {
			int v = q.poll();
				
			if (v == N - 1) return "YES";
			
			for (int nv = v + 1; nv < N; nv++) {
				if (nv - v > K) break;
				if (visited[nv]) continue;
				
				if (isGo(v, nv)) {
					q.offer(nv);
					visited[nv] = true;
				}
			}
		}
		
		return "NO";
	}
	
	private static boolean isGo(int v, int nv) {
		if ((nv - v) * (1 + Math.abs(stones[v] - stones[nv])) > K) return false;
		
		return true;
	}
}
