package A2021_07_09;

import java.util.*;
import java.io.*;

/**
 * Two pointer
 * 15MIN
 * 투 포인터는 꼼꼼해야한다
 * @author beaverbae
 *
 */

public class BOJ_20922_겹치는건_싫어 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] arr = new int[N];
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
			map.put(n, 0);
		}
		
		int left = 0;
		int right = 0;
		
		while (right < N) {
			if (map.get(arr[right]) == K) {
				map.put(arr[left], map.get(arr[left]) - 1);
				left++;
			} else {
				map.put(arr[right], map.get(arr[right]) + 1);
				right++;
				ans = Math.max(ans, right - left);
			}
		}
		
		System.out.println(ans);
	}
}
