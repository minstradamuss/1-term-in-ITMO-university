queue = []
command = input()
while command != 'exit':
    #push n
    if command.find('push') > -1:
        com, n = command.split(' ')
        queue.append(int(n))
        print('ok')
    # pop
    elif command.find('pop') > -1:
        if len(queue) > 0:
            print(queue[0])
            del queue[0]
    # front
    elif command.find('front') > -1:
        if len(queue) > 0:
            print(queue[0])
    # size
    elif command.find('size') > -1:
        print(len(queue))
    # clear
    elif command.find('clear') > -1:
        queue = []
        print('ok')
 
    command = input()
 
print('bye')
