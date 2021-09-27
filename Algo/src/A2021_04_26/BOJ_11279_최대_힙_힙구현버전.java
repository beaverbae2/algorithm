package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * Heap
 * @author beaverbae
 *
 */

public class BOJ_11279_최대_힙_힙구현버전 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		MaxHeap maxHeap = new MaxHeap(N);
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x == 0) {
				if (maxHeap.isEmpty()) {
					sb.append(0).append("\n");
				} else {
					sb.append(maxHeap.poll()).append("\n");
				}
			} else {
				maxHeap.add(x);
			}
		}
		
		System.out.println(sb.toString());
	}
	
	
	static class MaxHeap {
		int[] arr;
		int cnt;
		
		public MaxHeap(int size) {
			this.arr = new int[size];
			this.cnt = 0;
		}
		
		public void add(int data) {
			arr[cnt] = data;
			int idx = cnt;
			int p_idx = (idx - 1) /2;
			
			while (arr[p_idx] < arr[idx]) {
				swap(p_idx, idx);
				idx = p_idx;
				p_idx = (idx-1)/2;
			}
			
			cnt++;
		}
		
		public int poll() {
			int v = arr[0];
			swap(0, cnt-1);
			cnt--;
			heapify(0);
			return v;
		}
		
		private void heapify(int p_idx) {
			int max = arr[p_idx];
			int idx = p_idx;
			
			int left = 2 * p_idx + 1;
			if (left < cnt && arr[left] > max) {
				idx = left;
				max = arr[left];
			}
			
			int right = left + 1;
			if (right < cnt && arr[right] > max) {
				idx = right;
				max = arr[right];
			}
			
			if (idx != p_idx) {
				swap(p_idx, idx);
				heapify(idx);
			}
		}

		public void swap(int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		public boolean isEmpty() {
			if (cnt == 0) return true;
			return false;
		}
		
	}
}
