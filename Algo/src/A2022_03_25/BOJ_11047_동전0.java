package A2022_03_25;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 5MIN
 * @author beaverbae
 *
 */


public class BOJ_11047_동전0 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		int N, K;
		int[] coins;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N];
		
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = N-1; i >= 0; i--) {
			ans += K / coins[i];
			K %= coins[i];
		}
		
		System.out.println(ans);
	}
}
