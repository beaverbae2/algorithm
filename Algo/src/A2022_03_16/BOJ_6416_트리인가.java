package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 문제가 이상한디..
 * @author beaverbae
 * - 트리인지 확인하는 문제 
 */

public class BOJ_6416_트리인가 {
	static Map<Integer, Integer> vertexCnts;
	static int edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = 1;
		StringBuilder sb = new StringBuilder();
		
		vertexCnts = new HashMap<>();
		edges = 0;
		
		while (true) {
			String[] arr = br.readLine().split(" +");
			if (arr.length == 1) continue;
			int i = 0;
			
			if (arr[0].equals("-1") && arr[1].equals("-1")) break;
			
			while (i < arr.length) {
				int a = Integer.parseInt(arr[i]);
				int b = Integer.parseInt(arr[i+1]);
				
				if (a == 0 && b == 0) {
					int rootCnt = 0;
					boolean isTree = true;
					sb.append("Case ").append(k++);
					for (int v : vertexCnts.keySet()) {
						if (vertexCnts.get(v) == 0) rootCnt++;
						else if (vertexCnts.get(v) > 1) {
							isTree = false;
							break;
						}
						
						if (rootCnt >= 2) {
							isTree = false;
							break;
						}
					}
					
					if (isTree && (vertexCnts.size() == 0 || vertexCnts.size() - 1 == edges)) sb.append(" is a tree.");
					else sb.append(" is not a tree.");
					sb.append("\n");
					
					vertexCnts = new HashMap<>();
					edges = 0;
					break;
				}
				
				vertexCnts.put(a, vertexCnts.getOrDefault(a, 0));
				vertexCnts.put(b, vertexCnts.getOrDefault(b, 0) + 1);
				edges++;
				
				i += 2;
			}
		}
		
		System.out.println(sb);
	}

}
