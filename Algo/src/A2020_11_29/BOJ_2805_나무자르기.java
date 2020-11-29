package A2020_11_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805_나무자르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] trees = new int[N];
		
		int start = 0, end = 0, answer = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			end = Math.max(trees[i], end);
		}
		
		while(start<=end) {
			int mid = (start+end)/2;
			long sum = 0;
			for (int tree : trees) {
				if(tree>mid) sum += (tree-mid);
			}
			
			if(sum>=M) {//점차 sum이 M과 가까워 지는 값으로 이동함
				answer = mid;
				start = mid+1;
			}else end = mid-1;
		}
		System.out.println(answer);
	}
}
