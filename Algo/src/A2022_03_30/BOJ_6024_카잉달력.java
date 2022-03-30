package A2022_03_30;

import java.util.*;
import java.io.*;
/**
 * Math
 * 53MIN
 * @author beaverbae
 * - 로직 머리로 돌려보자...
 * - x - y == X - Y 경우 정답성립
 * - x = 1, y = 1로 돌아오면 종료
 */


public class BOJ_6024_카잉달력 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (TC-- > 0) {
			int M, N, X, Y;
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			int x = 1, y = 1, cnt = 1;
			boolean flag = false;
			while (true) {
				if (X - Y != x - y) {
					int d = Math.min(M - x + 1, N - y + 1);
					cnt += d;
					x += d;
					y += d;
				} else {
					int d = X - x;
					cnt += d;
					flag = true;
					break;
				}
				
				if (x > M) x = 1;
				if (y > N) y = 1;
				
				if (x == 1 && y == 1) break;
			}
			
			if (flag) sb.append(cnt);
			else sb.append(-1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
