package A2020_12_12;

import java.util.*;
import java.io.*;


public class BOJ_2003_수들의합2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = arr[0];
		int answer = 0;
		
		while(true) {
			if(end == arr.length) break;
			
			if(sum<M) {
				end++;
				if(end != arr.length) sum += arr[end];
			}else {
				if(sum == M) answer++; 
				sum -= arr[start];
				start++;
				if(start>end) { 
					end = start;
					if(end != arr.length) sum = arr[end];
				}
			}
		}
		System.out.println(answer);
		
	}
}
