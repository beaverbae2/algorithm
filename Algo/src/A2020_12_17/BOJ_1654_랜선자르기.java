package A2020_12_17;

import java.util.*;
import java.io.*;

public class BOJ_1654_랜선자르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[K];
		
		long left = 1;
		long right = 1;
		long answer = 0;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			right = Math.max(arr[i], right);
		}
		
		while (left<=right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			
			for (int i = 0; i < arr.length; i++) {
				cnt += arr[i] / mid;
			}
			
			if (cnt >=  N) {//덜 잘라야함
				answer = mid;
				left = mid +1 ;
			}else {//더 많이 잘라야함
				right = mid - 1;
			}
		}
		
		System.out.println(answer);
		
		
	}
}
