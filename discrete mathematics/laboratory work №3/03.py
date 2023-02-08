def anti_gray_code(n):
    g = []
    const = 3 ** (n - 1)
    for i in range (const):
        buf = 0
        for j in range (3):
            cur = i
            s = ""
            for k in range (n):
                s = str((cur % 3 + buf) % 3) + s
                cur = cur // 3
            buf += 1
            g.append(s)
    return g

res = []
n = int(input())
g = anti_gray_code (n)

if n >= 1:
    for i in range (len(g)):
        res.append(g[i])

for i in res:
    print (i)
