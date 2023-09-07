import java.util.Stack;

// author   : bmincof
// date     : 2023-09-07
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        // {temperature, index}
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            // pop smaller element before insert
            while (!stack.isEmpty() && stack.peek()[0] < temperatures[i]) {
                int[] popped = stack.pop();
                // write diff. between two day
                answer[popped[1]] = i - popped[1];
            }

            stack.push(new int[]{temperatures[i], i});
        }

        return answer;
    }
}