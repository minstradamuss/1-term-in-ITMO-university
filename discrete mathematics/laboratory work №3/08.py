def solve(a):
  result = [[]]
  for x in a:
    result = result + [c + [x] for c in result]
  return result

n, k = map (int, input().split())

a = []
for i in range (1, n + 1):
    a.append(i)

final = solve(a)

alm = [x for x in final if len(x) == k]
alm.sort()
for i in alm:
    print (*i)

