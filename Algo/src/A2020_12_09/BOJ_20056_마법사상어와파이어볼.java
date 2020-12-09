package A2020_12_09;

import java.io.*;
import java.util.*;

public class BOJ_20056_마법사상어와파이어볼 {
	static int[][] dirs = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static List<Ball>[][] map;
	static int N, M, K;
	
	static class Ball{
		int m,d,s;

		public Ball(int m, int d, int s) {
			super();
			this.m = m;
			this.d = d;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Ball [m=" + m + ", d=" + d + ", s=" + s + "]";
		}
	}
	
	//파이어볼 이동
	private static void move() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		for (int k = 0; k < K; k++) {//K번 반복
			//다음 map 상태 저장하는 배열 초기화
			List<Ball>[][] next_map = new List[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					next_map[i][j] = new ArrayList<>();
				}
			}
			
			//이동
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == null) continue;
					
					for (int l = 0; l < map[i][j].size(); l++) {
						Ball ball = map[i][j].get(l);
						int m = ball.m;
						int d = ball.d;
						int s = ball.s;
						int distance = s%N;
						
						int r = i+dirs[d][0]*distance;
						int c = j+dirs[d][1]*distance;
						
						if(r>=N) r -= N;
						else if(r<0) r+=N;
						
						if(c>=N) c -= N;
						else if(c<0) c+=N;
						
						next_map[r][c].add(new Ball(m, d, s));
						
						
						
//						int cnt = 1;
//						int inv_d = (d+4)%8;
//						
//						LinkedList<int[]> pointList = new LinkedList<>();//좌표 저장
//						
//						int r = i;
//						int c = j;
//						
//						pointList.add(new int[] {i,j});
//						
//						//정방향
//						while(true) {
//							r = r+dirs[d][0];
//							c = c+dirs[d][1];
//							if(!isInMap(r, c)) break;
//							cnt++;
//							pointList.add(new int[] {r, c});;
//						}
//						
//						//반대방향
//						r = i;
//						c = j;
//						int idx = pointList.size();
//						while(true) {
//							r = r+dirs[inv_d][0];
//							c = c+dirs[inv_d][1];
//							if(!isInMap(r, c)) break;
//							cnt++;
//							pointList.add(idx, new int[] {r,c});
//						}
//						
////						for (int n = 0; n < pointList1.size(); n++) {
////							pointList.add(pointList1.get(n));
////						}
////						
////						for (int n = pointList2.size()-1; n >= 0; n--) {
////							pointList.add(pointList2.get(n));
////						}
//						
//						int real_move = s%cnt;
//					
//						int next_r = pointList.get(real_move)[0];
//						int next_c = pointList.get(real_move)[1];
//					
//						next_map[next_r][next_c].add(new Ball(m, d, s));
					}
				}
			}
			//정리
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(next_map[i][j].size()<2) continue;
					
					int total_m = 0;
					int total_s = 0;
					int size = next_map[i][j].size();
					boolean[] visited = new boolean[2];
					int visited_cnt = 0;
					
					for (int l = 0; l < next_map[i][j].size(); l++) {
						Ball ball = next_map[i][j].get(l);
						total_m += ball.m;
						total_s += ball.s;
						if(!visited[ball.d%2]){
							visited_cnt++;
							visited[ball.d%2] = true;
						}
					}
					
					int next_m = total_m/5;
					int next_s = total_s/size;
					
					next_map[i][j] = new ArrayList<>();
					if(next_m>0) {
						if(visited_cnt==1) {
							for (int d = 0; d < dirs.length; d+=2) {
								next_map[i][j].add(new Ball(next_m, d, next_s));
							}
						}else if(visited_cnt==2) {
							for (int d = 1; d < dirs.length; d+=2) {
								next_map[i][j].add(new Ball(next_m, d, next_s));
							}
						}
					}
				}
			}
			map = next_map;
			//정리되고 난 후
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(next_map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			//정리되고 난 후
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					map[i][j] = new ArrayList<>();
//			
//				}
//			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					for (int l = 0; l < next_map[i][j].size(); l++) {
//						map[i][j].add(next_map[i][j].get(l));
//					}
//				}
//			}
		}
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<N&&nc>=0&&nc<N;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Ball(m, d, s));
		}
		
		move();
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					answer += map[i][j].get(k).m;
				}
			}
		}
		System.out.println(answer);
	}	
}
