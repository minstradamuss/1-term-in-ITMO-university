def cheker(a, otn, n):

    i = 0
    while i < n:
        if a[i][i] == 0:
            otn[0] = 0
            break
        i += 1

    i = 0
    while i < n:
        if a[i][i] == 1:
            otn[1] = 0
            break
        i += 1

    for i in range(n - 1):
        if otn[2] == 0:
            break
        for j in range(i + 1, n):
            if a[i][j] != a[j][i]:
                otn[2] = 0
                break

    for i in range(n - 1):
        if otn[3] == 0:
            break
        for j in range(i + 1, n):
            if a[i][j] == 1 and a[j][i] == 1:
                otn[3] = 0
                break

    for i in range(n):
        if otn[4] == 0:
            break
        for j in range(n):
            if otn[4] == 0:
                break
            for k in range(n):
                if (a[i][j] == 1) and (a[j][k] == 1) and (a[i][k] == 0):
                    otn[4] = 0
                    break

    
n = int(input())
a = [0] * n
b = [0] * n
otn_a = [1] * 5
otn_b = [1] * 5
komp = []

for i in range (n):
    komp.append([0] * n)

for i in range(n):
    a[i] = list(map(int, input().split()))

for i in range(n): 
    b[i] = list(map(int, input().split()))
    
cheker(a, otn_a, n)
print (*otn_a)
    
cheker(b, otn_b, n)
print (*otn_b)


for i in range(n):
    for j in range(n):
        for k in range(n):
            if (a[i][j] == 1) and (b[j][k] == 1):
                komp[i][k] = 1

for i in range(n):
    print(*komp[i])


