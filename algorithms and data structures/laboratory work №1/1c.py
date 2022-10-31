n = int (input())
a = []
for i in range (n):
    a.append(i + 1)

def solve (a):
    b = a.copy()
    for i in range (2, n):
        b[i], b[i // 2] = b[i // 2], b[i]
    return b

print (*solve (a))
