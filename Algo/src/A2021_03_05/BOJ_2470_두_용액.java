package A2021_03_05;

import java.util.*;
import java.io.*;

/**
 * Two Pointer
 * @author beaverbae
 *
 */

public class BOJ_2470_두_용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		int start = 0;
		int end = N-1;
		
		int min_sum = Integer.MAX_VALUE;
		int min_x = 0;
		int min_y = 0;
		
		while (start < end) {
			int sum = input[start] + input[end];
			
			if (sum == 0) {
				min_x = input[start];
				min_y = input[end];
				break;
			} else if (sum <0) {
				sum = Math.abs(sum);
				if (min_sum > sum) {
						min_sum = sum;
						min_x = input[start];
						min_y = input[end];
				}
				start++;
			} else {
				sum = Math.abs(sum);
				if (min_sum > sum) {
						min_sum = sum;
						min_x = input[start];
						min_y = input[end];
				}
				end--;
			}
		}
		
		System.out.println(min_x+" "+min_y);
	}
}
