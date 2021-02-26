package A2021_02_26;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * @author beaverbae
 *
 */

public class BOJ_1654_랜선_자르기 {
	static int[] input;
	static int K;
	static long N;
	static int max;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Long.parseLong(st.nextToken());
		input = new int[K];
		
		for (int i = 0; i < K; i++) {
			input[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, input[i]);
		}
		
		System.out.println(parametric_search());
	}
	
	static long parametric_search() {
		long result = 0;
		long start = 1;
		long end = max;
		
		while (start<=end) {
			long mid = (start+end)/2;
			
			long cnt = 0;
			for (int i = 0; i < input.length; i++) {
				cnt += input[i] / mid;
			}
			
			if (cnt < N) {// 더 잘라야함
				end = mid-1;
			} else {
				start = mid+1;
				result = mid;
			}
		}
		
		return result;
	}
}
