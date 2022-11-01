def binp (a):
    l = 0
    r = h[0]
    while r - l > 0.0000000001:
        h[1] = (l + r) / 2
        flag = True
        for i in range (2, n):
            h[i] = 2 * h[i - 1] - h[i - 2] + 2
            if h[i] < 0:
                flag = False
                break
        if flag:
            r = h[1]
        else:
            l = h[1]
    return h[n - 1]
s = input().split()
n  = int (s[0])
a = float (s[1])
h = [0] * n
h[0] = a
print ("{:.2f}".format(binp(h)))
