package A2021_04_26;

import java.util.*;
import java.io.*;

public class BOJ_1927_최소_힙_힙구현버전 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		MinHeap minHeap = new MinHeap(N);
		
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x == 0) {
				if (minHeap.isEmpty()) {
					sb.append(0);
				} else {
					sb.append(minHeap.poll());
				}
				sb.append("\n");
			} else {
				minHeap.add(x);
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static class MinHeap {
		int[] arr;
		int cnt;
		
		public MinHeap (int size) {
			this.arr = new int[size];
			cnt = 0;
		}
		
		public boolean isEmpty() {
			return cnt == 0;
		}
		
		public void add(int x) {
			arr[cnt] = x;
			int idx = cnt;
			int p_idx = (idx-1)/2;
			
			while (arr[p_idx] > arr[idx]) {
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
	
		public void heapify(int p_idx) {
			int min = arr[p_idx];
			int idx = p_idx;
			
			int left = 2*p_idx+1;
			if (left < cnt && min > arr[left]) {
				idx = left;
				min = arr[left];
			}
			
			int right = left+1;
			if (right < cnt && min > arr[right]) {
				idx = right;
				min = arr[right];
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
	}
}
