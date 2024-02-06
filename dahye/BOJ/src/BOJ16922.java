import java.util.HashSet;
import java.util.Scanner;

/*
 * 로마 숫자 만들기
 */

public class BOJ16922 {
	static int num[] = {1, 5, 10, 50};
	static HashSet<Integer> set = new HashSet<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		func(0, 0, 0, N);
		
		System.out.println(set.size());
	}

	private static void func(int start, int k, int sum, int N) {
		if(k == N) {
			set.add(sum);
			return;
		}
		
		for(int i = start; i < 4; i++) {
			func(i, k + 1, sum + num[i], N);
		}
	}
}
