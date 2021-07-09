package A2021_07_09;

import java.util.*;
import java.io.*;

/**
 * DFS(subset)
 * 53MIN
 * 어려웠던 부분
 * - 합이 같은 경우 사전순으로 앞서는 것 출력
 *    - 부분 집합 구현 코드의 실행 순서를 명확히 알고 사용하자
 * - 부분 집합의 정답 확인 하는 코드에서 조건문 잘못씀
 * - 재귀 사용시 매개변수가 무엇이 필요한지 꼼꼼히 파악 후 사용하자
 * @author beaverbae
 *
 */

public class BOJ_19942_다이어트 {
	static int N;
	static int mp, mf, ms, mv;
	static int[][] arr;
	static String ans_selected;
	static int ans;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][5];
		ans = Integer.MAX_VALUE;
		ans_selected = "";
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0, 0, 0, 0, "");
		
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
			System.out.println(ans_selected);
		}
	}

	private static void dfs(int i, int p, int f, int s, int v, int sum, String selected) {
		if (i == N) {
			if (p >= mp && f >= mf && s >= ms && v >= mv) {
				if (ans == sum) {
					if (ans_selected.compareTo(selected) > 0) {
						ans_selected = selected;
					}
					ans = sum;
				} else if (ans > sum){
					ans_selected = selected;
					ans = sum;
				}
			}
			return;
		}
		
		dfs(i+1, p+arr[i][0], f+arr[i][1], s+arr[i][2], v+arr[i][3], sum+arr[i][4], selected+(i+1)+" ");
		dfs(i+1, p, f, s, v, sum, selected);
	}
}
