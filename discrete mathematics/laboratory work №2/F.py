# ...
dictionary = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q','r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
 
def cheker():
    if s[i] >= len(dictionary):
        add = dictionary[process][0]
    else:
        add = dictionary[s[i]][0]
    return add
 
n = int(input())
s = [int(x) for x in input().split()]
res = []
 
process = s[0]
for i in range(1, n + 1):
    if i == n:
        res.append(dictionary[process])
    else:
        add = cheker()
        old = dictionary[process] + add
        if old in dictionary:
            buf = dictionary.index(old)
        else:
            res.append(dictionary[process])
            dictionary.append(old)
            process = s[i]
print (*[x for x in res], sep="")
