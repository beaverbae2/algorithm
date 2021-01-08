package A2021_01_08;

import java.util.*;
import java.io.*;
/**
 * 
 * @author beaverbae
 * @see https://velog.io/@hyeon930/BOJ-8982-%EC%88%98%EC%A1%B1%EA%B4%80-1-Java
 *
 */
public class BOJ_8982_수족관1_Solution {
	static int[] depth;//depth[i] : i번 세로줄의 물 높이
	static int[] surface;//surface[i] : i번 세로줄에서 버려진 물 높이
	static Pair[] hole;//구멍
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		depth = new int[40001];
		surface = new int[40001];
		
		int N = Integer.parseInt(br.readLine());
		int lastIdx = 0;
		
		
		//depth구성하기
		br.readLine();//0,0 제거
		for (int i = 0; i < N/2 - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int c2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
		
			for (int j = c1; j <= c2; j++) {
				depth[j] = r1;
			}
			
			lastIdx = c2-1;
		}
		br.readLine();//마지막제거
		
		int K = Integer.parseInt(br.readLine());
		hole = new Pair[K];
		
		for (int i = 0; i < hole.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dep = Integer.parseInt(st.nextToken());
			hole[i] = new Pair(dep, idx);
		}
		
		for (Pair p : hole) {
			int curDepth = p.r;
			int c = p.c;
			
			//왼쪽
			for (int i = c; i >= 0; i--) {
				//현재 깊이를 최소로 유지
				curDepth = curDepth > depth[i] ? depth[i] : curDepth;
				surface[i] = curDepth > surface[i] ? curDepth : surface[i];
			}
			
			curDepth = p.r;
			c = p.c;
			
			//오른쪽
			for (int i = c; i <= lastIdx; i++) {
				//현재 깊이를 최소로 유지
				curDepth = curDepth > depth[i] ? depth[i] : curDepth;
				surface[i] = curDepth > surface[i] ? curDepth : surface[i];
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i <= lastIdx; i++) {
			answer += depth[i] - surface[i];
		}
		System.out.println(answer);
	}
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
}
