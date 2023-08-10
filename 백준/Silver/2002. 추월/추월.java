import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// author : bmincof
// date   : 2023-08-11
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 터널에 들어간 차 번호의 순서
        Queue<String> originalOrder = new LinkedList<>();
        // 터널에서 나와있는 차 번호
        Set<String> isOut = new HashSet<>();

        for (int i = 0; i < N; i++) {
            originalOrder.offer(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            String out = br.readLine();

            // 이미 나와있는 차가 큐에 들어가 있다면 꺼내기
            while (isOut.contains(originalOrder.peek())) {
                originalOrder.poll();
            }

            // 나와야 하는 순서의 차가 나오지 않았다면 (== out이 추월했다면)
            if (!out.equals(originalOrder.peek())) {
                count++;
                isOut.add(out);
            } else {
                originalOrder.poll();
            }
        }

        System.out.println(count);
    }
}