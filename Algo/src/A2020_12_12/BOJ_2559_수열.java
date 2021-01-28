package A2020_12_12;

import java.io.*;
import java.util.*;

public class BOJ_2559_수열 {
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
		
		long answer = -9876543210L;
	
		for (int i = 0; i < arr.length; i++) {
			if(i+M<=arr.length) {
				long temp = 0L;
				for (int j = i; j < i+M; j++) {
					temp += arr[j];
				}
				answer = Math.max(answer, temp);
			}
		}
		System.out.println(answer);
	}
}
