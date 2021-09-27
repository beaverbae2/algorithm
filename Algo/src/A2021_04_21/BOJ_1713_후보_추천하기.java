package A2021_04_21;

import java.util.*;
import java.io.*;

public class BOJ_1713_후보_추천하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] recommend = new int[101];
		int[] time = new int[101];
		Arrays.fill(time, 1001);
		
		int size = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int t = 0; t < M; t++) {
			int id = Integer.parseInt(st.nextToken());
			if (size == N) {
				if (recommend[id] == 0) {
					// 지우기
					int remove_time = 1001;
					int remove_rec = 1001;
					int remove_idx = -1;
					
					for (int i = 1; i < recommend.length; i++) {
						if (recommend[i] !=0) {
							if (recommend[i] < remove_rec) {
								remove_rec = recommend[i];
								remove_idx = i;
								remove_time = time[i];
							} else if (recommend[i] == remove_rec) {
								if (time[i] < remove_time) {
									remove_idx = i;
									remove_time = time[i];
								}
							}
						}
					}
					
					recommend[remove_idx] = 0;
					time[remove_idx] = 1001;
					
					recommend[id]++;
					time[id] = t;
				} else {
					recommend[id]++;
				}
			} else {
				if (recommend[id] == 0) {
					recommend[id]++;
					time[id] = t;
					size++;
				} else {
					recommend[id]++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < recommend.length; i++) {
			if (recommend[i] != 0) sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	
	}
	
	static class Node {
		int idx, rec, time;

		public Node(int idx, int rec, int time) {
			this.idx = idx;
			this.rec = rec;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", rec=" + rec + ", time=" + time + "]";
		}
	}
}
