package beginner;

import java.util.Scanner;

public class SevenSegment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
        int t=s.nextInt();
        int a[]={6,2,5,5,4,5,6,3,7,6};
        while(t-->0){
            int n=s.nextInt();
            int d=0;
            if(n==0){
                System.out.print(111);
                continue;
            }
            while(n!=0){
                int p=n%10;
                d+=a[p];
                n=(int)(n/10);
            }
            int c=(int)(d/2);
            if(d%2==0){
                
                while(c-->0){
                    System.out.print(1);
                }
            }else{
                c--;
                System.out.print(7);
                while(c-->0){
                    System.out.print(1);
                }

            }
            System.out.println();

        }
        s.close();


	}

}
