def generate(put, j):

    const = len(put)

    if j == const:
        print (" ".join(put))

    for i in range(j, const):
        put = list(put)
        put[j], put[i] = put[i], put[j]
        generate(put, j + 1)

n = int (input())
put = ""
for i in range (1, n + 1):
    put += str(i)
    
generate(put, j = 0)
