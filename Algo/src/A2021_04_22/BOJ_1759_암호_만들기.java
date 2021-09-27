package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * Combination
 * 15MIN
 * 오래 걸린 이유
 * - 좀 막풀었음... 자음 모음 개수 고려x, toCharArray()
 * @author beaverbae
 *
 */
public class BOJ_1759_암호_만들기 {
	static int L, C;
	static char[] input, arr;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	
		sb = new StringBuilder();
		arr = new char[L];
		input = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(input);
		combination(0, 0, 0, 0);
		System.out.println(sb.toString());
	}

	private static void combination(int cnt, int start, int consonant, int vowel) {
		if (cnt == L) {
			if (consonant < 2 || vowel < 1) return;
			
			for (char ch : arr) {
				sb.append(ch);
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < input.length; i++) {
			arr[cnt] = input[i];
			if (isVowel(arr[cnt])) {
				combination(cnt+1, i+1, consonant, vowel+1);
			} else {
				combination(cnt+1, i+1, consonant+1, vowel);
			}
		}
	}
	
	private static boolean isVowel(char ch) {
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
	}
}
