from math import factorial

def solve(ii, k):
    if ii == n:
        return
    for i in range(1, n + 1):
        if d[i] == 0:
            f = factorial(n - ii - 1)
            if f <= k:
                k -= f
            else:
                d[i], a[ii] = 1, i
                solve(ii + 1, k)

n, k = map(int, input().split())
a, d = [0] * n, [0] * (n + 1)

solve(0, k)
print (*a)



