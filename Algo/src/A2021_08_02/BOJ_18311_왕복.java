package A2021_08_02;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 생각보다 구현이 까다롭다
 * 32MIN
 * @author beaverbae
 *
 */

public class BOJ_18311_왕복 {
	static int N, ans;
	static long K;
	static long[] arr, A;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
	
		arr = new long[N+1];
		A = new long[2*N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			A[i] = A[i-1] + arr[i];
			if (isK(i)) {
				ans = i;
			}
		}
		
		int idx = N+1;
		for (int i = N; i >= 1; i--) {
			A[idx] = A[idx-1] + arr[i];
			if (isK(idx)) {
				ans = i;
			}
			idx++;
		}
		
		System.out.println(ans);
	}

	private static boolean isK(int i) {
		if (K >= A[i-1] && K < A[i]) {
			return true;
		}
		return false;
	}
}
