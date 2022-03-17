package A2022_03_17;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 2H
 * @author beaverbae
 * - Map : 메모리초과  -> 배열로 변경
 * - 트리만드는 로직이 너무 오래 걸림
 * 
 */

public class BOJ_9489_사촌 {
	static int[] parent;
	static int k, pk, ppk;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			if (N == 0 && K == 0) break;
			
		    int[] arr = new int[N];
		    parent = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (arr[i] == K) k = i;
			}

			if (N == 1) {
				sb.append(0).append("\n");
				continue;
			}
			
			parent[0] = -1;
			int v = arr[1]-1, i = 1, j = 0;
			
			while (i < N) {
				if (v+1 == arr[i]) {
					v = arr[i];
					parent[i] = j;
					i++;
				} else {
					v = arr[i]-1;
					j++;
				}
			}
			
			pk = parent[k];
			if (pk == 0 || pk == -1) {
				sb.append(0).append("\n");
				continue;
			}
			
			ppk = parent[pk];
			if (ppk == -1) {
				sb.append(0).append("\n");
				continue;
			}
			
			int ans = 0;
			for (int c = 0; c < N; c++) {
				if (isOk(c)) ans++;
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean isOk(int c) {
		int p = parent[c];
		if (p == 0 || p == -1) return false;
		
		int pp = parent[p];
		if (pp == -1) return false;
		
		if (c != k && p != pk && pp == ppk) return true;
		return false;
	}
}
