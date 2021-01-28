package A2020_12_16;

import java.io.*;
import java.util.*;

public class BOJ_2110_공유기설치 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int left = 1;//최소거리
		int right = arr[N-1] - arr[0];//최대거리
		int answer = 0;
		
		while (left<=right) {
			int start = arr[0];
			int mid = (left+right)/2;//가장 인접한 두 공유기 사이의 최대거리
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				int d = arr[i] - start;
				if(d >= mid) {
					cnt++;
					start = arr[i];
				}
			}
			
			if (cnt>=C) {
				answer = mid;
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}
}
