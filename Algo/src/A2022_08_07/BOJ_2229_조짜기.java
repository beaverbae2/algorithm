package A2022_08_07;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * @see https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=occidere&logNo=221535723529
 */

public class BOJ_2229_조짜기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] s = new int[N+1];
		int[] d = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int max, min;
			s[i] = Integer.parseInt(st.nextToken());
			max = min = s[i];
			
			for (int j = i; j > 0; j--) {
				max = Math.max(max, s[j]);
				min = Math.min(min, s[j]);
				d[i] = Math.max(d[i], max - min + d[j-1]);
			}
		}
		
		System.out.println(d[N]);
	}
}
