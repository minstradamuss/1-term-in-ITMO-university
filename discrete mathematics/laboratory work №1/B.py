
def intoBinary(n):
    return list(bin(n)[2:].zfill(5))

def solve():
    for i in range(n):

        if sum(post) == 0:
            return

        s1, s2 = map(str, input().split())
        s_1 = int (s1)
        a = [int(x) for x in s2]

        k = 2 ** s_1

        if a[0] == 1:
            post[0] = 0

        if a[-1] == 0:
            post[1] = 0
            
        if k == 1:
            post[2] = 0
        else:
            for j in range(k // 2):
                if a[j] == a[k - 1 - j]:
                    post[2] = 0
                    break

        for j in range(k - 1):
            if post[3] == 0:
                break
            for m in range(j + 1, k):
                if post[3] == 0:
                    break
                if a[j] != a[m]:
                    conj = 1

                    cur1 = intoBinary(j)
                    cur2 = intoBinary(m)

                    cur_1 = [int(x) for x in cur1]
                    cur_2 = [int(x) for x in cur2]

                    for q in range(5):
                        if cur_1[q] > cur_2[q]:
                            conj = 0
                    if conj == 1 and a[j] > a[m]:
                        post[3] = 0


        for j in range(k):
            for m in range(k - 1, j, -1):
                a[m] = (a[m] + a[m - 1]) % 2
        cheker = 1
        for j in range(k):
            if j > 1:
                if j == 2 * cheker:
                    cheker = j
                elif a[j] == 1:
                    post[4] = 0
                    break

post = [1] * 5
n = int(input())

solve()

if sum(post) == 0:
    print("YES")
else:
    print("NO")
