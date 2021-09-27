package A2021_09_24;

import java.util.*;
import java.io.*;

/**
 * 자료구조
 * 5MIN
 * @author beaverbae
 *
 */

public class BOJ_2164_카드2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		Deque<Integer> dq = new ArrayDeque<>();
		N = Integer.parseInt(br.readLine());
		for (int i = N; i > 0; i--) {
			dq.add(i);
		}
		
		while (dq.size() > 1) {
			dq.removeLast();
			dq.addFirst(dq.removeLast());
		}
		
		System.out.println(dq.pop());
	}
}
