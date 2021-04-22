package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * DFS, Bit-masking
 * @author beaverbae
 * @see https://onlytrying.tistory.com/m/154?category=837398
 *
 */

public class BOJ_1062_가르침 {
	static int[] arr;
	static HashSet<Character> set;
	static int N, K;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int learn = 0;
		learn |= getNum('a');
		learn |= getNum('c');
		learn |= getNum('i');
		learn |= getNum('n');
		learn |= getNum('t');
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		K -= 5;
		arr = new int[N];
		set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine().replaceAll("anta|tica", "");
			char[] temp = str.toCharArray();
			int v = 0;
			for (char ch : temp) {
				v |= getNum(ch);
				set.add(ch);
			}
			arr[i] = v;
		}
		
		if (K < 0) System.out.println(0);
		else {
			if (K > set.size()) K = set.size();
			dfs(learn, 0, 0);
			System.out.println(answer);
		}
	}
	
	private static void dfs(int learn, int start, int cnt) {
		if (cnt == K) {
			answer = Math.max(answer, getReadableWordsCnt(learn));
			return;
		}
		
		for (int i = start; i < 26; i++) {
			int alphabet = 1 << i;
			if ((learn & alphabet) != 0) continue;
			
			int next_learn = learn | alphabet;
			dfs(next_learn, i+1, cnt+1);
		}
	}

	private static int getReadableWordsCnt(int learn) {
		int result = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & learn) == arr[i]) result++;
		}
		
		return result;
	}

	private static int getNum(char ch) {
		return 1 << (ch-'a');
	}
}
