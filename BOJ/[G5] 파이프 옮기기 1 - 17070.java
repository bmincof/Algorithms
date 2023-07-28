package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-28
public class Main {
    static int N;
    static int[][] map;

    static final int HOR = 1;
    static final int VER = 2;
    static final int DIAG = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 집 상태 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(putPipe(0, 1, HOR));
    }

    // DFS로 파이프를 놓아서 (N, N) 까지 이동하기
    static int putPipe(int r, int c, int dir) {
        if (r == N-1 && c == N-1) {
            return 1;
        }

        // 현재 위치에서 (N, N)에 도달할 수 있는 경우의 수
        int count = 0;

        // 가로 방향 이동
        // 이전에 세로로 이동하지 않았고 가로가 막혀있지 않다면
        if (dir != VER && r < N && c+1 < N && map[r][c+1] == 0) {
            count += putPipe(r, c+1, HOR);
        }

        // 세로 방향 이동
        // 이전에 가로로 이동하지 않았고 세로가 막혀있지 않다면
        if (dir != HOR && r+1 < N && c < N && map[r+1][c] == 0) {
            count += putPipe(r+1, c, VER);
        }

        // 대각 방향 이동
        // 가로, 세로, 대각선이 막혀있지 않다면
        if (r+1 < N && c+1 < N && map[r][c+1] + map[r+1][c] + map[r+1][c+1] == 0) {
            count += putPipe(r+1, c+1, DIAG);
        }

        return count;
    }
}
