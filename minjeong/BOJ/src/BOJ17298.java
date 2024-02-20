import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && arr[i] > arr[stk.peek()]) {
                arr[stk.pop()] = arr[i];
            }
            stk.push(i);
        }

        while (!stk.isEmpty()) arr[stk.pop()] = -1;

        StringBuilder sb = new StringBuilder();
        for (int a : arr) sb.append(a).append(" ");
        bw.write(sb.toString());
        bw.close();
    }
}
