package A2021_06_11;

import java.util.*;
import java.io.*;

/**
 * Two Pointer
 * 50MIN (질문 검색 참고)
 * 
 * 어려웠던 부분
 * - 세용액을 투포인터로 표현하는 방법 : 하나 고정 시키고, 나머지 두 개를 포인터로 잡아서 해결
 * - 포인터 이동 조건문 오류 : 고정 시킨 용액의 특성값 == 두 용액의 합인 경우를 답으로 해버림... =>  세 용액의 합이 0, < 0, > 0 인 경우로 나뉨
 * - 오버플로우 문제 : int대신 long으로 표현
 * 
 * @author beaverbae
 *
 */

public class BOJ_2473_세_용액 {
	static int N;
	static ArrayList<Long> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Long.parseLong(st.nextToken()));
		}
		
		Collections.sort(list);
		
		long answer = 3000000000L;
		long a = 0;
		long b = 0;
		long c = 0;
		
		boolean isZero = false;
		for (int i = 0; i < N; i++) {
			long target = list.remove(i);
		
			int left = 0;
			int right = list.size() - 1;
			
			while (left < right) {
				long sum = list.get(left) + list.get(right);
				long total_sum = sum + target;
				long total_sum_abs = Math.abs(total_sum);
				
				if (answer > total_sum_abs) {
					answer = total_sum_abs;
					a = target;
					b = list.get(left);
					c = list.get(right);
				}
				
				if (total_sum == 0) {
					isZero = true;
					break;
				} else if (total_sum > 0) {
					right--;
				} else {
					left++;
				}
			}
			
			list.add(i, target);
			
			if (isZero) break;
		}
		
		long[] arr = new long[3];
		arr[0] = a;
		arr[1] = b;
		arr[2] = c;
		Arrays.sort(arr);
		
		System.out.println(arr[0]+" "+arr[1]+" "+arr[2]);
	}
}
