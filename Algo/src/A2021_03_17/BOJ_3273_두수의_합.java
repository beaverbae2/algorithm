package A2021_03_17;

import java.util.*;
import java.io.*;

/**
 * Two Pointers
 * 10MIN
 * @author beaverbae
 *
 */

public class BOJ_3273_두수의_합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
	
		int target = Integer.parseInt(br.readLine());
		int ans = 0;
		int start = 0;
		int end = N-1;
		
		while (start < end) {
			int sum = arr[start] + arr[end];
			
			if (sum >= target) {
				if (sum == target) ans++;
				end--;
			} else start++;
		}
		
		System.out.println(ans);
	}
}
