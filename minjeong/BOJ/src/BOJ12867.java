import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m, idxs[];
        br.readLine();
        m = Integer.parseInt(br.readLine());
        idxs = new int[m];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            idxs[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        String dir = br.readLine();
        HashSet<String> set = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int res = 1;
        for (int i = 0; i < m; i++) {
            int move = (dir.charAt(i) == '+' ? 1 : -1);
            map.compute(idxs[i], (k, v) -> v == null ? move : v + move);
            if (map.get(idxs[i]) == 0) map.remove(idxs[i]);

            sb.delete(0, sb.length());
            for (int idx : map.keySet()) sb.append(idx).append(':').append(map.get(idx)).append('.');
            if (map.isEmpty() || set.contains(sb.toString())) {
                res = 0;
                break;
            }
            set.add(sb.toString());
        }

        System.out.println(res);
    }
}
