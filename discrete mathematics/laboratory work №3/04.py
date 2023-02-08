def chain_code(g, cur, n):
    flag = True
    while cur != '0' * n or flag:
        if flag:
            flag = False
        else:
            g[cur] = 0
        buf = cur[1::]
        if buf + '1' not in g.keys():
            cur = buf + '1'
        else:
            cur = buf + '0'
    return g

n = int(input())

cur = '0' * n
g = {cur: 0}
g = chain_code(g, cur, n)

for i in g.keys():
    print (i)
