def do (length, a):
    res = 0
    for i in range (len(a)):
        res += a[i] // length
    return res
def solve(a, k):
    l = 0
    r = 10 ** 100
    length = 0
    while l <= r:
        m = (l + r) // 2
        if m == 0:
            l = m + 1
            continue
        curAmount = do(m, a);
        if (curAmount < k):
            r = m - 1
        elif (curAmount >= k):
            l = m + 1
            length = max(length, m)
    return length

n, k = map(int, input().split())
a = []
for i in range (n):
    a.append(int(input()))
print (solve(a, k))
