package A2020_12_10;

import java.util.*;

public class PGS_외벽점검Fail {
	static LinkedList<Integer> selected_weak;
	static LinkedList<Integer> selected_dist;
	static LinkedHashMap<Integer, Boolean> selected_weakMap;
	static int N, weak_n, dist_n, answer;
	static int[] s_weak;
	static int[] s_dist;
	static boolean[] visited;

	public static int solution(int n, int[] weak, int[] dist) {
		answer = Integer.MAX_VALUE;
		selected_weak = new LinkedList<>();
		selected_dist = new LinkedList<>();
		selected_weakMap = new LinkedHashMap<>();
		weak_n = weak.length;
		dist_n = dist.length;
		N = n;
		s_weak = weak;
		s_dist = dist;

		getSeletedDist(0);// 부분집합

		if (answer == Integer.MAX_VALUE)
			return -1;
		return answer;
	}

	// 부분집합
	private static void getSeletedDist(int r) {
		if (r == s_dist.length) {
			System.out.println(selected_dist);
			visited = new boolean[s_weak.length];
//			getSeletedWeak(0, selected_dist.size());
			return;
		}

		selected_dist.add(s_dist[r]);
		getSeletedDist(r + 1);
		selected_dist.removeLast();
		getSeletedDist(r + 1);
	}

	
	private static void getMin(int size, int i, int j, boolean[] visited2, int cnt) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		System.out.println(solution(12, new int[] { 1, 5, 6, 10 }, new int[] { 1, 2, 3, 4 }));
		System.out.println(solution(12, new int[] { 1, 3, 4, 9, 10 }, new int[] { 3, 5, 7 }));
	}
}
