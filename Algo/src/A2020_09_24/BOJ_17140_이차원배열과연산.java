package A2020_09_24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산 {
	static int[][] map = new int[3][3];
	static int r,c,k,answer;
	
	static class Pair implements Comparable<Pair>{
		int n,c;//num, count

		public Pair(int n, int c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [n=" + n + ", c=" + c + "]";
		}

		@Override
		public int compareTo(Pair p) {
			int c1 = this.c;
			int c2 = p.c;
			
			if(c1!=c2) return Integer.compare(c1, c2);
			else {
				return Integer.compare(this.n, p.n);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//구현 시작
		while(true) {
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
		
			//종료조건
			if(r<map.length&&c<map[0].length&&map[r][c]==k) break;
			if(answer>100) {
				answer = -1;
				break;
			}
			
			//연산 수행(100넘어가는지 확인)
			int R_len = map.length;
			int C_len = map[0].length;
			
			ArrayList<Pair>[] next_map;//다음 map의 값을 저장할 next_map
			
			if(R_len>=C_len) {//R연산
				next_map = new ArrayList[R_len];
				int next_C_len = 0;
				for (int i = 0; i < next_map.length; i++) {
					next_map[i] = new ArrayList<>();
				}
				for (int i = 0; i < map.length; i++) {
					int[] num_counts = new int[101];//숫자의 등장횟수
					boolean[] visited = new boolean[101];
					Queue<Integer> nums = new LinkedList<>();
					for (int j = 0; j < map[0].length; j++) {
						if(map[i][j]==0) continue; 
						num_counts[map[i][j]]++;
						
						if(visited[map[i][j]]) continue;
						nums.offer(map[i][j]);
						visited[map[i][j]] = true;
					}
					
					int nums_len = 0;//nums큐의 길이
					while(true) {
						if(nums.isEmpty()) break;
						if(nums_len==50) break;
						
						int num = nums.poll();
						next_map[i].add(new Pair(num, num_counts[num]));
						nums_len++;
					}
					Collections.sort(next_map[i]);
					next_C_len = Math.max(next_C_len, 2*next_map[i].size());
				}
				map = new int[R_len][next_C_len];
				for (int i = 0; i < next_map.length; i++) {
					int j = 0;
					for (Pair p : next_map[i]) {
						map[i][j] = p.n;
						map[i][j+1] = p.c;
						j+=2;
					}
				}
			}else {//C연산
				next_map = new ArrayList[C_len];
				int next_R_len = 0;
				for (int i = 0; i < next_map.length; i++) {
					next_map[i] = new ArrayList<>();
				}
				for (int i = 0; i < map[0].length; i++) {
					int[] num_counts = new int[101];//숫자의 등장횟수
					boolean[] visited = new boolean[101];
					Queue<Integer> nums = new LinkedList<>();
					for (int j = 0; j < map.length; j++) {
						if(map[j][i]==0) continue; 
						num_counts[map[j][i]]++;
						
						if(visited[map[j][i]]) continue;
						
						nums.offer(map[j][i]);
						visited[map[j][i]] = true;
					}
					
					int nums_len = 0;//nums큐의 길이
					while(true) {
						if(nums.isEmpty()) break;
						if(nums_len==50) break;
						
						int num = nums.poll();
						next_map[i].add(new Pair(num, num_counts[num]));
					}
					Collections.sort(next_map[i]);
					next_R_len = Math.max(next_R_len, 2*next_map[i].size());
				}
				map = new int[next_R_len][C_len];
				for (int i = 0; i < next_map.length; i++) {
					int j = 0;
					for (Pair p : next_map[i]) {
						map[j][i] = p.n;
						map[j+1][i] = p.c;
						j+=2;
					}
				}
			}
			answer++;
			
		}
		
		System.out.println(answer);
	}
}
