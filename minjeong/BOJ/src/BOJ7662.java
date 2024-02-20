import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;
import java.util.TreeSet;

public class BOJ7662 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> dq;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            dq = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                String[] inputs = br.readLine().split(" ");
                int num = Integer.parseInt(inputs[1]);
                if (inputs[0].equals("I")) {
                    dq.putIfAbsent(num, 0);
                    dq.put(num, dq.get(num) + 1);
                }
                else {
                    if (dq.isEmpty()) continue;
                    if (num == 1) {
                        int lastKey = dq.lastKey();
                        if (dq.get(lastKey) == 1) dq.remove(lastKey);
                        else dq.put(lastKey, dq.get(lastKey) - 1);
                    }
                    else {
                        int firstKey = dq.firstKey();
                        if (dq.get(firstKey) == 1) dq.remove(firstKey);
                        else dq.put(firstKey, dq.get(firstKey) - 1);
                    }
                }
            }
            if (dq.isEmpty()) sb.append("EMPTY\n");
            else {
                int max = dq.lastKey();
                dq.remove(max);
                sb.append(max).append(" ");
                if (dq.isEmpty()) sb.append(max);
                else sb.append(dq.firstKey());
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }
}
