import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        if (y < x) {
            return -1;
        }
        
        int[] dp = new int[y + 1];
        Arrays.fill(dp, 1_000_000);
        dp[x] = 0;
        
        for (int i = x; i <= y; i++) {
            if (dp[i] == 1_000_000) {
                continue;
            }
            
            if (i + n <= y) {
                dp[i + n] = Math.min(dp[i] + 1, dp[i + n]);
            }
            if (i * 2 <= y) {
                dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
            }
            if (i * 3 <= y) {
                dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
            }
        }
    
        return dp[y] == 1_000_000 ? -1 : dp[y];
    }
}