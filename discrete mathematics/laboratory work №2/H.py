from math import ceil
from decimal import *

getcontext().prec = 200 #кол-во знаков после запятой (для супер точности)

def power(a, n):
    if n == 0:
        return 1
    elif n == 1:
        return a
    elif n % 2 != 0:
        return a * power(a, n - 1)
    elif n % 2 == 0:
        return power(a * a, n / 2)

def intoBinary(step, number, otv):
    pred_pr = []
    while step > 0:
        ost = number % 2
        number //= 2
        pred_pr.append(ost)
        step -= 1
    if len(pred_pr) == 0:
        otv.append(0)
    else:
        for i in range (len(pred_pr) - 1, -1, -1):
            otv.append(pred_pr[i])
    return otv

def probability():
    for i in range(n):
        prob.append(Decimal(cnt[i]) / Decimal(sum(cnt)))
        if i > 0:
            prob[i] += prob[i - 1]
    prob.insert(0, 0)
    
    return prob

def add():
    res = []
    a = Decimal(p) / Decimal(power(2, len(strr)))
    for i in range (sum(cnt)):
        j = 0
        while prob[j] <= a:
            j += 1
        index = j - 1
        res.append(dictionary[index])
        a = Decimal(a - prob[index]) / Decimal(prob[j] - prob[index])
    return res


if __name__ == "__main__": 
    n = int(input())

    dictionary = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q','r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

    s = input().split()
    cnt = []
    for i in s:
        cnt.append(int(i))

    strr = str(input())[::-1]

    prob = []
    prob = probability()

    p = 0
    step = -1
    for i in strr:
        step += 1
        p += int(i) * power(2, step)

    res = add()

    print(*[x for x in res], sep = "")
