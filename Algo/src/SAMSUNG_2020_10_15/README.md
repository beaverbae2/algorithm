# 2020_10_15 회고

- BOJ 15684 사다리조작
  - 푸는데 너무 오래걸렸다
  - adj : 가로선의 정보를 담는 배열을 만들었다
  - adj의 값이 1이면 오른쪽 이동, -1 이면 왼쪽 이동 하게 나름 과학적으로 짰다
  - 하지만?!, 사다리를 타고 이동시에 가로선을 탄 후 아래로 한 칸 내려가야되는데(문제에도 언급되어있음) 가로선만 타니까 1, -1,1,-1 왔다 갔다 하면서 무한 루프에 빠졌다.
  - 또한 최종 목적지의 행 값이 H(놓을수 있는 가로선의 개수)가 아니었다. H행에도 가로선을 둘 수 있기에 H+1이 최종 목적지이다.
  - 재귀를 잘못돌았다. cnt==4 일때 return 하는 조건을 먼저달아야 했는데, i -> i 가 되는지 확인한 후 answer를 계산하는 작업을 먼저 했더니 answer가 4가 되는 대참사가 발생했다. 히든 케이스에 있는 경우라서 이게 가장 까다로웠다.
  - 이차원 배열에서 조합을 구현했어야 했는데, 처음에 시작좌표를 1로 두니까 코드가 까다로웠다. 마지막열 처리해 주는게 까다로웠다. 시작좌표 설정은 설계하면서 잘 따져봐야될 것 같다.
- BOJ 14891 톱니바퀴
  - 그냥 1,2,3,4번에 톱니비퀴 각각에 대해 노가다를 하려 헀다.
  - 노가다로 코드 짜고 있었는데, 웬걸 모든 톱니 바퀴에 적용할 수 있는 패턴을 찾았다.
  - for문 indexing의 개념을 명확히 이해하면 바로 나올수 있는 발상 이었다
  - n번 톱니에서, n-1번에서 1번 톱니바퀴 까지는 i+1번 톱니바퀴의 7번 톱니와 i번째 톱니바퀴의 3번 톱니의 값(극)을 비교하고,
  - n번 톱니에서, n+1번에서 마지막번 톱니바퀴 까지는 i-1번 톱니바퀴의 3번 톱니와 i번째 톱니바퀴의 6번 톱니의 값(극)을 비교하면 끝이었다
  - 1번과 톱니바퀴의 경우 앞의 반복문의 시작이 0이기 떄문에  진행되지 않고, 마지막번 톱니바퀴의 경우 뒤의 반복문의 시작이 마지막+1이기 떄문에 자동으로 진행되지 않는다.