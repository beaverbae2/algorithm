package A2021_03_16;

import java.util.*;
import java.io.*;

public class BOJ_1453_직각삼각형 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int[] arr = new int[3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) {
				break;
			}
			
			Arrays.sort(arr);
			if (arr[0]*arr[0] + arr[1]*arr[1] == arr[2]*arr[2]) {
				sb.append("right");
			} else {
				sb.append("wrong");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
