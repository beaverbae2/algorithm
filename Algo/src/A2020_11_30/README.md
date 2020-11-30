# 2020.11.30

- [백준 2458 키순서](https://www.acmicpc.net/problem/2458)
  - 방향이 다른 두 개의 단방형 그래프 생성(tallGraph, smallGraph)
  - 키순서를 명확히 알 수 있는 노드는 
    - 해당 노드를 시작점으로 tallGraph를 탐색했을 때 방문하는 노드의 개수
    - 해당 노드를 시작점으로 smallGraph를 탐색했을 때 방문하는 노드의 개수
    - 위 둘을 합한 값이 전체 노드의 개수와 동일하다



- [백준 17135 캐슬디펜스](https://www.acmicpc.net/problem/17135)

  - 푸는데 우여곡절이 많았다

  - 푸는데 오래 걸린 원인

    - map의 복사본(copy_map) 이 필요

    - 적군의 이동을 잘 못함 -> 인덱싱이 여전히 서툴다

    - 변수 초기화 시점

      ```java
      private static int game() {
      		int killed = 0;
      		copy_map = new int[N+1][M];
      		deepcopy();
          	//기존 초기화 위치
      		//isKilled = new boolean[N][M]; -> 적군은 다르지만 위치가 같은 경우 판별 불가
      		for (int i = 0; i < N; i++) {
      			killedList = new ArrayList<>();
      			isKilled = new boolean[N][M];//here!!
      			
      			//궁수의 공격
      			for (int j = 0; j < bower.length; j++) {
      				bfs(N,bower[j]);
      			}
      
      			for (int[] elem : killedList) {
      				int r = elem[0];
      				int c = elem[1];
      				if(!isKilled[r][c]) {
      					isKilled[r][c] = true;
      					copy_map[r][c] = 0;
      					killed++;
      				}
      			}
      			//적군 1칸식 이동
      			moveEnemies();
      		}
      		return killed;
      	}
      ```

      

    - (중요)bfs 시점

      ```java
      private static void bfs(int bower_r, int bower_c) {
      		Queue<int[]> q = new LinkedList<>();
      		boolean[][] visited = new boolean[N+1][M];
      		q.offer(new int[] {bower_r, bower_c, 0});
      		visited[bower_r][bower_c] = true;
      		
      		int enemy_dist = Integer.MAX_VALUE-1;
      		ArrayList<int[]> enemies = new ArrayList<>();
          
      		while(!q.isEmpty()) {
      			int[] elem = q.poll();
      			int r = elem[0];
      			int c = elem[1];
      			int dist = elem[2];
      			
                  /////	탈출 조건	/////
      			if(dist>D) break;
      			if(dist==enemy_dist+1) break;
      			///////////////////////
      			for (int d = 0; d < dirs.length; d++) {
      				int nr = r+dirs[d][0];
      				int nc = c+dirs[d][1];
      			
      				if(nr<0||nr>=N+1||nc<0||nc>=M||visited[nr][nc]) continue;
      				
      				if(copy_map[nr][nc]==1) {
                          //반드시 dist+1이어야 한다.(중요)
      					if(dist+1<=D&&enemy_dist==Integer.MAX_VALUE-1||dist+1 == enemy_dist) {
      						enemy_dist = dist+1;
      						enemies.add(new int[] {nr,nc});
      					}
      				}
      				q.offer(new int[] {nr,nc,dist+1});
      				visited[nr][nc] = true;
      			}
      		}
      }
      ```

      