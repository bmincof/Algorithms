N = int(input())

fact = 1
while N > 1:
    fact *= N
    N -= 1

print(fact)