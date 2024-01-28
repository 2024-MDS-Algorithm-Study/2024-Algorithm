import java.util.Scanner;

/*
수들의 합
 */

public class BOJ1789 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        long N = sc.nextLong();
        long S = 0;
        while(S * (S + 1) < 2 * N) {
            S++;
        }

        if(S * (S + 1) != 2 * N) --S;
        System.out.println(S);
    }
}
