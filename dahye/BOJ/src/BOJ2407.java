import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*
조합
 */

public class BOJ2407 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static BigInteger map[][];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new BigInteger[n + 1][n + 1];


        for(int r = 1; r < n + 1; r++) {
            for(int c = 0; c < r + 1; c++) {
                if(c == 0 || r == c) map[r][c] = BigInteger.ONE;
                else {
                    map[r][c] = map[r - 1][c - 1].add(map[r - 1][c]);
                }
            }
        }

        System.out.print(map[n][m]);

    }
}
