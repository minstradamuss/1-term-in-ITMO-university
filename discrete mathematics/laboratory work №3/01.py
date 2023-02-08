n = int(input())
for i in range(2 ** n):
    res = ""
    for j in range(n):
        res = str(i % 2) + res
        i = i // 2
    print(res)
