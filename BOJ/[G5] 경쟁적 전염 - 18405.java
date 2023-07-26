package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-07-26
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        // 맵 크기, 바이러스 종류 개수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 지도, 바이러스가 해당 칸에 퍼진 시간, 바이러스 초기 위치
        int[][] map = new int[N][N];
        int[][] time = new int[N][N];
        List<int[]> virus = new LinkedList<>();  // {x, y, 번호}
        // 지도 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 시간 초기화 해두기
                time[i][j] = 100_000;
                // 바이러스면 초기 위치, 번호 기억해두기
                if (map[i][j] != 0) {
                    virus.add(new int[] {i, j, map[i][j]});
                    time[i][j] = 0;
                }
            }
        }

        // 바이러스의 초기 위치를 큐에 넣기
        Queue<int[]> q = new LinkedList<>();
        // 바이러스 번호 오름차순으로 정렬
        Collections.sort(virus, (v1, v2) -> v1[2] - v2[2]);
        for (int[] v : virus) {
            q.offer(v);
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                // 지금 보는 위치가 맵 밖이거나 이미 바이러스가 있는 칸이라면 제외
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != 0) continue;

                map[nr][nc] = map[r][c];
                time[nr][nc] = time[r][c] + 1;
                q.offer(new int[] {nr, nc});
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        // 1-indexed -> 0-indexed
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(map[X][Y] != 0 && time[X][Y] <= S ? map[X][Y] : 0);
    }
}
