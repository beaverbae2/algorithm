package A2021_07_02;

import java.util.*;
import java.io.*;

/**
 * Sorting
 * 37MIN
 * 백준 알고리즘 분류 참고
 * 
 * @author beaverbae
 *
 */

public class BOJ_18114_블랙_프라이데이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		
		int N, C;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(st.nextToken());
			list.add(v);
			set.add(v);
			if (v == C) {
				System.out.println(1);
				return;
			}
		}
		
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			int v1 = list.get(i);
			for (int j = i+1; j < list.size(); j++) {
				int v2 = list.get(j);
				
				if (v1 + v2 == C) {
					System.out.println(1);
					return;
				} else if (v1 + v2 < C) {
					int v3 = C - v1 - v2;
					if (v3 == v1 || v3 == v2 || !set.contains(v3)) continue;
					System.out.println(1);
					return;
				}
			}
		}
		
		System.out.println(0);
	}
	
	static class Pair {
		int v1, v2;

		public Pair(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
		}

		@Override
		public String toString() {
			return "Pair [v1=" + v1 + ", v2=" + v2 + "]";
		}
	}
}
