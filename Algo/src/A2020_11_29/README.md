# 이분 탐색의 진행

- 점차 최적값을 향해 진행
- 관련 문제
  - [백준 2512 예산](https://www.acmicpc.net/problem/2512)
  - [백준 2805 나무 자르기](https://www.acmicpc.net/problem/2805)
  - [백준 1654 랜선 자르기](https://www.acmicpc.net/problem/1654)

- 풀이 예시

  ```java
  package A2020_11_29;
  
  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  import java.util.StringTokenizer;
  
  public class BOJ_2805_나무자르기 {
  	public static void main(String[] args) throws Exception {
  		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  		StringTokenizer st = new StringTokenizer(br.readLine());
  		int N = Integer.parseInt(st.nextToken());
  		int M = Integer.parseInt(st.nextToken());
  		int[] trees = new int[N];
  		
  		int start = 0, end = 0, answer = 0;
  		
  		st = new StringTokenizer(br.readLine());
  		for (int i = 0; i < N; i++) {
  			trees[i] = Integer.parseInt(st.nextToken());
  			end = Math.max(trees[i], end);
  		}
  		
  		while(start<=end) {
  			int mid = (start+end)/2;
  			long sum = 0;
  			for (int tree : trees) {
  				if(tree>mid) sum += (tree-mid);
  			}
  			
  			if(sum>=M) {//점차 sum이 M과 가까워 지는 값으로 이동함
  				answer = mid;
  				start = mid+1;
  			}else end = mid-1;
  		}
  		System.out.println(answer);
  	}
  }
  ```

# 어려웠던 문제

- [백준 1300 K번째 수](https://www.acmicpc.net/problem/1300)
  - 어렵다....
  - [참고링크](https://geehye.github.io/baekjoon-1300/) 
- [백준 2512 예산](https://www.acmicpc.net/problem/2512)
  - 같은 수가 여러 개가 있는 경우가 까다로웠다. HashMap을 이용해서 처리했다


  

