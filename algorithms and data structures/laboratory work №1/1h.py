n, m, k = map(int, input().split())
seq = []
for _ in range(n):
    seq.append(input())
for i in range(k):
    seq.sort(key=lambda elem: elem[m - 1 - i])
for s in seq:
    print(s)
