n = int (input())
a = list (map (int, input().split()))

m = int (input())
b = list (map (int, input().split()))

d = []
for i in range(n + 1):
     d.append([0] * (m + 1))

for i in range (1, n + 1):
    for j in range (1, m + 1):
        if b[j - 1] == a[i - 1]:
            d[i][j] = d[i - 1][j - 1] + 1
        else:
            d[i][j] = max (d[i - 1][j], d[i][j - 1])
res = []
i, j = n, m
while j != 0 and i != 0:
    if d[i][j] == d[i - 1][j]:
        i -= 1
    elif d[i][j] == d[i][j - 1]:
        j -= 1
    else:
        res.append(a[i - 1])
        i -= 1
        j -= 1
print (len(res))
print (*res[::-1])
