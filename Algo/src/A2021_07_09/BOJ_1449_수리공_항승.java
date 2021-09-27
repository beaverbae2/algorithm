package A2021_07_09;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 19MIN
 * 입력으로 정렬된 배열이 들어오는 줄 알았다
 * @author beaverbae
 *
 */

public class BOJ_1449_수리공_항승 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, L;
		int[] arr;
		boolean[] visited;
		int ans = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new boolean[1001];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
	
		for (int n : arr) {
			if (visited[n]) continue;
			
			for (int i = n; i < n + L; i++) {
				if (i > 1000) break;
				
				visited[i] = true;
			}
			ans++;
		}
		
		System.out.println(ans);
	}
}
