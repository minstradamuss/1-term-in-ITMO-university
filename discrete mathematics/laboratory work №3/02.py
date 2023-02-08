def gray_code(n):
    
    def recurse(g, n):
        const = len(g)
        if n <= 0:
            return
        else:
            for i in range (const - 1, -1, -1):
                ch = '1' + g[i]
                g.append(ch)
            for i in range (const - 1, -1, -1):
                g[i] = '0' + g[i]
            recurse (g, n - 1)

    g = ['0', '1']
    recurse(g, n - 1)
    return g

res = []
n = int(input())
g = gray_code (n)

if n >= 1:
    for i in range (len(g)):
        res.append(g[i])

for i in res:
    print (i)

