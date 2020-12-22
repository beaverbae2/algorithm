package A2020_12_22;

import java.util.*;
import java.io.*;

//그리디
public class BOJ_13416_주식투자 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < TC; tc++) {
			int C = Integer.parseInt(br.readLine());
			
			int answer = 0;
			for (int i = 0; i < C; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int max = 0;
				for (int j = 0; j < 3; j++) {
					max = Math.max(max, Integer.parseInt(st.nextToken()));
				}
				answer += max;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
