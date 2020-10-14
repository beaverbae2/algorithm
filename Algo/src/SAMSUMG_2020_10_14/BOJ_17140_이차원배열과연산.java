package SAMSUMG_2020_10_14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산 {
	static int[][] map = new int[3][3];
	static int R,C,K,answer;
	
	static class Pair{
		int num, cnt;

		public Pair(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Pair [num=" + num + ", cnt=" + cnt + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken())-1;
		C = Integer.parseInt(st.nextToken())-1;
		K = Integer.parseInt(st.nextToken());
	
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		//연산 시작
		while(true) {
			if(answer>100) {
				answer = -1;
				break;
			}
			if(R>=0&&R<map.length&&C>=0&&C<map[0].length) {
				if(map[R][C]==K) break;//요소 찾음
			}
			
			
			int R_len = map.length;
			int C_len = map[0].length;
			
			
			ArrayList<Pair>[] pairs;
			if(R_len>=C_len) {//R연산
				int max_C= 0;
				pairs = new ArrayList[R_len];
				
				
				for (int i = 0; i < pairs.length; i++) {
					pairs[i] = new ArrayList<>();
				}
				for (int r = 0; r < map.length; r++) {
					int[] count = new int[101];
					for (int c = 0; c < map[0].length; c++) {
						if(map[r][c]==0) continue;
						int num = map[r][c];
						count[num]++;
					}
					for (int c = 1; c < count.length; c++) {
						if(count[c]==0) continue;
						pairs[r].add(new Pair(c, count[c]));
					}
					Collections.sort(pairs[r], new Comparator<Pair>() {

						@Override
						public int compare(Pair p1, Pair p2) {
							int c1 = p1.cnt;
							int c2 = p2.cnt;
							if(c1!=c2) return Integer.compare(c1, c2);
							else {
								int n1 = p1.num;
								int n2 = p2.num;
								return Integer.compare(n1, n2);
							}
						}
					});
					if(pairs[r].size()>100) {
						ArrayList<Pair> new_pair = new ArrayList<>();
						for (int i = 0; i < 100; i++) {
							new_pair.add(pairs[r].get(i));
						}
						pairs[r] = new_pair;
					}
					max_C = Math.max(pairs[r].size(), max_C);
				}
				
				map = new int[R_len][2*max_C];
				for (int i = 0; i < pairs.length; i++) {
					for (int j = 0; j < pairs[i].size(); j++) {
						map[i][2*j] = pairs[i].get(j).num;
						map[i][2*j+1] = pairs[i].get(j).cnt;
					}
				}
				
//				for (int i = 0; i < map.length; i++) {
//					System.out.println(Arrays.toString(map[i]));
//				}
			}else {//C연산
				int max_R= 0;
				pairs = new ArrayList[C_len];
				
				
				for (int i = 0; i < pairs.length; i++) {
					pairs[i] = new ArrayList<>();
				}
				for (int c = 0; c < map[0].length; c++) {
					int[] count = new int[101];
					for (int r = 0; r < map.length; r++) {
						if(map[r][c]==0) continue;
						int num = map[r][c];
						count[num]++;
					}
					for (int r = 1; r < count.length; r++) {
						if(count[r]==0) continue;
						pairs[c].add(new Pair(r, count[r]));
					}
					Collections.sort(pairs[c], new Comparator<Pair>() {

						@Override
						public int compare(Pair p1, Pair p2) {
							int c1 = p1.cnt;
							int c2 = p2.cnt;
							if(c1!=c2) return Integer.compare(c1, c2);
							else {
								int n1 = p1.num;
								int n2 = p2.num;
								return Integer.compare(n1, n2);
							}
						}
					});
					if(pairs[c].size()>100) {
						ArrayList<Pair> new_pair = new ArrayList<>();
						for (int i = 0; i < 100; i++) {
							new_pair.add(pairs[c].get(i));
						}
						pairs[c] = new_pair;
					}
					max_R = Math.max(pairs[c].size(), max_R);
				}
//				for (int i = 0; i < pairs.length; i++) {
//					System.out.println(pairs[i]);
//				}
//				System.out.println(max_R);
				
				map = new int[2*max_R][C_len];
				for (int i = 0; i < pairs.length; i++) {
					for (int j = 0; j < pairs[i].size(); j++) {
						map[2*j][i] = pairs[i].get(j).num;
						map[2*j+1][i] = pairs[i].get(j).cnt;
					}
				}
				
//				for (int i = 0; i < map.length; i++) {
//					System.out.println(Arrays.toString(map[i]));
//				}
			}
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			
			
			answer++;
		}
		System.out.println(answer);
		
	}
}
