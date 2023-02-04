n = int (input())
money = [int(x) for x in input().split()]
s = int (input())

inf = 10 ** 10
d = [inf] * (s + 1)
d[0] = 0

cur = [0] * (s + 1)
res = []

for i in range (1, s + 1):
    for j in range (len(money)):
        if i - money[j] >= 0 and d[i - money[j]] < d[i]:
            d[i] = d[i - money[j]]
            cur[i] = money[j]
    d[i] += 1

if d[s] < inf:   
    while s > 0:
        res.append(cur[s])
        s -= cur[s]
else:
    print (-1)
    exit(0)
    
print (len(res))
print (*res, sep = ' ')

