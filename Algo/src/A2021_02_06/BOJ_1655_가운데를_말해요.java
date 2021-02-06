package A2021_02_06;

import java.util.*;
import java.io.*;
/**
 * Heap
 * @author beaverbae
 * @see https://dragon-h.tistory.com/6
 */
public class BOJ_1655_가운데를_말해요 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1-o2);
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2-o1);
		
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if (minHeap.size() == maxHeap.size()) {
				maxHeap.add(n);
			}else {
				minHeap.add(n);
			}
			
			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if (minHeap.peek() < maxHeap.peek()) {
					// swap
					int temp = minHeap.poll();
					minHeap.add(maxHeap.poll());
					maxHeap.add(temp);
				}
			}
			sb.append(maxHeap.peek()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
