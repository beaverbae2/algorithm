# 2020_09_22 회고

- BOJ 16916 부분문자열
  
- 나는 진짜 KMP는 이해를 못하겠다. 나중에 누구한테 물어보거나 따로 시간내서 공부해야겠다
  
- 택배

  - 다익스트라의 기본원리를 제대로 이해했다면 풀 수 있는 문제였다

  - 다익스트라 알고리즘 : 하나의 정점으로 부터  다른 정점들로 가는 최소 비용을 구할 때 사용

  - 구현방법은 배열과 우선순위큐를 이용해서 구현 가능하다

  - 구현 이슈

    - 다익스트라를 제대로 몰랐음 -> 인터넷 참고해서 직접 구현했음

    - 이해가 되지 않은 부분(Array2)

      ```java
      private static void dijkstra(int start_v) {
      		dist[start_v][start_v] = 0;
      		visited[start_v][start_v] = true;
      		int count = 1;
      		
      		int v = start_v;
      		while(true) {
      			if(count==graph.length) break;
      			//인접한 정점들 확인
      			int min = INF;
      			int min_vertex = 0;
      			
      			for (int next_v = 1; next_v < graph.length; next_v++) {
      				if(visited[start_v][next_v]) continue;
      				
      				int next_dist = dist[start_v][v]+graph[v][next_v];
      				
      				if(dist[start_v][next_v]>next_dist) {
      					next_vertex[next_v][start_v] = v;//1
      					dist[start_v][next_v] = next_dist;
      				}
      				
      				if(min>dist[start_v][next_v]) {
      					min_vertex = next_v;
      					min = dist[start_v][next_v];
      				}
      			}
      			//next_vertex[min_vertex][start_v] = v;//2
      			visited[start_v][min_vertex] = true;
      			v = min_vertex;
      			count++;
      		}
      	}
      ```

      원래는 1이 아닌 2로 했더니, 완전히 다른 결과가 나왔다 

      2로하면 문제에서 요구하는 바로 출발노드의 바로 다음 노드가 아니고, 최단 경로로 이동하는 경로가 나온다. 

      1로 해야 똑바로 나온다. 

