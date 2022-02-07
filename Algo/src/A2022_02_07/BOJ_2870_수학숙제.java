package A2022_02_07;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

/**
 * Regex
 * @author beaverbae
 *
 */

public class BOJ_2870_수학숙제 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		LinkedList<BigInteger> list = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		
		while (N-- > 0) {
			String[] strDigits = br.readLine().split("\\D+");
			
			for (String strDigit : strDigits) {
				if (strDigit.equals("")) continue;
				list.add(new BigInteger(strDigit));
			}
		}
		
		Collections.sort(list);
		for (BigInteger n : list) {
			sb.append(n).append("\n");
		}
		
		System.out.println(sb);
	}
}
