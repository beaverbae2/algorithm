package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * Permutation
 * 21MIN
 * @author beaverbae
 *
 */

public class BOJ_1339_단어_수학 {
	static int N;
	static List<Character>[] list;
	static HashMap<Character, Integer> map;
	static int[] arr;
	static boolean[] visited;
	static int answer = 0;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new List[N];
		map = new HashMap<>();
		visited = new boolean[10];
		
		int idx = 0;
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			char[] word = br.readLine().toCharArray();
			for (char ch : word) {
				list[i].add(ch);
				
				if (!map.containsKey(ch)) {
					map.put(ch, idx++);
				}
			}
		}
		
		arr = new int[map.size()];
	
		permutation(0);
		System.out.println(answer);
	}


	private static void permutation(int cnt) {
		if (cnt == arr.length) {
			answer = Math.max(answer, calc());
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			arr[cnt] = i;
			permutation(cnt+1);
			visited[i] = false;
		}
	}


	private static int calc() {
		int result = 0;
		for (int i = 0; i < list.length; i++) {
			int temp = 0;
			for (int j = 0; j < list[i].size(); j++) {
				char ch = list[i].get(j);
				int idx = map.get(ch);
				temp = temp*10 + arr[idx];
			}
			result += temp;
		}
		return result;
	}
}
