s = input()
result = [0] * len(s)
for i in range (len(s)):
    for j in range (len(s)):
        result[j] = str(s[j]) + str(result[j])
        #print (result[j])
    result.sort()
print (*[result[0][x] for x in range (0, len(result[0]) - 1)], sep="")
