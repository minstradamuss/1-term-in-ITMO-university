t = int (input())
for i in range (t):
    n, m = map (int, input().split()) 
    if (n * (n + 1) // 2) >= m:
        print ("Impostors")
        left = 0
        right = n
        while right - left > 1:
            mid = (left + right) // 2
            if (n + n - mid + 1) * mid // 2 >= m:
                right = mid
            else:
                left = mid
        print(right)
    else:
        print ("Crewmates")
        print (n)
