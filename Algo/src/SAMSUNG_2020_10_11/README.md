# 2020_10_11 회고

요약 : 20분 내에 키보드 손에 올리면 안된다...

### BOJ 17144 미세먼지 안녕!

- 인덱싱 관리에서 탈탈 털린 문제이다
- 인덱싱은 실수할 확률이 높으므로 손으로 설계를 하고, 검증 후 들어가자
- 시뮬은 method별로 나눠서 모듈화 해야함!!
- 또한 먼지가 확산할 때, 기존 먼지를 담고 있는 배열에 확산된 먼지를 담고 있는 배열의 값들을 더해야 한다.(동기화...)



### BOJ 19236 청소년 상어

- 재귀와 포인터로 인해서 털린 문제이다	

  ```java
  //핵심 변수들
  map = new Pair[4][4];
  fishes = new Fish[17];
  isDeadFishes = new boolean[17];
  
  private static void dfs(Pair[][] map, Fish[] fishes, int total, int cnt) {
  		answer = Math.max(total, answer);
  		
  		moveFish(map,fishes);
  		int r = fishes[0].r;
  		int c = fishes[0].c;
  		int dir = map[r][c].dir;
  		int id = map[r][c].id;
  		
  		for (int i = 1; i < 4; i++) {
  			int nr = r+dirs[dir][0]*i;
  			int nc = c+dirs[dir][1]*i;
  			int fish_id = 0;
  			int fish_dir = 0;
  			
  			if(isInMap(nr, nc)&&map[nr][nc] != null) {
  				//물고기 잡아 먹음
  				fish_id = map[nr][nc].id;
  				fish_dir = map[nr][nc].dir;
  				int next_total = total+map[nr][nc].id;
  				
                  ///next_map과 next_fishes를 만드시 선언 해주어야 한다.(메모리에 새로운 주소값 추가)
  				Pair[][] next_map = new Pair[4][4];
  				for (int j = 0; j < next_map.length; j++) {
  					for (int k = 0; k < next_map.length; k++) {
  						if(map[j][k] != null) {
  							next_map[j][k] = new Pair(map[j][k].id, map[j][k].dir);
  						}
  					}
  				}
  				Fish[] next_fishes = new Fish[17];
  				for (int j = 0; j < next_fishes.length; j++) {
  					next_fishes[j] = new Fish(fishes[j].r, fishes[j].c);
  				}
  				
  				next_fishes[0] = new Fish(nr, nc);
  				next_map[r][c] = null;
  				next_map[nr][nc] = new Pair(id, fish_dir);
  				isDeadFishes[fish_id] = true;
  
                  dfs(next_map,next_fishes,next_total,cnt+1);
  				isDeadFishes[fish_id] = false;
    				//문제의 구간이다.. 객체는 주소값이라서 원하는 대로 안된다..
  //				map[nr][nc] = new Pair(fish_id,fish_dir);
  //				map[r][c] = new Pair(id, dir);
  //				fishes[0] = new Fish(r, c);				
  			}
  			
  		}
  	}
  ```

  