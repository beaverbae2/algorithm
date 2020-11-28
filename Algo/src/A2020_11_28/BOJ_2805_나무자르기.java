package A2020_11_28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//이분 탐색
public class BOJ_2805_나무자르기 {
	static int N, H, min, max;
	static int[] trees;
	static int answer;
	static long sub = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		trees = new int[N];
		min = 0; 
		max= 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(trees[i], max);
		}
		
		while(true) {
			if(min>max) break;
			int mid = (min+max)/2;
			long cut = 0;//long type!! 
			
			for (int i = 0; i < N; i++) {
				if(trees[i]>mid) cut += trees[i]-mid; //조건문!!
			}

			if(cut>=H) {
				long temp_sub = cut-H;
				if(temp_sub<sub) {
					sub = temp_sub;
					answer = mid;
				}
			}
			if(cut>H) {
				min = mid+1;
			}else if(cut<H) {
				max = mid-1;
			}else if(cut==H) break;
			
		}
		System.out.println(answer);
		
	}
}
