import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static class Node {
        Map<Character, Node> child = new HashMap<>();
        boolean isEnd;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        Node head;
        String[] inputs;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            head = new Node();
            inputs = new String[n];
            for (int i = 0; i < n; i++) {
                inputs[i] = br.readLine();
                Node tmp = head;
                for (int j = 0; j < inputs[i].length(); j++) {
                    char c = inputs[i].charAt(j);
                    tmp = tmp.child.computeIfAbsent(c, k -> new Node());
                    if (j == inputs[i].length() - 1) tmp.isEnd = true;
                }
            }

            String answer = "YES\n";
            L: for (int i = 0; i < n; i++) {
                Node tmp = head;
                for (int j = 0; j < inputs[i].length(); j++) {
                    tmp = tmp.child.get(inputs[i].charAt(j));
                    if (j != inputs[i].length() - 1 && tmp.isEnd) {
                        answer = "NO\n";
                        break L;
                    }
                }
            }
            sb.append(answer);
        }
        System.out.println(sb);
    }
}
