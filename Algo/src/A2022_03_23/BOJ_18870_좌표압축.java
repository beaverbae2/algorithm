package A2022_03_23;

import java.util.*;
import java.io.*;

/**
 * Binary search
 * 16MIN
 * @author beaverbae
 *
 */

public class BOJ_18870_좌표압축 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> input = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			input.add(n);
			if (set.contains(n)) continue;
			
			list.add(n);
			set.add(n);
		}
		Collections.sort(list);
		
		for (int n : input) {
			sb.append(binary_search(list, n)).append(" ");
		}
		
		System.out.println(sb);
	}

	private static int binary_search(List<Integer> list, int n) {
		int s = 0;
		int e = list.size() - 1;
		int m = -1;
		
		while (s <= e) {
			m = (s + e) / 2;
			
			if (list.get(m) > n) e = m - 1;
			else if (list.get(m) < n) s = m + 1;
			else return m;
		}
		
		return m;
	}
	
	
}
