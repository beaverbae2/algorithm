package A2021_02_20;

import java.util.*;

/**
 * Set(LinkedHashSet)
 * @author beaverbae
 *
 */

public class PGS_캐시 {
	public int solution(int cacheSize, String[] cities) {
		LinkedHashSet<String> cache = new LinkedHashSet<>();
		int answer = 0;

		// 소문자 변환
		for (int i = 0; i < cities.length; i++) {
			cities[i] = cities[i].toLowerCase();
		}

		for (String city : cities) {
			if (cache.contains(city)) {
				cache.remove(city);
				cache.add(city);
				answer++;
			} else {
				if (!cache.isEmpty() && cache.size() == cacheSize) {
					Iterator<String> iter = cache.iterator();
					String first = iter.next();
					cache.remove(first);
					
					cache.add(city);
				} else if (cache.size() < cacheSize) {
					cache.add(city);
				}
				answer += 5;
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		new PGS_캐시().solution(3, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul",
				"NewYork", "LA" });
		new PGS_캐시().solution(3,
				new String[] { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" });
		new PGS_캐시().solution(2, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul",
				"Rome", "Paris", "Jeju", "NewYork", "Rome" });
		new PGS_캐시().solution(5, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul",
				"Rome", "Paris", "Jeju", "NewYork", "Rome" });
		new PGS_캐시().solution(2, new String[] { "Jeju", "Pangyo", "NewYork", "newyork" });
		new PGS_캐시().solution(0, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA" });
	}
}
