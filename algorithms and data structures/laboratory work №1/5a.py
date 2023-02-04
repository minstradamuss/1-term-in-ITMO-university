n = int(input())
d = [0] * (n + 5)
s = input() + "....."
minus_inf = -(10**10)
if s[1] == '"':
    d[1] = 1
elif s[1] == '.':
    d[1] = 0
else:
    d[1] = minus_inf
if s[2] == '"':
    d[2] = d[1] + 1
elif s[2] == '.':
    d[2] = d[1]
else:
    d[2] = minus_inf
if s[3] == '"':
    d[3] = max(d[0], d[2]) + 1
elif s[3] == '.':
    d[3] = max(d[0], d[2])
else:
    d[3] = minus_inf
if s[4] == '"':
    d[4] = max(d[1], d[3]) + 1
elif s[4] == '.':
    d[4] = max(d[1], d[3])
else:
    d[4] = minus_inf
for i in range(5, n):
    if s[i] == '"':
        d[i] = max(d[i - 1], d[i - 3], d[i - 5]) + 1
    elif s[i] == '.':
        d[i] = max(d[i - 1], d[i - 3], d[i - 5])
    else:
        d[i] = minus_inf
print(max(d[n - 1], -1))
