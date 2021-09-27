package A2021_04_26;

import java.io.*;
import java.util.*;

/**
 * DP
 * 아려웠던점
 * - 처음에 dfs로 짠뒤 dp로 최적화 하려고 했으나 하향식 dp에 익숙하지 않았음
 * - memo배열에 어떻게 값을 할당해야할지 감이 안왔음 -> dfs메소드 내의 answer 변수 활용
 * - memo배열을 2차원 배열로 했어야 했음 -> memo[i][j] : i번째 요일까지 쿠폰이 j개 있을때 비용의 최솟값, 1차원으로 하니 틀린 결과가 나옴
 * @author beaverbae
 * @see https://justicehui.github.io/koi/2018/10/30/BOJ13302/
 * 
 */

public class BOJ_13302_리조트_solution {
	static int N, M;
	static HashSet<Integer> set;
	static int[][] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		set = new HashSet<>();
		memo = new int[N+1][N+1];
		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], Integer.MAX_VALUE);
		}
		
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		System.out.println(dfs(1, 0));
	}

	private static int dfs(int idx, int coupon) {
		if (idx > N) {
			return 0;
		}
		if (memo[idx][coupon] != Integer.MAX_VALUE) return memo[idx][coupon];
		
		int answer = Integer.MAX_VALUE;
		
		if (set.contains(idx)) {
			answer = Math.min(answer, dfs(idx+1, coupon));
		} else {
			answer = Math.min(answer, dfs(idx+1, coupon)+10000);
			answer = Math.min(answer, dfs(idx+3, coupon+1)+25000);
			answer = Math.min(answer, dfs(idx+5, coupon+2)+37000);
		
			if (coupon >= 3) {
				answer = Math.min(answer, dfs(idx+1, coupon-3));
			}
		}
		
		memo[idx][coupon] = answer;
		return answer;
	}
	
}
