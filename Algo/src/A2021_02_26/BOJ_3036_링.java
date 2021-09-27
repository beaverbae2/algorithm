package A2021_02_26;

import java.util.*;
import java.io.*;

public class BOJ_3036_링 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < arr.length; i++) {
			int max = Math.max(arr[0], arr[i]);
			int min = Math.min(arr[0], arr[i]);
			
			int g = gcd(max, min);
			sb.append((arr[0]/g)).append("/").append((arr[i]/g)).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	private static int gcd(int a, int b) {
		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp; 
		}
		
		return a;
	}
}
