def solve (n, posl):
    r, l = 0, 0
    res = ''
    for i in range (n - 1, -1, -1):
        if posl[i] == '(':
            r += 1
            if l > r:
                break
        else:
            l += 1
    new = posl[:n - l - r]
    if new == '':
        res = '-'
    else:
        res = new + ')' + '(' * r + ')' * (l - 1)
    return res
        
posl = input()
n = len(posl)

print (solve (n, posl))
