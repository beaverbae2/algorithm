package A2021_03_15;

import java.util.*;
import java.io.*;

public class BOJ_9020_골드바흐의_추측 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MAX = 10000;
		boolean[] notPrime = new boolean[MAX+1];
		notPrime[0] = true;
		notPrime[1] = true;
		
		for (int i = 2; i <= MAX; i++) {
			if (notPrime[i]) continue;
			for (int j = i+i; j <= MAX; j+=i) {
				notPrime[j] = true;
			}
		}
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			for (int i = n/2; i >= 2; i--) {
				if (notPrime[i]) continue;
				
				int j = n - i;
				if (notPrime[j]) continue;
				sb.append(i).append(" ").append(j).append("\n");
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
}
