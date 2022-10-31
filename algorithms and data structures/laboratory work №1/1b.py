def kps(a, l, r, k):
    x = a[(l + r) // 2]
    i, j = l, r
    while i <= j:
        while a[i] < x:
            i += 1
        while a[j] > x:
            j -= 1
        if i <= j:
            a[i], a[j] = a[j], a[i]
            i += 1
            j -= 1
    if l <= k <= j:
        return kps(a, l, j, k)
    if i <= k <= r:
        return kps(a, i, r, k)
    return a[k]

n, a0, k = map(int, input().split())
a = [0] * n
a[0] = a0
const = 2 ** 31

for i in range (1, n):
    a[i] = (1103515245 * a[i - 1] + 12345) % const
    
print (kps(a, 0, len(a) - 1, k))
