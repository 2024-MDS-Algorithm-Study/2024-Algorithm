import java.io.*;

public class BOJ2877 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int gap = 2;
        StringBuilder sb = new StringBuilder();
        while (k > 0) {
            int tmp = k % gap;
            if (tmp <= gap / 2 && tmp != 0) sb.insert(0, 4);
            else sb.insert(0, 7);
            k -= gap;
            gap *= 2;
        }
        System.out.println(sb);
    }
}
