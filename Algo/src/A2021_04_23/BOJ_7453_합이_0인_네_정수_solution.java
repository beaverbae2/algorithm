package A2021_04_23;

import java.util.*;
import java.io.*;

/**
 * Two Pointer
 * @author beaverbae
 * @see https://ukyonge.tistory.com/39
 */

public class BOJ_7453_합이_0인_네_정수_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];
	
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
		
			A[i] = a;
			B[i] = b;
			C[i] = c;
			D[i] = d;
		}
		
		long[] AB = new long[N*N];
		long[] CD = new long[N*N];
	
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx] = C[i] + D[j];
				idx++;
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		int left = 0;
		int right = N*N-1;
		long answer = 0;
		
		while (left <= N*N-1 && right >= 0) {
			long left_sum = AB[left];
			long right_sum = CD[right];
			long sum = left_sum + right_sum;
			
			// 중복 값이 존재함에 유의
			if (sum == 0) {
				long left_cnt = 0;
				while (left <= N*N-1 && AB[left] == left_sum) {
					left++;
					left_cnt++;
				}
				
				long right_cnt = 0;
				while (right >= 0 && CD[right] == right_sum) {
					right--;
					right_cnt++;
				}
				
				answer += left_cnt * right_cnt;
			
			} else if (sum > 0) { 
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(answer);
	}
}
