def solve(a, n, res):
    if n:
        for i in range(1, n + 1):
            if i <= res:
                solve(a + [i], n - i, i)
    else:
        a.sort()
        print (*a, sep = "+")
 
 
n = int(input())
res = n

solve([], n, res)



