def solve (n, const, d, psl):
    res, pred_pod = 0, 0
    for i in range (const):
        if psl[i] == '(':
            pred_pod += 1
        if psl[i] == ')':
            res += d[const - i - 1][pred_pod + 1]
            pred_pod -= 1
    return res
        
psl = input()

n = len(psl) //  2
const = 2 * (len(psl) //  2)

d = [[0 for i in range(n + 2)] for i in range(const + 1)]
d[0][0] = 1
for i in range(const):
    for j in range(n + 2):
        if j + 1 <= n:
            d[i + 1][j + 1] += d[i][j]
        if j > 0:
            d[i + 1][j - 1] += d[i][j]

print (solve (n, const, d, psl))
