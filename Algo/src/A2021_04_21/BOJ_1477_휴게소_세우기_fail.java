package A2021_04_21;

import java.util.*;
import java.io.*;

public class BOJ_1477_휴게소_세우기_fail {
	static int N, M, L;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	
		arr = new int[N+2];
		arr[0] = 0;
		arr[N+1] = L;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
	
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		
		for (int i = 1; i < arr.length; i++) {
			int dist = arr[i] - arr[i-1];
			pq.offer(dist);
		}
		
		System.out.println(pq);
		
		for (int i = 0; i < M; i++) {
			System.out.println(pq.peek());
			int dist = pq.poll();
			int next_dist1 = dist/2;
			int next_dist2 = next_dist1;
			
			if (dist % 2 != 0) {
				next_dist2++;
			}
			
			pq.offer(next_dist1);
			pq.offer(next_dist2);
		}
		
		System.out.println(pq.poll()-2);
	}
	
	
	
}
