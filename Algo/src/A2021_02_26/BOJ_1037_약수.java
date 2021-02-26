package A2021_02_26;

import java.util.*;
import java.io.*;

public class BOJ_1037_약수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int answer;
		if (N % 2 == 0) {// 짝수
			answer = arr[0]*arr[N-1];
		} else {// 홀수
			int mid = (N-1)/2;
			answer = arr[mid] * arr[mid];
		}
		
		System.out.println(answer);
	}
}
