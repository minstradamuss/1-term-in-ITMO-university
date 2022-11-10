n, w, h = map (int, input().split())

mas = []
for i in range (n):
    mas.append([int(x) for x in input().split()])
mas.append([0, 0])

n = len(mas)

res_x, res_y, side = 0, 0, 1

for i in range (n):
    for j in range (n):
        cur_x, cur_y = mas[i][0], mas[j][1]
        cur_side = min (w - cur_x, h - cur_y)
        for k in range (n):
            if mas[k][0] > cur_x and mas[k][1] > cur_y:
                pred = max(mas[k][0] - cur_x, mas[k][1] - cur_y)
                if pred < cur_side:
                    cur_side = pred
                    
        if side < cur_side:
            side = cur_side
            res_x, res_y = cur_x, cur_y
            
print (res_x, res_y, side)
