package A2020_12_24;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_1339_단어수학Greedy {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		Integer[] alphabet = new Integer[26];
		Arrays.fill(alphabet, 0);
		
		for (int i = 0; i < N; i++) {
			String t = str[i];
			for (int j = 0; j < t.length(); j++) {
				alphabet[(t.charAt(j) - 'A')] += (int) Math.pow(10, (t.length() - j - 1));
			}
		}
		
//		System.out.println(Arrays.toString(alphabet));
		int sum = 0;
		Arrays.sort(alphabet, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		
		});
//		System.out.println(Arrays.toString(alphabet));
		int idx = 9;
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] != 0) {
				sum += alphabet[i] * idx--;
			}
			if (alphabet[i] == 0)
				break;
		}

		System.out.println(sum);

	}
}