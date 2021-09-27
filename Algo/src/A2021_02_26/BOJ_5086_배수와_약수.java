package A2021_02_26;

import java.util.*;
import java.io.*;

public class BOJ_5086_배수와_약수 {
	static final String FACTOR = "factor";
	static final String MULTIPLE = "multiple";
	static final String NEITHER = "neither";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			if (a == 0 && b == 0) break;
			
			if (a < b) { 
				if (b % a == 0) {
					sb.append(FACTOR).append("\n");
				} else {
					sb.append(NEITHER).append("\n");
				}
			} else {
				if (a % b == 0) {
					sb.append(MULTIPLE).append("\n");
				} else {
					sb.append(NEITHER).append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
