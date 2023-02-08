def f(d):
    for i in range(n + 1):
        for j in range(i, -1, -1):
            if i < j:
                d[i][j] = 0
            elif i == j:
                d[i][j] = 1
            else:
                d[i][j] = d[i][j + 1] + d[abs(i - j)][j]
            
def solve(r, l, res, n, k):
    while l != n:
        for j in range(r, n + 1):
            if k >= d[n - (j + l)][j] and l + (j + 1) <= n:
                k -= d[n - (j + l)][j] 
            else:
                res.append(j)
                l, r = l + j, j
                break
    return res


n, k = map(int, input().split())
d = [[0 for i in range(n + 1)] for i in range(n + 1)]

f(d)

res = []
r, l = 1, 0
otv = ''
for i in solve(r, l, res, n, k):
    otv += str(i) + '+'
print (otv[:-1:])
