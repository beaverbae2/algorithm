package A2020_12_29;

import java.util.*;

public class PGS_N으로_표현 {
	private int answer;

	public int solution(int N, int number) {
		answer = 9;// 최댓값

		dfs(N, number, 0, 0);

		if (answer > 8)
			answer = -1;

		return answer;
	}

	public void dfs(int N, int number, int sum, int cnt) {
		int temp_N = N;

		if (cnt > 8)
			return;

		if (sum == number) {
			answer = Math.min(answer, cnt);
			return;
		}

		for (int i = 0; i < 8 - cnt; i++) {
			dfs(N, number, sum + temp_N, cnt + i + 1);
			dfs(N, number, sum - temp_N, cnt + i + 1);
			dfs(N, number, sum * temp_N, cnt + i + 1);
			dfs(N, number, sum / temp_N, cnt + i + 1);

			temp_N = temp_N * 10 + N;
		}
	}

	public static void main(String[] args) {
		System.out.println(new PGS_N으로_표현().solution(5, 12));
		System.out.println(new PGS_N으로_표현().solution(2, 11));
	}
}
