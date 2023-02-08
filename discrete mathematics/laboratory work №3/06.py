def solve(n, s):
    if n == len(s):
        cur.append(s)
        return
    else:
        solve(n, s + '0')
        solve(n, s + '1')
    
n = int(input())

cur = []
solve(n, s = '')
#print (cur)

res = []
for i in cur:
    if i.count('11') == 0 and len(i) == n:
        res.append(i)
        
print(len(res))
print (*[x for x in res], sep = '\n')
    

