package A2021_02_17;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * @author beaverbae
 * @see https://steady-coding.tistory.com/12
 *
 */

public class BOJ_2212_센서_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] censors = new int[N];
		int[] dist = new int[N-1];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			censors[i] = Integer.parseInt(st.nextToken());
		}
		if (K >= N) System.out.println(0);
		
		else {
			Arrays.sort(censors);
			for (int i = 1; i < censors.length; i++) {
				dist[i-1] = censors[i] - censors[i-1];
			}
			Arrays.sort(dist);
			int answer = 0;
			for (int i = 0; i < dist.length-(K-1); i++) {
				answer += dist[i];
			}
			System.out.println(answer);
		}
	}
}
