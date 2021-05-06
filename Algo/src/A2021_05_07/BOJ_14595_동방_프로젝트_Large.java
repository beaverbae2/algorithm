package A2021_05_07;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 22MIN
 * @author beaverbae
 * 비슷한문제 : https://programmers.co.kr/learn/courses/30/lessons/72414
 */

public class BOJ_14595_동방_프로젝트_Large {
	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		if (M != 0) {
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				arr[start] -= 1;
				arr[end] += 1;
			}
		}
		
		//누적합 활용
		int cnt = 0;
		for (int i = 1; i < arr.length; i++) {
			arr[i] += arr[i-1];
		
			if (arr[i] == 0) cnt++;
		}
		System.out.println(cnt);
	}
}
