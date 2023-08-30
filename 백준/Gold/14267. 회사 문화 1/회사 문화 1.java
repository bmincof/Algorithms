import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author   : bmincof
// date     : 2023-08-30
public class Main {
    static List<Integer>[] members;
    static int[] scores;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 회사 직원 수, 최초 칭찬 횟수
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // [i]번 직원의 부하 직원들
        members = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            members[i] = new LinkedList<>();
        }
        // 최초 칭찬의 정도
        scores = new int[n + 1];

        // 부하 직원들 저장하기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss == -1) continue;

            members[boss].add(i);
        }

        // 최초로 칭찬받아서 얻은 점수들을 누적
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int member = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            scores[member] += score;
        }

        // DFS로 부하 직원들에게 칭찬 점수들을 누적
        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(scores[i]).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(int node, int score) {
        scores[node] += score;

        if (!members[node].isEmpty()) {
            for (int member : members[node]) {
                dfs(member, scores[node]);
            }
        }
    }
}
