package A2021_10_21;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 40MIN
 * @author beaverbae
 *
 */

public class BOJ_20055_컨베이어_벨트_위의_로봇 {
	static int N, K, end;
	static int ans;
	static boolean[] isRobot;
	static int[] durability;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		end = N-1;
		N += N;
		isRobot = new boolean[N];
		durability = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = 1;
		while (true) {
			moveBelt();
			moveRobots();
			putRobot();
			
			if (zeroDurabilityCnt() >= K) break;
			ans++;
		}
		
		System.out.println(ans);
	}

	private static void moveBelt() {
		// 벨트가 한 칸씩 이동
		// 내구도와 로봇이 함께 이동
		boolean[] nextIsRobot = new boolean[N];
		int[] nextDurability = new int[N];
		
		for (int i = 1; i < N; i++) {
			nextIsRobot[i] = isRobot[i-1];
			nextDurability[i] = durability[i-1];
		}
		
		nextIsRobot[0] = isRobot[N-1];
		nextDurability[0] = durability[N-1];
	
		isRobot = nextIsRobot;
		durability = nextDurability;
		checkEndIsRobot();
	}


	private static void moveRobots() {
		// 로봇이 한 칸씩 이동
		// 가장 먼저 벨트에 올라간 로봇부터
		for (int i = N-2; i >= 0; i--) {
			if (isRobot[i]) {
				if (!isRobot[i+1] && durability[i+1] >= 1) {
					isRobot[i] = false;
					isRobot[i+1] = true;
					durability[i+1] -= 1;
				}
			}
		}
		
		checkEndIsRobot();
	}

	private static void putRobot() {
		// 첫칸에 로봇 올리기
		if (durability[0] >= 1) {
			isRobot[0] = true;
			durability[0]--;
		}
	}

	private static void checkEndIsRobot() {
		if (isRobot[end]) {
			isRobot[end] = false;
		}
	}

	private static int zeroDurabilityCnt() {
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			if (durability[i] == 0) result++;
		}
		
		return result;
	}
}
