package A2022_03_18;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 25MIN
 * @author beaverbae
 * - 나머지도 일반적인 누적합처럼 계산가능
 * - 배열을 쓸 수 있는 경우 배열을 쓰자 -> Map보다 시간, 공간 효율성이 높음 
 * 
 */

public class BOJ_10986_나머지합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		long ans = 0L;
		int[] A;
		long[] D = new long[1000];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int v = Integer.parseInt(st.nextToken()) % M;
			A[i] = (A[i-1] + v) % M;
			D[A[i]]++;
		}
		
		for (int i = 0; i < D.length; i++) {
			if (D[i] == 0) continue;
			if (i == 0) ans += D[i];
			ans += D[i] * (D[i]-1) / 2; 
		}
		
		System.out.println(ans);
	}
}
