package A2020_11_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1929_수찾기 {
	static int N,M;
	static int[] arr1, arr2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr1 = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr1);
		
		M = Integer.parseInt(br.readLine());
		arr2 = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int target : arr2) {
			sb.append(binary_search(target,0,N-1)).append("\n");
		}
		System.out.println(sb);
	}

	private static int binary_search(int target, int start, int end) {
		if(start>end) return 0;
		
		int mid = (start+end)/2;
		if(target==arr1[mid]) return 1;
		else if(target<arr1[mid]) return binary_search(target, start, mid-1);
		else return binary_search(target, mid+1, end);
	}
}
