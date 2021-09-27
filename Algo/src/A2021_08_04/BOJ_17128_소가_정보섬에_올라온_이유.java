package A2021_08_04;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 42MIN
 * 인덱싱!!
 * @author beaverbae
 *
 */

public class BOJ_17128_소가_정보섬에_올라온_이유 {
	static int N, Q;
	static int[] cows;
	static int[] S;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
	
		cows = new int[N];
		S = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			int s = 1;
			
			int j = i;
			for (int k = 0; k < 4; k++) {
				if (j >= N) j -= N;
				s *= cows[j];
				j++;
			}
			S[i] = s;
			sum += s;
		}
		
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int idx = Integer.parseInt(st.nextToken()) - 1;
			cows[idx] *= -1;
			
			for (int k = 0; k < 4; k++) {
				int s = 1;
				int j = idx - k;
				if (j < 0) j += N;
				int c = j; // j가 변경됨
				
				sum -= S[j];
				for (int l = 0; l < 4; l++) {
					s *= cows[j];
					j++;// j가 증가해야함
					if (j >= N) j -= N;
				}
				S[c] = s;
				sum += S[c];
			}
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb);
	}
}
