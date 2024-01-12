import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
AC
 */

public class BOJ5430 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, n;
    static String str;
    static Deque<String> deque;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for(int test_case = 0; test_case < T; test_case++) {
            str = br.readLine();
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");

            deque = new LinkedList<>();

            for(int i = 0; i < n; i++) {
                deque.add(st.nextToken());
            }

            func();
        }
        System.out.print(sb);
    }

    private static void func() {
        boolean startFlag = true;

        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == 'R') {
                startFlag = !startFlag;
            }
            if(str.charAt(i) == 'D') {
                if(deque.isEmpty()) {
                    sb.append("error" + "\n");
                    return;
                }

                if(startFlag) deque.pollFirst();
                else deque.pollLast();
            }
        }

        sb.append("[");
        if(!deque.isEmpty()){
            if(startFlag) {
                sb.append(deque.pollFirst());

                while(!deque.isEmpty()) {
                    sb.append("," + deque.pollFirst());
                }

            } else {
                sb.append(deque.pollLast());

                while(!deque.isEmpty()) {
                    sb.append("," + deque.pollLast());
                }
            }
        }

        sb.append("]" + "\n");
    }
}
