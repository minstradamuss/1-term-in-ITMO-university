s = input()
n = int(input())
mod = 998244353
correct_sample = True
for i in range(1, len(s)):
    if ord(s[i]) - ord(s[i - 1]) == 1:
        correct_sample = False
if not correct_sample:
    print(0)
elif len(s) == n:
    print(1)
else:
    d = [[1] * 26 for i in range(n - len(s))]
    last_char = ord(s[-1]) - ord("a")
    if last_char != 25:
        d[0][last_char + 1] = 0
    prev_sum = sum(d[0]) % mod
    for i in range(1, n - len(s)):
        d[i][0] = prev_sum % mod
        for char in range(1, 26):
            d[i][char] = (prev_sum - d[i - 1][char - 1]) % mod
        prev_sum = sum(d[i]) % mod
    print(sum(d[-1]) % mod)
