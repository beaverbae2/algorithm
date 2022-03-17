package A2022_03_14;

import java.util.*;
import java.io.*;

public class BOJ_22871_징검다리_건너기_large {
	static int N;
	static int[] arr;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		max = (N-1) * (1 + Math.abs(arr[0] - arr[N-1]));
		System.out.println(binary_search());
	}
	
	private static int binary_search() {
		int result = -1;
		int l = 0;
		int r = max;
		
		while (l <= r) {
			int m = (l + r) / 2;
			System.out.println(m);
			int i = 0;
			boolean flag = false;
			while (i < N-1) {
				flag = false;
				for (int j = N-1; j > i; j--) {
					int k = (j - i) * (1 + Math.abs(arr[i] - arr[j]));
					if (k <= m) {
						flag = true;
						i = j;
						break;
					}
				}
				
				if (!flag) break;
			}
			
			if (flag) {
				result = m;
				r = m - 1;
			} else l = m + 1;
		}
		
		return result;
	}
}
