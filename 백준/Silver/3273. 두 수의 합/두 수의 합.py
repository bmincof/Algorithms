from sys import stdin

# n은 안쓰므로 버림
stdin.readline()
nums = list(map(int, stdin.readline().split()))
target = int(stdin.readline())

# [i]번째 숫자가 하나라도 있는지
upper_bound = max(nums) + 1
is_exist = [False] * upper_bound

# target을 만들 수 있는 쌍의 개수
count = 0

for num in nums:
    check = target - num
    # target - num이 있다면 num과 합쳐서 target을 만들 수 있으므로
    # target - num이 있으면 count에 +1
    count += 1 if 0 < check < upper_bound and is_exist[check] else 0
    # num이 있다고 체크하기
    is_exist[num] = True

print(count)