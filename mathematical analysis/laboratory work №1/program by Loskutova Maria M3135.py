import matplotlib.pyplot as plt

print ("Часть 2. Численный метод.")
print ("Выберите номер задания, который вы хотите посмотреть (введите номер задания без пробелов).")
print ("Если вы хотите закончить просмотр, введите 0.")

s = ""

while s != "0":

    s = input()


    if s == "1":
        
        #рисуем график последовательности
        plt.title('x_n = n / (n + 1) * (2 + (-1)^n)')

        plt.xlabel('x')   
        plt.ylabel('y')

        k = 0
        x = []
        y = []
        n = 0
        while k != 100:
            if (n/(n + 1)) * (2 + (-1) ** n) in y:
                n += 1
                continue
            else:
                y.append ((n/(n + 1)) * (2 + (-1) ** n))
                x.append (n)
                n += 1
                k += 1
        x[0] = 1
        y[0] = 0.5

        gr1 = plt.scatter(x, y, label='sequence Xn', color='r')

        #отмечаем sup, inf, верхний и нижний пределы
        plt.axhline(y=3, xmin=0, xmax=100, linewidth=7, label='supremum', color='b') #sup
        plt.axhline(y=0.5, xmin=0, xmax=100, label='infinum', color='k') #inf
        plt.axhline(y=1, xmin=0, xmax=100, label='lower limit', color='c') #верхний предел
        plt.axhline(y=3, xmin=0, xmax=100, linewidth=3, label='upper limit', color='m') #нижний предел

        plt.legend()   
        plt.show()


    if s == "2":
        
        #рисуем график последовательности
        plt.title('x_n = n / (n + 1) * (2 + (-1)^n)')

        plt.xlabel('x')   
        plt.ylabel('y')

        k = 0
        x = []
        y = []
        n = 0
        while k != 200:
            if (n/(n + 1)) * (2 + (-1) ** n) in y:
                n += 1
                continue
            else:
                y.append ((n/(n + 1)) * (2 + (-1) ** n))
                x.append (n)
                n += 1
                k += 1
        x[0] = 1
        y[0] = 0.5

        gr1 = plt.scatter(x, y, label='последовательность Xn', color='r')
        
        #отмечаем на графике любую сходящуюся подпоследовательность другим цветом
        #на данном графике выделена подпоследовательность a_2k
        plt.title('x_n = n / (n + 1) * (2 + (-1)^n)')

        plt.xlabel('x')   
        plt.ylabel('y')

        k = 0
        x = []
        y = []
        n = 0
        while k != 100:
            if (n/(n + 1)) * (2 + (-1) ** n) in y:
                n += 2
                continue
            else:
                y.append ((n/(n + 1)) * (2 + (-1) ** n))
                x.append (n)
                n += 2
                k += 1
        x[0] = 2
        y[0] = (2/(2 + 1)) * (2 + (-1) ** 2)

        gr1 = plt.scatter(x, y, label='подпоследовательность a_2k', color='b') 

        plt.legend()   
        plt.show()  
        
        
    if s == "3":
        
        print ("Введите значение eps.")
        eps = float(input())
        plt.title('x_n = n / (n + 1) * (2 + (-1)^n)')
        plt.xlabel('x * 100')   
        plt.ylabel('y * 100')
        k = 0
        x = []
        y = []
        lim = 3

        #построение графика функции
        plt.title('x_n = n / (n + 1) * (2 + (-1)^n)')

        plt.xlabel('x')   
        plt.ylabel('y')

        k = 0
        x = []
        y = []

        ch = lim - eps
        if 0.1 > eps > 0.01:
            n = 2 + 2 * 300
        elif 0.01 >= eps >= 0.001:
            n = 2 + 2 * 100
        elif eps >= 0.1:
            n = 2 + 2 * 10
        else:
            n = 6 + 2 * 500
        while k != 10000:
            if (n/(n + 1)) * (2 + (-1) ** n) in y:
                n += 4
                continue
            else:
                y.append ((n/(n + 1)) * (2 + (-1) ** n))
                x.append (n)
                n += 4
                k += 1

        #формируем массив значений для e - окрестности предела и отмечаем эту окрестность на графике
        n_x = []
        n_y = []
        for i in range (len(x)):
            if (x[i]/(x[i] + 1)) * (2 + (-1) ** x[i]) > ch:
                n_x.append(x[i])
                n_y.append((x[i]/(x[i] + 1)) * (2 + (-1) ** x[i]))
        
        gr1 = plt.scatter(x, y, label='последовательность Xn', color='r')

        gr3 = plt.scatter(n_x, n_y, label='e - окрестность предела', color='b')

        plt.axhline(y=3, xmin=0, xmax=100, label='верхний предел', color='c')

        plt.legend()   
        plt.show()

        
    if s == "4":
        
        print ("Введите значение eps.")
        eps = float(input())

        #построение графика функции
        plt.title('x_n = n / (n + 1) * (2 + (-1)^n)')

        plt.xlabel('x')   
        plt.ylabel('y')

        k = 0
        x = []
        y = []
        sup = 3
        ch = sup + eps
        if 0.1 > eps > 0.01:
            n = 2 + 2 * 300
        elif 0.01 >= eps >= 0.001:
            n = 2 + 2 * 100
        elif eps >= 0.1:
            n = 2 + 2 * 10
        else:
            n = 6 + 2 * 500
        while k != 100:
            if (n/(n + 1)) * (2 + (-1) ** n) in y:
                n += 2
                continue
            else:
                y.append ((n/(n + 1)) * (2 + (-1) ** n))
                x.append (n)
                n += 2
                k += 1
        
        gr1 = plt.scatter(x, y, label='sequence Xn', color='r')

        #проверка критерия точной грани
        #находим номер m такой, что xm > sup (x_n) + eps
        #отмечаем эту точку на графике
        n_x = []
        n_y = []
        flag = 0
        for i in range(int(ch) + 2, int(ch) + 20):
            if i > ch:
                for j in range (len(y)):
                    if i >= y[j]:
                        n_x.append(x[j])
                        n_y.append(y[j])
                        flag = 1
                        break
                if flag == 1:
                    break

        plt.plot(n_x, n_y, ':o')

        #выводим значение этой точки
        print ("x_m =", *n_x, ", y =", *n_y)

        plt.legend()   
        plt.show()

    else:
        if s != "0":
            print ("Вы ввели неправильный номер задания.")
