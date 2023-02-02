def close():
    return exit(0)
 
a = input().split()
b = input().split()
cnt = 0
 
while a and b:
    cnt += 1
    k1, k2 = a.pop(0), b.pop(0)
    if (k1 > k2 and (k1, k2) != ('9', '0')) or (k1, k2) == ('0', '9'):
        a += [k1, k2]
    else:
        b += [k1, k2]
    if cnt > 10 ** 6:
        print('botva')
        close()
if a:
    print('first', cnt)
else:
    print('second', cnt)
