package A2022_03_29;

import java.util.*;
import java.io.*;

/**
 * Greedy, Sorting
 * @author beaverbae
 * @see https://steady-coding.tistory.com/56
 * - 보석을 무게에 오름차순, 무게가 같은 경우 가치 내림차순 정렬
 * - pq는 가치에 내림차순 정렬 
 * - 현재 가방의 무게 >= 보석의 무게 면 pq에 보석 가치 추가 
 * - 아니면 pq의 첫번째 원소(가장높은가치) 더해줌
 */

public class BOJ_1202_보석도둑 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long ans = 0;
		int N, K;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Pair[] arr = new Pair[N];
		int[] bags = new int[K];
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[i] = new Pair(v, w);
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);
		
		int j = 0;
		for (int bag_w : bags) {
			while (j < N && bag_w >= arr[j].w) {
				pq.add(arr[j++].v);
			}
			
			if (!pq.isEmpty()) {
				ans += pq.poll();
			}
		}
		
		System.out.println(ans);
	}
	
	
	static class Pair implements Comparable<Pair> {
		int v, w;

		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Pair [v=" + v + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Pair o) {
			if (this.w != o.w) return this.w - o.w;
			return o.v - this.v;
		}
	}
}