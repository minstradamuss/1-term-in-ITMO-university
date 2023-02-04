n = int (input())
koef = []
zn_per = []

for i in range(2 ** n):
    inp_str = [x for x in input().split()]
    zn_per.append(inp_str[0])
    koef.append(int(inp_str[1]))
    
for i in range(2 ** n):
    for j in range(2 ** n - 1, i, -1):
        koef[j] = (koef[j] + koef[j - 1]) % 2
for i in range(2 ** n):
    print(zn_per[i], koef[i])
