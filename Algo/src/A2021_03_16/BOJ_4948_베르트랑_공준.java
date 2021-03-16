package A2021_03_16;

import java.util.*;
import java.io.*;

public class BOJ_4948_베르트랑_공준 {
	static final int N = 123456;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean[] notPrime = new boolean[2*N+1];
		notPrime[0] = true;
		notPrime[1] = true;
		
		for (int i = 2; i < notPrime.length; i++) {
			if (notPrime[i]) continue;
			for (int j = i+i; j < notPrime.length; j+=i) {
				notPrime[j] = true;
			}
		}
		
		
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break; 
			
			int cnt = 0;
			for (int i = n+1; i <= 2*n; i++) {
				if (notPrime[i]) continue;
				cnt++;
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
