package A2021_02_01;

import java.util.*;
import java.io.*;

public class BOJ_1244_스위치_켜고_끄기 {
	static int[] light;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		light = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			light[i] = Integer.parseInt(st.nextToken());
		}
	
		int cnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if (gender == 1) {// 남자
				// 배수
				for (int j = num; j <= N; j+=num) {
					change(j);
				}
				
			}else {// 여자
				change(num);
				int left = num - 1;
				int right = num +1;
				while(true) {
					if (left<1 || right>N || light[left] != light[right]) break;
					
					change(left);
					change(right);
					left--;
					right++;
				}
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int idx = i%20;
			sb.append(light[i]).append(" ");
			if (i != 1 && idx==0) sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void change(int idx) {
		if (light[idx] == 1) light[idx] = 0;
		else light[idx] = 1;
	}
}
