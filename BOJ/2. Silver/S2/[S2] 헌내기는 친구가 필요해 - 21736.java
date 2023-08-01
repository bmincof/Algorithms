package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-07-28
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 8 방향 델타 배열
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        // 공간의 크기 (행, 열)
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 상어의 위치를 나타내는 맵 (값이 1이면 방문한 곳으로 생각)
        char[][] map = new char[N][M];
        // BFS를 진행하기 위한 큐 {행, 열, 시작점으로부터 거리}
        Queue<int[]> q = new LinkedList<>();

        // 지도 입력 받기
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = line[j];
                // 해당 위치가 상어라면 큐에 넣기
                if (map[i][j] == 'I') {
                    q.offer(new int[] {i, j});
                    // 방문 처리
                    map[i][j] = 'X';
                }
            }
        }

        // 만날 수 있는 사람 수
        int meet = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            // 현재 위치로부터 8 방향을 탐색하기
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                // 맵 밖이거나 방문할 수 없는 칸이면 제외
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 'X') {
                    continue;
                }

                // 만난 사람의 수 세기
                if (map[nr][nc] == 'P') {
                    meet++;
                }

                // 방문 처리하고 큐에 넣기
                map[nr][nc] = 'X';
                q.offer(new int[] {nr, nc});
            }
        }

        System.out.println(meet == 0 ? "TT" : meet);
    }
}
