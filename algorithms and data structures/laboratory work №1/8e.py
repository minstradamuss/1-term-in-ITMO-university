n = int(input())
w = list(map(int, input().split()))
s = sum(w)
if s % 3 != 0:
    print(-1)
    exit()
dp = [[-1] * (s // 3 + 1) for i in range((s // 3 + 1))]
dp[0][0] = 0
for k in range(n):
    for i in range(s // 3, -1, -1):
        for j in range(s // 3, -1, -1):
            if dp[i][j] == -1:
                if i - w[k] >= 0 and dp[i - w[k]][j] != -1:
                    dp[i][j] = k + 1
                elif j - w[k] >= 0 and dp[i][j - w[k]] != -1:
                    dp[i][j] = k + 1
if dp[s // 3][s // 3] == -1:
    print(-1)
else:
    ans1 = []
    ans2 = []
    used = [False] * n
    i = s // 3
    j = s // 3
    while i > 0 or j > 0:
        num_w = dp[i][j] - 1
        used[num_w] = True
        if i >= w[num_w] and dp[i - w[num_w]][j] != -1 and dp[i - w[num_w]][j] < dp[i][j]:
            ans1.append(num_w + 1)
            i = i - w[num_w]
        else:
            ans2.append(num_w + 1)
            j = j - w[num_w]
    ans3 = []
    for i in range(n):
        if not used[i]:
            ans3.append(i + 1)
    print(len(ans1), *ans1)
    print(len(ans2), *ans2)
    print(len(ans3), *ans3)
