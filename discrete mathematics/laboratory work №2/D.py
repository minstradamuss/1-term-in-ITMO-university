#...
dictionary = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q','r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
s = input()

res = []

for i in range(len(s)):
    res.append(dictionary.index(s[i]) + 1)
    cur = [dictionary[dictionary.index(s[i])]]
    for i in range(0, 26):
        if dictionary[i] not in cur:
            cur.append(dictionary[i])
    dictionary = cur

for i in res:
    print (i, end = " ")
