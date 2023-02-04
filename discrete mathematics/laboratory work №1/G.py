def intoBinary(n):
    s = bin(n)[2::].zfill(33)
    bin_n = []
    for i in s:
        bin_n.append(int(i))
    return bin_n
 
def close():
    return exit(0)
 
def cheker (i):
    return (not(i) == 0)
 
n = int(input())
 
v = [intoBinary(int(x)) for x in input().split(" ")]
p = [[v[y][x] for y in range(n)] for x in range(33)]
 
s = intoBinary(int(input()))
 
result = []
 
for i in range(32 + 1):
    for j in range(32 + 1):
        if (p[i] == p[j] and s[i] != s[j]):
            print("Impossible")
            close()
 
 
for i in range(32 + 1):
    if s[i] == 1:
        pred = "("
        for j in range(n):
            if j != 0:
                pred += "&"
            if p[i][j] == 0:
                pred += "~"
            pred += str(j + 1)
        pred += ")"
        result.append(pred)
 
 
if len(result) == 0:
    otv = "1&(~1)"
    print(otv)
    close()
 
 
for i in range(len(result)):
 
    if cheker (i):
        print("|", end = "")
         
    print(result[i], end = "")
