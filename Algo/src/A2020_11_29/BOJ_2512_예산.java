package A2020_11_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512_예산 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] state = new int[N];
		int start = 0, end = 0, answer = 0, limit = 0, sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			state[i] = Integer.parseInt(st.nextToken());
			sum += state[i];
			end = Math.max(end, state[i]);
		}
		limit = Integer.parseInt(br.readLine());
		
		if(sum<=limit) System.out.println(end);
		else {
			while(start<=end) {
				int mid = (start+end)/2;
				sum = 0;
				for (int i = 0; i < state.length; i++) {
					if(state[i]>=mid) sum += mid;
					else sum += state[i];
				}
				
				if(sum<=limit) {
					answer = mid;
					start = mid+1;
				}else end = mid-1;
			}
			System.out.println(answer);
		}
	}
}
