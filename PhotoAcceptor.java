package beginner;

import java.util.Scanner;

public class PhotoAcceptor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int l=sc.nextInt();
		int n=sc.nextInt();
		for(int k=0;k<n;k++) {
			int w=sc.nextInt();
			int h=sc.nextInt();
			if(w==h) {
				System.out.println("ACCEPTED");
				
			}
			else if(w==l) {
				if(h==l) {
					System.out.println("ACCEPTED");
				}
				else if(h<l) {
					System.out.println("UPLOAD ANOTHER");
				}
				else
					System.out.println("CROP IT");
			}
			else if(w>l) {
				if(h>=l)
					System.out.println("CROP IT");
				else
					System.out.println("UPLOAD ANOTHER");
			}
			else {
				System.out.println("UPLOAD ANOTHER");
				
			}
			
		}
		
		
		sc.close();

	}

}
