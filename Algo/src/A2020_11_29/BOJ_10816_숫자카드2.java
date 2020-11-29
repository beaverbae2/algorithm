package A2020_11_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_10816_숫자카드2 {
	static int[] arr,target;
	static HashMap<Integer, Integer> hm = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(hm.get(arr[i])==null) {
				hm.put(arr[i], 1);
			}else {
				int cnt = hm.get(arr[i])+1;
				hm.put(arr[i], cnt);
			}
		
		}
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		target = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb= new StringBuilder();
		
		for (int i = 0; i < target.length; i++) {
			int start = 0;
			int end = N-1;
			
			int result = binary_search(start,end,target[i]);
			if(result==Integer.MIN_VALUE) {
				sb.append(0).append(' ');
			}else {
				sb.append(hm.get(result)).append(' ');
			}
		}
		System.out.println(sb);
	}

	private static int binary_search(int start, int end, int t) {
		if(start>end) return Integer.MIN_VALUE;//존재x
		
		int mid = (start+end)/2;
		
		if(t==arr[mid]) return t;
		else if(t<arr[mid]) return binary_search(start, mid-1, t);
		else return binary_search(mid+1, end, t);
	}
}
