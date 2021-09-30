package A2021_09_30;

import java.util.*;
import java.io.*;

/**
 * Sorting
 * @author beaverbae
 * pq가 삽입시 log n 만큼의 시간복잡도가 걸려서 배열로 푸는거 보다 느림
 */

public class BOJ_2751_수_정렬하기_2_ver2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		while (N-- > 0) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		System.out.println(sb);
	}
}
