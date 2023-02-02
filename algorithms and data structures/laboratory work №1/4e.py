n = int(input())
a = list(map(int, input().split()))
s1 = []
answer_left = [i for i in range(n)]
for i in range(n):
    while len(s1) > 0 and a[i] > a[s1[-1]]:
        answer_left[s1.pop()] = i
    s1.append(i)
for i in range(n):
    if a[i] > 0:
        a[i] -= 1
        a[answer_left[i]] += 1
s2 = []
answer_right = [i for i in range(n)]
for i in range(n - 1, -1, -1):
    while len(s2) > 0 and a[i] > a[s2[-1]]:
        answer_right[s2.pop()] = i
    s2.append(i)
for i in range(n):
    if a[i] > 0:
        a[i] -= 1
        a[answer_right[i]] += 1
print(max(a))
