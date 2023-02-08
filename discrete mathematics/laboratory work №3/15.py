from math import factorial

def solve(g, m):
    if m == 0:
        const = len(g)
        if const == k:
            return g
    for i in range(1, n + 1):
        if len(g) == 0 or int(g[-1]) < i:
            n_1 = factorial(n - i)
            n_2 = factorial(k - 1 - len(g)) * factorial(n - i - k + 1 + len(g))
            number = n_1 / n_2
            if m >= number:
                m -= number
            else:
                i_i = str(i)
                g = [*g, i_i]
                return solve(g, m)

if __name__ == "__main__":
    n, k, m = map(int, input().split())
    g = []
    print(*solve(g, m), sep = " ")
