def solve(n, k, a): 
    i = k - 1
    res = ""
    while i >= 0 and a[i] == n + 1 - k + i:
        i -= 1
      
    if i == -1:
        res = str(-1)
         
    else:
        for j in a[:i:]:
            res += str(j) + " "
        cur = a[i] + 1
        res += str(cur) + " "
        for j in range(cur + 1, cur + k - i):
            res += str(j) + " "
 
    return res
 
 
n, k = map(int, input().split())
a = [int(i) for i in input().split()]
 
print (solve(n, k, a), end = " ")
