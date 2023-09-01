import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-09-01
public class Main {
    static class Road implements Comparable<Road> {
        int x;
        int y;
        int cost;
        int sign;

        Road(int x, int y, int cost, int sign) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.sign = sign;
        }

        @Override
        public int compareTo(Road o) {
            return this.sign == o.sign ? (this.cost - o.cost) : (this.sign - o.sign);
        }
    }

    static int[] connected;

    static int find(int u) {
        if (connected[u] == u) {
            return u;
        }

        return connected[u] = find(connected[u]);
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) {
            return false;
        }

        connected[u] = v;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 마을의 수, 도로의 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        connected = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            connected[i] = i;
        }
        List<Road> roads = new LinkedList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            // 도로로 연결된 마을번호
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 표지판의 번호, 통행료
            int z = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            roads.add(new Road(x, y, w, z));
        }

        Collections.sort(roads);

        StringBuilder answer = new StringBuilder();
        int connect = 0;
        long cost = 0;
        for (Road road : roads) {
            // 아직 이동한 적 없었다면 -> union : true
            if (union(road.x, road.y)) {
                answer.append(road.sign);
                cost += road.cost;
                // 연결된 도시의 개수를 +1
                // N - 1개의 도로를 이용했다면 모든 도시를 이동할 수 있으므로 끝내기
                if (++connect == N - 1) {
                    break;
                }
            }
        }

        if (connect == N - 1) {
            answer.append(" ").append(cost);
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}