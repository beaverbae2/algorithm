package A2020_12_11;

import java.util.*;

public class PGS_n진수게임 {
	private List<Character> list;
	int n,t,m,p;
	
	public String solution(int n, int t, int m, int p) {
        String answer = "";
        this.list = new ArrayList<>();
        this.n = n;
        this.t = t;
        this.m = m;
        this.p = p;
        
        for (int num = 0; num < m*t; num++) {
			get(n,num);
		}
        
        int temp_t = 0;
        for (int i = p-1; i < list.size(); i+=m) {
			answer += list.get(i);
			temp_t++;
			if(temp_t == t) break;
		}
        
        return answer;
    }
	
	public void get(int n, int num) {
		List<Character> result = new ArrayList<>();
		while(true) {
			int mod = num%n;
			result.add(trans(mod));
			num = num/n;
		
			if(num==0) break;
		}
		
		for (int i = result.size()-1; i >= 0; i--) {
			list.add(result.get(i));
		}
	}
	
	public char trans(int n) {
		if(n==0) return '0';
		if(n==1) return '1';
		if(n==2) return '2';
		if(n==3) return '3';
		if(n==4) return '4';
		if(n==5) return '5';
		if(n==6) return '6';
		if(n==7) return '7';
		if(n==8) return '8';
		if(n==9) return '9';
		if(n==10) return 'A';
		if(n==11) return 'B';
		if(n==12) return 'C';
		if(n==13) return 'D';
		if(n==14) return 'E';
		if(n==15) return 'F';
		
		return 'G';//실제론 x
	}
	
	
	public static void main(String[] args) {
		PGS_n진수게임 a = new PGS_n진수게임();
		System.out.println(a.solution(2, 4, 2, 1));
		a = new PGS_n진수게임();
		System.out.println(a.solution(16, 16, 2, 1));
		a = new PGS_n진수게임();
		System.out.println(a.solution(16, 16, 2, 2));
	}
}
