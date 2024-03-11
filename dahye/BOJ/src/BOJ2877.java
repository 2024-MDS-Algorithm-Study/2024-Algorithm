import java.util.Scanner;

/*
4와 7
 */

public class BOJ2877 {
    static int K;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));

        K = sc.nextInt() + 1;
        String binary = Integer.toBinaryString(K);

        for(int i = 1; i < binary.length(); i++) {
            sb.append(binary.charAt(i) == '0' ? 4 : 7);
        }

        System.out.println(sb);
    }
}
