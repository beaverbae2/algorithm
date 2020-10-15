package SAMSUMG_2020_10_14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_16235_나무재테크 {
	static int[][] map;//양분
	static int[][] spring_add_map;//봄에 죽은 나무의 양분
	static int[][] winter_add_map;//겨울에 추가하는 양분
	
	static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};//가을 인접확인
	static LinkedList<Integer>[][] trees;
	static int N,M,K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		map = new int[N][N];
		trees = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				trees[i][j] = new LinkedList<>();
			}
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 5);
		}
		winter_add_map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				winter_add_map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			trees[r][c].add(age);
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(winter_add_map[i]));
//		}
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(trees[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for (int k = 0; k < K; k++) {
			spring();
			summer();
			autumn();
			winter();
		}
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tree_cnt = trees[i][j].size();
				if(tree_cnt==0) continue;
				answer += tree_cnt;
			}
		}
		System.out.println(answer);
	}

	private static void spring() {
		spring_add_map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tree_cnt = trees[i][j].size();
				if(tree_cnt==0) continue;
				
				//나무를 나이순으로 정렬
				Collections.sort(trees[i][j], new Comparator<Integer>() {
					@Override
					public int compare(Integer age1, Integer age2) {
						return Integer.compare(age1, age2);
					}
				});
				LinkedList<Integer> new_trees = new LinkedList<>();
				for (int k = 0; k < trees[i][j].size(); k++) {
					int age = trees[i][j].get(k);
					if(map[i][j]>=age) {
						map[i][j] -= age;//나이만큼 양분 흡수 
						new_trees.add(age+1);
					}else {//나우의 나이가 양분보다 더 많은 경우
						spring_add_map[i][j] += age/2;
					}
				}
				//죽은 나무 삭제
				trees[i][j] = new_trees;

			}
		}
//		System.out.println("spring");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(trees[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}

	private static void summer() {
		//양분 추가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += spring_add_map[i][j];
			}
		}
//		System.out.println("summmer");	
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
	}

	private static void autumn() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				//5의 배수인 나무 찾기
				for (int age : trees[r][c]) {
					if(age%5!=0) continue;
					// 5의 배수
					for (int d = 0; d < dirs.length; d++) {
						int nr = r+dirs[d][0];
						int nc = c+dirs[d][1];
					
						if(!isInMap(nr, nc)) continue;
					
						trees[nr][nc].add(1);
					} 
				}
			}
		}
	}

	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += winter_add_map[i][j];
			}
		}
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
