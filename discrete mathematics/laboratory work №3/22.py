def f(d):
    for i in range(1, n + 1):
        for j in range(n, 0, -1):
            if j > i:
                d[i][j] = 0
            elif i == j:
                d[i][j] = 1
            else:
                d[i][j] = d[i - j][j] + d[i][j + 1]

def solve(p, r, l):
    global res
    np = len(p)
    if r == n:
        if p == m:
            print (res)
        res += 1
    for i in range(l, n + 1):
        if r + i <= n:
            cur = d[n - (r + i)][i]
            if m[np] == i:
                solve(p + [i], r + i, i)
                 
            else:
                res += cur

razb = input().split('+')
m = [int(x) for x in razb]
n = sum(m)
 
d = [[0 for i in range(n + 1)] for i in range(n + 1)]
d[0][0] = 1
 
f(d)

res = 0
p = []
r, l = 0, 1
solve(p, r, l)
