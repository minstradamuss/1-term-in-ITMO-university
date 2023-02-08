def solve (n, k, const, d):
    res = ''
    index = 0
    if k == 1:
        res = '(' * n + ')' * n
    else:
        for i in range (const):
            if d[const - i - 1][index + 1] >= k:
                res += '('
                index += 1
            else:
                k -= d[const - i - 1][index + 1]
                res += ')'
                index -= 1
    return res
        
n, k = map (int, input().split())

k += 1
const = 2 * n
d = [[0 for i in range(n + 1)] for i in range(const + 1)]
d[0][0] = 1
for i in range (const):
    for j in range (n + 1):
        if j + 1 <= n:
            d[i + 1][j + 1] += d[i][j]
        if j > 0:
            d[i + 1][j - 1] += d[i][j]

print (solve (n, k, const, d))
