package A2021_04_16;

import java.util.*;
import java.io.*;

/**
 * fail
 * @author beaverbae
 *
 */

public class BOJ_13144_List_of_Unique_Numbers {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		int start = 0;
		int end = 0;
		int cnt = 0;
		
		while (true) {
			if (start == N && end == N) break;
			
			if (map.containsKey(arr[end])) {
				if (map.containsKey(arr[start])) {
					int v = map.get(arr[start]);
					
					if (v == 1) {
						map.remove(arr[start]);
					} else {
						map.put(arr[start], v-1);
					}
				}
				
				cnt += end - start;
				start++;
			} else {
				map.put(arr[end], 1);
				if (end < N) end++;
			}
		}
		
		System.out.println(cnt);
	}
}
