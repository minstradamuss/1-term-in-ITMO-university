def brackets(n, pref, start = 0, finish = 0):
    if start + finish == 2 * n:
        print(''.join(pref))
    else:
        if start < n:
            brackets(n, pref + ['('], start + 1, finish)
        if start > finish:
            brackets(n, pref + [')'], start, finish + 1)

n = int(input())
pref = []
brackets(n, pref)
