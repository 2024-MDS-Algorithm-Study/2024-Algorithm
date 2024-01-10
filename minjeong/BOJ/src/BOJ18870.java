import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] inputs = new int[n], arr;
        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        arr = inputs.clone();
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int x : arr) {
            if (!map.containsKey(x)) map.put(x, idx++);
        }

        for (int x : inputs) {
            sb.append(map.get(x)).append(" ");
        }

        System.out.println(sb);
    }
}
