from math import factorial

def solve(n, a):
    res = 0
    l = len(a)
    for i in a:
        for j in range (1, i):
            if j in a[:a.index(i)]:
                continue
            else:
                res += factorial (n - a.index(i) - 1)
    return res

n = int (input())
a = list (map (int, input().split()))

print (solve(n, a))
        
