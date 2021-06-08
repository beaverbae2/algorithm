package A2021_06_08;

import java.util.*;
import java.io.*;

/**
 * Binary Search
 * 
 * 어려웠던 부분
 * - 어떤 알고리즘 적용해야 하는지 -> 문제해석 잘 못했음
 * - 구간의 개수 구하기
 * 
 * @author beaverbae
 * @see https://odh93.tistory.com/9
 *
 */

public class BOJ_13397_구간_나누기2_solution {
	static int[] arr;
	static int N, M;
	static final int INF = 987654321;
	static int left, right;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		
		answer = right;
		parametric_search();
		System.out.println(answer);
	}

	private static void parametric_search() {
		while (left <= right) {
			int mid = (left + right) / 2;
			
			//////////		솔루션 참조		//////////
			int cnt = 0;
			int max = arr[0], min = arr[0];
			
			for (int i = 1; i < arr.length; i++) {
				max = Math.max(max, arr[i]);
				min = Math.min(min, arr[i]);
				
				if (max - min > mid) {// 이 경우에 구간을 나눠주면 된다
					cnt++;
					max = arr[i]; 
					min = arr[i];
				}
			}
			cnt++; // 마지막 구간 추가
			//////////////////////////////////////////
			if (cnt <= M) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
	}
}
