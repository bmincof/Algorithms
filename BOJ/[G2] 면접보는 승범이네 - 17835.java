package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-25
public class Main {
    // 도시와 해당 도시까지의 거리
    static class Road implements Comparable<Road> {
        int cityNum;
        long dist;

        Road(int cityNum, long dist) {
            this.cityNum = cityNum;
            this.dist = dist;
        }

        @Override
        public int compareTo(Road o) {
            return this.dist < o.dist ? -1 : 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final long INF = 1_000_000_000_000_000L;

        // 도시의 수, 도로의 수, 면접장의 수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] dist = new long[N+1];
        List<Road>[] roads = new LinkedList[N+1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
            roads[i] = new LinkedList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 출발지점, 도착지점, 거리
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 면접장으로 오는 거리를 계산하기 위해 역방향 그래프를 생성
            roads[v].add(new Road(u, c));
        }

        // 면접장(시작점)을 pq에 넣으면서 다익스트라 시작
        PriorityQueue<Road> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int cityNum = Integer.parseInt(st.nextToken());
            dist[cityNum] = 0;
            pq.offer(new Road(cityNum, 0));
        }

        // 다익스트라
        while (!pq.isEmpty()) {
            Road curr = pq.poll();

            if (dist[curr.cityNum] != curr.dist) {
                continue;
            }

            // 현재 도시에 붙어있는 도로들을 모두 탐색
            for (Road next : roads[curr.cityNum]) {
                if (dist[next.cityNum] <= curr.dist + next.dist) {
                    continue;
                }

                dist[next.cityNum] = curr.dist + next.dist;
                pq.offer(new Road(next.cityNum, dist[next.cityNum]));
            }
        }

        // 가장 먼 도시 찾기
        int farthestCity = 0;
        long farthest = 0;
        for (int i = 1; i <= N; i++) {
            if (farthest < dist[i]) {
                farthestCity = i;
                farthest = dist[i];
            }
        }

        System.out.println(farthestCity);
        System.out.println(farthest);
    }
}
