n = int (input())
a = list (map(int, input().split()))
a.sort()
result = 0
while len(a) > 1:
    p1, p2 = a[0], a[1]
    del a[0]
    del a[0]
    result += (p1 + p2)
    a.append(p1 + p2)
    a.sort()
print (result)
