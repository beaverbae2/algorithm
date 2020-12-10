# 2020.12.04

### 어려웠던 문제

##### [백준 1543 문서 검색](https://www.acmicpc.net/problem/1543)

- 어떻게 접근해야 될지 감을 못잡았다

- 초기 문제 접근시 그리디는 위헙하다. 무식한 방법(brute)으로 접근해야한다



##### [백준 1987 알파벳](https://www.acmicpc.net/problem/1987)

- dfs가 바로 떠오르지 않았던 문제

- 이미 방문했던 알파벳을 다시 재방문 했을 때 return을 하면 안됐다

  - 코드

    ```java
    private static void dfs(int r, int c, int move_cnt) {
    		for (int d = 0; d < dirs.length; d++) {
    			int nr = r+dirs[d][0];
    			int nc = c+dirs[d][1];
    		
    			if(isInMap(nr,nc)) {
    				int ch = map[nr][nc];
    				int index = ch-'A';
    				if(visited[index]) {
    					answer = Math.max(answer, move_cnt);
    					//return; NOT!!!
    				}else {
    					visited[index] = true;
    					dfs(nr, nc, move_cnt+1);
    					visited[index] = false;
    				}
    			}
    		}
    	}
    
    ```

  - return은 **메소드 자체**를 return 시키는 것임

