def generate(m):
    sets = []
    const = len(m)
    for i in range (2 ** const):
        cur_set = []
        for x in range (const):
            if i & (2 ** x):
                cur_set.append(m[x])
        sets.append(cur_set)
    return sets

n = int (input())
m = [x for x in range (1, n + 1)] 

res = []
for i in generate(m):
    res.append(i)

res.sort()
for i in res:
    print (*i)
