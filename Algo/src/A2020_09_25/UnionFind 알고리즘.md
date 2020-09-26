# UnionFind 알고리즘

- 참고 : [나동빈 블로그](https://m.blog.naver.com/ndb796/221230967614)

- 정의 
  -  합집합 찾기 , 서로소 집합
  - 여러 개의 노드가 존재할 때, 두 개의 노드를 선택하여, 이 두 노드가 서로 같은 그래프에 있는지 확인



- 과정

  - 초기화 : 각 노드가 자기 자신을 가르킴

    | 1    | 2    | 3    | 4    |
    | ---- | ---- | ---- | ---- |
    | 1    | 2    | 3    | 4    |

  - Union :  두 노드의 부모 노드를 더 **작은 값을 가지는 부모 노드**(<u>일반적으로</u>)로 합침

  - Find : 두 노드의 부모 노드를 확인 하여 같은 집합에 존재하는지 확인



- 코드

  - getParent(int v) : 노드 v의 부모 노드 찾기

    ```java
    private static int getParent(int[] parent,int v) {
    	if(parent[v]==v) return v; //부모 노드
    	return parent[v] = getParent(parent[v]);
    }
    ```

  - unionParent(int a, int b) : 두 노드의 부모 노드를 구하여 더 작은 값을 가지는 부모노드로 합침

    ```java
    private static void unionParent(int[] parent, int a, int b) {
    	a = getParent(a);
    	b = getParent(b);
    		
    	if(a<b) parent[b] = a;
    	else parent[a] = b;
    }
    ```

  - findParent(int a, int b) : 두 노드의 부모 노드가 동일한가 확인

    ```java
    private static boolean findParent(int a, int b) {
    	a = getParent(a);
    	b = getParent(b);
    	if(a==b) return true;
    	else return false;
    }
    ```

- 이슈
  - 과정을 잘못 알고 있었다. union의 과정을 마치고 나면 parent배열 요소에 <u>최상단의 부모 노드</u>가 추가되는지 알았다
  - getParent과정을 이해하는게 중요하다. 노드와 부모 노드가 같을 때 까지 재귀를 돈다. 