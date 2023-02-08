def f(d, n):
    d = [-1, -1]
    for i in range(n):
        flag = (m[i] == '1')
        d[flag] = i
    return d

def solve(d, m, n):
    res = []
    for i in range(1, -1, -1):
        if d[i] == -1:
            res.append('-')
        else:
            i_i = d[i]
            add = abs(1 - i)
            res.append(m[:i_i] + [str(add)] + [i] * (n - (i_i + 1)))      
    return res

vector = input()
m = [x for x in vector]
d = []
n = len(m)

d = f(d, n)

for i in solve(d, m, n):
    print (*i, sep = '')
