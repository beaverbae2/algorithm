package A2021_03_18;

import java.util.*;
import java.io.*;

/**
 * Map
 * 11MIN
 * @author beaverbae
 *
 */

public class BOJ_10816_숫자_카드_2_Map {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		HashMap<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i])+1);
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] target = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int t : target) {
			if (!map.containsKey(t)) {
				sb.append(0);
			} else {
				sb.append(map.get(t));
			}
			sb.append(" ");
		}
		
		System.out.println(sb.toString());
	}	
}
