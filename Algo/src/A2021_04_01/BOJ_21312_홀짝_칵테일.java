package A2021_04_01;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbae
 *
 */

public class BOJ_21312_홀짝_칵테일 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 1;
		LinkedList<Integer> odds = new LinkedList<>();
		LinkedList<Integer> evens  = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			int v = Integer.parseInt(st.nextToken());
			
			if (v % 2 == 0) {
				evens.add(v);
			} else {
				odds.add(v);
			}
		}
		
		if (odds.isEmpty()) {
			for (int v : evens) {
				answer *= v;
			}
		} else {
			for (int v : odds) {
				answer *= v;
			}
		}
		System.out.println(answer);
	}
}
