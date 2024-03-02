import java.io.*;
import java.util.*;

public class BOJ3986 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] inputs = new String[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = br.readLine();
        }

        int res = 0;
        for (String s: inputs) {
            Stack<Character> st = new Stack<>();
            for (char c : s.toCharArray()) {
                if (!st.isEmpty() && st.peek() == c) st.pop();
                else st.push(c);
            }
            if (st.isEmpty()) res++;
        }

        System.out.println(res);
    }
}
