import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()), n;
        String input, arr;
        ArrayDeque<Integer> deque;
        StringBuilder sb = new StringBuilder();

        l:
        while (t-- > 0) {
            input = br.readLine();
            n = Integer.parseInt(br.readLine());
            arr = br.readLine().replace('[', ' ').replace(']', ' ').trim();
            deque = new ArrayDeque<>();
            if (n > 0) {
                for (String x : arr.split(",")) {
                    deque.offer(Integer.parseInt(x));
                }
            }

            boolean flag = true;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == 'R') flag = !flag;
                else if (c == 'D') {
                    if (deque.isEmpty()) {
                        sb.append("error\n");
                        continue l;
                    }
                    if (flag) deque.pollFirst();
                    else deque.pollLast();
                }
            }

            sb.append("[");
            while (!deque.isEmpty()) {
                if (flag) sb.append(deque.pollFirst());
                else sb.append(deque.pollLast());
                if (!deque.isEmpty()) sb.append(",");
            }
            sb.append("]\n");
        }
        System.out.println(sb);
    }
}
