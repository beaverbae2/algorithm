package A2020_12_24;

import java.util.*;
import java.io.*;

public class BOJ_1339_단어수학BruteForce {
	static List<Character>[] list;
	static HashMap<Character, Integer> map;
	static int[] selectedNum;
	static boolean[] visited;
	static int N, M, answer;
	static int[] pow = {1,10,100,1000,10000,100000,1000000,10000000};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		M = Integer.parseInt(br.readLine());
		list = new List[M];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		map = new HashMap<>();
		
		int index = 0;
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				char ch = str.charAt(j);
				
				list[i].add(ch);
				if(map.get(ch) == null) {
					map.put(ch, index);
					index++;
				}
			}
		}
		N = map.size();
		selectedNum = new int[N];
		visited = new boolean[10];
		
		permutation(0);
		System.out.println(answer);
	}
	
	static void permutation(int r) {
		if (r == N) {
			answer = Math.max(answer, getMax());
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			selectedNum[r] = i;
			permutation(r+1);
			visited[i] = false;
		}
	}

	static int getMax() {
		int result = 0;
		
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].size(); j++) {
				result += selectedNum[map.get(list[i].get(j))] * pow[list[i].size()-1-j];
			}
		}
		
		return result;
	}
}
