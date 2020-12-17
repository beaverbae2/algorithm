package A2020_12_16;

import java.util.*;

public class PGS_다리를_지나는_트럭 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		int bridge_weight = 0;
		LinkedList<Integer> passedTruck = new LinkedList<>();
		Queue<Integer> waitTruck = new LinkedList<>();
		Queue<int[]> passingTruck = new LinkedList<>();
		
		for (int i = 0; i < truck_weights.length; i++) {
			waitTruck.offer(truck_weights[i]);
		}
		
		while (true) {
			if (truck_weights.length==passedTruck.size()) break;
			
			if(!passingTruck.isEmpty()) {
				int[] truck = passingTruck.peek();
				int truckWeight = truck[0];
				int time = truck[1];
				
				if(answer-time==bridge_length) {
					passingTruck.poll();
					bridge_weight -= truckWeight;
					passedTruck.add(truckWeight);
				}
			}
			
			if (!waitTruck.isEmpty()) {
				int firstTruckWeight = waitTruck.peek();
				
				if (bridge_weight + firstTruckWeight<=weight) {
					waitTruck.poll();
					bridge_weight += firstTruckWeight;
					passingTruck.add(new int[] {firstTruckWeight, answer});
				}
			}
			answer++;
		}
		
		return answer;
	}

	public static void main(String[] args) {
		PGS_다리를_지나는_트럭 a = new PGS_다리를_지나는_트럭();
		System.out.println(a.solution(2, 10, new int[] { 7, 4, 5, 6 }));
		a = new PGS_다리를_지나는_트럭();
		System.out.println(a.solution(100, 100, new int[] { 10 }));
		a = new PGS_다리를_지나는_트럭();
		System.out.println(a.solution(100, 100, new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 }));
	}
}
