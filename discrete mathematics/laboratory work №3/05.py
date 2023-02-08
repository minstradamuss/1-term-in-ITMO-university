def tely(n, k):
    res = []
    if n == 1:
        for x in range (k):
            res.append(str(x))
    else:
        cur = tely(n - 1, k)
        for i in range(k):
            if i % 2 == 0:
                for j in cur:
                    add = str(i) + str(j)
                    res.append(add)
            else:
                for j in cur[::-1]:
                    add = str(i) + str(j)
                    res.append(add)
    return res

n, k = map(int, input().split()) 
print (*tely(n, k), sep = "\n")


