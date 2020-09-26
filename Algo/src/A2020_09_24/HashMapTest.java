package A2020_09_24;

import java.util.HashMap;

public class HashMapTest {
	
	public static void main(String[] args) {
		HashMap<String, Boolean> map = new HashMap<>();
		for (int i = 0; i <500000; i++) {
			map.put("123123456", true);
		}
	}
}
