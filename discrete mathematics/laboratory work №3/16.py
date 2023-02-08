from math import factorial

def solve(n, k, a):
    res = 0
    ind = 1
    for i in range (k):
        elem = a[i]
        for j in range (ind, elem):
            n_1 = factorial(n - j)
            n_2 = factorial(k - i - 1)
            n_3 = factorial(n - j - k + i + 1)
            res += n_1 / (n_2 * n_3)
        ind = elem + 1
    return res

n, k = map (int, input().split())
a = list (map (int, input().split()))

print (int(solve(n, k, a)))
        
