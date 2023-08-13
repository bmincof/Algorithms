import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];
        
        // 가격, 산 날짜
        Deque<int[]> stack = new ArrayDeque<>();
        
        for (int i = 0; i < N; i++) {
            int price = prices[i];
            // 가격이 살때보다 떨어졌다면 빼내기
            while (!stack.isEmpty() && stack.peek()[0] > price) {
                int drop = stack.pop()[1];
                answer[drop] = i - drop;
            }
            stack.push(new int[]{price, i});
        }
        
        // 끝까지 남은 것들 다 빼내기
        while (!stack.isEmpty()) {
            int drop = stack.pop()[1];
            answer[drop] = N - drop - 1;
        }
        
        return answer;
    }
}