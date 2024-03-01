import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1874 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            st.push(i);
            sb.append("+\n");
            while (idx < n && !st.isEmpty() && arr[idx] == st.peek()) {
                st.pop();
                idx++;
                sb.append("-\n");
            }
        }

        while (idx < n && !st.isEmpty() && arr[idx] == st.peek()) {
            st.pop();
            idx++;
            sb.append("-\n");
        }

        if (!st.isEmpty()) System.out.println("NO");
        else System.out.println(sb);
    }
}
