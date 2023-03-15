n = int (input())
a = list(map(int, input().split()))

def merge(a, b):
    c = []
    i_a = 0
    i_b = 0
    while i_a < len(a) and i_b < len(b):
        if a[i_a] <= b[i_b]:
            c.append(a[i_a])
            i_a += 1
        else:
            c.append(b[i_b])
            i_b += 1
    while i_a < len(a):
        c.append(a[i_a])
        i_a += 1
    while i_b < len(b):
        c.append(b[i_b])
        i_b += 1
    return c


def merge_sort(a):
    b = a.copy()
    if len(b) <= 1:
        return b
    mid = len(b) // 2
    left = merge_sort(b[:mid])
    right = merge_sort(b[mid:])
    merged_ab = merge(left, right)
    return merged_ab

print(*merge_sort(a))
