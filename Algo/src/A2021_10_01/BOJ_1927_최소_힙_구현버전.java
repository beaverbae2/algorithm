package A2021_10_01;

import java.util.*;
import java.io.*;

/**
 * Heap
 * @author beaverbae
 *
 */

public class BOJ_1927_최소_힙_구현버전 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		MyHeap heap = new MyHeap(N);
		StringBuilder sb = new StringBuilder();
		
		while (N-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (heap.isEmpty()) sb.append(0);
				else sb.append(heap.poll());
				sb.append("\n");
			} else heap.add(x);
		}
		
		System.out.println(sb);
	}
	
	static class MyHeap {
		int[] arr;
		int idx;
		
		public MyHeap(int n) {
			this.arr = new int[n+1];
			this.idx = 1;
		}
		
		public void add(int data) {
			arr[this.idx] = data;
			int idx = this.idx;
			int p_idx = idx / 2;
			
			while (p_idx > 0 && arr[p_idx] > arr[idx]) {
				swap(p_idx, idx);
				idx = p_idx;
				p_idx = idx / 2;
			}
			this.idx++;
		}
		
		public int poll() {
			int root = arr[1];
			arr[1] = arr[this.idx - 1];
			heapify(1);
			idx--;
			return root;
		}
		
		public boolean isEmpty() {
			return this.idx == 1;
		}
		
		private void heapify(int p_idx) {
			int idx = p_idx;
			int min = arr[p_idx];
			int left = 2 * p_idx;
			int right = left + 1;
			
			if (left < this.idx && min > arr[left]) {
				min = arr[left];
				idx = left;
			}
			if (right < this.idx && min > arr[right]) {
				min = arr[right];
				idx = right;
			}
			
			if (idx != p_idx) {
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
