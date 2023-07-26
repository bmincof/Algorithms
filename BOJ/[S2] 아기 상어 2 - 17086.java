package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-07-27
public class Main {
    /*
     * 아기 상어 2
     *
     * 모든 상어를 큐에 넣고 BFS를 시작
     * BFS를 진행하면서 상어의 시작점보다 얼마나 먼 곳인지 체크
     * 가장 큰 거리가 정답이 됨
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 8 방향 델타 배열
        int[] dr = {1, -1, 0, 0, 1, -1, -1, 1};
        int[] dc = {0, 0, 1, -1, 1, -1, 1, -1};

        // 공간의 크기 (행, 열)
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 상어의 위치를 나타내는 맵 (값이 1이면 방문한 곳으로 생각)
        int[][] map = new int[N][M];
        // BFS를 진행하기 위한 큐 {행, 열, 시작점으로부터 거리}
        Queue<int[]> q = new LinkedList<>();

        // 지도 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 해당 위치가 상어라면 큐에 넣기
                if (map[i][j] == 1) {
                    q.offer(new int[] {i, j, 0});
                }
            }
        }

        // 가장 큰 안전거리
        int farthest = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];

            // 가장 큰 안전거리로 교체
            if (dist > farthest) {
                farthest = dist;
            }

            // 현재 위치로부터 8 방향을 탐색하기
            for (int dir = 0; dir < 8; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                // 맵 밖이거나 방문한 적 있는 위치이면 건너뛰기
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != 0) {
                    continue;
                }

                // 방문 처리하고 큐에 넣기
                map[nr][nc] = 1;
                q.offer(new int[] {nr, nc, dist+1});
            }
        }

        System.out.println(farthest);
    }
}
