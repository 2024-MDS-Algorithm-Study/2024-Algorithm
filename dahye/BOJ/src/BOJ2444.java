import java.util.*;

/*
 * 별 찍기 -7 
 */

public class BOJ2444 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int space = N - 1;
		for(int i = 1; i < 2 * N; i++) {
			int n = Math.abs(space);
			int star = (N - n - 1) * 2 + 1;
			for(int j = 0; j < n; j++) System.out.print(" ");
			for(int j = 0; j < star; j++) System.out.print("*");
			System.out.println();
			space--;
		}
	}
}
