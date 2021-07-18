package A2021_07_19;

import java.util.*;
import java.io.*;

/**
 * Two pointer
 * 30MIN
 * @author beaverbae
 *
 */

public class BOJ_겹치는_건_싫어 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		int N, K;
		int[] arr;
		HashMap<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0, e = 0;
		while (e < N) {
			if (map.containsKey(arr[e])) {
				if (map.get(arr[e]) == K) {
					int v = map.get(arr[s]) - 1;
					
					if (v == 0) map.remove(arr[s]);
					else map.put(arr[s], v);
					
					ans = Math.max(ans, e - s);
					s++;
				} else {
					map.put(arr[e], map.get(arr[e]) + 1);
					e++;
					ans = Math.max(ans, e - s);
				}
			} else {
				map.put(arr[e++], 1);
				ans = Math.max(ans, e - s);
			}
		}
		
		System.out.println(ans);
	}
}
