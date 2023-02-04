n, m = map(int, input().split())
a = map(int, input().split())
d = [0] * (10 ** 5)
d[0] = 1

for i in a:
    for j in range(m, -1, -1):
        if d[j] == 1:
            d[j + i] = 1
            
for i in range(m, -1, -1):
    if d[i] == 1 and i <= m:
        print(i)
        break
