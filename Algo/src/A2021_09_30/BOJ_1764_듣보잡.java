package A2021_09_30;

import java.util.*;
import java.io.*;

/**
 * Hashing
 * 7MIN
 * @author beaverbae
 * 문제 똑바로 읽자
 */

public class BOJ_1764_듣보잡 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M, cnt;
		HashSet<String> notHeard;
		TreeSet<String> notHeardAndnotSaw;
		StringBuilder sb;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = 0;
		notHeard = new HashSet<>();
		notHeardAndnotSaw = new TreeSet<>();
		sb = new StringBuilder();
		
		while (N-- > 0) {
			notHeard.add(br.readLine());
		}
		
		while (M-- > 0) {
			String notSawPerson = br.readLine();
			if (!notHeard.contains(notSawPerson)) continue;
			cnt++;
			notHeardAndnotSaw.add(notSawPerson);
		}
		
		for (String person : notHeardAndnotSaw) {
			sb.append(person).append("\n");
		}
		
		System.out.println(cnt);
		System.out.println(sb);
	}
}
