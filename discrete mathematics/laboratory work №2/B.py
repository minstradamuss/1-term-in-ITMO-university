s = input()
mas_sdv = []
for i in range (len(s)):
    pr_pr = s[i::] + s[:i:]
    mas_sdv.append(pr_pr)
    #print ("___", res)
mas_sdv.sort()
for i in range (len(mas_sdv)):
    print (mas_sdv[i][len(mas_sdv) - 1], end = "")
