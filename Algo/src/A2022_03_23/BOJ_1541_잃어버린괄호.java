package A2022_03_23;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * @author beaverbae
 * - 이런 문제가 어려운 것 같다
 */

public class BOJ_1541_잃어버린괄호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] s1 = input.split("\\+|-");
		String[] ops = input.split("[0-9]+");
		int N = s1.length;
		
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(s1[i]);
		}
		
		int ans = 0, temp = nums[0];
		boolean isMinus = false;
		
		for (int i = 1; i < N; i++) {
			if (ops[i].equals("-")) {
				if (isMinus) ans -= temp;
				else ans += temp;
				isMinus = true;
				temp = nums[i];
			} else temp += nums[i];
		}
		
		if (isMinus) ans -= temp;
		else ans += temp;
	
		System.out.println(ans);
	}
}
