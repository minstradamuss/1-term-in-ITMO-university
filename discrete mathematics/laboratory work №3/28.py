def solve(n, a, index):
    res = []
    if index == -1:
        for i in range (n):
            res.append(0)
    else:
        l = index + 1
        while l < n - 1 and a[l + 1] > a[index]:
            l += 1
        a[index], a[l] = a[l], a[index]
        res = a[:index + 1:] + a[index + 1:n][::-1]
    return res

n = int(input())
a = list (map (int, input().split()))
index = -1
for i in range(n - 2, -1, -1):
    if a[i] < a[i + 1]:
        index = i
        break
print (*solve(n, a, index))
