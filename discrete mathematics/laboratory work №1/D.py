def do():
    for i in range(2 ** n):
        s, x = map(str, input().split())
        b = list(s)
        for j in range(len(b)):
            pred_pr[i][j] = int(b[j])
        pred_pr[i][n] = int(x)
 
n = int(input())
 
pred_pr = []
for i in range(2 ** n):
    pred_pr.append([0] * (n + 1))
 
temp = 0
k = n
 
do()
 
for i in range(2 ** n):
    temp += pred_pr[i][n]
 
result = []
     
if temp == 0:
    print(n + 2)
    print("1", "1")
    print("2", "1", n + 1)
 
else:
    print(temp - 1 + temp * (n - 1) + 2 * n)
 
    for i in range(n):
        print("1", i + 1)
 
        k += 1
         
    for i in range(2 ** n):
 
        if pred_pr[i][n] == 1:
 
            for j in range(0, n - 1):
 
                if j == 0:
                    tmp1, tmp2 = j + 1, j + 2
 
                    if pred_pr[i][j] == 0:
                        tmp1 += n
 
                    if pred_pr[i][j + 1] == 0:
                        tmp2 += n
 
                    print("2", tmp1, tmp2)
                    k += 1
 
                else:
                    tmp2 = j + 2
 
                    if pred_pr[i][j + 1] == 0:
                        tmp2 += n
 
                    print("2", k, tmp2)
                    k += 1
 
            result.append(k)
 
    if len(result) == 0:
        print("0")
 
    for i in range(len(result) - 1):
        if i == 0:
            print("3", result[0], result[1])
            k += 1
        else:
            print("3", k, result[i + 1])
            k += 1
