#...
dictionary = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q','r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

s = input()
 
cur = s[0]
res = []
for i in range (1, len(s) + 1):
    if i == len(s):
        res.append(dictionary.index(cur))
    else:
        new = cur + s[i]
        if new not in dictionary:
            dictionary.append(new)
            res.append(dictionary.index(cur))
            cur = s[i]
        else:
            cur += s[i]
            
print (*[x for x in res])
