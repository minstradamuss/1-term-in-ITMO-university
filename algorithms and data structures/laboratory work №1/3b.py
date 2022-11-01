n, m = map (int, input().split())
a = list (map (int, input().split()))
b = list (map (int, input().split()))
for x in b:
    #left
    left = -1
    right = len(a)
    while right - left > 1:
        middle = (right + left) // 2
        if a[middle] < x:
            left = middle
        else:
            right = middle
    #right
    left_1 = -1
    right_1 = len(a)
    while right_1 - left_1 > 1:
        middle = (right_1 + left_1) // 2
        if a[middle] <= x:
            left_1 = middle
        else:
            right_1 = middle
    if left == left_1 and right == right_1:
        print(0)
        continue
    if right == left_1:
        print(right + 1, right + 1)
    else:
        print(right + 1, left_1 + 1)
