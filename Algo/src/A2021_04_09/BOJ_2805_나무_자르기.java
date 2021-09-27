package A2021_04_09;

import java.util.*;
import java.io.*;

public class BOJ_2805_나무_자르기 {
	static int[] arr;
	static int N;
	static long M;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tree = Integer.parseInt(st.nextToken());
			arr[i] = tree;
			max = Math.max(max, tree);
		}
		
		System.out.println(parametric_search());
	}

	private static int parametric_search() {
		int left = 0;
		int right = max;
		int result = -1;
		
		while (left <= right) {
			int mid = (left + right) / 2;// 절단기의 현재 높이\
			long len = 0; // 가져갈 수 있는 나무의 길이
			
			for (int tree : arr) {
				if (tree > mid) {
					len += tree - mid;
				}
			}
			
			if (len < M) {// 절단기 높이를 낮춰줘야함
				right = mid-1;
			} else {// 절단기 높이 올려줘야함
				left = mid+1;
				result = mid;
			}
		}
		
		return result;
	}
}
