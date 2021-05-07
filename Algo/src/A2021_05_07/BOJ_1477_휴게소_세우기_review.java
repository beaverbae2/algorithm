package A2021_05_07;

import java.util.*;
import java.io.*;

public class BOJ_1477_휴게소_세우기_review {
	static int N, M, L, max;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	
		arr = new int[N+2];
		arr[0] = 0;
		arr[1] = L;
		st = new StringTokenizer(br.readLine());
		for (int i = 2; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i] - arr[i-1]);
		}
		
		System.out.println(parametric_search());
	}
	
	private static int parametric_search() {
		int result = max;
		int left = 1;
		int right = max;
		
		while (left <= right) {
			int mid = (left + right) / 2;// 두 공유기 사이의 최대 거리
			
			int cnt = 0;
			for (int i = 1; i < arr.length; i++) {
				int dist = arr[i] - arr[i-1];
				cnt += (dist-1) / mid; // 휴게소에 중복해서 휴게소를 지을 수 없음
			}
			
			// 최솟갑을 구해야함 -> lower bound
			if (cnt > M) {
				left = mid+1;
			} else {
				right = mid-1;
				result = mid;
			}
		}
		
		return result;
	}
}
