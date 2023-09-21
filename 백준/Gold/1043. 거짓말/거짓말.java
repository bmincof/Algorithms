import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-09-21
public class Main {
    static int[] knowTruth;

    static int find(int u) {
        if (knowTruth[u] == u) {
            return u;
        }

        return knowTruth[u] = find(knowTruth[u]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == 0) {
            knowTruth[v] = u;
        } else {
            knowTruth[u] = v;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사람의 수, 파티의 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // [i]번 인원이 진실을 아는지
        // 0번의 그룹에 포함되면 진실을 아는 것
        knowTruth = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            knowTruth[i] = i;
        }

        // 진실을 아는 사람들을 기록해두기
        st = new StringTokenizer(br.readLine());
        int alreadyKnow = Integer.parseInt(st.nextToken());
        while (alreadyKnow-- > 0) {
            knowTruth[Integer.parseInt(st.nextToken())] = 0;
        }

        int[][] party = new int[M][];
        // 같은 파티에 한 번이라도 참석하는지 체크
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] people = party[i] = new int[n];

            for (int j = 0; j < n; j++) {
                int person = Integer.parseInt(st.nextToken());
                if (j > 0) {
                    union(person, people[j - 1]);
                }
                people[j] = person;
            }
        }

        int count = 0;
        while (M-- > 0) {
            boolean cannotLie = false;
            for (int person : party[M]) {
                cannotLie |= find(person) == 0;
            }
            if (!cannotLie) {
                count++;
            }
        }

        System.out.println(count);
    }
}