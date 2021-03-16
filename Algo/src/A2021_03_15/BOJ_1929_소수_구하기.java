package A2021_03_15;

import java.util.*;
import java.io.*;

public class BOJ_1929_소수_구하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		boolean[] notPrime = new boolean[N+1];
		notPrime[0] = true;
		notPrime[1] = true;
		
		for (int i = 2; i <= N; i++) {
			if (notPrime[i]) continue;
			
			for(int j = i+i; j <= N; j+=i) {
				notPrime[j] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = M; i <= N; i++) {
			if (!notPrime[i]) {
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
