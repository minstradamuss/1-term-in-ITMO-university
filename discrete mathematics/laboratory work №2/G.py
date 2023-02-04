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
    prob = []
    for i in range(n):
        prob.append(Decimal(cnt[i]) / Decimal(len(strr)))
        if i > 0:
            prob[i] += prob[i - 1]
    prob.insert(0, 0)
    l, r = 0, 0
    for j in range (len(strr)):
        pl, pr = l, r
        if r == 0:
            pl, pr = l, r
            l = Decimal(pl) + Decimal((pr + 1 - pl)) * Decimal(prob[dictionary.index(strr[j])])
            r = Decimal(pl) + Decimal((pr + 1 - pl)) * Decimal(prob[dictionary.index(strr[j]) + 1])
        else:
            pl, pr = l, r
            l = Decimal(pl) + Decimal((pr - pl)) * Decimal(prob[dictionary.index(strr[j])])
            r = Decimal(pl) + Decimal((pr - pl)) * Decimal(prob[dictionary.index(strr[j]) + 1])
    step, number = 0, 0
    flag = True
    while flag:
        if ceil(l * power(2, step)) >= r * power(2, step):
            step += 1
        else:
            number = ceil(l * power(2, step))
            break
    res = []
    res.append(step)
    res.append(number)
    return res
    
n = int(input())
print (n)

strr = str(input())
dictionary = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q','r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

cnt = [0] * n
for i in range(len(strr)):
    cnt[dictionary.index(strr[i])] += 1
print(*[x for x in cnt], sep = " ")

step, number = probability()[0], probability()[1]
otv = []
otv = intoBinary(step, number, otv)
print(*[x for x in otv], sep = "")
