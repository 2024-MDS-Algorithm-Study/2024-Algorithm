import java.io.*;
import java.util.*;

public class BOJ4195 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Map<String, String> parents = new HashMap<>();
            Map<String, Integer> cnts = new HashMap<>();

            while (n-- > 0) {
                String[] inputs = br.readLine().split(" ");
                sb.append(union(inputs[0], inputs[1], parents, cnts)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static String find(String a, Map<String, String> parents) {
        parents.putIfAbsent(a, a);
        String pa = parents.get(a);
        if (pa.equals(a)) return a;
        String tmp = find(pa, parents);
        parents.put(a, tmp);
        return tmp;
    }

    private static int union(String a, String b, Map<String, String> parents, Map<String, Integer> cnts) {
        String pa = find(a, parents);
        String pb = find(b, parents);
        if (!pa.equals(pb)) {
            parents.put(pa, pb);
            cnts.putIfAbsent(pa, 1);
            cnts.putIfAbsent(pb, 1);
            cnts.put(pb, cnts.get(pa) + cnts.get(pb));
        }
        return cnts.get(pb);
    }
}