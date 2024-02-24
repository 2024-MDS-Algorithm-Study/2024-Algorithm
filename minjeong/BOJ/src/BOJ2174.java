import java.io.*;
import java.util.*;

public class BOJ2174 {
    static int a, b, n, m;
    static int[][] map, del = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Map<Integer, Node> robots = new HashMap<>();
    static Map<Character, Integer> dirs = Map.of('N', 1, 'E', 2, 'S', 3, 'W', 4);

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[b][a];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            robots.put(i + 1, new Node(x, y));
            map[y][x] = dirs.get(st.nextToken().charAt(0));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num, ro, nx, ny, dir;
            char comm;
            num = Integer.parseInt(st.nextToken());
            comm = st.nextToken().charAt(0);
            ro = Integer.parseInt(st.nextToken());
            nx = robots.get(num).x;
            ny = robots.get(num).y;
            dir = map[ny][nx] - 1;
            if (comm == 'L') dir = (((dir - ro) % 4) + 4) % 4;
            else if (comm == 'R') dir = (dir + ro) % 4;
            else {
                map[ny][nx] = 0;
                while (ro-- > 0) {
                    nx += del[dir][1];
                    ny += del[dir][0];
                    if (nx < 0 || nx >= a || ny < 0 || ny >= b) {
                        System.out.printf("Robot %d crashes into the wall", num);
                        return;
                    }
                    if (map[ny][nx] != 0) {
                        System.out.printf("Robot %d crashes into robot %d", num, findRobot(nx, ny));
                        return;
                    }
                }
                robots.put(num, new Node(nx, ny));
            }
            map[ny][nx] = dir + 1;
        }

        System.out.println("OK");
    }

    private static int findRobot(int nx, int ny) {
        for (int x : robots.keySet()) {
            if (robots.get(x).x == nx && robots.get(x).y == ny) return x;
        }
        return -1;
    }
}