import java.io.*;
import java.util.*;

public class BOJ1891 {
    static long x, y, targetX = -1, targetY = -1;
    static int d;
    static String num;
    static boolean flag;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        num = st.nextToken();
        st = new StringTokenizer(br.readLine());
        x = Long.parseLong(st.nextToken());
        y = Long.parseLong(st.nextToken());

        long LEN = 1L << d;

        find(0, 0, LEN, 0);

        targetX += x;
        targetY -= y;
        if (targetX < 0 || targetX >= LEN || targetY < 0 || targetY >= LEN) {
            System.out.println(-1);
            return;
        }

        flag = false;
        solve(0, 0, LEN);
        System.out.println(sb);
    }

    private static void find(long x, long y, long len, int pos) {
        if (flag) return;
        if (len == 1) {
            targetX = x;
            targetY = y;
            flag = true;
            return;
        }

        long half = len / 2;
        if (num.charAt(pos) == '1') find(x + half, y, half, pos + 1);
        else if (num.charAt(pos) == '2') find(x, y, half, pos + 1);
        else if (num.charAt(pos) == '3') find(x, y + half, half, pos + 1);
        else find(x + half, y + half, half, pos + 1);
    }

    private static void solve(long x, long y, long len) {
        if (flag) return;
        if (len == 1) {
            flag = true;
            return;
        }

        long half = len / 2;
        if (x + half <= targetX && y + half > targetY) {
            sb.append(1);
            solve(x + half, y, half);
        } else if (x + half > targetX && y + half > targetY) {
            sb.append(2);
            solve(x, y, half);
        } else if (x + half > targetX && y + half <= targetY) {
            sb.append(3);
            solve(x, y + half, half);
        } else {
            sb.append(4);
            solve(x + half, y + half, half);
        }
    }
}
