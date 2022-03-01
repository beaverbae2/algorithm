package A2022_03_01;

import java.util.*;
import java.io.*;

/**
 * 27MIN
 * DFS
 * @author beaverbae
 * - 루트노드를 지웠을 떄 고려하기
 *
 */

public class BOJ_1068_트리 {
	static int N, M;
	static int[] arr;
	static Map<Integer, List<Integer>> map;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		arr[M] = N;
		
		for (int i = 0; i < N; i++) {
			List<Integer> list;
			if (!map.containsKey(arr[i])) {
				list = new ArrayList<>();
				list.add(i);
				map.put(arr[i], list);
			} else {
				list = map.get(arr[i]);
				list.add(i);
			}
		}
		
		if (map.containsKey(-1)) dfs(-1);
		
		System.out.println(ans);
	}

	private static void dfs(int v) {
		if (!map.containsKey(v)) {
			ans++;
			return;
		}
		
		for (int nv : map.get(v)) {
			dfs(nv);
		}
	}
}
