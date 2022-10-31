import itertools

n, comparators, slices = map(int, input().split())
comp = []
for _ in range(slices):
    temp = list(map(int, input().split()))
    for i in range(1, len(temp), 2):
        comp.append((temp[i] - 1, temp[i + 1] - 1))
answer = True
for seq in itertools.product([0, 1], repeat=n):
    a = list(seq)
    for elem in comp:
        if a[min(elem)] > a[max(elem)]:
            a[min(elem)], a[max(elem)] = a[max(elem)], a[min(elem)]
    if a != sorted(a):
        answer = False
        break
if answer:
    print("Yes")
else:
    print("No")
