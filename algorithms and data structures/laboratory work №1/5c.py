n = int (input())
a = list (map (int, input().split()))
d = [1] * n
for i in range (n):
    for j in range (i):
        if a[i] > a[j]:
            d[i] = max (d[i], d[j] + 1)
maxi = max (d)
res = []
i = n - 1
while maxi > 0:
    if d[i] == maxi:
        res.append(a[i])
        maxi -= 1
    i -= 1
print (len(res))
print (*res[::-1])
