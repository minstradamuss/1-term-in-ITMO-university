def solve(a, b):
    n, m = len(a), len(b)
    if n > m:
        a, b = b, a
        n, m = m, n
    cur = range(n + 1)
    
    for i in range(1, m + 1):
        prev, cur = cur, [i] + [0] * n
        for j in range(1, n + 1):
            add, delete, change = prev[j] + 1, cur[j - 1] + 1, prev[j - 1]
            if a[j - 1] != b[i - 1]:
                change += 1
            cur[j] = min(add, delete, change)

    return cur[n]

a = input()
b = input()
print (solve(a, b))
