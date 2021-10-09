package A2021_10_09;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * 메모리 최적화가 어려웠습니다..
 */

public class BOJ_13908_비밀번호_solution {
	static int N, M, ans;
	static int[] cnts;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[10];
		cnts = new int[M];
		
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				cnts[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(ans);
	}
	
	private static void dfs(int cnt) {
		if (cnt == N) {
			if (check()) ans++;
			return;
		}
		
		for (int n = 0; n < 10; n++) {
			arr[n]++;
			dfs(cnt + 1);
			arr[n]--;
		}
	}
	
	private static boolean check() {
		for (int n : cnts) {
			if (arr[n] == 0) return false;
		}
		return true;
	}
}
