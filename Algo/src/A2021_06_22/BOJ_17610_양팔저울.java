package A2021_06_22;

import java.util.*;
import java.io.*;

/**
 * Brute force
 * 50MIN
 * 어려웠던 부분
 * - 저울에서 빈그릇에 x개의 추가 있다면, 반대편엔 1개이상 (k-x)개 미만의 추가 있어야함을 구현하기 어려웠음
 * 
 * @author beaverbae
 * 
 *
 */

public class BOJ_17610_양팔저울 {
	static boolean[] checked;
	static boolean[] visited;
	static int[] arr;
	static int K, S, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		arr = new int[K];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			S += arr[i];
		}
		
		checked = new boolean[S+1];
		ans = S;
		
		for (int i = 0; i < K; i++) {
			visited = new boolean[K];
			dfs1(i, 0, 0, 0);
		}
		
		System.out.println(ans);
	}
	
	// 빈 그릇에 올라가는 추 (0개 이상 K-1개 이하의 추 필요)
	private static void dfs1(int n, int cnt, int start, int sum) {
		if (cnt == n) {
			for (int i = 1; i <= arr.length; i++) {
				dfs2(i, 0, 0, sum*(-1));
				
				if (n + i == arr.length) break;
			}
			return;
		}
		
		for (int i = start; i < arr.length; i++) {
			visited[i] = true;
			dfs1(n, cnt + 1, i + 1, sum + arr[i]);
			visited[i] = false;
		}
	}

	// 반대편에 올라가는 추 (1개이상, K개 이하의 추 필요)
	private static void dfs2(int n, int cnt, int start, int sum) {
		if (cnt == n) {
			if (sum > 0 && !checked[sum]) {
				checked[sum] = true;
				ans--;
			}
			
			return;
		}
		
		for (int i = start; i < arr.length; i++) {
			if (visited[i]) continue;
			
			dfs2(n, cnt+1, i+1, sum+arr[i]);
		}
	}
}
