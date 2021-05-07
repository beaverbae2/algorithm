package A2021_05_07;

import java.util.*;
import java.io.*;

public class BOJ_2805_나무_자르기_review {
	static int N, M, max;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		System.out.println(parametric_search());
	}
	
	private static int parametric_search() {
		int left = 0;
		int right = max;
		int answer = -1;
		
		while (left <= right) {
			int mid = (left + right) / 2; // 절단기의 높이
		
			//가져갈 수 있는 나무 높이의 합 구하기
			long height = 0;
			
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > mid) {
					height += arr[i]-mid;
				}
			}
			
			if (height < M) {
				right = mid-1;
			} else {
				answer = mid;
				left = mid+1;
			}
		}
		
		return answer;
	}
}
