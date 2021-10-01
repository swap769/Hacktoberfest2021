package beginner;


import java.util.Scanner;

public class ItsMagic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
        int[] arr = new int[N];
        long sum = 0;
        int min = 1000000000;
        int index = -1;
        for(int i = 0; i < N; i++) {
            arr[i] = s.nextInt();
            sum += arr[i];
        }
        for(int i = 0; i < N; i++) {
            if((sum - arr[i]) % 7 == 0 && arr[i] < min){
                min = arr[i];
                index = i;
            }
        }
        System.out.println(index);
        

        s.close();

	}

}
