n, m = map(int, input().split())
a = []
for i in range(n):
    a.append((list(map(int, input().split()))))
d = [[0] * m for i in range(n)]
d[0][0] = a[0][0]
for i in range(1, n):
    d[i][0] = d[i - 1][0] + a[i][0]
for j in range(1, m):
    d[0][j] = d[0][j - 1] + a[0][j]
for i in range(1, n):
    for j in range(1, m):
        d[i][j] = max(d[i - 1][j], d[i][j - 1]) + a[i][j]
print(d[n - 1][m - 1])
y, x = n - 1, m - 1
path = ""
while y > 0 and x > 0:
    if d[y - 1][x] > d[y][x - 1]:
        path += "D"
        y -= 1
    else:
        path += "R"
        x -= 1
while x > 0:
    path += "R"
    x -= 1
while y > 0:
    path += "D"
    y -= 1
print(path[::-1])
