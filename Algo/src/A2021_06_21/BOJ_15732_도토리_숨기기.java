package A2021_06_21;

import java.util.*;
import java.io.*;

/**
 * Binary Search
 * 26MIN
 * 어려웠던 부분
 * - 이분탐색 조건 : '앞의 상자 부터' 꽉꽉 채워야 한다 -> mid에서 구한 cnt가 D여도 더 작은 값이 없는지 확인해야함
 * @author beaverbae
 *
 */

public class BOJ_15732_도토리_숨기기 {
	static int min, max;
	static final int INF = 1000000001;
	static int N, K, D;
	static Node[] arr;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	
		arr = new Node[K];
		min = INF;
		max = 0;
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int gap = Integer.parseInt(st.nextToken());
		
			arr[i] = new Node(start, end, gap);
			min = Math.min(min, start);
			max = Math.max(max, end);
		}
		
		System.out.println(binary_search());
	}
	
	private static int binary_search() {
		int result = -1;
		
		int left = min;
		int right = max;
	
		while (left <= right) {
			int mid = (left + right) / 2; 
			
			// 도토리의 개수
			int cnt = 0;
			for (Node node : arr) {
				int start = node.start;
				int end = node.end;
				int gap = node.gap;
				
				if (start > mid) continue;
				
				if (mid > end) {
					cnt += 1 + (end - start) / gap; 
				} else {
					cnt += 1 + (mid - start) / gap; 
				}
				
				if (cnt > D) break;
			}
			
			if (cnt >= D) {
				right = mid - 1;
				result = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return result;
	}
	
	static class Node {
		int start, end, gap;

		public Node(int start, int end, int gap) {
			this.start = start;
			this.end = end;
			this.gap = gap;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", gap=" + gap + "]";
		}
	}
}
