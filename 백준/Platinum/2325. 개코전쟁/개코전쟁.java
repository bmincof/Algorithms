import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author   : bmincof
// date     : 2023-09-09
public class Main {
    static class Edge {
        int to;
        int num;
        int weight;

        Edge(int to, int num, int weight) {
            this.to = to;
            this.num = num;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int INF = 1_000_000_000;

        // 정점의 수, 도로의 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // i번 도시로 가는 최단거리
        int[] shortest = new int[N + 1];
        List<Edge>[] roads = new LinkedList[N + 1];

        for (int i = 1; i <= N; i++) {
            shortest[i] = INF;
            roads[i] = new LinkedList<>();
        }

        // 도로 입력받기
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            roads[u].add(new Edge(v, M, w));
            roads[v].add(new Edge(u, M, w));
        }

        // 모든 도로가 있을 때 최단경로를 찾고, 사용한 도로를 저장하기
        // [i]번 정점에 최단거리로 도착하기 위해 사용한 도로의 번호
        int[] usedRoad = new int[N + 1];
        // [i]번 정점에 최단거리로 도착하기 위해 방문한 이전 정점
        int[] prev = new int[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((r1, r2) -> r1.weight - r2.weight);

        // 다익스트라
        shortest[1] = 0;
        pq.offer(new Edge(1, -1, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.weight != shortest[curr.to]) {
                continue;
            }
            // 연결된 도로 모두 사용
            for (Edge road : roads[curr.to]) {
                if (shortest[road.to] <= shortest[curr.to] + road.weight) {
                    continue;
                }

                shortest[road.to] = shortest[curr.to] + road.weight;
                usedRoad[road.to] = road.num;
                prev[road.to] = curr.to;
                pq.offer(new Edge(road.to, road.num, shortest[road.to]));
            }
        }

        List<Integer> selectedRoad = new LinkedList<>();
        int current = N;
        while (current != 1) {
            selectedRoad.add(usedRoad[current]);
            current = prev[current];
        }

        // 최단거리에 사용된 도로를 사용하지 않고 다시 최단거리를 구해보기
        // 가장 긴 것이 정답
        int answer = 0;
        for (int num : selectedRoad) {
            Arrays.fill(shortest, INF);
            shortest[1] = 0;
            pq.offer(new Edge(1, -1, 0));

            while (!pq.isEmpty()) {
                Edge curr = pq.poll();

                if (curr.weight != shortest[curr.to]) {
                    continue;
                }
                // 연결된 도로 모두 사용
                for (Edge road : roads[curr.to]) {
                    // 끊어진 도로거나 더 오래걸리면 제외
                    if (road.num == num || shortest[road.to] <= shortest[curr.to] + road.weight) {
                        continue;
                    }

                    shortest[road.to] = shortest[curr.to] + road.weight;
                    pq.offer(new Edge(road.to, road.num, shortest[road.to]));
                }
            }

            answer = Math.max(answer, shortest[N]);
        }

        System.out.println(answer);
    }
}
