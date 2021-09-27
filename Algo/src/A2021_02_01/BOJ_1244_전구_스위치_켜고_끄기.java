package A2021_02_01;

import java.util.Scanner;

public class BOJ_1244_전구_스위치_켜고_끄기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int light_num = sc.nextInt(); // 스위치의 개수
		boolean light[] = new boolean[light_num + 1]; // 스위치

		for (int i = 1; i <= light_num; i++)
			light[i] = (sc.nextInt() == 1) ? true : false;

		int stu_num = sc.nextInt(); // 학생 수

		for (int j = 0; j < stu_num; j++) {

			int gender = sc.nextInt();
			int num = sc.nextInt();

			if (gender == 1) { // 남학생일 경우
				int k = num;
				while (k <= light_num) {
					light[k] = !light[k];
					k += num;
				}
			}

			else { // 여학생일 경우
				// 본인은 무조건 바꿈
				light[num] = !light[num];

				int front = num - 1;
				int rear = num + 1;

				while (front >= 1 && rear <= light_num) {
					if (light[front] == light[rear]) {
						light[front] = !light[front];
						light[rear] = !light[rear];
						front--;
						rear++;
					}else break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();// 답안 출력용
		for (int i = 1; i <= light_num; i++) {
			int tmp = (light[i]) ? 1 : 0;
			//System.out.print(tmp + " ");
			sb.append(tmp).append(" ");
			
			if (i % 20 == 0)
				//System.out.println();
				sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}