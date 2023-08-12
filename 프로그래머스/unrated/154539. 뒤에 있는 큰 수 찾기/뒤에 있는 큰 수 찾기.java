import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        // 인덱스, 값
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            // 스택에 자신보다 작은 수가 들어있다면 모두 빼내면서 해당 수의 뒷 큰수를 number로
            while (!stack.isEmpty() && stack.peek()[1] < number) {
                int[] low = stack.pop();
                
                answer[low[0]] = number;
            }
            
            stack.push(new int[]{i, number});
        }
        
        while (!stack.isEmpty()) {
            answer[stack.pop()[0]] = -1;
        }
        
        return answer;
    }
}