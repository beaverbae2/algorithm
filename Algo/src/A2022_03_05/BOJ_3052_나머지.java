package A2022_03_05;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class BOJ_3052_나머지 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 10;
		final int MOD = 42;
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine()) % MOD;
		}
		
		IntStream intStream = IntStream.of(arr);
		System.out.println(intStream.distinct().count());
		
	}
}
