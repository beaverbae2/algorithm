package A2021_10_01;

import java.util.*;
import java.io.*;

/**
 * Heap
 * @author beaverbae
 *
 */

public class BOJ_11279_최대_힙_구현버전 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		MyMaxHeap maxHeap = new MyMaxHeap(N);
		StringBuilder sb = new StringBuilder();
		
		while (N-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (maxHeap.isEmpty()) sb.append(0);
				else sb.append(maxHeap.poll());
				sb.append("\n");
			} else maxHeap.add(x);
		}
		
		System.out.println(sb);
	}
	
	static class MyMaxHeap {
		int[] arr;
		int idx;
		
		public MyMaxHeap(int n) {
			this.arr = new int[n+1];
			this.idx = 1;
		}
		
		public void add(int data) {
			arr[this.idx] = data;
			int idx = this.idx;
			int p_idx = idx / 2;
			
			while (p_idx > 0 && arr[p_idx] < arr[idx]) {
				swap(idx, p_idx);
				idx = p_idx;
				p_idx = idx / 2;
			}
			
			this.idx++;
		}
		
		public int poll() {
			int root = arr[1];
			arr[1] = arr[idx-1];
			heapify(1);
			idx--;
			return root;
		}
		

		public boolean isEmpty() {
			return this.idx == 1;
		}

		private void heapify(int p_idx) {
			int idx = p_idx;
			int max = arr[idx];
			
			int left = 2 * idx;
			int right = left + 1;
			
			if (left < this.idx && max < arr[left]) {
				max = arr[left];
				idx = left;
			}
			
			if (right < this.idx && max < arr[right]) {
				max = arr[right];
				idx = right;
			}
			
			if (p_idx != idx) {
				swap(idx, p_idx);
				heapify(idx);
			}
		}
		
		private void swap(int i, int j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
}
