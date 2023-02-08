def solve(razb, n, m, k):
    if k > 1:
        if m[k - 1] - 1 < m[k - 2] + 1:
            m[k - 2] += m[k - 1]
            k -= 1
        else:
            m[k - 1], m[k - 2] = m[k - 1] - 1, m[k - 2] + 1
            while m[k - 2] * 2 <= m[k - 1]:
                m.append(m[k - 1] - m[k - 2])
                m[k - 1] = m[k - 2]
                k += 1
        # 'n' '=' 'smth' '+' 'smth'
        print (n, end = '')
        print ('=', end = '')
        add = m[:k]
        print (*add, sep = '+')
    else:
        print ('No solution')

razb = input().split('=')
n = int(razb[0])
m = [int(x) for x in razb[1].split('+')]
k = len(m)
solve(razb, n, m, k)

