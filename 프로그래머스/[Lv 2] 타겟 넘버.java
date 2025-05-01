class Solution {
    public int solution(int[] numbers, int target) {
        return count(numbers, 0, 0, target);
    }
    
    // 백트래킹으로 target을 만들 수 있는 경우 계산
    int count(int[] numbers, int k, int sum, int target) {
        // 모든 수를 사용했을 때 합이 target이면 개수 + 1
        if (k == numbers.length) {
            return sum == target ? 1 : 0;
        }
        
        // k번째 수를 더하고 뺀 분기로 들어가기
        return count(numbers, k + 1, sum + numbers[k], target)
            + count(numbers, k + 1, sum - numbers[k], target);
    }
}
