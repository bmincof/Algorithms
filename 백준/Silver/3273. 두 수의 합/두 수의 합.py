n = int(input())
nums = list(map(int, input().split()))
target = int(input())

# [i]번째 숫자가 하나라도 있는지
is_exist = [False] * 2_000_000

# target을 만들 수 있는 쌍의 개수
count = 0

for num in nums:
    # target - num이 있다면 num과 합쳐서 target을 만들 수 있으므로
    # target - num이 있으면 count에 +1
    count += 1 if target - num > 0 and is_exist[target - num] else 0
    # num이 있다고 체크하기
    is_exist[num] = True

print(count)