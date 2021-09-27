package A2021_05_07;

import java.util.*;
import java.io.*;

public class BOJ_2470_두_용액_review {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int min_sum = Integer.MAX_VALUE;
		int left_ans = 0;
		int right_ans = 0;
		
		int left = 0;
		int right = N-1;
		
		while (left < right) {
			int temp_sum = arr[left] + arr[right];
			int temp_abs_sum = Math.abs(temp_sum);
			
			if (temp_abs_sum < min_sum) {
				min_sum = temp_abs_sum;
				left_ans = arr[left];
				right_ans = arr[right];
			}
			
			if (temp_sum < 0) left++;
			else if (temp_sum > 0) right--;
			else break;
		}
		
		System.out.println(left_ans+" "+right_ans);
	}
}
