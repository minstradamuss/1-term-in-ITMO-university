def add(cur):
    return "|((A" + cur + "|A" + cur + ")|(B" + cur + "|B" + cur + ")))|(A" + cur + "|B" + cur + "))"

def create_formula(n):
    global stroka
    cur = str(n - 1)
    if n == 1:
        stroka += "((A0|B0)|(A0|B0))"
    else:
        stroka += "(("
        create_formula(n - 1)
        stroka += add(cur)
    return stroka

n = int(input())
stroka = ""

print(create_formula(n))

