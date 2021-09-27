package A2021_05_07;

import java.util.*;
import java.io.*;

public class BOJ_2110_공유기_설치_review {
	static int N, C, max;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		max = arr[N-1]-arr[0];
		
		System.out.println(parametric_search());
	}
	
	private static int parametric_search() {
		int result = -1;
		int left = 1;
		int right = max;
		
		while (left <= right) {
			int mid = (left + right) / 2;// 가장 인접한 두 공유기 사이의 거리
			
			// 공유기 설치 하기
			int start = 0;
			int cnt = 1; //첫 지점엔 항상 설치
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] - arr[start] >= mid) {
					cnt++;
					start = i;
				}
			}
			
			if (cnt < C) {
				right = mid-1;
			} else {
				left = mid+1;
				result = mid;
			}
		}
		
		
		return result;
	}
}
