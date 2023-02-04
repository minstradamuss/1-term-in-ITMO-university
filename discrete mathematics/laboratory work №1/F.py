def close():
    return exit(0)

def check_one(a):
    flag = False
    for i in a:
        if not i == -1:
            if flag:
                return False
            flag = True
    return flag

def check_empty(a, n):
    for i in range(n):
        if a[i] != -1:
            return False
    return True

def main_cheker(ind, d, n, zn):
    if check_empty(d[ind], n):
        return True
    flag = False
    for i in range(n):
        if zn[i] == d[ind][i] and zn[i] != -1:
            flag = True
            for j in range(n):
                d[ind][j] = -1
            break
        if zn[i] == -1 and d[ind][i] != -1:
            flag = True
        if zn[i] == 1 - d[ind][i]:
            d[ind][i] = -1
    return flag

n, k = map(int, input().split())
d = [0] * k

for i in range(k):
    d[i] = list(map(int, input().split()))

zn = [-1 for x in range(n)]

while True:
    flag = False
    for i in range(k):
        if check_one(d[i]):
            flag = True
            for j in range(n):
                if d[i][j] == 1:
                    zn[j] = 1
                    break
                if d[i][j] == 0:
                    zn[j] = 0
                    break
            break
    
    for i in range(k):
        if not (main_cheker(i, d, n, zn)):
            print("YES")
            close()
    
    if not (flag):
        print("NO")
        close()
