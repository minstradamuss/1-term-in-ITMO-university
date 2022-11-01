def left_binp(a, key):
    left = -1
    right = len(a)
    while right - left > 1:
        middle = (left + right) // 2
        if a[middle] < key:
            left = middle
        else:
            right = middle
    return right

n, m = map (int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

for i in range (len(b)):
    right = left_binp (a, b[i])
    if right < len(a) and a[right] == b[i]:
        print('YES')
    else:
        print('NO')
