package A2021_03_16;

import java.util.*;
import java.io.*;

public class BOJ_3053_택시_기하학 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double r1 = 0, r2 = 0;
		double r = Double.parseDouble(br.readLine());
		r1 = r*r*Math.PI;
		r2 = r*r*2;
		
		System.out.println(r1);
		System.out.println(r2);
	}
}
