package A2021_06_14;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 진짜 문제 좀 장 읽자
 * 
 * 어려웠던 부분
 * - 파라메트릭 서치로 오인 : 결정 문제로 변환 불가 (기준이 없음)
 * - "사람까지의 거리의 합" 문구 해석 :
 *     input
 *     3
 *     1 1
 *     2 1
 *     10000 1 
 *     
 *     answer : 2 (가장 작은 위치 출력)
 *   
 * @author beave
 * @see https://hsdevelopment.tistory.com/490
 */

public class BOJ_2141_우체국_solution {
	static long left, right;
	static List<Pair> list;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long total = 0;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long n = Long.parseLong(st.nextToken());
			
			list.add(new Pair(x, n));
			left = Math.min(left, x);
			right = Math.max(right, x);
			total += n;
		}
		
		Collections.sort(list, (o1, o2) -> Long.compare(o1.x, o2.x));
		
		long cnt = 0;
		for (Pair p : list) {
			cnt += p.n;
			if (cnt >= (total + 1) / 2) {
				System.out.println(p.x);
				break;
			}
		}
	}


	static class Pair {
		long x, n;

		public Pair(long x, long n) {
			this.x = x;
			this.n = n;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", n=" + n + "]";
		}
	}
}
