package beginner;

import java.util.Scanner;

public class AnagramMaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int t=s.nextInt();
		for(int j=0;j<t;j++) {
			String a1=s.next();
			String a2=s.next();
			int count1[] = new int[26];  
	        int count2[] = new int[26];
	        for (int i = 0; i < a1.length() ; i++)
	        	count1[a1.charAt(i) -'a']++;
	        for (int i = 0; i < a2.length() ; i++)
	        	count2[a2.charAt(i) -'a']++;
	        int x=0;
	        for (int i = 0; i < 26; i++) {
	        	x=Math.abs(count1[i] - count2[i]);
	        	
	        }
	        System.out.println(x);
		}
		s.close();

	}

}
