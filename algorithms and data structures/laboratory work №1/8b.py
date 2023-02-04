n, m = map(int, input().split())
weights = list(map(int, input().split()))
prices = list(map(int, input().split()))

d = []
d.append([-1] * (m + 1))
d[0][0] = 0

for i in range(n):
    d.append([0] * (m + 1))
    for j in range(m + 1):
        d[-1][j] = d[-2][j]
    for j in range(m - weights[i], -1, -1):
        b = d[-1][j] != -1
        if b and d[-1][j + weights[i]] < d[-1][j] + prices[i]:
            d[-1][j + weights[i]] = d[-1][j] + prices[i]

c = max(d[-1])
pos = 0
for i in range(len(d[-1])):
    if d[-1][i] == c:
        pos = i
        
i = n
j = pos
res = []
while i > 0 and j > 0:
    temp = j - weights[i - 1]
    if d[i - 1][j] != d[i][j]:
        res.append(i)
        i -= 1
        j = temp
        c = d[i][j]
    else:
        i -= 1
        
print (len(res))
res = res[::-1]
for i in res:
    print (i, end = " ")
