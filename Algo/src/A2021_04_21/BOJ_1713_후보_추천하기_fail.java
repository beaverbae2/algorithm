package A2021_04_21;

import java.util.*;
import java.io.*;

public class BOJ_1713_후보_추천하기_fail {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		
		Pair[] arr = new Pair[101];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int t = 0; t < M; t++) {
			int idx = Integer.parseInt(st.nextToken());
			
			if (pq.size() == N) {
				if (arr[idx] == null) {
					Pair temp = pq.poll();
					arr[temp.idx] = null;
					
					Pair p = new Pair(idx, 1, t);
					arr[idx] = p;
					pq.offer(p);
				} else {
					arr[idx].r++;
					int cnt = 0;
					while (cnt < pq.size()) {
						pq.offer(pq.poll());
						cnt++;
					}
				}
			} else {
				if (arr[idx] == null) {
					Pair p = new Pair(idx, 1, t);
					arr[idx] = p;
					pq.offer(p);
				} else {
					arr[idx].r++;
					int cnt = 0;
					while (cnt < pq.size()) {
						pq.offer(pq.poll());
						cnt++;
					}
				}
			}
			
//			System.out.println(pq);
//			for (int i = 0; i < arr.length; i++) {
//				if (arr[i] != null) {
//					System.out.println(arr[i]+" ");
//				}
//			}
//			System.out.println();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
	
	static class Pair implements Comparable<Pair>{
		int idx, r, t;

		public Pair(int idx, int r, int t) {
			this.idx = idx;
			this.r = r;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Pair [idx=" + idx + ", r=" + r + ", t=" + t + "]";
		}

		@Override
		public int compareTo(Pair o) {
			if (this.r != o.r) {
				return Integer.compare(this.r, o.r);
			}
			
			return Integer.compare(this.t, o.t);
		}
	}
}
