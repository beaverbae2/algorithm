package A2020_12_16;

import java.util.*;

public class PGS_풍선터트리기 {
	private HashSet<Integer> set;
	private LinkedList<Integer> balloons;
	
	public int solution(int[] a) {
		int answer = 0;
		int n = a.length;
		set = new HashSet<>();
		balloons = new LinkedList<>();
		
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			balloons.add(a[i]);
		}
	
		
		dfs(n, 0, 0);
		answer = set.size();
		System.out.println();
		return answer;
	}

	public void dfs(int n, int cnt, int k) {
		if (cnt==n-1) {
			System.out.println(balloons.get(0));
			set.add(balloons.get(0));
			return;
		}
		
		int b1 = balloons.get(0);
		int b2 = balloons.get(1);
	
		if (b1<b2) {
			if(k == 0) {
				balloons.remove(0);
				dfs(n, cnt+1, k+1);
				balloons.add(0, b1);
			}
			balloons.remove(1);
			dfs(n, cnt+1, k);
			balloons.add(1, b2);
		}else if(b1>b2) {
			if(k == 0) {
				balloons.remove(1);
				dfs(n, cnt+1, k+1);
				balloons.add(1, b2);
			}
			balloons.remove(0);
			dfs(n, cnt+1, k);
			balloons.add(0, b1);
		}
	}

	public static void main(String[] args) {
		PGS_풍선터트리기 a = new PGS_풍선터트리기();
		System.out.println(a.solution(new int[] { 9, -1, -5 }));
		a = new PGS_풍선터트리기();
		System.out.println(a.solution(new int[] { -16, 27, 65, -2, 58, -92, -71, -68, -61, -33 }));
	}
}
