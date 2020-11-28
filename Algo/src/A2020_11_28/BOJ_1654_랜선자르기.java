package A2020_11_28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 도움이 된 반례
 * 1 1
 * 2147483647
 */

public class BOJ_1654_랜선자르기 {
	static int K,N;
	static int[] line;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		line = new int[K];
		
		//1부터 시작임
		long start = 1;
		long end = 1;
		long answer = 0;
		
		for (int i = 0; i < K; i++) {
			line[i] = Integer.parseInt(br.readLine());
			end = Math.max(end, line[i]);
		}
	
		while(start<=end) {
			long mid = (start+end)/2;
			long cnt = 0;
			
			for (int i = 0; i < K; i++) {
				cnt += line[i]/mid;//start와 end가 0부터 시작하면 런타임에러 발생
			}
			
			if(cnt>=N) {
				//cnt는 N을 넘어가도 되고, 랜선의 최대 길이를 구해야 하므로
				answer = mid;
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		
		System.out.println(answer);
	}
}
