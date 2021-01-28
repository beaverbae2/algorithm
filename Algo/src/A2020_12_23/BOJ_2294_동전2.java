package A2020_12_23;

import java.util.*;
import java.io.*;

public class BOJ_2294_동전2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(Integer.parseInt(br.readLine()));
		}
		int[] coin = new int[set.size()];
		Iterator<Integer> iter = set.iterator();
		int idx = 0;
		while(iter.hasNext()) {
			coin[idx] = iter.next();
			idx++;
		}
		int[] dp = new int[K+1];
		Arrays.fill(dp, 987654321);
		for (int i = 0; i < dp.length; i++) {
			if(i%coin[0] == 0) {
				dp[i] = i/coin[0];
			}
		}
		
		for (int i = 1; i < coin.length; i++) {
			for (int j = coin[i]; j < dp.length; j++) {
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
			}
		}
		
		if(dp[K] == 0 || dp[K] == 987654321) System.out.println(-1);
		else System.out.println(dp[K]);
	}
}
