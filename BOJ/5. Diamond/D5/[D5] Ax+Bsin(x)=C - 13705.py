from decimal import *

# Decimal의 precision을 높이기
getcontext().prec = 64
PI = Decimal('3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679')

# 정확한 근으로 인정할 오차 범위
UPPER_BOUND = Decimal('1e-26')
LOWER_BOUND = Decimal('-1e-26')

# 직접 구현한 사인함수, 테일러 전개로 근사치를 구하기
def sin(x):
    nx = x % (2*PI)
    result = nx

    isMinus = False
    factorial = 1

    for i in range(3, 100, 2):
        factorial = factorial * i * (i-1)
        isMinus = not isMinus
        result += (-1 if isMinus else 1) * Decimal(nx**i) / factorial

    return result

# 근을 구해야하는 함수
def f(A, B, C, x):
    return A*x + B*sin(x) - C

A, B, C = map(Decimal, input().split())

lower = Decimal('-100000')
upper = Decimal('100000')

# 근이 유일하므로 이분탐색을 통해 해의 위치를 판단
while(lower < upper):
    mid = (lower + upper) / 2
    y = f(A, B, C, mid)
    if(LOWER_BOUND < y and y < UPPER_BOUND):
        print(f"{mid:.6f}")
        break
    elif(y < 0):
        lower = mid
    elif(y > 0):
        upper = mid
