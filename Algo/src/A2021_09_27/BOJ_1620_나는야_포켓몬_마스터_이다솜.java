package A2021_09_27;

import java.util.*;
import java.io.*;

/**
 * Hashing
 * 9MIN
 * @author beaverbae
 * 
 */

public class BOJ_1620_나는야_포켓몬_마스터_이다솜 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N, M;
		HashMap<String, String> map1 = new HashMap<>();
		HashMap<String, String> map2 = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			String name = br.readLine();
			map1.put(name, i+"");
			map2.put(i+"", name);
		}
		
		while (M-- > 0) {
			String input = br.readLine();
			if (map1.containsKey(input)) sb.append(map1.get(input));
			else sb.append(map2.get(input));
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
