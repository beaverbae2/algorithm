# 2020.12.06

##### [백준 4179 불!](https://www.acmicpc.net/problem/4179)

- [백준 3055 탈출](https://www.acmicpc.net/problem/3055) 과 거의 똑같은 문제였다
- 마지막에 MAX가 아닌 MIN으로 조건 처리해서 대참사 났다. 불(F)가 번진 것을 map에 표시를 안해서 틀리기도 했다
- Queue에서 poll()이 아닌 peek()을 먼저 해야해야한다. 
- 또한 사람(J)가 이동한 위치는 map에 표시해 주는게 좋은 것 같다. 그래야 디버깅이 편하다



##### [백준 20057 마법사 상어와 토네이도](https://www.acmicpc.net/problem/20057)

- 그냥 대참사가 난 문제다
- dirs를 서남동북로 설정해 놓고, 모래 흩뿌릴때(moveSand()) 서남북동으로 처리했다
- 각 방향에 맞게 모래를 일정 비율 흩뿌릴 때(moveSand()) 좌표(sub_r, sub_c)도 잘못 표시 했었다
- 마지막으로 모래를 다 흩뿌렸으면(SpreadSand()) 기준위치의 모래는 0이 되야하는것을 처리 안했다