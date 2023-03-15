def find_smaller(a, x):
    # find amount a[i] <= x
    left = -1
    right = len(a)
    while right - left > 1:
        mid = (left + right) // 2
        # a[left] <= x < a[right]
        if a[mid] > x:
            right = mid
        else:
            left = mid
    return left + 1


def merge(a, b, invers, k):
    c = []
    i_a = 0
    i_b = 0
    while i_a < len(a) and i_b < len(b):
        if a[i_a] <= b[i_b]:
            c.append(a[i_a])
            i_a += 1
        else:
            invers[0] += find_smaller(a, b[i_b] - k)
            c.append(b[i_b])
            i_b += 1
    while i_a < len(a):
        c.append(a[i_a])
        i_a += 1
    while i_b < len(b):
        invers[0] += find_smaller(a, b[i_b] - k)
        c.append(b[i_b])
        i_b += 1
    return c


def merge_sort(a, invers, k):
    if len(a) <= 1:
        return a
    left = a[:len(a) // 2]
    right = a[len(a) // 2:]
    left = merge_sort(left, invers, k)
    right = merge_sort(right, invers, k)
    return merge(left, right, invers, k)


n, k = map(int, input().split())
a = list(map(int, input().split()))
prefix = [0] * (n + 1)
prefix[0] = 0
for i in range(1, n + 1):
    prefix[i] = prefix[i - 1] + a[i - 1]
invers = [0]
merge_sort(prefix, invers, k)
print(invers[0])
