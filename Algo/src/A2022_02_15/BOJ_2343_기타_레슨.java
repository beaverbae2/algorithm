package A2022_02_15;

import java.util.*;
import java.io.*;

/**
 * Binary search
 * 1H10MIN
 * @author beaverbae
 * - 시작 조건 잘 못 잡았음
 * - 블루레이 개수 구하는게 까다로웠음
 *
 */

public class BOJ_2343_기타_레슨 {
	static int[] arr;
	static int N, M, min, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		min = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max += arr[i];
			min = Math.max(min, arr[i]);
		}
		
		System.out.println(binary_search());
	}
	
	private static int binary_search() {
		int result = 0;
		int s = min;
		int e = max;
		
		while (s <= e) {
			int m = (s + e) / 2;
			
			int cnt = getCnt(m);
			
			if (cnt > M) {
				s = m + 1;
			} else {
				e = m - 1;
				result = m;
			}
		}
		
		return result;
	}

	private static int getCnt(int m) {
		int cnt = 0;
		int sum = 0;
		int i = 0;
		
		while (i < N) {
			if (sum > m) {
				cnt++;
				sum = arr[i-1];
			} else {
				sum += arr[i++];
			}
			
			if (i == N) cnt++;
		}
		if (sum > m) cnt++; 
		
		return cnt;
	}
}
