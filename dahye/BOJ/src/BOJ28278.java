import java.io.*;
import java.util.*;

/*
스택 2
 */

public class BOJ28278 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            if(num == 1) {
                stack.add(Integer.parseInt(st.nextToken()));
            } else if(num == 2) {
                if(stack.isEmpty()) sb.append(-1 + "\n");
                else sb.append(stack.pop() +  "\n");
            } else if(num == 3) {
                sb.append(stack.size() +  "\n");
            } else if(num == 4) {
                if(stack.isEmpty()) sb.append(1 +  "\n");
                else sb.append(0 +  "\n");
            } else if(num == 5) {
                if(stack.isEmpty()) sb.append(-1 +  "\n");
                else sb.append(stack.peek() +  "\n");
            }
        }

        System.out.print(sb);
    }
}
