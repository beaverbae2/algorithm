package A2020_11_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1300_K번째수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
	
		int start = 1;
		int end = K;
		int answer = 0;
		
		
		while(start<=end) {
			int mid = (start+end)/2;
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				cnt += Math.min(mid/i, N);
			}
			
			if(cnt>=K) {
				end = mid-1;
				answer = mid;
			}else if(cnt<K) {
				start = mid+1;
			}
		}
		System.out.println(answer);
	}
}
