n = int (input())
a = list(map(int, input().split()))

def merge(a, b, invers):
    c = []
    i_a = 0
    i_b = 0
    while i_a < len(a) and i_b < len(b):
        if a[i_a] <= b[i_b]:
            c.append(a[i_a])
            i_a += 1
        else:
            c.append(b[i_b])
            invers[0] += len(a) - i_a
            i_b += 1
    while i_a < len(a):
        c.append(a[i_a])
        i_a += 1
    while i_b < len(b):
        c.append(b[i_b])
        i_b += 1
    return c

def merge_sort(a, invers):
    if len(a) <= 1:
        return a
    left = a[:len(a) // 2]
    right = a[len(a) // 2:]
    left = merge_sort(left, invers)
    right = merge_sort(right, invers)
    return merge(left, right, invers)

amount_invers = [0]
merge_sort(a, amount_invers)

print(*amount_invers)
