package A2021_05_03;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 어려웠던 부분
 * - 가방 무게를 오름차순으로 정렬하고, 순서대로 보석을 할당하는것 까지는 알았음
 * - 보석 할당시 해당 가방 무게 이하의 무게인 보석중에 가격이 가장 높은 물건을 어떻게 넣어야 했는데 이 부분을 구현 못함
 * - 솔루션을 보니 가격의 내림차순으로 정렬하는 pq를 만들고, 가방의 무게 이하인 보석을 모두 pq에 집어 넣고 poll하면 됐음
 * @author beaverbae
 * @see J.H.KIM solution
 */

public class BOJ_1202_보석_도둑_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		Pair[] jewelry = new Pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
		
			jewelry[i] = new Pair(w, v);
		}
		
		int[] bag = new int[M];
		for (int i = 0; i < M; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(jewelry);
		Arrays.sort(bag);
		
		long answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()); //가격의 내림차순
		
		int idx = 0;
		for (int capacity : bag) {
			while (idx < N && capacity >= jewelry[idx].weight) {
				pq.offer(jewelry[idx++].value);
			}
			
			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		System.out.println(answer);
	}
	
	
	static class Pair implements Comparable<Pair>{
		int weight, value;

		public Pair(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Pair [weight=" + weight + ", value=" + value + "]";
		}

		@Override
		public int compareTo(Pair o) {
			return this.weight - o.weight;
		}
	}
}
