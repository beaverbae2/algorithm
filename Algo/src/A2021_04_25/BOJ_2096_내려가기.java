package A2021_04_25;

import java.util.*;
import java.io.*;

/**
 * Sliding Window
 * 21MIN
 * @author beave
 *
 */

public class BOJ_2096_내려가기 {
	static int[] prev_max, max;
	static int[] prev_min, min;
	static int N;
	static final int len = 3;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		prev_max = new int[3];
		max = new int[3];
		prev_min = new int[3];
		min = new int[3];
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (j == 0) {
					max[j] = Math.max(prev_max[j], prev_max[j+1]) + n;
					min[j] = Math.min(prev_min[j], prev_min[j+1]) + n;
				} else if (j == len-1) {
					max[j] = Math.max(prev_max[j], prev_max[j-1]) + n;
					min[j] = Math.min(prev_min[j], prev_min[j-1]) + n;
				} else {
					max[j] = Math.max(prev_max[j], Math.max(prev_max[j-1], prev_max[j+1])) + n;
					min[j] = Math.min(prev_min[j], Math.min(prev_min[j-1], prev_min[j+1])) + n;
				}
			}
			
			for (int j = 0; j < len; j++) {
				prev_max[j] = max[j];
				prev_min[j] = min[j];
			}
		}
		
		int max_n = 0;
		int min_n = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			max_n = Math.max(max_n, max[i]);
			min_n = Math.min(min_n, min[i]);
		}
		System.out.println(max_n+" "+min_n);
	}
}
