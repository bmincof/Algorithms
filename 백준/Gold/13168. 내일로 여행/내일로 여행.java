import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-08-26
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final double INF = 1_000_000_000;

        // 도시의 수, 내일로 티켓 가격
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        double R = Integer.parseInt(st.nextToken());

        // 도시 이름 -> 번호
        Map<String, Integer> cityToNum = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String city = st.nextToken();
            if (!cityToNum.containsKey(city)) {
                cityToNum.put(city, i);
            }
        }

        // 내일로 티켓없이 도시 간에 이동할 때 필요한 최소 비용
        double[][] minCosts = new double[N][N];
        // 내일로 티켓을 이용해서 도시 간에 이동할 때 필요한 최소 비용
        double[][] minCostsTicket = new double[N][N];

        // 여행할 도시의 수, 순서
        int M = Integer.parseInt(br.readLine());
        int[] plan = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = cityToNum.get(st.nextToken());
        }
        
        // 이동비용 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                minCosts[i][j] = INF;
                minCostsTicket[i][j] = INF;
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            // 교통 수단, 양 끝 도시, 가격
            String type = st.nextToken();
            int u = cityToNum.get(st.nextToken());
            int v = cityToNum.get(st.nextToken());
            double price = Integer.parseInt(st.nextToken());

            // 가장 싼 교통편 선택
            minCosts[u][v] = minCosts[v][u] = Math.min(minCosts[u][v], price);

            // 내일로 티켓이 있을 때 할인 적용해서 가격비교
            if (type.equals("Mugunghwa") || type.startsWith("ITX")) {
                price = 0;
            } else if (type.startsWith("S-") || type.startsWith("V-")) {
                price /= 2;
            }

            minCostsTicket[u][v] = minCostsTicket[v][u] = Math.min(minCostsTicket[u][v], price);
        }

        // 플로이드-워셜로 최소비용 계산하기
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == end) continue;

                    minCosts[start][end] = Math.min(minCosts[start][end],
                            minCosts[start][mid] + minCosts[mid][end]);
                    minCostsTicket[start][end] = Math.min(minCostsTicket[start][end],
                            minCostsTicket[start][mid] + minCostsTicket[mid][end]);
                }
            }
        }

        double cost = 0;
        double costWithTicket = 0;

        // 여행 경로를 따라서 최소비용 계산
        for (int i = 0; i < M - 1; i++) {
            cost += minCosts[plan[i]][plan[i + 1]];
            costWithTicket += minCostsTicket[plan[i]][plan[i + 1]];
        }

        System.out.println(cost <= (costWithTicket + R) ? "No" : "Yes");
    }
}