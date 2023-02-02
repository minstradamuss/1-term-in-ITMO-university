a_a = list(map(int, input().split()))
n = a_a[0]
a = []
for i in range (1, len(a_a)):
    a.append(a_a[i])
 
min_right = [n] * n
s1 = []
for i in range(n):
    while len(s1) > 0 and s1[-1][0] > a[i]:
        min_right[s1[-1][1]] = i
        s1.pop()
    s1.append((a[i], i))
min_left = [-1] * n
s2 = []
for i in range(len(a) - 1, -1, -1):
    while len(s2) > 0 and s2[-1][0] > a[i]:
        min_left[s2[-1][1]] = i
        s2.pop()
    s2.append((a[i], i))
best_l = 0
best_r = 0
best_res = a[0]
for i in range(n):
    if a[i] * (min_right[i] - min_left[i] - 1) > best_res:
        best_res = a[i] * (min_right[i] - min_left[i] - 1)
print(best_res)
