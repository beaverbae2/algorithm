package A2021_09_19;

import java.util.*;
import java.io.*;

/**
 * Sorting
 * 10MIN
 * @author beaverbae
 * 유의점
 * - pq에서 특정 값이 존재하는지 확인할 때 시간복잡도는 O(N) 
 * - Comparator를 사용하는 여러가지 방법
 */

public class BOJ_1181_단어_정렬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<String> pq = new PriorityQueue<>(new Node());
		
		HashSet<String> words = new HashSet<>();
		
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			String s = br.readLine();
			if (words.contains(s)) continue;
			pq.add(s);
			words.add(s);
		}
		
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static class Node implements Comparator<String> {
		public int compare(String s1, String s2) {
			if (s1.length() != s2.length()) return s1.length() - s2.length();
			return s1.compareTo(s2);
		}
	}
}
