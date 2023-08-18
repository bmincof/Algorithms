class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        boolean[] wall = new boolean[n];
        for (int num : section) {
            wall[num - 1] = true;
        }
        
        for (int i = 0; i < n; i++) {
            if (wall[i]) {
                answer++;
                for (int j = 0; j < m; j++) {
                    if (i + j == n) {
                        break;
                    }
                    wall[i + j] = false;
                }
                i += m - 1;            
            }
        }
        
        return answer;
    }
}