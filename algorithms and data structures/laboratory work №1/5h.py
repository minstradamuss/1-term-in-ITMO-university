n = int(input())
notes = list(map(int, input().split()))
d = [[-1] * 5 for i in range(n)]
d[0] = [1] * 5
for i in range(1, n):
    if notes[i] == notes[i - 1]:
        for prev in range(5):
            for cur in range(5):
                if prev != cur and d[i - 1][prev] != -1:
                    d[i][cur] = prev
    elif notes[i] > notes[i - 1]:
        for prev in range(5):
            for cur in range(5):
                if cur > prev and d[i - 1][prev] != -1:
                    d[i][cur] = prev
    elif notes[i] < notes[i - 1]:
        for prev in range(5):
            for cur in range(5):
                if cur < prev and d[i - 1][prev] != -1:
                    d[i][cur] = prev
last_finger = -1
for i in range(5):
    if d[n - 1][i] != -1:
        last_finger = i
if last_finger == -1:
    print(-1)
else:
    ans = [last_finger + 1]
    for i in range(n - 1, 0, -1):
        last_finger = d[i][last_finger]
        ans.append(last_finger + 1)
    print(*ans[::-1])
