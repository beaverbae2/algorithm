package A2021_02_26;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * @author beaverbae
 *
 */

public class BOJ_2805_나무_자르기 {
	static int[] input;
	static int N;
	static long M;
	static long max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, input[i]);
		}
		
		System.out.println(parametric_search());
	}
	
	private static long parametric_search() {
		long result = 0;
		
		long start = 0;
		long end = max;
		
		while(start<=end) {
			long mid = (start+end)/2;// 절단기의 높이
			
			long sum = 0;
			for (int i = 0; i < input.length; i++) {
				if (input[i] - mid <= 0) continue;
				
				sum += (input[i] - mid);
			}
			
			if (sum < M) {// 자르는 높이는 줄여야함
				end = mid-1;
			} else {
				start = mid+1;
				result = mid;
			}
		}
		
		return result;
	}
	
}
